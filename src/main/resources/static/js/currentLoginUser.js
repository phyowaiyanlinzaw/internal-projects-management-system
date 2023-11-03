import {getData} from "/js/api-function.js";

export async function getUser() {
    try {
        return await getData("/api/currentuser")
    } catch (error) {
        return error;
    }
}