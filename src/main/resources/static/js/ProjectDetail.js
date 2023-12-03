import { getData } from '/js/api-function.js'
import { getUser } from "/js/currentLoginUser.js";

const projectId = document.querySelector("#projectId")

const toolbaroptions = [
    // Test-style
    ["bold", "italic", "underline"],

    // Bullet points style...
    [{ list: "ordered" }, { list: "bullet" }],

    // Indentation...
    [{ indent: "+1" }, { indent: "-1" }],

    // Alignment
    [{ align: [] }],

    // Header for test, e.g., h1, h2...
    [{ header: [1, 2, 3, 4, 5, 6, false] }],

    // Adding image, link, video, or formula
    ["link"],
];

const currentUser = await getUser();

if (currentUser.currentUser.role !== 'PROJECT_MANAGER') {
    document.querySelector("button[data-bs-target='#add-deliverable-modal']").classList.add('d-none')
    document.querySelector("button[data-bs-target='#add-architecture-modal']").classList.add('d-none')
}

const project = await getData('/api/project/list/' + projectId.textContent)

function formatDate(a) {
    const date = new Date(a);
    return `${date.getDate()} / ${date.getMonth() + 1} / ${date.getFullYear()}`;
}

let objectiveQuill;


objectiveQuill = new Quill("#project-detail-objective", {
    modules: {
        toolbar: false,
    },
});
objectiveQuill.enable(false);


const deliListContainer = document.querySelector("#project-detail-deliverable")
const archContainer = document.querySelector('#project-detial-architecture')
const projectDetail = document.querySelector(`button[data-bs-target="#project-detials-view"]`)
projectDetail.addEventListener('show.bs.tab', async function (e) {

    const project = await getData('/api/project/list/' + projectId.textContent);

    console.log(project)

    document.querySelector("#project-detail-name").textContent = project.name;

    document.querySelector("#project-detail-start-date").textContent = formatDate(project.start_date)

    document.querySelector("#project-detail-end-date").textContent = formatDate(project.end_date)

    document.querySelector("#project-detail-duration").textContent = project.duration + (project.duration > 1 ? " Months" : " Month");

    console.log(project.background)

    document.querySelector('#project-detail-background').textContent = project.background

    objectiveQuill.root.innerHTML = "";

    objectiveQuill.clipboard.dangerouslyPasteHTML(
        0,
        project.objective
    );

    console.log(project)

    deliListContainer.innerHTML = ''
    archContainer.innerHTML = ''

    project.deliverableDto.forEach(a => {

        const deli = createDeliverable(a)

        deliListContainer.appendChild(deli);
    });

    project.architectureDto.forEach(a => {
        const arch = createArchitecture(a)
        archContainer.appendChild(arch)
    });

    $('input[data-lol="deli"]').bootstrapToggle({
        on: 'ready',
        off: "no",
        onStyle: 'success',
        size: 'sm'
    })

    if (currentUser.currentUser.role !== "PROJECT_MANAGER") {
        $('input[data-lol="deli"]').bootstrapToggle("disable");
    }

    $('input[data-lol="deli"]').change(function () {
        const isReady = $(this).prop("checked");
        const deliId = $(this).attr("id");

        const actualId = deliId.split("-").pop();

        const deliverable = {
            id: actualId,
            status: isReady,
        };

        $.ajax({
            url: "/api/deliverable/update-status",
            method: "PUT",
            data: JSON.stringify(deliverable),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                console.log(response);
                console.log("Deliverable Status Updated Successfully...");
            },
        });
    });

})

function createDeliverable(a) {

    const outerDiv = document.createElement("div");
    outerDiv.className = "d-flex";
    outerDiv.classList.add('bg-light', 'rounded')

    if (currentUser.currentUser.role === 'PROJECT_MANAGER') {
        const delitype = document.createElement('div');
        delitype.className = 'delitype'
        delitype.innerHTML = `<i class="fa-solid  p-2 bg-danger text-white rounded fa-xmark" title="delete" style="cursor:pointer;"></i>`

        outerDiv.appendChild(delitype)

        delitype.addEventListener('click', function () {

            if (deliListContainer.children.length === 1) {
                return;
            } else {
                $.ajax({
                    method: "PUT",
                    url: "/api/project/delete/" + projectId.textContent + "/deliveralbeList",
                    data: JSON.stringify({ deliID: a.id }),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (response) {
                        console.log(response);
                        console.log("Deliverable Deleted Successfully...");
                        deliListContainer.removeChild(outerDiv);
                    },
                    error: function (error) {
                        console.log("Error in Url : /api/client/save ", error);
                    },
                });
            }

        })
    }

    const innerDIv = document.createElement('div')
    innerDIv.className = 'd-flex justify-content-between align-items-center'

    // Create the inner div for the text
    const typeDiv = document.createElement("div");
    typeDiv.textContent = a.deliverableType.type;
    typeDiv.classList.add('text-center', 'fw-bold', 'ms-3', 'me-3', 'text-primary')
    innerDIv.appendChild(typeDiv);

    const inputDiv = document.createElement("div");
    const input = document.createElement("input");
    input.type = "checkbox";
    input.setAttribute("data-lol", "deli");
    input.checked = a.status;
    input.id = `deliveralbe-${a.id}`;
    input.setAttribute("data-toggle", "toggle");
    inputDiv.appendChild(input);
    innerDIv.appendChild(inputDiv);
    outerDiv.appendChild(innerDIv)

    return outerDiv;
}


