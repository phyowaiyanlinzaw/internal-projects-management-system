
$(document).ready(function () {
    setupLogoutConfirmation();
});

import { calculateDuration, calculateEndDate, formatDateFromMilliseconds, MONTH_NAMES, } from "/js/time.js";

import { getData } from "/js/api-function.js";

import { getArchitecturelist, getClientList, getDeliverableTypeList, } from "/js/getDataForProject.js";

import { getUser } from "/js/currentLoginUser.js";

const languageList = {
    "c#": "/images/languages/cshift.png",
    "c++": "/images/languages/c++-min.png",
    html: "/images/languages/html-min.png",
    java: "/images/languages/java-min.png",
    js: "/images/languages/js-min.png",
    php: "/images/languages/php-min.png",
    python: "/images/languages/python-min.png",
    ruby: "/images/languages/ruby-min.png",
    sql: "/images/languages/SQL-min.png",
    css: "/images/languages/css-min.png",
    typescript: "/images/languages/typescript-min.png",
};

console.log("await getUser ", await getUser());

const loginUser = await getUser();

if (loginUser.currentUser.role === "PROJECT_MANAGER") {
    const addDepartmentButton = document.querySelector(
        `button[data-bs-target="#add-department-modal"]`
    );
    if (addDepartmentButton) {
        addDepartmentButton.classList.remove("d-none");
    }
    const updateButtonContainer = document.getElementById("updateReview");
    updateButtonContainer.innerHTML =
        '<button id="updateButton" type="button" class="btn btn-primary">Update</button>';
}

const clientList = await getClientList();

console.log(clientList);

const clientSelect = document.querySelector("select[id='client']");

clientList.forEach((client) => {
    const option = document.createElement("option");
    option.value = JSON.stringify(client);
    option.textContent = client.name;

    clientSelect.append(option);
});

function initializeTagify(input, whiteList) {
    return new Tagify(input, {
        whitelist: whiteList.map((item) => item.tech_name),
        dropdown: {
            consoleLog: true,
            classname: "tags-look",
            enabled: 0,
            closeOnSelect: false,
        },
    });
}

let deliTypeList = await getDeliverableTypeList();

let deliTagfy;

getDeliverableTypeList().then((deliType) => {
    const deliTypeSelect = document.querySelector("#deliverable");
    console.log(deliType);
    deliTagfy = new Tagify(deliTypeSelect, {
        whitelist: deliType.map((item) => item.type),
        dropdown: {
            consoleLog: true,
            classname: "tags-look",
            enabled: 0,
            closeOnSelect: false,
        },
    });

    deliTagfy
        .on("input", (b) => {
            $("#deliverable").siblings(".error-container").addClass("d-none");
            deliTagfy.DOM.scope.classList.add("border", "border-success", "is-valid");
        })
        .on("add", (b) => {
            deliTagfy.DOM.scope.classList.add("border", "border-success", "is-valid");
            $("#deliverable").siblings(".error-container").addClass("d-none");
        });
});

let arcccList = await getArchitecturelist();


let tagifyInstance;

getArchitecturelist()
    .then((architectureList) => {
        const inputElement = document.querySelector('input[name="architectures"]');
        tagifyInstance = initializeTagify(inputElement, architectureList);

        // Set custom texts for Tagify validation messages
        tagifyInstance.settings.texts = {
            duplicate: "Duplicates are not allowed",
            empty: "Architecture is required",
        };

        tagifyInstance
            .on("input", (a) => {
                $("#architectures").siblings(".error-container").addClass("d-none");
                tagifyInstance.DOM.scope.classList.add(
                    "border",
                    "border-success",
                    "is-valid"
                );
            })
            .on("add", (a) => {
                tagifyInstance.DOM.scope.classList.add(
                    "border",
                    "border-success",
                    "is-valid"
                );
                $("#architectures").siblings(".error-container").addClass("d-none");
            });

        // Implement custom validation using the validate setting
        tagifyInstance.settings.validate = function (tagData) {
            if (tagData.value.trim() === "") {
                return tagifyInstance.TEXTS.empty;
            }

            const isDuplicate = tagifyInstance.value.some(
                (tag) => tag.value === tagData.value
            );
            if (isDuplicate) {
                return tagifyInstance.TEXTS.duplicate;
            }

            return true;
        };

        // Validate on input change
        inputElement.addEventListener("change", function () {
            validatePrjArchitecture();
        });
    })
    .catch((error) => {
        console.log("Error:", error);
    });

let validatedProjectForm = false;

function validatePrjArchitecture() {
    const architectures = $("#architectures").val();
    const errorContainer = $("#architectures").siblings(".error-container");

    if (!architectures || architectures.trim().length === 0) {

        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text("Architecture is required")
                .css("margin", "0")
                .addClass("error-message");

            $("#architectures").after('<div class="error-container"></div>');
            $("#architectures").siblings(".error-container").append(p);
        }

        $("#architectures").addClass("is-invalid");
        $("#architectures").removeClass("is-valid");
        return false;
    } else {

        errorContainer.remove();
        $("#architectures").addClass("is-valid");
        $("#architectures").removeClass("is-invalid");
        return true;
    }
}

// document.addEventListener('DOMContentLoaded', async () => {
//     try {
//         const arcccList = await getArchitecturelist();
//         const inputElement = document.querySelector('input[name="architectures"]');
//
//         // Check if Tagify is defined and initialize it
//         if (typeof Tagify !== 'undefined') {
//             const tagifyInstance = new Tagify(inputElement, {
//                 whitelist: arcccList.map(item => item.value), // Assuming arcccList is an array of objects with a 'value' property
//                 enforceWhitelist: true,
//                 duplicates: false,
//                 originalInputValueFormat: valuesArr => valuesArr.map(item => item.value),
//             });
//
//             // Set custom texts for validation messages
//             tagifyInstance.settings.templates = {
//                 dropdownItem: item => item.value,
//             };
//
//             // Implement custom validation using the validate setting
//             tagifyInstance.settings.validate = function(tagData) {
//                 if (tagData.value.trim() === "") {
//                     return "Architecture is required";
//                 }
//
//                 const isDuplicate = tagifyInstance.value.some(tag => tag.value === tagData.value);
//                 if (isDuplicate) {
//                     return "Duplicates are not allowed";
//                 }
//
//                 return true;
//             };
//
//             // Rest of your code remains unchanged
//             // ...
//         } else {
//             console.error("Tagify is not defined. Please make sure the library is loaded.");
//         }
//     } catch (error) {
//         console.log("Error:", error);
//     }
// });
//

// document.addEventListener('DOMContentLoaded', async () => {
//     try {
//         const inputElement = document.querySelector('input[name="architectures"]');
//
//         inputElement.addEventListener('input', function() {
//             const archHtml = inputElement.value.trim();
//
//             // Check if the input is not empty
//             if (archHtml === "") {
//                 alert("Architecture is required");
//             } else {
//                 console.log("Input value:", archHtml);
//             }
//         });
//
//         $("#validation-form").submit(function (event) {
//             event.preventDefault();
//
//             // Implement your form submission logic here
//             // For example, you can fetch the input value
//             const archHtml = inputElement.value.trim();
//
//             // Perform any further processing or validation
//
//             // Log the value or perform other actions
//             console.log("Input value on form submit:", archHtml);
//         });
//     } catch (error) {
//         console.log("Error:", error);
//     }
// });

