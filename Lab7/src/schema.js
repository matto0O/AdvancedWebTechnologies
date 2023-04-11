import { createSchema } from "graphql-yoga";
import * as fs from "fs";
import * as path from "path";
import axios from "axios";

export const schema = createSchema({
  typeDefs: fs.readFileSync(path.join("src", "schema.graphql"), "utf-8"),
  resolvers: {
    Query: {
      users: () => getRestUsersList(),
      todos: () => getRestTodosList(),
      todo: (parent, args, context, info) =>
        getSingleTodoById(parent, args, context, info),
      user: (parent, args, context, info) =>
        getSingleUserById(parent, args, context, info),
    },
    User: {
      todos: (parent, args, context, info) => {
        return getTodosByUser(parent, args, context, info);
      },
    },
    ToDoItem: {
      user: (parent, args, context, info) => {
        return getUserByTodo(parent, args, context, info);
      },
    },
  },
});

async function getRestUsersList() {
  try {
    const users = await axios.get("https://jsonplaceholder.typicode.com/users");
    return users.data.map(({ id, name, email, username }) => ({
      id: id,
      name: name,
      email: email,
      login: username,
    }));
  } catch (error) {
    throw error;
  }
}

async function getSingleUserById(parent, args, context, info) {
  const users = await getRestUsersList();
  return users.find((u) => u.id == args.id);
}

async function getUserByTodo(parent, args, context, info) {
  const users = await getRestUsersList();
  return users.find((u) => u.id == parent.user_id);
}

async function getRestTodosList() {
  try {
    const todos = await axios.get("https://jsonplaceholder.typicode.com/todos");
    return todos.data.map(({ id, title, completed, userId }) => ({
      id: id,
      title: title,
      completed: completed,
      user_id: userId,
    }));
  } catch (error) {
    throw error;
  }
}

async function getSingleTodoById(parent, args, context, info) {
  const todos = await getRestTodosList();
  return todos.find((t) => t.id == args.id);
}

async function getTodosByUser(parent, args, context, info) {
  const todos = await getRestTodosList();
  return todos.filter((t) => t.user_id == parent.id);
}
