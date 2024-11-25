import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from './pages/Home.vue'
import Insert from './pages/Insert.vue'
import Update from './pages/Update.vue'
import Login from './pages/Login.vue'
import Logout from './pages/Logout.vue'
import Register from './pages/Register.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/insert',
    name: 'Insert',
    component: Insert
  },
  {
    path: '/update/:id',
    name: 'Update',
    component: Update
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/logout',
    name: 'Logout',
    component: Logout
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },

]
const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