// document.addEventListener('DOMContentLoaded', async () => {
//     try {
//         const arcccList = await getArchitecturelist();
//         const inputElement = document.querySelector('input[name="architectures"]');
//         const tagifyInstance = initializeTagify(inputElement, arcccList);
//
//         const selectElement = document.querySelector('select[name="yourSelectName"]'); // Replace with the actual name of your select element
//
//         function isTagifyEmpty(tagifyInstance) {
//             return tagifyInstance.value.length === 0;
//         }
//
//         function isSelectEmpty(selectElement) {
//             return selectElement.value === ""; // Adjust this check based on your select element's requirements
//         }
//
//         $("#validation-form").submit(function (event) {
//             if (isTagifyEmpty(tagifyInstance) && isSelectEmpty(selectElement)) {
//                 event.preventDefault();
//                 alert("Please fill in at least one field.");
//                 return;
//             }
//
//             // Handle the architectures input
//             const archHtml = inputElement.value;
//
//             if (archHtml.trim() !== "") {
//                 try {
//                     const arch = JSON.parse(archHtml);
//
//                     if (Array.isArray(arch) && arch.length !== 0) {
//                         const whitelist = tagifyInstance.settings.whitelist;
//                         arch.forEach((item) => {
//                             if (!whitelist.includes(item.value)) {
//                                 whitelist.push(item.value);
//                             }
//                         });
//                     } else {
//                         event.preventDefault();
//                         alert("Invalid input for Architectures. Please provide a valid JSON array.");
//                     }
//                 } catch (error) {
//                     event.preventDefault();
//                     alert("Error parsing JSON input for Architectures. Please provide a valid JSON array.");
//                     console.error("JSON Parsing Error:", error);
//                 }
//             }
//
//             // Handle the select input
//             if (isSelectEmpty(selectElement)) {
//                 event.preventDefault();
//                 alert("Please select a value for your select field.");
//             }
//
//             console.log(tagifyInstance.settings.whitelist);
//             console.log("Selected value from the select:", selectElement.value);
//         });
//     } catch (error) {
//         console.log("Error:", error);
//     }
// });

function initializeTagifyAndValidation() {
    let validatedProjectForm = false;

    // Your Tagify initialization code...

    // Validate on input change
    inputElement.addEventListener("change", function () {
        validatePrjArchitecture();
    });
}

document.addEventListener("DOMContentLoaded", async () => {
    try {
        const arcccList = await getArchitecturelist();
        const inputElement = document.querySelector('input[name="architectures"]');
        initializeTagifyAndValidation(arcccList, inputElement);
    } catch (error) {
        console.error("Error:", error);
    }
});



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

// Validation For Client

import * as ValidateClient from '/js/ClientValidation.js'

$("#client-name").on("input", function () {
    ValidateClient.validateName();
});

$("#client-email").on("input", function () {
    ValidateClient.validateEmail();
});

$("#client-phone").on("input", function () {
    ValidateClient.validatePhone();
});

function clearClientValidationStylesAndMessages(fieldSelector) {
    $(fieldSelector).removeClass("is-valid is-invalid");
    $(fieldSelector).siblings(".error-container").remove();
}

document.querySelector("#client-form").addEventListener("submit", function (e) {
    e.preventDefault();

    let name = document.querySelector("#client-name").value;
    let email = document.querySelector("#client-email").value;
    let phone = document.querySelector("#client-phone").value;

    if (!ValidateClient.validateName()) return;
    if (!ValidateClient.validateEmail()) return;
    if (!ValidateClient.validatePhone()) return;

    Swal.fire({
        title: "Are you sure want to add?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes",
    }).then((result) => {
        if (result.isConfirmed) {
            const clientDto = {
                name: name,
                email: email,
                phone: phone,
            };

            console.log(clientDto);

            $.ajax({
                method: "POST",
                url: "/api/client/save",
                data: JSON.stringify(clientDto),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (response) {
                    console.log(response);
                    const option = document.createElement("option");
                    option.value = JSON.stringify(response);
                    option.textContent = response.name;
                    clientSelect.append(option);
                    option.selected = true;
                    document.querySelector("#client-name").value = "";
                    document.querySelector("#client-email").value = "";
                    document.querySelector("#client-phone").value = "";
                    $('alert-modal')
                    $("#alert-text").text(
                        "Client updated successfully"
                    );


                    clearClientValidationStylesAndMessages("#client-name");
                    clearClientValidationStylesAndMessages("#client-email");
                    clearClientValidationStylesAndMessages("#client-phone");
                    $("#alert-modal").modal("show");

                    //go back to the #project-create tab
                    $("[data-bs-target='#project-create']").tab("show");

                },
                error: function (error) {
                    console.error("Error in Url : /api/client/save ", error);
                },
            });
        }
    });
});

const editProjectObjectiveEitor = document.querySelector("#edit-project-objective");

const editObjectiveEditor = new Quill(editProjectObjectiveEitor, {
    modules: {
        toolbar: toolbaroptions,
    },
    theme: "snow",
});

async function getAllProjects() {
    switch (loginUser.currentUser.role) {
        case "PROJECT_MANAGER":
            console.log("project manager");
            return await getData(
                `/api/project/new/list/role/${loginUser.currentUser.role}/id/${loginUser.currentUser.id}`
            );
        case "DEPARTMENT_HEAD":
            console.log("department head");
            return await getData(
                `/api/project/new/list/role/${loginUser.currentUser.role}/id/${loginUser.currentUser.id}`
            );
        case "EMPLOYEE":
        case "CONTRACT":
        case "FOC":
            console.log("one of the memeber");
            return await getData(
                `/api/project/new/list/role/${loginUser.currentUser.role}/id/${loginUser.currentUser.id}`
            );
        default:
            console.log("pmo or sdqc");
            return await getData(
                `/api/project/new/list/role/${loginUser.currentUser.role}/id/${loginUser.currentUser.id}`
            );
    }
}

const motherContainer = document.querySelector("#sort-container");

const fragment = document.createDocumentFragment();

let pList = await getAllProjects();

if (pList.length == 0) {
    document.querySelector('#no-result').classList.remove('d-none')
}

const searchBtn = document.querySelector('#search-project');

searchBtn.addEventListener('input', function () {

    const inputText = this.value;

    let searchProjectList = []

    for (let i = 0; i < pList.length; i++) {
        let proectTitle = pList[i].projectName;
        let users = pList[i].user.name;
        if (isMatch(inputText.trim(), proectTitle, users)) {
            searchProjectList.push(pList[i]);
        }
    }
    if (searchProjectList.length === 0) {
        document.querySelector('#no-result').classList.remove('d-none')
    } else {
        document.querySelector('#no-result').classList.add('d-none')
    }

    $('#pagination-container').pagination({
        dataSource: function (done) {
            const projects = []

            for (let i = searchProjectList.length - 1; i >= 0; i--) {
                projects.push(searchProjectList[i]);
            }

            done(projects);
        },
        callback: function (data, pagination) {
            // template method of yourself
            var html = template(data);

            $('#sort-container').html(html);
        },
        pageSize: 5, // Number of items per page
        className: 'paginationjs-theme-blue',
    });

})

function isMatch(input, title, card) {
    // Escape special characters in the input
    const escapedInput = input.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');

    // Create a regular expression pattern
    const pattern = new RegExp(`^.*${escapedInput.split('').join('.*')}.*$`, 'i');

    // Check if the input matches either the title or card
    return pattern.test(title) || pattern.test(card);
}


function template(a) {
    const container = document.createElement('div');

    for (let i = 0; i < a.length; i++) {
        const projectElement = createProjectList(a[i]);
        container.appendChild(projectElement);
    }

    return container.innerHTML;
}

$('#pagination-container').pagination({
    dataSource: function (done) {
        const projects = []

        for (let i = pList.length - 1; i >= 0; i--) {
            projects.push(pList[i]);
        }

        done(projects);
    },
    callback: function (data, pagination) {
        // template method of yourself
        var html = template(data);

        $('#sort-container').html(html);
    },
    pageSize: 8, // Number of items per page
    className: 'paginationjs-theme-blue  ',
});

let departmentProjectId;

if (loginUser.currentUser.role == "PMO" || loginUser.currentUser.role == "SDQC") {
    departmentProjectId = await getData("/api/project/list/for/pmo-and-sdqc");
}

if (departmentProjectId) {
    console.log("proejct id mapped by department id", departmentProjectId);
}

console.log(pList);

const dPList = await getData("/api/department/list");

dPList.forEach((item) => {
    const option = document.createElement("option");
    option.value = item.id;
    option.textContent = item.name;
    document.getElementById("department-filter").appendChild(option);
});

