import { sendData, getData } from '/js/api-function.js';

let architecturelist = [];

getData("/api/project/architecturelist", (error, response) => {
    if(error) {
        console.log(error)
    } else {
        console.log(response)
        architecturelist = response
    }
})

export function getArchitecturelist () {
    return architecturelist;
}