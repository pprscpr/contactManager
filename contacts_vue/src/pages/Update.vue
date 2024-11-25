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
                    <input type="text" v-model="firstName">
                    <input type="text" v-model="lastName">
                    <input type="text" v-model="emailAddress">
                    <button class="btn btn-primary" @click="updateContact()">
                        Update                    
                    </button>
                </div>
            </div>
        </section>


</template>

<style scoped>

</style>
