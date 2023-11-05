import { getData } from '/js/api-function.js';

export async function getArchitecturelist() {
    try {
        return await getData("/api/project/architecturelist");
    } catch (error) {
        return error;
    }
}

export async function getClientList() {
    try {
    return await getData("/api/client/lists")
    } catch (error) {
        return error
    }
}

export async function getDeliverableTypeList() {
    try {
        return await getData("/api/project/deliverableType")
    } catch (error) {
        return error
    }
}