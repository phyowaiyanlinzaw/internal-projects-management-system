import {getData} from "/js/api-function.js";
import {getTimeElapsed} from "/js/time.js";

// function for creating anchor element
const createA = ({id, description, time}) => {
    const anchor = document.createElement("a");
    anchor.className = "dropdown-item";

    const heading = document.createElement("h6");
    heading.className = "fw-normal mb-0";
    heading.textContent = description;

    const small = document.createElement("small");
    small.textContent = getTimeElapsed(time);
    small.style.color = "rgba(0, 0, 0, .6)";

    anchor.appendChild(heading);
    anchor.appendChild(small);

    return anchor;
}

const loginUser = await getData("/api/currentuser");

const notiList = await getData(`/api/notification/list/${loginUser.currentUser.id}`);

if(notiList.length === 0) {
    document.querySelector("#notification-container").innerHTML = "<p class='text-center'>No notifications</p>"
    document.querySelector("#notification-light").classList.add("d-none");
} else {

    document.querySelector("#notification-light").classList.remove("d-none");

    for (let i = notiList.length - 1; i >= 0; i--) {
        const noti = notiList[i];

        const anchor = createA({
            id: noti.id,
            description: noti.description,
            time: noti.noti_time
        });

        const hr = document.createElement("hr");
        hr.className = "dropdown-divider";

        const notificationContainer = document.querySelector("#notification-container");
        notificationContainer.appendChild(anchor);
        notificationContainer.appendChild(hr);
    }

}

const pusher = new Pusher('3c0b3426bd0875be392f', {
    cluster: 'ap1'
});

const channel = pusher.subscribe(`my-channel-${loginUser.currentUser.id}`);
channel.bind('noti-event', function(response) {

    document.querySelector("#notification-light").classList.remove("d-none");

    const data = JSON.parse(response);

    const notification = data.notification;

    console.log(notification);

    console.log(notification.description);
    console.log(notification.noti_time);

    if ('description' in notification) {
        console.log("Notification description:", notification.description);
    } else {
        console.log("Notification description is undefined or does not exist");
    }

    const anchor = createA({
        id: notification.id,
        description: notification.description,
        time: notification.noti_time
    })

    const hr = document.createElement("hr");
    hr.className = "dropdown-divider";

    const notificationContainer = document.querySelector("#notification-container");
    notificationContainer.appendChild(anchor);
    notificationContainer.appendChild(hr);
});

document.querySelector("#notification-dropdown").addEventListener("shown.bs.dropdown", () => {
    document.querySelector("#notification-light").classList.add("d-none");
})
