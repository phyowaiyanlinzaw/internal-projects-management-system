import { sendData, getData } from '/js/api-function.js';

let taskList = [];


getData("/api/task/list", (error, response) => {
    if (error) {
        console.error('Error:', error);
    } else {
        console.log('Data:', response);
        taskList = response
    }
})

export function getTaskList () {
    return taskList;
}

export function removeAnItemFromTaskList(i) {
    taskList.splice(i, 1);
}