<script setup lang="ts">
import { onMounted,ref } from 'vue'
import { useContacts } from '../stores/contacts'
import { useRoute } from 'vue-router'
import { contact } from '../types/contact';
import router from '../router';



const route = useRoute()
const contacts = useContacts()
const editContact = ref<contact>()
const firstName = ref("")
const lastName = ref("")
const emailAddress = ref("")
const contactID =  route.params.id


function updateContact () {
    const updateContact: contact =  {
            id: parseInt(contactID.toString()),
            firstName: firstName.value,
            lastName: lastName.value,
            emailAddress: emailAddress.value
    }
    contacts.update(
        contactID.toString(),
        updateContact
    )
    router.push('/') 
}

onMounted(async ()=> {
    console.log("update", contactID)
    editContact.value = await contacts.getById(contactID.toString())
    console.log(editContact.value)
    firstName.value =  editContact.value.firstName.toString()
    lastName.value =  editContact.value.lastName.toString()
    emailAddress.value =  editContact.value.emailAddress.toString()
})
 
</script>

<template>
       <section>
            <div class="row">
                <div class="col-12">
                    <h2>Update</h2>
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
                    <button class="btn btn-primary" @click="updateContact()" >
                        Update
                    </button>
                </div>
            </div>
        </section>
</template>

<style scoped>

</style>