const addDeliverableModal = document.querySelector("#add-deliverable-modal")

const deliverableAddContainer = document.querySelector('#list-of-deliverable-to-add-in-project')

let newDeli = [];

addDeliverableModal.addEventListener('show.bs.modal', async function (e) {

    console.log('lol')

    const deliverableList = await getData('/api/deliverable-type/list/byProject/' + projectId.textContent)


    console.log(deliverableAddContainer)

    deliverableAddContainer.innerHTML = ''

    deliverableList.forEach(a => {

        const b = createDeli(a)

        deliverableAddContainer.appendChild(b);

    })

    if (deliverableAddContainer.children.length === 0) {
        document.querySelector("#no-result-deli").classList.remove('d-none')
    } else {
        document.querySelector("#no-result-deli").classList.add('d-none')
    }

    $('input[data-lol="add-deli"]').bootstrapToggle({
        on: 'Yes',
        off: "No",
        onStyle: 'success',
        size: 'sm'
    })

    $('input[data-lol="add-deli"]').change(function () {
        const value = $(this).val();
        if ($(this).prop('checked')) {
            // Add the value if the checkbox is checked and not already in the array
            if (newDeli.indexOf(value) === -1) {
                newDeli.push(value);
            }
        } else {
            // Remove the value if the checkbox is unchecked
            const index = newDeli.indexOf(value);
            if (index !== -1) {
                newDeli.splice(index, 1);
            }
        }
    });

    console.log(deliverableList)

})
const addDeliverableBtn = document.querySelector('#save-new-deliverable')

addDeliverableBtn.addEventListener('click', function () {

    if (newDeli.length === 0) {
        return;
    }

    const newDeliverableList = newDeli.map(a => {
        console.log(a)
        return {
            deliverableType: {
                id: a
            }
        }
    })

    console.log(projectId.textContent)


    $.ajax({
        method: "PUT",
        url: "/api/project/save/" + projectId.textContent + "/deliveralbeList",
        data: JSON.stringify(newDeliverableList),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            for (let i = 0; i < newDeliverableList.length; i++) {

                const a = newDeliverableList[i]

                $('#deliverable-to-add-' + a.deliverableType.id).remove()
            }
            console.log(response);
            deliListContainer.innerHTML = ''
            response.forEach(a => {
                const b = createDeliverable(a)

                deliListContainer.appendChild(b);
                $('input[data-lol="deli"]').bootstrapToggle({
                    on: 'ready',
                    off: "no",
                    onStyle: 'success',
                    size: 'sm'
                })

                $('input[data-lol="deli"]').change(function () {
                    const isReady = $(this).prop("checked");
                    const deliId = $(this).attr("id");

                    const actualId = deliId.split("-").pop();

                    const deliverable = {
                        id: actualId,
                        status: isReady,
                    };

                    $.ajax({
                        url: "/api/deliverable/update-status",
                        method: "PUT",
                        data: JSON.stringify(deliverable),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function (response) {
                            console.log(response);
                            console.log("Deliverable Status Updated Successfully...");
                        },
                    });
                });
            })
            if (deliverableAddContainer.children.length === 0) {
                console.log('not result')
                document.querySelector("#no-result-deli").classList.remove('d-none')
            } else {
                console.log('not result')
                document.querySelector("#no-result-deli").classList.add('d-none')
            }
        },
        error: function (error) {
            console.log("Error in Url : /api/client/save ", error);
        },
    });


})

