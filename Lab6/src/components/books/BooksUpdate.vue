<template>
<div id="person-form" class="container">
    <form @submit.prevent="handleSubmit">
      <label>Tytuł</label>
      <input
          v-model="book.title"
          type="text"
          :class="{ 'has-error': submitting && invalidTitle }"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <label>Liczba stron</label>
      <input
          v-model="book.pages"
          type="text"
          :class="{ 'has-error': submitting && invalidPages }"
          @focus="clearStatus"
      />
      <label>Autor</label>
      <select v-model="book.author">
        <option
            v-for="a in authors" :key="a.id">
          {{ a.firstName + ' ' + a.lastName }}
        </option>
      </select>
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zapisano
      </p>
      <button class="btn btn-primary mt-3">Aktualizuj dane książki</button>
    </form>
  </div>
  </template>
  
    <script>
  export default {
    name: "BooksUpdate",
    data() {
      return {
        submitting: false,
        error: false,
        success: false,
        book: {
          id: 0,
          title: "",
          pages: 0,
          author: {
            firstName: '',
            lastName: '',
        },
        },
        authors: []
      };
    },
    mounted() {
      this.getBook();
      this.getAuthors();
    },
    methods: {
    async getAuthors() {
      try {
        const response = await fetch("http://localhost:8081/authors");
        console.log(response);
        const data = await response.json();
        this.authors = data;
      } catch (error) {
        console.error(error);
      }
    },
      async updateData() {
        try {
          const data = {
            id: this.book.id,
            title: this.book.title,
            author: this.authors[0],
            pages: this.book.pages,
          };
          const response = await fetch("http://localhost:8081/books", {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
          });
          console.log(response.data);
          this.$router.push({ name: "books" });
        } catch (error) {
          console.error(error);
        }
      },
      async getBook() {
        const response = await fetch(
          `http://localhost:8081/books/${this.bookId()}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
  
        const data = await response.json();
        // Obsługa odpowiedzi serwera
        console.log(data);
        // this.book = response.data;
        this.book.id = data.id;
        this.book.title = data.title;
        this.book.pages = data.pages;
        this.book.author = data.author;
      },
      bookId() {
        return this.$route.params.id;
      },
      handleSubmit() {
        this.submitting = true;
        this.clearStatus();
        if (this.invalidTitle || this.invalidPages) {
          this.error = true;
          return;
        }
        this.updateData();

        this.error = false;
        this.success = true;
        this.submitting = false;
      },
      clearStatus() {
        this.success = false;
        this.error = false;
      },
    },
    computed: {
      invalidTitle() {
        return this.book.title === "";
      },
      invalidPages() {
        return this.book.pages === "";
      },
    },
  };
  </script>
    <style scoped>
  form {
    margin-bottom: 2rem;
  }
  [class*="-message"] {
    font-weight: 500;
  }
  .error-message {
    color: #d33c40;
  }
  .success-message {
    color: #32a95d;
  }
  </style>