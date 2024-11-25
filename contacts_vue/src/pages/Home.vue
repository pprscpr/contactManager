<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useContacts } from '../stores/contacts';
import { RouterLink } from 'vue-router'
import { contact } from "../types/contact.ts"

const list = ref<contact []>([])

onMounted(async ()=> {

  try {
    // todo: check timing
    setTimeout(async ()=> {
        const contacts = useContacts()
        const c = await contacts.fetchAllContacts()
        list.value = c;
    }, 500)
  }
  catch(e) {
    console.log("error", e)
  }

})
 
</script>

<template>

    <section>
        <div class="row">
            <div class="col-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Email</th>
                        <th scope="col"></th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, _) of list">
                            <th scope="row">{{ item.id }}</th>
                            <td>{{ item.firstName }}</td>
                            <td>{{ item.lastName }}</td>
                            <td>{{ item.emailAddress }}</td>
                            <td>     
                                <router-link
                                    class="btn btn-primary" 
                                    :to="'/update/' + item.id">
                                        Edit
                                </router-link>
                            </td>
                        </tr>
                      
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</template>

<style scoped>

</style>
