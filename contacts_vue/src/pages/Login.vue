<script setup lang="ts">
import { ref } from 'vue'
import router from '../router';
import { auth } from '../types/login';
import { useLogin } from '../stores/login';

const login = useLogin()
const loginRequest = ref<auth>({
    email: "",
    password: "",
})

function tryLogin () {
    login.tryLogin(loginRequest.value);
}

function goToRegister () {
    router.push("/register")
}
 
</script>

<template>
    <section>
        <div class="row">
            <div class="col-12">
                <h2 class="mb-3">
                    Login
                </h2>
            </div>
            <div class="col-12 mb-3">
                <div class="form-floating mb-3">
                    <input 
                        type="email" 
                        class="form-control" 
                        id="email" 
                        v-model="loginRequest.email" 
                        placeholder="name@example.com"
                    >
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input 
                        type="password" 
                        class="form-control" 
                        id="password" 
                        v-model="loginRequest.password" 

                        placeholder="Password"
                    >
                    <label for="floatingPassword">Password</label>
                </div>
            </div>
            <div class="col-12 mb-3 login-actions">
                <button class="btn btn-secondary" @click="goToRegister()" >
                    Register
                </button>
                <button class="btn btn-primary" @click="tryLogin()" >
                    Login
                </button>
            </div>
        </div>
    </section>
</template>

<style scoped>
    .login-actions {
        display: flex;
        justify-content: space-between;
    }
</style>
