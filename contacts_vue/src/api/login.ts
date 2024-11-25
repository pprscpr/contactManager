import { auth, tokenResponse } from "../types/login";

export function login (authRequest: auth) {
    return new Promise<boolean | tokenResponse>(async (resolve, reject) => {
        const data: auth = {
            email: authRequest.email,
            password: authRequest.password,
        };

        fetch('http://localhost:8080/api/v1/auth/authenticate', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(data)
        })
          .then(response => response.json())
          .then(data => {
            if (data.token) {
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