function updateSpanValues(amountDto) {
    // Define a mapping between keys in amountDto and the corresponding span element IDs
    const keyToSpanId = {
        basic_design: "basic-design-count",
        detail_design: "dd",
        coding: "ing",
        unit_testing: "ut",
        integrated_testing: "it",
    };

    // Iterate through amountDto and update the span elements accordingly
    for (const key in amountDto) {
        if (amountDto.hasOwnProperty(key)) {
            const value = amountDto[key];
            const spanId = keyToSpanId[key];

            if (spanId) {
                const spanElement = document.getElementById(spanId);
                if (spanElement) {
                    spanElement.innerText = value;
                }
            }
        }
    }
}

function updateListItems(a) {
    const list = document.querySelector(".list-group");
    const items = list.querySelectorAll(".list-group-item");

    // Define a mapping between keys in systemOutLineDto and the corresponding list item index
    const keyToIndex = {
        analysis: 0,
        sys_design: 1,
        coding: 2,
        testing: 3,
        deploy: 4,
        maintenance: 5,
        document: 6,
    };

    // Iterate through systemOutLineDto and update the list items accordingly
    for (const key in a) {
        if (a.hasOwnProperty(key)) {
            const value = a[key];
            const index = keyToIndex[key];

            if (index !== undefined && items[index]) {
                if (value) {
                    // Add the <i> element with the checkmark
                    items[
                        index
                    ].innerHTML = `${key}<i class="bi bi-check2-all text-success fs-4"></i>`;
                } else {
                    // Remove the <i> element
                    items[index].innerHTML = key;
                }
            }
        }
    }
}

function createProjectList(project) {
    function formatDate(a) {
        const date = new Date(a);
        return `${date.getDate()} / ${date.getMonth() + 1} / ${date.getFullYear()}`;
    }

    function getBadgeColor(status) {
        switch (status) {
            case "TODO":
                return "primary";
            case "IN_PROGRESS":
                return "info";
            case "FINISHED":
                return "success"; // default to secondary if status is not recognized
        }
    }
    console.log("project percentage", project.percentage);
    const taskListHtml = project.tasks
        ? project.tasks
            .map(
                (task) => `
        <div class="d-flex ">
            <div class="col p-1 text-start ps-3">
                <span class="badge bg-${getBadgeColor(task.status)}">${task.status
                    }</span>
            </div>
            <div class="col p-1 text-center">
                ${task.title}
            </div>
            <div class="col p-1 text-center">
                ${formatDate(task.planStartTime)}
            </div>
            <div class="col p-1 text-center">
                ${formatDate(task.planEndTime)}
            </div>
            <div class="col p-1 text-center">${task.userDto.name}</div>

        </div>
        `
            )
            .join("")
        : "";

    const newProject = document.createElement("div");
    newProject.classList.add("accordion", "container-fluid", 'project-card', 'p-0');
    newProject.id = `outer-project-${project.id}`;

    newProject.innerHTML += `
        <div style="cursor:pointer;" class="row flex-grow-1 d-flex align-items-center col-rols-5 border border-${project.closed ? "danger" : "dark"
        }" id="project-${project.id
        }" data-bs-toggle="modal" data-bs-target="#project-details">
        <div class="col ps-2 pt-2 pe-2 pb-2">
            <div class="progress bg-light border border-${project.closed ? "danger" : "primary"
        }">
            <div
                class="progress-bar progress-bar-striped progress-bar-animated bg-${project.closed ? "danger" : "primary"
        }"
            role="progressbar"
            style="width: ${project.percentage}%"
            aria-label="Basic example"
            aria-valuenow="${project.percentage}"
            aria-valuemin="0"
            aria-valuemax="100"
                                        >${project.percentage}%</div>
    </div>
</div>
    <div class="col p-0 text-center card-title">${project.projectName
        }</div>
    <div class="col p-0 text-center">
        ${formatDate(project.startDate)}
    </div>
    <div class="col p-0 text-center">
        ${formatDate(project.endDate)}
    </div>
    <div class="col p-0 text-center card-text">
        ${project.user.name}
    </div>

                            </div >
                            <div
                                id="collapse-project-${project.id}"
                                class="accordion-collapse collapse border border-top-0 border-dark pb-1"
                                data-bs-parent="#project-${project.id}"
                            >
                                <div
                                    class="text-center text-white fs-5 bg-primary"
                                >
                                    Task List
                                </div>
                                ${taskListHtml}
                            </div>
                            <div
                            class="d-flex justify-content-end rounded-1 text-white gap-2"
                            >${loginUser.currentUser.role === 'SDQC' || loginUser.currentUser.role === 'PMO' ? `<input type="checkbox" value="export-project-${project.id}" class="form-check-input"/>` : ''}
                            
                                <i class="fa-solid fa-angle-down bg-primary p-1 rounded-bottom" data-bs-toggle="collapse"
                            data-bs-target="#collapse-project-${project.id}"
                            aria-expanded="true"
                            aria-controls="collapse-project-${project.id}"
                            style="cursor: pointer;"></i>
                            
                            </div>`;

    return newProject;
}

const detailMoal = document.querySelector("#project-details");

function formmatDateFromMillisecondForEdit(milliseconds) {
    const date = new Date(milliseconds);

    // Get the year, month, and day from the Date object
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are 0-indexed, so add 1
    const day = String(date.getDate()).padStart(2, "0");

    // Create the formatted date string
    const formattedDate = year + "-" + month + "-" + day;

    return formattedDate;
}

function createToast(a) {
    const toast = document.createElement('div')

    toast.classList = 'toast show bg-success text-white'
    toast.setAttribute('role', 'alert')
    toast.setAttribute('aria-live', 'assertive')
    toast.setAttribute('aria-atomic', 'true')

    toast.innerHTML = `<div class="toast-body">
                <strong class="text-white">${a}</strong>
            </div>`
    return toast
}

