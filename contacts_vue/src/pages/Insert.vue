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
                </div>
                <div class="col-12 mb-3">
                    <div class="form-floating mb-3">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="firstName" 
                            v-model="firstName" 
                            placeholder="Enter First Name"
                        >
                        <label for="floatingInput">First Name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="lastName" 
                            v-model="lastName" 
                            placeholder="Enter Last Name"
                        >
                        <label for="lastName">Last Name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="emailAddress" 
                            v-model="emailAddress" 
                            placeholder="Enter Email Address"
                        >
                        <label for="emailAddress">Email Address</label>
                    </div>
                </div>

                <div class="col-12 mb-3 login-actions">
                    <button class="btn btn-primary" @click="insertContact()" >
                        Insert
                    </button>
                </div>
            </div>
        </section>


</template>

<style scoped>

</style>
