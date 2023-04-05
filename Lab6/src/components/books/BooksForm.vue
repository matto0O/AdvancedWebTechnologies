<template>
    <div id="person-form" class="container mt-5">
    <h1>Dodaj nową książkę</h1>
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
            v-for="a in authors" :key="a.id" :value="a"
            @focus="clearStatus"
        >
          {{a.firstName + ' ' + a.lastName}}
        </option>
      </select>
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zapisano
      </p>
      <button class="btn btn-success mt-4">Dodaj książkę</button>
    </form>
  </div>
  </template>
  
    <script>
  export default {
    name: "booksForm",
    data() {
    return {
      submitting: false,
      error: false,
      success: false,
      book: {
        title: '',
        pages: '',
        author: {
            id: 0,
            firstName: '',
            lastName: '',
        },
      },
      authors : [],
    }
  },
    mounted() {
      this.getAuthors();
    },
    methods: {
      async postData() {
        try {
          const data = {
            title: this.book.title,
            pages: this.book.pages,
            author: this.book.author,
          };
          const response = await fetch("http://localhost:8081/books", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
          });
          console.log(data)
          console.log(await response.json());
          this.$router.push({ name: "books" });
        } catch (error) {
          console.error(error);
        }
      },
      async getAuthors() {
      try {
        const response = await fetch("http://localhost:8081/authors");
        console.log(response);
        const data = await response.json();
        this.authors = data;
        console.log(this.authors);
      } catch (error) {
        console.error(error);
      }
    },

      handleSubmit() {
        this.submitting = true;
        this.clearStatus();

        if (this.invalidTitle || this.invalidPages) {
          this.error = true;
          return;
        }
        this.postData();
        this.book = {
          title: '',
          pages: '',
          author: {
          id: 0,
          firstName: '',
          lastName: '',
        },
        };
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
        return this.book.title === '';
      },
      invalidPages() {
        return this.book.pages === '';
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