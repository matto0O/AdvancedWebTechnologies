<template>
  <div id="person-form" class="container">
    <form @submit.prevent="handleSubmit">
      <label>Imię</label>
      <input
        v-model="reader.firstName"
        type="text"
        :class="{ 'has-error': submitting && invalidFirstName }"
        @focus="clearStatus"
        @keypress="clearStatus"
        :placeholder="reader.firstName"
      />
      <label>Nazwisko</label>
      <input
        v-model="reader.lastName"
        type="text"
        :class="{ 'has-error': submitting && invalidLastName }"
        @focus="clearStatus"
        :placeholder="reader.lastName"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
      <button class="btn btn-primary">Dodaj czytelnika</button>
    </form>
  </div>
</template>

  <script>
export default {
  name: "readersForm",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      reader: {
        firstName: "",
        lastName: "",
      },
    };
  },
  mounted() {
    this.getReader();
  },
  methods: {
    async postData() {
      try {
        const data = {
          firstName: this.reader.firstName,
          lastName: this.reader.lastName,
        };
        const response = await fetch("http://localhost:8081/readers", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        });
        console.log(await response.json());
        this.$router.push({ name: "readers" });
      } catch (error) {
        console.error(error);
      }
    },
    async getReader() {
      const response = await fetch(`http://localhost:8081/readers/${this.readerId()}`)
      const data = await response.json()
          // Obsługa odpowiedzi serwera
          console.log(data);
          (this.reader.id = data.id),
            (this.reader.firstName = data.firstName);
          this.reader.lastName = data.lastName;
    },
    readerId() {
      return this.$route.params.id;
    },
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();
      if (this.invalidFirstName || this.invalidLastName) {
        this.error = true;
        return;
      }
      this.postData();
      this.reader = {
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
      return this.reader.firstName === "";
    },
    invalidLastName() {
      return this.reader.lastName === "";
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