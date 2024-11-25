
import { defineStore } from "pinia"
import {registerUser} from "../api/register"
import { register, tokenResponse } from "../types/login"
import router from '../router';


export const useRegister = defineStore('register', {
  state: () => ({
  }),
  actions:  {

    async tryRegisterUser (registerRequest: register) {
       const response: tokenResponse | boolean = await registerUser(registerRequest)
       if (typeof response === "string") {
          router.push("/login")
       }
    }

  }
})

 