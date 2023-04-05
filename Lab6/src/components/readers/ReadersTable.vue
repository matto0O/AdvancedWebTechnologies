<template>
  <div id="readers-table" class="container">
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
        <tr v-for="reader in readers" :key="reader.id">
          <td>{{ reader.firstName }}</td>
          <td>{{ reader.lastName }}</td>
          <td>
            <button class="btn btn-danger" @click="deleteReader(reader.id)">
              Usuń
            </button>
          </td>
          <td>
            <button
              class="btn btn-warning"
              @click="
                this.$router.push({
                  name: 'readersUpdate',
                  params: { id: reader.id },
                })
              "
            >
              Aktualizuj
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <ReaderForm :readersSource="readers" class="mt-5" />
  </div>
</template>
  
  <script>
import ReaderForm from "@/components/readers/ReadersForm.vue";
export default {
  name: "readersTable",
  components: { ReaderForm },
  data() {
    return {
      readers: [],
    };
  },
  mounted() {
    this.getReaders();
  },
  methods: {
    async getReaders() {
      try {
        const response = await fetch("http://localhost:8081/readers");
        console.log(response);
        const data = await response.json();
        this.readers = data;
      } catch (error) {
        console.error(error);
      }
    },
    async deleteReader(itemId) {
      const response = await fetch(
        `http://localhost:8081/readers/${itemId}`,
        {
          method: "DELETE",
        }
      );
      console.log(response.data);
      this.readers = this.readers.filter((obj) => {
        return obj.id !== itemId;
      });
    },
  },
};
</script>
  
  <style scoped>
</style>
  