motherContainer.addEventListener("click", async function (e) {
    let target = e.target;

    console.log(target.getAttribute("data-bs-target"));

    console.log(target);

    const closestProjectDiv = target.closest('[data-bs-target="#project-details"]');

    if (closestProjectDiv) {
        console.log(closestProjectDiv.getAttribute("data-bs-target"));

        let id = parseInt(closestProjectDiv.getAttribute("id").split("-").pop());
        let targetData = pList.find((a) => a.id == id);

        console.log("target id", id);

        console.log("target id", id);

        const currentProjectDetail = await getData(`/api/project/list/${id}`);

        console.log("current project detail", currentProjectDetail);

        const showDeliContainer = document.querySelector('#show-project-deliverable')

        showDeliContainer.innerHTML = ''

        currentProjectDetail.deliverableDto.forEach(a => {

            console.log(a)

            showDeliContainer.innerHTML += `
    <li
class="list-group-item d-flex justify-content-between"
    >
    ${a.deliverableType.type} <i
        class="bi fw-bold bi-check2-all text-${a.status ? 'success' : 'danger'} fs-4"
    ></i>
                            </li> `

        })

        const showAmountContainer = document.querySelector('#show-project-amount')

        showAmountContainer.innerHTML = ''

        for (const key in currentProjectDetail.amountDto) {
            if (key === 'id') continue
            if (currentProjectDetail.amountDto.hasOwnProperty(key)) {
                const value = currentProjectDetail.amountDto[key];
                showAmountContainer.innerHTML += `
    <li
class="list-group-item d-flex justify-content-between"
    >
    ${key.replace(/_/g, ' ')} <span class="badge bg-primary rounded-pill">${value}</span>
                            </li> `
            }
        }

        if (loginUser.currentUser.role === "PROJECT_MANAGER") {
            document
                .querySelector("#updateButton")
                .addEventListener("click", function () {
                    const internalReviewCount = document.getElementById(
                        "project-internal-review-count"
                    ).value;
                    const externalReviewCount = document.getElementById(
                        "project-external-review-count"
                    ).value;
                    const data = {
                        internal_review_count: internalReviewCount,
                        external_review_count: externalReviewCount,
                    };
                    Swal.fire({
                        title: "Are you sure want to edit?",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Yes",
                    }).then((result) => {
                        if (result.isConfirmed) {
                            fetch(
                                "/api/review/update-review/" + currentProjectDetail.reviewDto.id,
                                {
                                    method: "PUT",
                                    headers: {
                                        "Content-Type": "application/json",
                                    },
                                    body: JSON.stringify(data),
                                }
                            )
                                .then((response) => response.json())
                                .then((data) => {
                                    console.log(
                                        "Review counts updated successfully:",
                                        data
                                    );
                                    $("#alert-text").text(
                                        "Review counts updated successfully"
                                    );
                                    $("#alert-modal").modal("show");
                                })
                                .catch((error) => {
                                    console.error(
                                        "Error updating review counts:",
                                        error
                                    );
                                });
                        }
                    });
                });
        }

        if (loginUser.currentUser.role === "PROJECT_MANAGER") {
            document.querySelector("#project-detail-background").innerText =
                currentProjectDetail.background;

            autoResize(document.querySelector('#project-detail-background'));

            document.querySelector("#project-detail-name").value =
                currentProjectDetail.name;

            document.querySelector("#current-edditing-projetc-id").value =
                currentProjectDetail.id;

            document.querySelector("#project-edit-duration").value =
                currentProjectDetail.duration;

            document.querySelector("#start-date").value =
                formmatDateFromMillisecondForEdit(currentProjectDetail.start_date);

            document.querySelector("#end-date-in-edit").value =
                formatDateFromMilliseconds(currentProjectDetail.end_date);

            detailMoal.querySelector("#edit-basic-design-count").value =
                currentProjectDetail.amountDto.basic_design;

            detailMoal.querySelector("#edit-detail-design-count").value =
                currentProjectDetail.amountDto.detail_design;

            detailMoal.querySelector("#edit-coding-count").value =
                currentProjectDetail.amountDto.coding;

            detailMoal.querySelector("#edit-unit-testing-count").value =
                currentProjectDetail.amountDto.unit_testing;

            detailMoal.querySelector("#edit-integrated-testing-count").value =
                currentProjectDetail.amountDto.integrated_testing;
        }

        document.querySelector("#architectures-list-in-edit").textContent = "";

        currentProjectDetail.architectureDto.forEach((a) => {
            const div = document.createElement("div");

            div.classList.add(
                'rounded',
                'bg-light',
                'p-1'
            );

            div.style.cursor = 'pointer'

            const innerDiv = document.createElement('div')
            innerDiv.className = 'd-flex justify-content-center align-items-center flex-column text-primary'

            const image = document.createElement("img");

            image.style.width = "40px";
            image.loading = "lazy";

            if (languageList[a.tech_name]) {
                image.setAttribute("src", languageList[a.tech_name]);
            } else {
                // Handle the case where the language is not found in the map
                image.setAttribute("src", "/images/languages/default-min.png");
            }

            innerDiv.appendChild(image);

            const p = document.createElement("p");
            p.textContent = a.tech_name;
            p.classList.add("fs-6", "fw-bold", 'm-0');
            innerDiv.appendChild(p);

            div.appendChild(innerDiv);

            document.querySelector("#architectures-list-in-edit").appendChild(div);
        });

        //------------------------ architecture list end

        //------------------------ deliverable list start
        if (document.querySelector("#project-edit")) {

            detailMoal.querySelector("#project-internal-review-count").value =
                currentProjectDetail.reviewDto.internal_review_count;
            detailMoal.querySelector("#project-external-review-count").value =
                currentProjectDetail.reviewDto.external_review_count;
            let count = 1
            document.querySelector("#edit-deliverable-list").textContent = "";
            let deliverableId;
            currentProjectDetail.deliverableDto.forEach((a) => {
                const outerDiv = document.createElement("div");
                outerDiv.className = "d-flex justify-content-start";

                const p = document.createElement('p')
                p.textContent = count++
                outerDiv.appendChild(p)

                // Create the inner div for the text
                const typeDiv = document.createElement("div");
                typeDiv.textContent = a.deliverableType.type;
                typeDiv.classList.add('flex-grow-1', 'text-center')
                outerDiv.appendChild(typeDiv);

                const inputDiv = document.createElement("div");
                const input = document.createElement("input");
                input.type = "checkbox";
                input.checked = a.status;
                input.id = `deliveralbe - ${a.id} `;
                input.setAttribute("data-toggle", "toggle");
                inputDiv.appendChild(input);
                outerDiv.appendChild(inputDiv);

                document.querySelector("#edit-deliverable-list").appendChild(outerDiv);
            });
        }

        $('input[data-toggle="toggle"]').bootstrapToggle({
            on: "Yes",
            off: "No",
            onstyle: "success",
            size: "sm",
        });

        if (loginUser.currentUser.role !== "PROJECT_MANAGER") {
            $('input[data-toggle="toggle"]').bootstrapToggle("disable");
        }

        $('input[data-toggle="toggle"]').change(function () {
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
                    const toast = createToast("Deliverable Updated Successfully...")
                    const btoawe = new bootstrap.Toast(toast)
                    document.querySelector("#toasts-noti-container").appendChild(toast)
                    btoawe.show();
                },
            });
        });

        const objectiveQuill = new Quill("#show-project-objective", {
            modules: {
                toolbar: false,
            },
            theme: "snow",
        });

        console.log(loginUser.currentUser.role);

        if (
            loginUser.currentUser.role !== "SDQC" &&
            loginUser.currentUser.role !== "PMO" &&
            loginUser.currentUser.role !== "DEPARTMENT_HEAD"
        ) {
            document.querySelector(
                "#navigate-task"
            ).href = `/project/${currentProjectDetail.id}`;
            document.querySelectorAll('.optional-view').forEach(a => {
                console.log(a)
                a.style.display = 'none'
            })
        }

        objectiveQuill.enable(false);

        objectiveQuill.root.innerHTML = "";

        objectiveQuill.clipboard.dangerouslyPasteHTML(
            0,
            currentProjectDetail.objective
        );

        const phaseOptionList = document.querySelectorAll("#edit-project-phase option");

        console.log(phaseOptionList);

        phaseOptionList.forEach((a) => {
            if (a.value === currentProjectDetail.current_phase) {
                a.selected = true;
            }
        });

        editObjectiveEditor.root.innerHTML = "";

        editObjectiveEditor.clipboard.dangerouslyPasteHTML(
            0,
            currentProjectDetail.objective
        );

        console.log(currentProjectDetail);

        updateListItems(currentProjectDetail.systemOutLineDto);

        updateSpanValues(currentProjectDetail.amountDto);

        let duration = calculateDuration(
            currentProjectDetail.start_date,
            currentProjectDetail.end_date
        );

        detailMoal.querySelector("#project-background").innerText =
            currentProjectDetail.background;
            
        detailMoal.querySelector("#project-name").innerText = currentProjectDetail.name;
        detailMoal.querySelector("#project-department-name").innerHTML =
            currentProjectDetail.departmentDto.name;
        //______________________________________________

        //_______________review update

        //____________________________________reveiw update end
        detailMoal.querySelector("#project-duration").innerText = `${currentProjectDetail.duration
            } ${currentProjectDetail.duration <= 1 ? " month" : " months"} `;
        detailMoal.querySelector("#project-start-date").innerText =
            formatDateFromMilliseconds(currentProjectDetail.start_date);
        detailMoal.querySelector("#project-end-date").innerText =
            formatDateFromMilliseconds(currentProjectDetail.end_date);
        detailMoal.querySelector("#project-current-phase").innerText =
            currentProjectDetail.current_phase;
        detailMoal.querySelector("#project-current-external-review-count").innerText =
            currentProjectDetail.reviewDto.external_review_count;
        detailMoal.querySelector("#project-current-internal-review-count").innerText =
            currentProjectDetail.reviewDto.internal_review_count;
        detailMoal.querySelector("#client-email-address").innerHTML =
            currentProjectDetail.clientDto.email;
        detailMoal.querySelector("#client-phone-contect").innerHTML =
            currentProjectDetail.clientDto.phone;
        detailMoal.querySelector("#my-dear-customer-name").innerHTML =
            currentProjectDetail.clientDto.name;
    }

    //_____________________________________________
});

const editProjectObjective = document.querySelector("#edit-project-objective");

