
import { contact } from "../types/contact"
import { useLogin } from "../stores/login"

export function fetchAll () {
    return new Promise<contact []>(async (resolve, reject) => {
        const login = useLogin()

        fetch('http://localhost:8080/contact/get_all', {
          method: 'GET',
          headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${login.token}`       
          },
        })
          .then(response => response.json())
          .then(data => {
            resolve(data.data)
          })
          .catch(e => {
              // todo: error handler
              reject(null)
              console.log("err", e)
          })
        
    }) 
} 

export function fetchOneById (id: String) {
  return new Promise<contact>(async (resolve, reject) => {
      const login = useLogin()

      fetch('http://localhost:8080/contact/get_by_id/' + id, {
        method: 'GET',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${login.token}`       
        },
      })
        .then(response => response.json())
        .then(data => {
          resolve(data.data)
        })
        .catch(e => {
            // todo: error handler
            reject(null)
            console.log("err", e)
        })
      
  }) 
} 
           
export function insert (firstName: String, lastName: String, emailAddress: String) {
  return new Promise<boolean>(async (resolve, reject) => {
      const login = useLogin()

      const data = {
          firstName: firstName,
          lastName: lastName,
          emailAddress: emailAddress, 
      };
      fetch('http://localhost:8080/contact/insert', {
        method: 'POST',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${login.token}`       
        },
        body: JSON.stringify(data)
      })
        .then(response => response.json())
        .then(data => {
          resolve(true)
          console.log("data", data)
        })
        .catch(e => {
          reject(false)
          // todo: error handler
          console.log("err", e)
        })
  })
}

export function update (id: String, contact: contact) {
    return new Promise<boolean>(async (resolve, reject) => {
        const login = useLogin()

        fetch('http://localhost:8080/contact/update_by_id/' + id, {
          method: 'PUT',
          headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${login.token}`       
          },
          body: JSON.stringify(contact)
        })
          .then(response => response.json())
          .then(data => {
            resolve(true)
            console.log("data", data)
          })
          .catch(e => {
            reject(false)
            // todo: error handler
            console.log("err", e)
          })
    })
} 
           
           

