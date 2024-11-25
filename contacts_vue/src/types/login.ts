export type auth = {
    "email": String,
    "password": String,
}

export type register = {
    "email": String,
    "password": String,
    "firstname": String,
    "lastname": String
}

export type tokenResponse = {
    token: String
}