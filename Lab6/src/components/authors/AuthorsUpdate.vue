<template>
  <div id="person-form" class="container">
    <h1>Edytuj dane autora</h1>
    <form @submit.prevent="handleSubmit">
      <label>Imię</label>
      <input
        v-model="author.firstName"
        type="text"
        :class="{ 'has-error': submitting && invalidFirstName }"
        @focus="clearStatus"
        @keypress="clearStatus"
        :placeholder="author.firstName"
      />
      <label>Nazwisko</label>
      <input
        v-model="author.lastName"
        type="text"
        :class="{ 'has-error': submitting && invalidLastName }"
        @focus="clearStatus"
        :placeholder="author.lastName"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
      <button class="btn btn-success">Zaaktualizuj dane autora</button>
    </form>
  </div>
</template>

  <script>
export default {
  name: "AuthorsUpdate",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: {
        id: 0,
        firstName: "",
        lastName: "",
      },
    };
  },
  mounted() {
    this.getAuthor();
  },
  methods: {
    async updateData() {
      try {
        const data = {
          id: this.author.id,
          firstName: this.author.firstName,
          lastName: this.author.lastName,
        };
        const response = await fetch("http://localhost:8081/authors", {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        });
        console.log(response.data);
        this.$router.push({ name: "authors" });
      } catch (error) {
        console.error(error);
      }
    },
    async getAuthor() {
      const response = await fetch(
        `http://localhost:8081/authors/${this.authorId()}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const data = await response.json();
      // Obsługa odpowiedzi serwera
      console.log(data.firstName);
      this.author.id = data.id,
      this.author.firstName = data.firstName;
      this.author.lastName = data.lastName;
    },
    authorId() {
      return this.$route.params.id;
    },
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();
      if (this.invalidFirstName || this.invalidLastName) {
        this.error = true;
        return;
      }
      this.updateData();
      this.author = {
        firstName: "",
        lastName: "",
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
    invalidFirstName() {
      return this.author.firstName === "";
    },
    invalidLastName() {
      return this.author.lastName === "";
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