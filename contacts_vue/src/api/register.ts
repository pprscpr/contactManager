import { register, tokenResponse } from "../types/login";

export function registerUser (registerRequest: register) {
    return new Promise<boolean | tokenResponse>(async (resolve, reject) => {
        const data: register = {
            email: registerRequest.email,
            password: registerRequest.password,
            firstname: registerRequest.firstname,
            lastname: registerRequest.lastname
        };

        fetch('http://localhost:8080/api/v1/auth/register', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(data)
        })
          .then(response => response.json())
          .then(data => {
            console.log("data", data)
            if (data.token) {
                console.log("data.token", data.token)
                resolve(data.token)
            }
            else {
                resolve(false)
            }
          })
          .catch(e => {
              // todo: error handler
              reject(false)
              console.log("err", e)
          })
        
    }) 
} 