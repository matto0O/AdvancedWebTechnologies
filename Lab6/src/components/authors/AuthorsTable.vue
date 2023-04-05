<template>
  <div id="authors-table" class="container">
    <table>
      <thead>
        <tr>
          <th>Imię</th>
          <th>Nazwisko</th>
          <th>Usuń</th>
          <th>Aktualizuj</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="author in authors" :key="author.id">
          <td>{{ author.firstName }}</td>
          <td>{{ author.lastName }}</td>
          <td>
            <button class="btn btn-danger" @click="deleteAuthor(author.id)">
              Usuń
            </button>
          </td>
          <td>
            <button
              class="btn btn-warning"
              @click="
                this.$router.push({
                  name: 'authorsUpdate',
                  params: { id: author.id },
                })
              "
            >
              Aktualizuj
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <AuthorForm :authorsSource="authors" class="mt-5" />
  </div>
</template>
  
  <script>
import AuthorForm from "@/components/authors/AuthorsForm.vue";
export default {
  name: "AuthorsTable",
  components: { AuthorForm },
  data() {
    return {
      authors: [],
    };
  },
  mounted() {
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
    async deleteAuthor(itemId) {
      const response = await fetch(
        `http://localhost:8081/authors/${itemId}`,
        {
          method: "DELETE",
        }
      );
      console.log(response.data);
      this.authors = this.authors.filter((obj) => {
        return obj.id !== itemId;
      });
    },
  },
};
</script>
  
  <style scoped>
</style>
  