function createDeli(a) {
    const outerDiv = document.createElement("div");
    outerDiv.className = "d-flex";
    outerDiv.id = 'deliverable-to-add-' + a.id

    const innerDIv = document.createElement('div')
    innerDIv.className = 'd-flex justify-content-between flex-grow-1 align-items-center px-5 '

    // Create the inner div for the text
    const typeDiv = document.createElement("div");
    typeDiv.textContent = a.type;
    typeDiv.classList.add('text-center', 'badge', 'bg-success', 'fw-bold')
    innerDIv.appendChild(typeDiv);

    const inputDiv = document.createElement("div");
    const input = document.createElement("input");
    input.setAttribute("data-lol", "add-deli");
    input.value = a.id;
    input.type = "checkbox";
    input.id = `deliveralbe-${a.id}`;
    input.setAttribute("data-toggle", "toggle");
    inputDiv.appendChild(input);
    innerDIv.appendChild(inputDiv);
    outerDiv.appendChild(innerDIv)
    return outerDiv;
}

function createArchitecture(a) {

    const outerDiv = document.createElement("div");
    outerDiv.className = "d-flex me-3 bg-light rounded";

    if (currentUser.currentUser.role === 'PROJECT_MANAGER') {
        const delitype = document.createElement('div');
        delitype.className = 'delitype bg-light '
        delitype.innerHTML = `<i class="fa-solid  p-2 bg-danger text-white rounded fa-xmark" title="delete" style="cursor:pointer;"></i>`

        outerDiv.appendChild(delitype)

        delitype.addEventListener('click', function () {

            console.log(a.id)

            if (archContainer.children.length === 1) {
                return;
            } else {
                $.ajax({
                    method: "PUT",
                    url: "/api/project/update/" + projectId.textContent + "/architectureList",
                    data: JSON.stringify({ arcid: a.id }),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (response) {
                        console.log(response);
                        console.log("Deliverable Deleted Successfully...");
                        archContainer.removeChild(outerDiv);
                    },
                    error: function (error) {
                        console.log("Error in Url : /api/client/save ", error);
                    },
                });
            }

        })
    }

    const innerDIv = document.createElement('div')
    innerDIv.className = 'd-flex justify-content-between flex-grow-1 align-items-center'

    // Create the inner div for the text
    const typeDiv = document.createElement("div");
    typeDiv.textContent = a.tech_name;
    typeDiv.classList.add('text-center', 'fw-bold', 'text-primary', 'ms-3')
    innerDIv.appendChild(typeDiv);

    outerDiv.appendChild(innerDIv)

    return outerDiv;

}

const archModal = document.querySelector("#add-architecture-modal")
const archListContainer = document.querySelector('#list-of-architecture-to-add-in-project')

let newArch = [];

archModal.addEventListener('show.bs.modal', async function (e) {

    const architectureList = await getData('/api/project/architecturelist/' + projectId.textContent)

    archListContainer.innerHTML = ''

    architectureList.forEach(a => {
        const b = createNewArch(a)
        archListContainer.appendChild(b)
    })

    if (archListContainer.children.length === 0) {

        document.querySelector("#no-result-arch").classList.remove('d-none')
    } else {
        document.querySelector("#no-result-arch").classList.add('d-none')
    }

    $('input[data-lol="add-arch"]').bootstrapToggle({
        on: 'Yes',
        off: "No",
        onStyle: 'success',
        size: 'sm'
    })

    $('input[data-lol="add-arch"]').change(function () {
        const value = $(this).val();
        if ($(this).prop('checked')) {
            // Add the value if the checkbox is checked and not already in the array
            if (newArch.indexOf(value) === -1) {
                newArch.push(value);
            }
        } else {
            // Remove the value if the checkbox is unchecked
            const index = newArch.indexOf(value);
            if (index !== -1) {
                newArch.splice(index, 1);
            }
        }
    });

})

const addArchBtn = document.querySelector('#save-new-architecture')

addArchBtn.addEventListener('click', function () {

    if (newArch.length === 0) {
        return;
    }

    const newArchList = newArch.map(a => {
        console.log(a)
        return {
            id: a
        }
    })

    $.ajax({
        method: "PUT",
        url: "/api/project/save/" + projectId.textContent + "/architectureList",
        data: JSON.stringify(newArchList),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            console.log(response);
            for (let i = 0; i < newArchList.length; i++) {
                const a = newArchList[i]

                $('#architecture-to-add-' + a.id).remove()
            }
            archContainer.innerHTML = ''
            response.forEach(a => {
                const b = createArchitecture(a)
                archContainer.appendChild(b)
            })
            if (archListContainer.children.length === 0) {
                console.log('not result')
                console.log(document.querySelector("#no-result-arch"))
                document.querySelector("#no-result-arch").classList.remove('d-none')
            } else {
                console.log('ther are result')
                document.querySelector("#no-result-arch").classList.add('d-none')
            }
        },
        error: function (error) {
            console.log("Error in Url : /api/client/save ", error);
        },
    })
})