document
    .querySelector("#project-detail-edit-form")
    .addEventListener("submit", function (e) {
        e.preventDefault();
        Swal.fire({
            title: "Are you sure want to edit?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes",
        }).then((result) => {
            if (result.isConfirmed) {
                const project = {
                    id: parseInt(
                        document.querySelector("#current-edditing-projetc-id").value
                    ),
                    name: document.querySelector("#project-detail-name").value,
                    duration: parseInt(
                        document.querySelector("#project-edit-duration").value
                    ),
                    start_date: new Date(
                        document.querySelector("#start-date").value
                    ).getTime(),
                    background: document.querySelector("#project-detail-background")
                        .value,
                    end_date: new Date(
                        document.querySelector("#end-date-in-edit").value
                    ).getTime(),
                    objective: editObjectiveEditor.root.innerHTML,
                };

                console.log(project);

                $.ajax({
                    url: `/api/project/update`, // Replace with the actual URL for your project registration endpoint
                    type: "PUT",
                    data: JSON.stringify(project),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: async function (data) {
                        // Reset the form if needed
                        console.log("this projce has benn saved", data);
                        $("#alert-text").text("Project Updated Successfully...");
                        $("#alert-modal").modal("show");
                        // event.currentTarget.submit();
                    },
                    error: function (data) {
                        alert("Error registering project");
                        // event.currentTarget.submit();
                    },
                });
            }
        });
    });

if (loginUser.currentUser.role === "PROJECT_MANAGER") {
    const updateButtonC = document.getElementById("updateAmount");
    updateButtonC.innerHTML =
        '<button id="update" type="button" class="btn btn-primary">Update</button>';

    document.querySelector("#update").addEventListener("click", function (e) {
        const projectId = parseInt(
            document.querySelector("#current-edditing-projetc-id").value
        );

        const amountDto = {
            basic_design: parseInt(
                document.querySelector("#edit-basic-design-count").value
            ),
            detail_design: parseInt(
                document.querySelector("#edit-detail-design-count").value
            ),
            coding: parseInt(document.querySelector("#edit-coding-count").value),
            unit_testing: parseInt(
                document.querySelector("#edit-unit-testing-count").value
            ),
            integrated_testing: parseInt(
                document.querySelector("#edit-integrated-testing-count").value
            ),
        };
        Swal.fire({
            title: "Are you sure want to edit?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes",
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: `/api/project/update/amount/${projectId}`, // Replace with the actual URL for your project registration endpoint
                    type: "PUT",
                    data: JSON.stringify(amountDto),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: async function (data) {
                        // Reset the form if needed
                        $("#alert-modal").modal("show");
                        $("#alert-text").text("Amount Updated Successfully...");
                    },
                    error: function (data) {
                        alert("Error registering project");
                    },
                });
            }
        });
    });
}

const sortProjectByDepartment = await getData("/api/project/list/sort/by/department");

document.getElementById("department-filter").addEventListener("change", function () {

    const selectedDepartmentId = this.value;
    console.log(sortProjectByDepartment);
    console.log(selectedDepartmentId);
    console.log(sortProjectByDepartment[parseInt(selectedDepartmentId)]);

    let dpFilterResult = []

    if (sortProjectByDepartment.hasOwnProperty(parseInt(selectedDepartmentId))) {
        for (let i = 0; i < pList.length; i++) {
            if (
                sortProjectByDepartment[selectedDepartmentId].includes(
                    parseInt(pList[i].id)
                )
            ) {
                dpFilterResult.push(pList[i]);
            }
        }
    }

    $('#pagination-container').pagination({
        dataSource: function (done) {
            const projects = []

            for (let i = dpFilterResult.length - 1; i >= 0; i--) {
                projects.push(dpFilterResult[i]);
            }

            done(projects);
        },
        callback: function (data, pagination) {
            // template method of yourself
            var html = template(data);

            $('#sort-container').html(html);
        },
        pageSize: 8, // Number of items per page
        className: 'paginationjs-theme-blue  ',
    });

    if (dpFilterResult.length === 0) {
        document.querySelector('#no-result').classList.remove('d-none')
    } else {
        document.querySelector('#no-result').classList.add('d-none')
    }

});

const monthFilter = document.querySelector("#month-filter");
const yearFilterSelect = document.getElementById("year-filter");

document.querySelectorAll("select[action='filter']").forEach((a) =>
    a.addEventListener("input", function (e) {

        const currentTime = monthFilter.value;
        const yearValue = parseInt(yearFilterSelect.value);

        const matchingProjects = pList.filter((a) => {
            const projectDate = new Date(a.startDate);
            console.log(projectDate)
            const projectMonth = projectDate.getMonth();
            console.log(projectMonth)
            const projectYear = projectDate.getFullYear();
            return (
                projectYear === yearValue && MONTH_NAMES[projectMonth] === currentTime
            );
        });

        if (matchingProjects.length === 0) {
            console.log('no result')
            console.log(document.querySelector('#no-result'))
            document.querySelector("#no-result").classList.remove("d-none")
        } else {
            console.log('some result')
            document.querySelector('#no-result').classList.add('d-none')
        }
        $('#pagination-container').pagination({
            dataSource: async function (done) {
                const projects = []

                for (let i = matchingProjects.length - 1; i >= 0; i--) {
                    projects.push(matchingProjects[i]);
                }

                done(projects);
            },
            callback: function (data, pagination) {
                // template method of yourself
                var html = template(data);

                $('#sort-container').html(html);
            },
            pageSize: 8, // Number of items per page
            className: 'paginationjs-theme-blue  ',
        });



        console.log(matchingProjects)
    })
);


document.querySelector("#reset-btn").addEventListener("click", function (e) {

    monthFilter.value = MONTH_NAMES[0];

    $('#pagination-container').pagination({
        dataSource: function (done) {
            const projects = []

            for (let i = pList.length - 1; i >= 0; i--) {
                projects.push(pList[i]);
            }

            done(projects);
        },
        callback: function (data, pagination) {
            // template method of yourself
            var html = template(data);

            $('#sort-container').html(html);
        },
        pageSize: 8, // Number of items per page
        className: 'paginationjs-theme-blue  ',
    });

    document.querySelector('#no-result').classList.add('d-none')
});

console.log("error");

let quillEditors = document.querySelectorAll(".quill-editor-bubble");

// Initialize Quill for each selected element
quillEditors.forEach(function (editor) {
    new Quill(editor, {
        modules: {
            toolbar: toolbaroptions,
        },
        theme: "snow",
    });
});

// function getData() {
//     const userInput = quill.root.innerHTML;
//     console.log(userInput);
// }

let minusButtons = document.querySelectorAll(".minus");
let plusButtons = document.querySelectorAll(".plus");

minusButtons.forEach(function (minusButton) {
    minusButton.addEventListener("click", function () {
        let input = this.parentElement.querySelector("input");
        let count = parseInt(input.value) - 1;
        count = count < 0 ? 0 : count;
        input.value = count;
        console.log(input, count);
        //input.dispatchEvent(new Event('change'));
        return false;
    });
});

plusButtons.forEach(function (plusButton) {
    plusButton.addEventListener("click", function () {
        let input = this.parentElement.querySelector("input");
        input.value = parseInt(input.value) + 1;
        //input.dispatchEvent(new Event('change'));
        return false;
    });
});

let empTagifyList = [];

empTagifyList = VirtualSelect.init({
    ele: "#employee-list",
    multiple: true,
    search: true,
    searchByStartsWith: true,
    popupPosition: null,
    maxWidth: "100%",
});

