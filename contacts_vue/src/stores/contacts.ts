
import { defineStore } from "pinia"
import { fetchAll, insert, fetchOneById, update } from "../api/contacts"
import { contact } from "../types/contact"

export const useContacts = defineStore('contacts', {
  state: () => ({
    state: 0,
  }),
  actions:  {

    fetchAllContacts () {
        return fetchAll()
    },

    insert (firstName: String, lastName: String, emailAddress: String) {
       return insert(firstName, lastName, emailAddress)
    },

    update(id: String, contact: contact) {
      return update(id, contact)
    },

    getById (id: String) {
      return fetchOneById(id)
    },

  }
})

 