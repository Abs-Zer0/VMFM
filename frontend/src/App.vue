<template>
  <div id="app">
    <v-app>
      <v-app-bar app>
        <v-img contain src="assets/spring-logo.png"></v-img>
        <v-img contain src="assets/camel-logo.png"></v-img>
        <v-img contain src="assets/vue-logo.png"></v-img>
        <h1>VMFM</h1>
      </v-app-bar>
      <v-main>
        <router-view></router-view>
      </v-main>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
