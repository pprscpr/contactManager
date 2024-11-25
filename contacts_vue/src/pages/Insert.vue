<script setup lang="ts">
import { onMounted,ref } from 'vue'
import { useContacts } from '../stores/contacts'
import router from '../router';

const contacts = useContacts();
const firstName = ref("")
const lastName = ref("")
const emailAddress = ref("")


function insertContact () {
    contacts.insert(
        firstName.value,
        lastName.value,
        emailAddress.value,
    )
    router.push('/')
}

onMounted(async ()=> {

    console.log("insert")

})
 
</script>

<template>
       <section>
            <div class="row">
                <div class="col-12">
                    <h2>Insert</h2>
                    <input type="text" v-model="firstName">
                    <input type="text" v-model="lastName">
                    <input type="text" v-model="emailAddress">
                    <button class="btn btn-primary" @click="insertContact()">
                        shoot !
                    </button>
                </div>
            </div>
        </section>


</template>

<style scoped>

</style>