if (loginUser.currentUser.role === "PROJECT_MANAGER") {

    $("#start_date").datepicker({
        dateFormat: 'yy-mm-dd',
        beforeShowDay: $.datepicker.noWeekends,
        onSelect: function (dateText, inst) {

            const projectDruation = document.querySelector("#project-add-duration").value;

            const startDate = $('#start_date').val();

            const projectEndDate = document.querySelector("#project-add-end-date");

            console.log(new Date(this.value))

            const endDate = calculateEndDate(
                new Date(startDate).getTime(),
                projectDruation
            );

            projectEndDate.value = formatDateFromMilliseconds(endDate.getTime());

        }

    });

    document
        .querySelector("#add-department-modal")
        .addEventListener("show.bs.modal", async function () {
            let employeeList;
            employeeList = await getData("/api/currentuser/available/employees");

            if (employeeList) {
                const showEmpList = employeeList.map((item) => {
                    console.log(item);
                    return {
                        label: item.name,
                        value: item.id,
                        alias: item.role.toLowerCase(),
                    };
                });
                console.log("showEmpList : ", showEmpList);
                document.querySelector("#employee-list").setOptions(showEmpList);
                console.log(empTagifyList);
                console.log(showEmpList)
            }
        });
}

if (loginUser.currentUser.role === "SDQC" || loginUser.currentUser.role === "PMO") {
    document.querySelector("#department-filter-container").classList.remove("d-none");
}

