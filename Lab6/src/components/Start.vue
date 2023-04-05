<template>
  <div class="d-flex justify-content-center p-5 container">
    <div class="text-center">
      <h1>Witamy w bibliotece</h1>
      <table>
        <tr>
          <td>
            <button
              class="btn btn-info btn-lg btn-block"
              @click="this.$router.push({ name: 'readers' })"
            >
              Czytelnicy
            </button>
          </td>
        </tr>
        <tr>
          <td>
            <button
              class="btn btn-info btn-lg btn-block"
              @click="this.$router.push({ name: 'books' })"
            >
              Książki
            </button>
          </td>
        </tr>
        <tr>
          <td>
            <button
              class="btn btn-info btn-lg btn-block"
              @click="this.$router.push({ name: 'authors' })"
            >
              Autorzy
            </button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "StartScreen",
  methods: {
    goToAuthors() {
      this.$router.push({ name: "authors" });
    },
    goToBooks() {
      this.$router.push({ name: "books" });
    },
    goToReaders() {
      this.$router.push({ name: "readers" });
    },
  },
};
</script>

<style scoped>
img {
  border-radius: 30px;
}

table {
  border-collapse: collapse;
}

td {
  padding: 10px;
  text-align: center;
}

button {
  width: 100%;
}
</style>
