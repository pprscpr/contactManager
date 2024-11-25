<script setup lang="ts">
import { ref } from 'vue'
import { register } from '../types/login';
import { useRegister } from '../stores/register';

const reg = useRegister()

const registerRequest = ref<register>({
    email: "",
    password: "",
    firstname: "",
    lastname: "",
})

function tryRegister () {
    reg.tryRegisterUser(registerRequest.value)
}


</script>
<template>
    <section>
        <div class="row">
            <div class="col-12">
                <h2 class="mb-3">
                    Register
                </h2>
            </div>
            <div class="col-12 mb-3" v-if="true">
                <div class="form-floating mb-3">
                    <input 
                        type="email" 
                        class="form-control" 
                        id="email" 
                        v-model="registerRequest.email" 
                        placeholder="name@example.com"
                    >
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input 
                        type="password" 
                        class="form-control" 
                        id="password" 
                        v-model="registerRequest.password" 

                        placeholder="Password"
                    >
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="form-floating">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="firstName" 
                        v-model="registerRequest.firstname" 

                        placeholder="firstName"
                    >
                    <label for="firstName">firstName</label>
                </div>
                <div class="form-floating">
                    <input 
                        type="text" 
                        class="form-control" 
                        id="lastName" 
                        v-model="registerRequest.lastname" 

                        placeholder="lastName"
                    >
                    <label for="lastName">lastName</label>
                </div>
            </div>
            <div class="col-12 mb-3 register-actions">
                <button class="btn btn-secondary" @click="tryRegister()" >
                    Register
                </button>
            </div>
        </div>
    </section>
</template>

<style scoped>
    .register-actions {
        display: flex;
        justify-content: space-between;
    }
</style>