<script setup lang="ts">
import { onMounted } from 'vue';
import { useLogin } from '../stores/login.ts';


const login = useLogin()


onMounted(async ()=> {
    login.logOut()
})
 
</script>

<template>
    <div>
        logging out
    </div>
</template>


