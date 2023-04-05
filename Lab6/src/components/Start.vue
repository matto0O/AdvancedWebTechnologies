<template>
  <div class="d-flex justify-content-center p-5 container">
    <div class="text-center">
      <h1>Witamy w bibliotece</h1>
      <table>
        <tr>
          <td>
            <a href="http://localhost:3000/readers">
            <button class="btn btn-info btn-lg btn-block">{{ "Czytelnicy" }}</button></a>
          </td>
        </tr>
        <tr>
          <td>
            <a href="http://localhost:3000/authors">
            <button class="btn btn-outline-info btn-lg btn-block">{{ "Autorzy" }}</button></a>
          </td>
        </tr>
        <tr>
          <td>
            <a href="http://localhost:3000/books">
            <button class="btn btn-info btn-lg btn-block">{{ "Książki" }}</button></a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
  
  <script>
export default {
  name: "StartScreen",
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

button{
    width: 100%
}
</style>