$(document).ready(function () {
    document.querySelector("#start_date").addEventListener("input", function () {
        const projectDruation = document.querySelector("#project-add-duration").value;

        const projectEndDate = document.querySelector("#project-add-end-date");

        console.log(new Date(this.value))

        const endDate = calculateEndDate(
            new Date(this.value).getTime(),
            projectDruation
        );

        projectEndDate.value = formatDateFromMilliseconds(endDate.getTime());
    });

    if (loginUser.currentUser.role === "PROJECT_MANAGER") {
        document.querySelector("#start-date").addEventListener("change", function () {
            const projectDruation =
                document.querySelector("#project-edit-duration").value;

            const projectEndDate = document.querySelector("#end-date-in-edit");

            const endDate = calculateEndDate(
                new Date(this.value).getTime(),
                projectDruation
            );

            projectEndDate.value = formatDateFromMilliseconds(endDate.getTime());
        });

        document
            .querySelector("#project-add-duration")
            .addEventListener("input", function () {
                const projectStartDate = document.querySelector("#start_date").value;

                if (projectStartDate !== "") {
                    const projectEndDate =
                        document.querySelector("#project-add-end-date");

                    // Calculate the end date only when there's a valid start date
                    const endDate = calculateEndDate(
                        new Date(projectStartDate).getTime(),
                        this.value
                    );

                    // Check if endDate is not NaN
                    if (!isNaN(endDate)) {
                        projectEndDate.value = formatDateFromMilliseconds(endDate);
                    }
                }
            });

        document
            .querySelector("#project-edit-duration")
            .addEventListener("input", function () {
                const projectStartDate = document.querySelector("#start-date").value;

                if (projectStartDate !== "") {
                    const projectEndDate = document.querySelector("#end-date-in-edit");

                    // Calculate the end date only when there's a valid start date
                    const endDate = calculateEndDate(
                        new Date(projectStartDate).getTime(),
                        this.value
                    );

                    // Check if endDate is not NaN
                    if (!isNaN(endDate)) {
                        projectEndDate.value = formatDateFromMilliseconds(endDate);
                    }
                }
            });
    }

    // Validation for project
    let validatedProjectForm = false;

    console.log("validation eeee");
    function validateProjectName() {
        const prjName = $("#name").val();
        const errorContainer = $("#name").siblings(".error-container");

        if (prjName.trim().length < 1) {

            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("Project Name is required")
                    .css("margin", "0");
                $("#name").after('<div class="error-container"></div>');
                $("#name").siblings(".error-container").append(p);
            }
            $("#name").addClass("is-invalid");
            $("#name").removeClass("is-valid");
            return false;
        } else {
            errorContainer.remove();
            $("#name").addClass("is-valid");
            $("#name").removeClass("is-invalid");
            return true;
        }
        console.log("project name validaiont : ", validatedProjectForm);
    }

    function validateProjectClient() {
        const prjclient = $("#client").val();
        const errorContainer = $("#client").siblings(".error-container");
        if (!prjclient || prjclient.length === 0) {

            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("Client is required")
                    .css("margin", "0")
                    .addClass("error-message");

                $("#client").after('<div class="error-container"></div>');
                $("#client").siblings(".error-container").append(p);
            }

            $("#client").addClass("is-invalid");
            $("#client").removeClass("is-valid");
            return false;
        } else {
            errorContainer.remove();
            $("#client").addClass("is-valid");
            $("#client").removeClass("is-invalid");
            return true;
        }
        console.log("project client validaiont : ", validatedProjectForm);
    }



    function validateProjectEmployee() {
        const empToAddProject = $("#employee-list").val();  // Assuming empTagifyList is your tagify instance
        const errorContainer = $("#employee-list").siblings(".error-container");

        if (!empToAddProject || empToAddProject.length === 0) {
            if (errorContainer.length === 0) {
                // Create the error container if it doesn't exist
                const errorMessage = $("<p>")
                    .addClass("text-danger fs-6 error-message")
                    // .text("Employee list is required")
                    .css("margin", "0");

                $("#employee-list").after('<div class="error-container"></div>');
                $("#employee-list").siblings(".error-container").append(errorMessage);


            } else {
                validatedProjectForm = false;
                // Update the error message if the container already exists
                errorContainer.removeClass("d-none");
                // errorContainer.find(".error-message").text("Employee list is required");
            }

            $("#employee-list").css("border", "1px solid #dc3545");
            return false;
        } else {
            errorContainer.remove();
            $("#employee-list").css("border", "1px solid #28a745");
            return true;
        }
    }




    function validatePrjDuration() {
        const prjduration = $("#project-add-duration").val();
        const errorContainer = $("#project-add-duration").siblings(".error-container");
        if (prjduration.length < 1) {

            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("End duration is required")
                    .css("margin", "0")
                    .addClass("error-message");

                $("#project-add-duration").after('<div class="error-container"></div>');
                $("#project-add-duration").siblings(".error-container").append(p);
            }

            $("#project-add-duration").addClass("is-invalid");
            $("#project-add-duration").removeClass("is-valid");
            return false;
        } else {
            errorContainer.remove();
            $("#project-add-duration").addClass("is-valid");
            $("#project-add-duration").removeClass("is-invalid");
            return true;
        }
    }

    function validatePrjBackground() {
        const background = $("#background").val();
        const errorContainer = $("#background").siblings(".error-container");
        if (background.length < 1) {
            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("Background is required")
                    .css("margin", "0")
                    .addClass("error-message");

                $("#background").after('<div class="error-container"></div>');
                $("#background").siblings(".error-container").append(p);
            }

            $("#background").addClass("is-invalid");
            $("#background").removeClass("is-valid");
            return false;
        } else {
            errorContainer.remove();
            $("#background").addClass("is-valid");
            $("#background").removeClass("is-invalid");
            return true;
        }
        console.log("project background validaiont : ", validatedProjectForm);
    }


    function validatePrjSystemOutline() {
        const systemOutlines = $("#systemOutlines").val()
        const errorContainer = $("#systemOutlines").siblings(".error-container");

        if (systemOutlines.length === 0) {
            if (errorContainer.length === 0) {
                // Create the error container if it doesn't exist
                const errorMessage = $("<p>")
                    .addClass("text-danger fs-6 error-message")
                    .text("System Outlines is required")
                    .css("margin", "0");

                $("#systemOutlines").after('<div class="error-container"></div>');
                $("#systemOutlines").siblings(".error-container").append(errorMessage);
            } else {
                // Update the error message if the container already exists
                errorContainer.removeClass("d-none");
                errorContainer.find(".error-message").text("System Outlines is required");
            }

            $("#systemOutlines").css("border", "1px solid #dc3545");
            return false; // Red border color for invalid state
        } else {
            errorContainer.remove();
            $("#systemOutlines").css("border", "1px solid #28a745");
            return true; // Green border color for valid state
        }
    }

    console.log("project system outline validaiont : ", validatedProjectForm);
    function validatePrjObjective() {

        const objective = $("#objective").val();
        const errorContainer = $("#objective").siblings(".error-container");

        if (!objective || objective.trim().length === 0) {
            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("Objective is required")
                    .css("margin", "0")
                    .addClass("error-message");

                $("#objective").after('<div class="error-container"></div>');
                $("#objective").siblings(".error-container").append(p);
            }

            $("#objective").addClass("is-invalid");
            $("#objective").removeClass("is-valid");
            return false;
        } else {
            errorContainer.remove();
            $("#objective").addClass("is-valid");
            $("#objective").removeClass("is-invalid");
            return true;

        }
    }

    function validateStartDate() {
        const start_date = $("#start_date").val();
        const errorContainer = $("#start_date ").siblings(".error-container");
        if (start_date.length === 0) {

            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("Date is required")
                    .css("margin", "0")
                    .addClass("error-message");

                $("#start_date ").after('<div class="error-container"></div>');
                $("#start_date ").siblings(".error-container").append(p);
            }

            $("#start_date ").addClass("is-invalid");
            $("#start_date ").removeClass("is-valid");
            return false;
        } else {
            errorContainer.remove();
            $("#start_date").addClass("is-valid");
            $("#start_date").removeClass("is-invalid");
            return true;

        }
    }

    let projectObjectiveEditor;

    function initializeQuillEditor() {
        projectObjectiveEditor = new Quill(".quill-editor-full", {
            modules: {
                toolbar: toolbaroptions,
            },
            theme: "snow",
        });
    }

    function validatePrjObjective() {
        if (!projectObjectiveEditor) {
            console.error("Quill editor not initialized");
            return;
        }

        const description = projectObjectiveEditor.getText().trim();
        const errorContainer = $(".quill-editor-full").siblings(".error-container");

        if (!description || description.length === 0) {

            if (errorContainer.length === 0) {
                let p = $("<p>")
                    .addClass("text-danger fs-6")
                    .text("Objective is required")
                    .css("margin", "0")
                    .addClass("error-message");

                $(".quill-editor-full")
                    .after('<div class="error-container"></div>')
                    .siblings(".error-container")
                    .append(p);
            } else {
                errorContainer.show(); // Show the error container
            }

            $(".quill-editor-full").addClass("is-invalid");
            $(".quill-editor-full").removeClass("is-valid");
            return false;
        } else {
            console.log("Objective success");

            if (errorContainer.length > 0) {
                errorContainer.hide(); // Hide the error container
            }

            $(".quill-editor-full").addClass("is-valid");
            $(".quill-editor-full").removeClass("is-invalid");
            return true;

        }
    }

    if (document.readyState === "complete" || document.readyState === "interactive") {
        initializeQuillEditor();
    } else {
        document.addEventListener("DOMContentLoaded", initializeQuillEditor);
    }

    let validationPerformed = false;

    $("#name").on("input", function () {
        validateProjectName();
    });

    $("#client").on("change", function () {
        validateProjectClient();
    });

    $("#employee-list").on("change", function () {
        validateProjectEmployee();
    });

    $("#start-date").on("input", function () {
        validateStartDate();
    });

    $("#project-add-duration").on("input", function () {
        validatePrjDuration();
    });

    $("#background").on("input", function () {
        validatePrjBackground();
    });

    $("#systemOutlines").on("change", function () {
        validatePrjSystemOutline();
    });

    $("#start_date").on("input", function () {
        validateStartDate();
    });

    $(".quill-editor-full ").on("input", function () {
        validatePrjObjective();
    });

    function validateProject() {

        return validatedProjectForm;
    }

    tagifyInstance
        .on("input", (a) => {
            $("#architectures").siblings(".error-container").addClass("d-none");
            tagifyInstance.DOM.scope.classList.add(
                "border",
                "border-success",
                "is-valid"
            );
        })
        .on("add", (a) => {
            tagifyInstance.DOM.scope.classList.add(
                "border",
                "border-success",
                "is-valid"
            );
            $("#architectures").siblings(".error-container").addClass("d-none");
        });

    deliTagfy
        .on("input", (b) => {
            $("#deliverable").siblings(".error-container").addClass("d-none");
            deliTagfy.DOM.scope.classList.add(
                "border",
                "border-success",
                "is-valid"
            );
        })
        .on("add", (b) => {
            deliTagfy.DOM.scope.classList.add(
                "border",
                "border-success",
                "is-valid"
            );
            $("#deliverable").siblings(".error-container").addClass("d-none");
        });

    $("#project-add-btn").on("click", function (event) {
        console.log("submitted");
        event.preventDefault();

        if (!validateProjectName()) return
        if (!validateProjectClient()) return
        if (!validateStartDate()) return
        if (!validatePrjDuration()) return
        if (!validatePrjBackground()) return

        let arcList;

        const architectures = document.querySelector("#architectures").value;

        if (architectures !== "") {
            try {
                const a = JSON.parse(architectures);
                // Continue processing parsedData
                arcList = a.map((ar) => {
                    const matchingObj = arcccList.find(
                        (arccObj) => arccObj.tech_name === ar.value
                    );
                    return matchingObj ? matchingObj : { tech_name: ar.value };
                });
            } catch (error) {
                console.log("Error parsing JSON:", error);
            }
        } else {
            tagifyInstance.DOM.scope.classList.add("is-invalid");

            $("#architectures").siblings(".error-container").removeClass("d-none");
            return;
        }

        if (!validatePrjSystemOutline()) return
        if (!validateProjectEmployee()) return

        const deliverable = $("#deliverable").val();

        let deliverableList;

        if (deliverable !== "") {
            const oldDeliTypeList = JSON.parse(deliverable);

            const deliverTypeList = oldDeliTypeList.map((deli) => {
                const matchingObj = deliTypeList.find(
                    (deliObj) => deliObj.type === deli.value
                );

                return matchingObj ? matchingObj : { type: deli.value };
            });

            deliverableList = deliverTypeList.map((deli) => {
                return {
                    status: false,
                    deliverableType: deli,
                };
            });
        } else {
            deliTagfy.DOM.scope.classList.add("is-invalid");
            console.warn("JSON data is empty.");
            $("#deliverable").siblings(".error-container").removeClass("d-none");
            return
        }

        if (!validatePrjObjective()) return

        console.log('passed all the validation')

        const projectName = $("#name").val();
        const client = JSON.parse($("#client").val());
        const startDate = new Date($("#start_date").val()).getTime();
        const duration = +$("#project-add-duration").val();

        const background = $("#background").val();

        const empList = empTagifyList.selectedValues;
        console.log("architectures list : ", architectures);
        const systemOutlines = sysSelect.selectedValues;

        const objective = projectObjectiveEditor.root.innerHTML; // Assuming you're using a WYSIWYG editor like Quill
        console.log(
            projectName,
            client,
            startDate,
            duration,
            background,
            architectures,
            empList,
            systemOutlines,
            deliverable,
            objective
        );

        const project = {
            name: projectName,
            clientDto: client,
            duration: duration,
            start_date: startDate,
            end_date: calculateEndDate(startDate, duration).getTime(),
            background: background,
            architectureDto: arcList,
            systemOutLineDto: systemOutlines.reduce((acc, cur) => {
                acc[cur] = true;
                return acc;
            }, {}),
            current_phase: "PLANNING",
            deliverableDto: deliverableList,
            objective: objective,
            amountDto: {
                basic_design: 0,
                detail_design: 0,
                coding: 0,
                unit_testing: 0,
                integrated_testing: 0,
            },
            departmentDto: {
                id: loginUser.currentUser.department.id,
            },
            projectManagerUserDto: {
                id: loginUser.currentUser.id,
            },
            membersUserDto: empList.map((str) => ({
                id: parseInt(str),
            })),
        };

        console.log(project);
        Swal.fire({
            title: "Are you sure want to add?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes",
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "/api/project/save",
                    type: "POST",
                    data: JSON.stringify(project),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: async function (data) {
                        $('#pagination-container').pagination('destroy');
                        $('#pagination-container').pagination({
                            dataSource: async function (done) {
                                const projects = await getAllProjects();
                                const reverseProject = [];

                                for (let i = projects.length - 1; i >= 0; i--) {
                                    reverseProject.push(projects[i]);
                                }

                                done(reverseProject);
                            },
                            callback: function (data, pagination) {
                                var html = template(data);
                                $('#sort-container').html(html);
                            },
                            pageSize: 8,
                            className: 'paginationjs-theme-blue  ',
                        });
                        $("#alert-text").text("New Project Created Successfully...");
                        $("#alert-modal").modal("show");
                        $("#add-department-modal").modal("hide");
                    },
                    error: function (data) {
                        alert("Error registering project");
                    },
                });
            }
        });
    });
});

