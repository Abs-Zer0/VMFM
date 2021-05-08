<template>
  <div>
    <v-btn @click="toLogin">Login</v-btn>
    <v-btn @click="toAuth">Auth</v-btn>
  </div>
</template>

<script>
export default {
  name: "Home",
  methods: {
    toLogin() {
      this.$router.push("/login");
    },
    toAuth() {
      this.$router.push("/auth");
    },
  },
};
</script>