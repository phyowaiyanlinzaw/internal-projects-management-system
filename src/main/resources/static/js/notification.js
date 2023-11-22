import {getData} from "/js/api-function.js";
import {getTimeElapsed} from "/js/time.js";


const loginUser = await getData("/api/currentuser");

const currentlyWorkingProject = await getData(`/api/project/list/ID/${loginUser.currentUser.id}/false`);

// function for creating anchor element
const createA = ({id, description, time}) => {

    const anchor = document.createElement("a");
    if(loginUser.currentUser.role === "PROJECT_MANAGER") {
        anchor.href = "/issue";
    } else {
        if(currentlyWorkingProject !== null) {
            anchor.href = "/project/" + currentlyWorkingProject.projectId;
        }
    }
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

const notiList = await getData(`/api/notification/list/${loginUser.currentUser.id}`);

console.log("lee pal", notiList);

if(notiList.length === 0) {
    console.log("No notifications")
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

        anchor.addEventListener("click", function () {
            console.log("clicked");
            console.log(noti.id);
            fetch(`/api/notification/delete/byuserid`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(
                    loginUser.currentUser.id
                )
            }).then(response => {
                console.log(response);
                if (response.status === 200) {
                    console.log("Notification deleted");
                }
            })
        })

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
channel.bind('task-noti-event', function(response) {

    document.querySelector("#notification-container").innerHTML = "";

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

channel.bind("issue-noti-event", function(response) {

    console.log("in issue noti event function");
    console.log(response);

    document.querySelector("#notification-container").innerHTML = "";

    document.querySelector("#notification-light").classList.remove("d-none");

    const data = JSON.parse(response);

    const notification = data.notification;
    const newIssue = data.object;

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

    const issue = `<div class="p-md-3 p-sm-2">
                            <div class="card item-issue-approval" id="issueapprovalitem-${newIssue.id}">
                                <div class="d-inline p-2 issue-approval">
                                    <input type="radio" name="approval-${newIssue.id}" value="DUPLICATE"/>
                                    <label>Duplicate</label>
                                    <input type="radio" name="approval-${newIssue.id}" value="APPROVE"/>
                                    <label>Approve</label>
                                    <input type="radio" name="approval-${newIssue.id}" value="DECLINE"/>
                                    <label>Decline</label>
                                </div>
                                <div class="card-header border-bottom text-dark">
                                    <div class="d-flex justify-content-between align-items-center gap-2">
                                        <div class="d-flex align-items-center gap-2">
                                            <h6 class="card-title card-text">${newIssue.title}</h6>
                                            <p class="card-title badge d-flex align-items-center p-1 pe-2 text-primary-emphasis bg-primary border border-primary rounded-pill">
                                                ${newIssue.user_pic.name}
                                            </p>
                                        </div>
                                        <span
                                            class="card-title badge rounded-pill bg-${newIssue.status === 'Success' ? 'success' : 'danger'}">
                                            ${newIssue.status === 'true' ? 'Solved' : 'Unsolved'}
                                        </span>
                                    </div>
                                <div class="col d-flex justify-content-between align-items-center issue-time-line d-flex gap-2">
                                    <div class="d-flex align-items-center gap-2">
                                        <p>Posted</p>
                                        <span>${new Date(newIssue.created_date)}</span>
                                        <p>Modified at</p>
                                        <span>${getTimeElapsed(newIssue.created_date)}</span>
                                    </div>
                                    <button class="btn btn-primary btn-sm see-more-button" data-id="${newIssue.id}"
                                            data-bs-target="#issueDetailModal" data-bs-toggle="modal">
                                            See More
                                    </button>
                                </div>
                                <div class="col issue-time-line d-flex gap-2">
                                    <p class="btn custom-color rounded-pill">${newIssue.issueCategory}</p>
                                </div>
                                </div>
                                <div class="card-body">
                                    <div class="code-block">
                                        <p>${newIssue.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>`
    document.querySelector("#issue-approval-container").innerHTML += issue;
});

channel.bind("logout", function() {
    window.location.href = "/logout";
})

document.querySelector("#notification-light-container").addEventListener("shown.bs.dropdown",  () => {

    document.querySelector("#notification-light").classList.add("d-none");

});