let myOptions = [
    {
        label: "Analysis",
        value: "analysis",
        alias: "analysis",
    },
    {
        label: "System Design",
        value: "sys_design",
        alias: "system design",
    },
    {
        label: "Coding",
        value: "coding",
        alias: "coding",
    },
    {
        label: "Testing",
        value: "testing",
        alias: "testing",
    },
    {
        label: "Deploy",
        value: "deploy",
        alias: "deploy",
    },
    {
        label: "Maintenance",
        value: "maintenance",
        alias: "maintenance",
    },
    {
        label: "Document",
        value: "document",
        alias: "document",
    },
];

let sysSelect = VirtualSelect.init({
    ele: "#systemOutlines",
    options: myOptions,
    search: false,
    multiple: true,
    searchByStartsWith: false,
    popupPosition: null,
    maxWidth: "100%",
});

console.log("SYSSELECT", sysSelect);

$(".circle-progress").each(function () {
    const minValue = $(this).attr("data-min-value");
    const maxValue = $(this).attr("data-max-value");
    const value = $(this).attr("data-value");
    const type = $(this).attr("data-type");

    console.log("minValue : ", minValue);
    console.log("maxValue : ", maxValue);
    console.log("value : ", value);
    console.log("type : ", type);

    if (value < 40) {
        this.classList.add("circle-progress-secondary");
    } else if (value < 70) {
        this.classList.add("circle-progress-primary");
    } else if (value > 69) {
        this.classList.add("circle-progress-success");
    }

    if (this.id !== "" && this.id !== null) {
        new CircleProgress("#" + this.id, {
            min: minValue,
            max: maxValue,
            value: value,
            textFormat: type,
        });
    }
});

async function getAllProjectInfo() {

    return await getData('/api/project/list');

}

async function getAllSelectedProjectInfo() {

    const selectedProjectInput = document.querySelector("#sort-container").querySelectorAll("input[type='checkbox']:checked")

    const selectedProject = [];

    for (const checkbox of selectedProjectInput) {

        const id = checkbox.value.split("-").pop()

        try {
            const projectData = await getData(`/api/project/list/${id}`);
            selectedProject.push(projectData);
        } catch (error) {
            console.log(`Error fetching data for project with id ${id}: `, error);
        }

    }

    return selectedProject;
}

window.getAllSelectedProjectInfo = getAllSelectedProjectInfo // don't delete this 

window.getAllProjectInfo = getAllProjectInfo; // don't delete this

const exportPdfDropdown = document.querySelector("#exportPdfSelect");


if (exportPdfDropdown) {
    exportPdfDropdown.addEventListener("change", function () {
        const exportOption = exportPdfDropdown.value;
        if (exportOption === "all") {
            exportAllRows();
        } else if (exportOption === "selected") {

            exportSelectedRows();
        }
    });
}

// export all projects from getAllProjectInfo function with pdfMake
async function exportAllRows() {
    try {
        const allProjects = await getAllProjectInfo()
        const docDefinition = {
            pageOrientation: 'landscape',
            content: [
                {
                    text: 'Project List',
                    style: 'header'
                },
                {
                    style: 'tableExample',
                    table: {
                        widths: ['*', '*', '*', '*', '*', '*'],
                        body: [
                            ['Project Name', 'Client', 'Start Date', 'End Date', 'Duration', 'Status'],
                            ...allProjects.map(project => {
                                return [
                                    project.name,
                                    project.clientDto.name,
                                    formatDateFromMilliseconds(project.start_date),
                                    formatDateFromMilliseconds(project.end_date),
                                    project.duration,
                                    project.current_phase
                                ]
                            })
                        ]
                    }
                }
            ],
            styles: {
                header: {
                    fontSize: 18,
                    bold: true,
                    alignment: 'center',
                    margin: [0, 0, 0, 10]
                },
                tableExample: {
                    margin: [0, 5, 0, 15]
                },
                tableHeader: {
                    bold: true,
                    fontSize: 13,
                    color: 'black'
                }
            }
        };
        pdfMake.createPdf(docDefinition).open();
    } catch (error) {
        console.log(error);
    }
}

async function exportSelectedRows() {
    try {
        const selectedRows = await getAllSelectedProjectInfo()
        const docDefinition = {
            pageOrientation: 'landscape',
            content: [
                {
                    text: 'Project List',
                    style: 'header'
                },
                {
                    style: 'tableExample',
                    table: {
                        widths: ['*', '*', '*', '*', '*', '*'],
                        body: [
                            ['Project Name', 'Client', 'Start Date', 'End Date', 'Duration', 'Status'],
                            ...selectedRows.map(project => {
                                return [
                                    project.name,
                                    project.clientDto.name,
                                    formatDateFromMilliseconds(project.start_date),
                                    formatDateFromMilliseconds(project.end_date),
                                    project.duration,
                                    project.current_phase
                                ]
                            })
                        ]
                    }
                }
            ],
            styles: {
                header: {
                    fontSize: 18,
                    bold: true,
                    alignment: 'center',
                    margin: [0, 0, 0, 10]
                },
                tableExample: {
                    margin: [0, 5, 0, 15]
                },
                tableHeader: {
                    bold: true,
                    fontSize: 13,
                    color: 'black'
                }
            }
        };
        pdfMake.createPdf(docDefinition).open();
    } catch (error) {
        console.log(error);
    }
}

const exportExcelDropdown = document.querySelector("#exportExcelSelect");

if (exportExcelDropdown) {

    exportExcelDropdown.addEventListener("change", function () {
        const exportOption = exportExcelDropdown.value;
        if (exportOption === "all") {
            exportAllRowsExcel();
        } else if (exportOption === "selected") {
            exportSelectedRowsExcel();
        }
    });
}

async function exportAllRowsExcel() {
    try {
        const allProjects = await getAllProjectInfo()
        const data = [
            ['Project Name', 'Client', 'Start Date', 'End Date', 'Duration', 'Status'],
            ...allProjects.map(project => {
                return [
                    project.name,
                    project.clientDto.name,
                    formatDateFromMilliseconds(project.start_date),
                    formatDateFromMilliseconds(project.end_date),
                    project.duration,
                    project.current_phase
                ]
            })
        ];
        const ws = XLSX.utils.aoa_to_sheet(data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, "Project List");
        XLSX.writeFile(wb, "Project List.xlsx");
    } catch (error) {
        console.log(error);
    }
}

async function exportSelectedRowsExcel() {
    try {
        const selectedRows = await getAllSelectedProjectInfo()
        const data = [
            ['Project Name', 'Client', 'Start Date', 'End Date', 'Duration', 'Status'],
            ...selectedRows.map(project => {
                return [
                    project.name,
                    project.clientDto.name,
                    formatDateFromMilliseconds(project.start_date),
                    formatDateFromMilliseconds(project.end_date),
                    project.duration,
                    project.current_phase
                ]
            })
        ];
        const ws = XLSX.utils.aoa_to_sheet(data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, "Project List");
        XLSX.writeFile(wb, "Project List.xlsx");
    } catch (error) {
        console.log(error);
    }
}

//list project end
if (loginUser.currentUser.role !== "PROJECT_MANAGER") {
    $('button[data-bs-target="#project-edit"]').remove();
    $('button[data-bs-target="#amount"]').remove();
    $("#project-edit").remove();
    $("#amount").remove();
    $("#project-external-review-count").attr("disabled", true);
    $("#project-internal-review-count").attr("disabled", true);
    // hide the manage task button if the role is pmo or sdqc and
}
if (loginUser.currentUser.role === "SDQC" || loginUser.currentUser.role === "PMO" || loginUser.currentUser.role === 'DEPARTMENT_HEAD') {
    $("#navigate-task").remove();
}

