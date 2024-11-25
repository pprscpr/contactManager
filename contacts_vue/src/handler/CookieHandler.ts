class CookieHandler {
    setCookie (key: string, value: string): void {
        document.cookie = `${key}=${value}; SameSite=Lax; Secure`

    }

    deleteCookie(key: string) {
        document.cookie = key + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    }

    getCookie(cookieName: string): string | boolean  {
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            if (cookies[i].trim().startsWith(cookieName + '=')) {
                const cookieArr = cookies[i].split("=")
                const cookieValue = cookieArr[1]
                if (cookieValue) {
                    return cookieValue;
                }
            
            }
        }
        return false; 
    }

    
}

export default CookieHandler;