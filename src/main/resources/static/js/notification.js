import {getData} from "/js/api-function.js";
import {formatDate, getTimeElapsed} from "/js/time.js";
import notiSound from '/js/notisound.js';


const loginUser = await getData("/api/currentuser");



// function for creating anchor element
const createA = async ({id, description, time, object}) => {
    const currentlyWorkingProject = await getData(`/api/project/list/ID/${loginUser.currentUser.id}/false`);
    const anchor = document.createElement("a");
    if(loginUser.currentUser.role === "PROJECT_MANAGER") {
        anchor.href = "/issue";
    } else {
        if(currentlyWorkingProject !== null) {
            anchor.href = "/project/" + currentlyWorkingProject.projectId;
        } else {
            anchor.href = "/project/" + object.id;
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

        const anchor = await createA({
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
channel.bind('task-noti-event', async function(response) {

    const hiddenElement = document.createElement('div');
    hiddenElement.style.display = 'none';
    document.body.appendChild(hiddenElement);
    hiddenElement.addEventListener('click', () => {
        notiSound();
        hiddenElement.remove();
    });
    const clickEvent = new Event('click');
    hiddenElement.dispatchEvent(clickEvent);
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

    const anchor = await createA({
        id: notification.id,
        description: notification.description,
        time: notification.noti_time
    })

    console.log(anchor)

    const hr = document.createElement("hr");
    hr.className = "dropdown-divider";

    const notificationContainer = document.querySelector("#notification-container");

    if (notificationContainer.querySelector('p')) {
        notificationContainer.innerHTML = "";
    }

    notificationContainer.appendChild(anchor);
    notificationContainer.appendChild(hr);

    const toast = createToast({ notification: notification })

    const btoawe = new bootstrap.Toast(toast)


    console.log(btoawe)

    document.querySelector('#toasts-noti-container').appendChild(toast)

    btoawe.show();
});

function createPendingIssueCard(issue) {
    console.log(issue)
    const outerDiv = document.createElement("div");
    outerDiv.classList.add("item-issue-approval", 'issue-card');
    outerDiv.id = `issueapprovalitem-${issue.id}`;

    const approvalContainer = document.createElement("div");
    approvalContainer.classList.add(
        "d-inline-flex",
        "bg-primary",
        "gap-2",
        "flex-row",
        "p-2",
        "border-top",
        "rounded-top",
        "text-white",
        "issue-approval"
    );

    approvalContainer.innerHTML += `
                    <input type="radio" name="approval-${issue.id}" value="DUPLICATE">
                    <label>duplicate</label>
                    <input type="radio" name="approval-${issue.id}" value="APPROVE">
                    <label>approve</label>
                    <input type="radio" name="approval-${issue.id}" value="DECLINE">
                    <label>decline</label>
                `;

    const main = document.createElement("div");
    main.classList.add("card", "p-0");
    main.style.minWidth = "320px";

    const cardHeader = `<div class="card-header justify-content-between">
                <div
                    class="d-flex justify-content-between align-items-center flex-wrap"
                >
                    <div class="d-flex gap-3 align-items-center flex-wrap">
                        <span class="issue-title issueDetails" data-id="${issue.id}" style="font-size: 32px;cursor: pointer;font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande","Lucida Sans Unicode", Geneva, Verdana, sans-serif;">${issue.title}</span>
                    </div>

                    <div class="d-flex justify-content-between gap-2">
                        <div>
                            <span class="badge bg-success">${issue.issueCategory}</span>
                            <span class="badge bg-primary bg-${issue.solved === true ? 'danger' : 'primary'}">
                                ${issue.solved === true ? 'Closed' : 'Open'}
                            </span>
                        </div>
                        <button
                            type="button"
                            class="btn btn-info see-more-button text-white"
                            data-id="${issue.id}"
                        >
                            <i class="fa-solid fa-pen-to-square"></i>
                        </button>
                    </div>
                </div>
                <div class="col issue-time-line d-flex gap-4 flex-wrap">
                    <div class="d-flex align-items-center gap-2">
                        <p style="padding: 0;margin: 0;opacity: 0.5;">Uploader</p>
                        <span style="font-size: 0.9em;opacity: 1;font-weight: 600;">${issue.user_uploader.name}</span>
                    </div>
                    <div class="d-flex flex-wrap gap-4">
                        <div class="d-flex align-items-center gap-2">
                            <p style="padding: 0;margin: 0;opacity: 0.5;">Posted</p>
                            <span style="font-size: 0.9em;opacity: 1;font-weight: 600;">${formatDate(issue.created_date)}</span>
                        </div>
                        <div class="d-flex align-items-center gap-2">
                            <p style="padding: 0;margin: 0;opacity: 0.5;">Modified at</p>
                            <span style="font-size: 0.9em;opacity: 1;font-weight: 600;">${getTimeElapsed(issue.updated_date)}</span>
                        </div>
                    </div>
                </div>
            </div>`;

    const cardBody = document.createElement("div");
    cardBody.classList.add("card-body", "accordion-item", "p-0");
    cardBody.id = `accordionExample-${issue.id}`;

    const h2 = `<h2 class="accordion-header" id="headingTwo">
            <button
                class="accordion-button collapsed"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target= "#collapse-${issue.id}"
                aria-expanded="false"
                aria-controls="collapseTwo"
            >
                Description
            </button>
        </h2>`;

    const collapse = document.createElement("div");
    collapse.id = `collapse-${issue.id}`;
    collapse.classList.add("accordion-collapse", "collapse");
    collapse.setAttribute("aria-labelledby", "headingTwo");
    collapse.setAttribute("data-bs-parent", `#accordionExample-${issue.id}`);

    // quill editor goes in here
    const accordionBody = document.createElement("div");
    accordionBody.id = `quill-editor-${issue.id}`;
    accordionBody.classList.add("accordion-body");

    function initializeQuill() {
        console.log("Before Quill initialization");
        const quillContainer = document.querySelector(`#quill-editor-${issue.id}`);
        console.log(quillContainer);

        let quill;

        if (quillContainer) {
            quill = new Quill(quillContainer, {
                modules: {
                    toolbar: false,
                },
                theme: "snow",
            });

            quill.clipboard.dangerouslyPasteHTML(0, issue.description);
            quill.enable(false);
        }

        console.log("After Quill initialization");
        console.log("this feels suck man", quill);
    }

    cardBody.innerHTML = h2;
    collapse.appendChild(accordionBody);

    cardBody.appendChild(collapse);

    main.innerHTML = cardHeader;
    main.appendChild(cardBody);

    console.log(main)

    setTimeout(initializeQuill, 1000);

    outerDiv.appendChild(approvalContainer);
    outerDiv.appendChild(main);

    return outerDiv;

}

channel.bind("issue-noti-event", async function(response) {

    document.querySelector("#no-new-issue").classList.add("d-none");
    
    const hiddenElement = document.createElement('div');
    hiddenElement.style.display = 'none';
    document.body.appendChild(hiddenElement);
    hiddenElement.addEventListener('click', () => {
        notiSound();
    });

    const clickEvent = new Event('click');
    hiddenElement.dispatchEvent(clickEvent);

    console.log("in issue noti event function");
    console.log(response);

    const notificationContainer = document.querySelector("#notification-container");

    if (notificationContainer.querySelector('p')) {
        notificationContainer.innerHTML = "";
    }

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

    const anchor = await createA({
        id: notification.id,
        description: notification.description,
        time: notification.noti_time
    })

    const hr = document.createElement("hr");
    hr.className = "dropdown-divider";

    
    notificationContainer.appendChild(anchor);
    notificationContainer.appendChild(hr);

    if (document.querySelector("#new-issue-list")) {
        console.log(newIssue)
        const issue = createPendingIssueCard(newIssue)

        document.querySelector("#new-issue-list").insertBefore(issue, document.querySelector("#new-issue-list").children[1])

    } 

    const toast = createToast({ notification: notification })

    const btoawe = new bootstrap.Toast(toast)

    
    console.log(btoawe)

    document.querySelector('#toasts-noti-container').appendChild(toast)

    btoawe.show();


});

channel.bind("logout", function() {
    window.location.href = "/logout";
})

document.querySelector("#notification-light-container").addEventListener("shown.bs.dropdown",  () => {

    document.querySelector("#notification-light").classList.add("d-none");

});

channel.bind("project-save-event", async function (response) {

    const hiddenElement = document.createElement('div');
    hiddenElement.style.display = 'none';
    document.body.appendChild(hiddenElement);
    hiddenElement.addEventListener('click', () => {
        notiSound();
        hiddenElement.remove();
    });
    const clickEvent = new Event('click');
    hiddenElement.dispatchEvent(clickEvent);

    const notificationContainer = document.querySelector("#notification-container");

    if (notificationContainer.querySelector('p')) {
        notificationContainer.innerHTML = "";
    }

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

    const anchor = await createA({
        id: notification.id,
        description: notification.description,
        time: notification.noti_time,
        object: data.object
    })

    const hr = document.createElement("hr");
    hr.className = "dropdown-divider";


    notificationContainer.appendChild(anchor);
    notificationContainer.appendChild(hr);

    
    const toast = createToast({ notification: notification })

    const btoawe = new bootstrap.Toast(toast)


    console.log(btoawe)

    document.querySelector('#toasts-noti-container').appendChild(toast)

    btoawe.show();

})


function createToast ({notification}) {
    const toast = document.createElement('div')

    toast.classList = 'toast show'
    toast.setAttribute('role', 'alert')
    toast.setAttribute('aria-live', 'assertive')
    toast.setAttribute('aria-atomic', 'true')

    toast.innerHTML = `<div class="toast-header">
                <strong class="me-auto"><i class="fa-solid fa-bell fs-3" style="transform: rotate(45deg);"></i></strong>
                <small class="text-muted">${getTimeElapsed(Date.now())}</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                ${notification.description}
            </div>`
    return toast
}