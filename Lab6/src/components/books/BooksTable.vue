<template>
  <div class="go-back-container container">
    <router-link to="/">
      <button class="btn btn-info">Powrót</button>
    </router-link>
  </div>
  <div id="books-table" class="container">
    <h1>Lista książek</h1>
    <table>
      <thead>
        <tr>
          <th>Tytuł</th>
          <th>Imię autora</th>
          <th>Nazwisko autora</th>
          <th>Ilość stron</th>
          <th>Usuń</th>
          <th>Aktualizuj</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.title }}</td>
          <td>{{ book.author.firstName }}</td>
          <td>{{ book.author.lastName }}</td>
          <td>{{ book.pages }}</td>
          <td>
            <button class="btn btn-danger" @click="deleteBook(book.id)">
              Usuń
            </button>
          </td>
          <td>
            <button
              class="btn btn-warning"
              @click="
                this.$router.push({
                  name: 'booksUpdate',
                  params: { id: book.id },
                })
              "
            >
              Aktualizuj
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <BookForm :booksSource="books" class="mt-5" />
  </div>
</template>

<script>
import BookForm from "@/components/books/BooksForm.vue";
export default {
  name: "booksTable",
  components: { BookForm },
  data() {
    return {
      books: [],
    };
  },
  mounted() {
    this.getBooks();
  },
  methods: {
    async getBooks() {
      try {
        const response = await fetch("http://localhost:8081/books");
        console.log(response);
        const data = await response.json();
        console.log(data);
        this.books = data;
      } catch (error) {
        console.error(error);
      }
    },
    async deleteBook(itemId) {
      const response = await fetch(`http://localhost:8081/books/${itemId}`, {
        method: "DELETE",
      });
      console.log(response.data);
      this.books = this.books.filter((obj) => {
        return obj.id !== itemId;
      });
    },
  },
};
</script>

<style scoped>
.go-back-container {
  padding-top: 20px;
  padding-bottom: 20px;
}
</style>
