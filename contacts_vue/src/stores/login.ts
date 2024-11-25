
import { defineStore } from "pinia"
import { login } from "../api/login"
import { auth, tokenResponse } from "../types/login"
import router from '../router';
import CookieHandler from "../handler/CookieHandler";

const ch = new CookieHandler()

export const useLogin = defineStore('login', {
  state: () => ({
    token: "",
  }),
  actions:  {

    async tryLogin (authRequest: auth) {
       const response: tokenResponse | boolean = await login(authRequest)
       if (typeof response === "string") {
          this.token = response
          this.setTokenCookie()
          router.push('/') 
       }
    },

    isLoggedIn (): boolean {
        return this.token !== ""
    },

    checkLogin () {
      if (!this.isLoggedIn()) {
          const tokenCookie = this.getTokenCookie()
          if (typeof tokenCookie === "string" && tokenCookie !== "")  {
              this.token = tokenCookie;
              console.log("check login")
              router.push('/')   
              return
          }
      }

      console.log("redirect to login")
      router.push('/login') 
    },

    logOut () {
      this.token = ""
      ch.deleteCookie("jwt")
      router.push('/login') 
    },

    setTokenCookie (): void {
        ch.setCookie("jwt", this.token)
    },

    getTokenCookie () {
      const jwt: string|boolean = ch.getCookie("jwt")
      return jwt
    }
  }
})

 