function createNewArch(a) {
    const outerDiv = document.createElement("div");
    outerDiv.className = "d-flex";
    outerDiv.id = 'architecture-to-add-' + a.id

    const innerDIv = document.createElement('div')
    innerDIv.className = 'd-flex justify-content-between flex-grow-1 align-items-center px-5 '

    // Create the inner div for the text
    const typeDiv = document.createElement("div");
    typeDiv.textContent = a.tech_name;
    typeDiv.classList.add('text-center', 'badge', 'bg-success', 'fw-bold')
    innerDIv.appendChild(typeDiv);

    const inputDiv = document.createElement("div");
    const input = document.createElement("input");
    input.setAttribute("data-lol", "add-arch");
    input.value = a.id;
    input.type = "checkbox";
    input.id = `deliveralbe-${a.id}`;
    input.setAttribute("data-toggle", "toggle");
    inputDiv.appendChild(input);
    innerDIv.appendChild(inputDiv);
    outerDiv.appendChild(innerDIv)
    return outerDiv;
}

const deliSearchBtn = document.querySelector("#deli-search")

function isMatch(input, title) {
    // Escape special characters in the input
    const escapedInput = input.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');

    // Create a regular expression pattern
    const pattern = new RegExp(`^.*${escapedInput.split('').join('.*')}.*$`, 'i');

    // Check if the input matches either the title or card
    return pattern.test(title);
}

deliSearchBtn.addEventListener('input', async function () {

    const value = deliSearchBtn.value;

    let count = 0;

    for (let i = 0; i < deliverableAddContainer.children.length; i++) {
        const a = deliverableAddContainer.children[i]
        const text = a.querySelector('.text-center').textContent;

        if (isMatch(value.trim(), text.trim())) {
            a.classList.remove('d-none')
            count++;
        } else {
            a.classList.add('d-none')
        }
    }

    if (count === 0) {
        document.querySelector("#no-result-deli").classList.remove('d-none')
    } else {
        document.querySelector("#no-result-deli").classList.add('d-none')
    }

})

const archSearchBtn = document.querySelector("#arch-search")

archSearchBtn.addEventListener('input', function () {

    const value = archSearchBtn.value;

    let count = 0;

    for (let i = 0; i < archListContainer.children.length; i++) {
        const a = archListContainer.children[i]
        const text = a.querySelector('.text-center').textContent;

        if (isMatch(value.trim(), text.trim())) {
            a.classList.remove('d-none')
            count++;
        } else {
            a.classList.add('d-none')
        }
    }

    if (count === 0) {
        document.querySelector("#no-result-arch").classList.remove('d-none')
    } else {
        document.querySelector("#no-result-arch").classList.add('d-none')
    }

})

const changePhase = document.querySelector("#development-phase");

if (currentUser.currentUser.role !== 'PROJECT_MANAGER') {
    changePhase.disabled = true;
}

for (let i = 0; i < changePhase.options.length; i++) {
    if (changePhase.options[i].value === project.current_phase) {
        changePhase.options[i].selected = true;
        break;  // Stop the loop once the matching option is found
    }
}


function createToast(a) {
    const toast = document.createElement('div')

    toast.classList = 'toast show bg-success text-white'
    toast.setAttribute('role', 'alert')
    toast.setAttribute('aria-live', 'assertive')
    toast.setAttribute('aria-atomic', 'true')

    toast.innerHTML = `<div class="toast-header">
                <strong class="me-auto"><i class="fa-solid fa-bell fs-3" style="transform: rotate(45deg);"></i></strong>
                <small class="text-muted">${a}</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>`
    return toast
}

changePhase.addEventListener("change", function () {

    $.ajax({
        method: "PUT",
        url: "/api/project/update/" + projectId.textContent + "/phase",
        data: JSON.stringify({ phase: changePhase.value }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            console.log(response);
            console.log("Phase Updated Successfully...");
            const toast = createToast("Phase Updated Successfully...")
            const btoawe = new bootstrap.Toast(toast)
            document.querySelector("#toasts-noti-container").appendChild(toast)
            btoawe.show();
        },
        error: function (error) {
            console.log("Error in Url : /api/client/save ", error);
        },
    });

})