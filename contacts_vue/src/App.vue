<script setup lang="ts">
import { onMounted } from 'vue';
import { RouterLink, RouterView } from 'vue-router'
import 'bootstrap/dist/css/bootstrap.css'
import { useLogin } from './stores/login.ts';

const login = useLogin()

onMounted(async ()=> {
    login.checkLogin()
})
 
</script>

<template>
  <nav v-if="$route.path 
            && $route.path !== '/login'
            && $route.path !== '/register'
  ">
    <router-link to="/">Home</router-link> |
    <router-link to="/insert">Insert</router-link> |
    <router-link to="/logout">Logout</router-link> 
  </nav>
  <router-view />
</template>

<style scoped>

</style>
