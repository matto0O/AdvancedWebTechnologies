// const { GraphQLServer } = require("graphql-yoga");

// const resolvers = {
//   Query: {
//     demo: () => "Witaj, GraphQL działa!",
//   },
// };

// const server = new GraphQLServer({
//   typeDefs: "./src/schema.graphql",
//   resolvers,
// });

// server.start(() => console.log(`Server is running on http://localhost:4000`));

import { createServer } from "node:http";
import { createYoga } from "graphql-yoga";
import { schema } from "./schema.js";

// Create a Yoga instance with a GraphQL schema.
const yoga = createYoga({ schema });

// Pass it into a server to hook into request handlers.
const server = createServer(yoga);

// Start the server and you're done!
server.listen(4000, () => {
  console.info("Server is running on http://localhost:4000/graphql");
});
