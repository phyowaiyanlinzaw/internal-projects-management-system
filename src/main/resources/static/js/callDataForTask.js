import { sendData, getData } from '/js/api-function.js';

export async function getTaskList() {
    try {
        return await getData("/api/task/list");
    } catch (error) {
        return error;
    }
}