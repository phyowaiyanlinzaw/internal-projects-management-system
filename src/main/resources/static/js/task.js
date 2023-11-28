
import { getData, sendData } from "/js/api-function.js";
import { getTimeElapsed } from "/js/time.js";
import { getUser } from "/js/currentLoginUser.js";

const currentUser = await getUser(); // get the current login user 

const project = await getData("/api/project/list/" + document.getElementById("projectId").innerText); // get the current project data

// get the project close button
console.log(project)
const projectCloseBtn = document.querySelector('#project-open-close')

if (projectCloseBtn) {
    if (project.closed) {
        $('#project-open-close').bootstrapToggle('off')
    } else {
        $('#project-open-close').bootstrapToggle('on')
    }
}

//disable add task button if user is not project manager
if (currentUser.currentUser.role !== "PROJECT_MANAGER") {
    const addTaskBtn = document.getElementById("add-task-btn");
    addTaskBtn.disabled = true;

    // Add a class to style the button on hover
    addTaskBtn.classList.add("disabled-button");
    addTaskBtn.style.cursor = "not-allowed";
}

// get the available employee list when the tab is shown
const tabEl = document.querySelector('#add-employee-tab')
if (tabEl) {
    tabEl.addEventListener('show.bs.tab', async event => {
        const allWorkingEmployee = await getData('/api/user/list/projectId/' + document.getElementById("projectId").innerText)

        allWorkingEmployee.forEach(a => console.log(a))
    })
}

// when the add task modal is shown get the employee list and add the event listener to handle to add employee to the project
const addEmployeeModal = document.querySelector("#add-employee-modal")

addEmployeeModal.addEventListener("show.bs.modal", async () => {

    const empList = [];

    const empToProject = await getData('/api/user/list/available/pmId/' + currentUser.currentUser.id)

    const container = document.querySelector("#list-of-employee-to-add-in-project")
    container.innerHTML = ''
    empToProject.forEach(employee => {
        // Create a new div element for each employee
        const employeeDiv = document.createElement("div");
        employeeDiv.classList.add("row", "row-cols-3");

        // Create and append the child elements for employee name, role, and checkbox
        const nameDiv = document.createElement("div");
        nameDiv.textContent = employee.user.name;
        employeeDiv.appendChild(nameDiv);

        const roleDiv = document.createElement("div");
        roleDiv.textContent = employee.user.role; // Replace with the actual property name in your object
        employeeDiv.appendChild(roleDiv);

        const checkboxDiv = document.createElement("div");
        checkboxDiv.classList.add("text-end");

        const checkbox = document.createElement("input");
        checkbox.classList.add("pickme")
        checkbox.setAttribute("type", "checkbox");
        checkbox.setAttribute("data-toggle", "toggle");
        checkbox.setAttribute("value", employee.user.id);

        checkboxDiv.appendChild(checkbox);
        employeeDiv.appendChild(checkboxDiv);

        // Append the employeeDiv to the container
        container.appendChild(employeeDiv);
        $('.pickme').bootstrapToggle({
            on: 'dont pick me',
            off: 'pick me',
            onstyle: 'success',
            size: "sm"
        })
    });

    console.log(empList.length)

    if (empToProject.length === 0) {
        document.querySelector("#no-result-emp").classList.remove("d-none")
    } else {
        document.querySelector("#no-result-emp").classList.add("d-none")
    }

    $('.pickme').change(function () {
        const value = $(this).val();
        if ($(this).prop('checked')) {
            // Add the value if the checkbox is checked and not already in the array
            if (empList.indexOf(value) === -1) {
                empList.push(value);
            }
        } else {
            // Remove the value if the checkbox is unchecked
            const index = empList.indexOf(value);
            if (index !== -1) {
                empList.splice(index, 1);
            }
        }
        console.log(empList)
    })

    document.querySelector("#save-new-employee-list").addEventListener("click", function () {

        console.log(empList)

        console.log(empList.length)

        if (empList.length === 0) {
            return;
        }

        const empObjectList = empList.map(a => ({ id: parseInt(a) }))

        console.log(empObjectList)

        parseInt(document.getElementById("projectId").innerText)

        console.log(empObjectList.length)

        console.log(empObjectList)

        $.ajax({
            url: `/api/project/update/userlist/${document.getElementById("projectId").innerText}`,
            type: "PUT",
            data: JSON.stringify(empObjectList),
            contentType: "application/json",
            success: function (data) {
                console.log(data)
                console.log("this is good sign")
                $("#alert-text").text(
                    "New users assigned successfully to this project"
                );
                $('#alert-modal').modal('show');
                $("#add-employee-modal").modal('hide')
                empList.length = 0;

            }, error: function () {
                console.log("error")
            },
        });

    })

})

addEmployeeModal.addEventListener("hidden.bs.modal", function () {

    this.querySelector("#add-new-employee-to-project").innerText = "Select all"

})

// select all the employee to add to the project
document.querySelector("#add-new-employee-to-project").addEventListener('click', (e) => {

    console.log(e.target.innerText)

    if (e.target.innerText === 'Select all') {
        e.target.innerText = 'Deselect all'
        console.log($('.pickme').prop('checked'))
        $('.pickme').bootstrapToggle('on');

    } else {
        e.target.innerText = 'Select all'
        console.log($('.pickme').prop('checked'))
        $('.pickme').bootstrapToggle('off');

    }
})

// to handle the project close and open
$('#project-open-close').change(function () {

    let condition = $(this).prop('checked');
    console.log(condition)
    const id = parseInt(document.getElementById("projectId").innerText)

    if (!condition) {

        document.querySelector("#add-employee-list-button-container").classList.add("d-none")

        const taskInTodo = $("#TODO").children().find("*").length
        const taskInProgress = $("#IN_PROGRESS").children().find("*").length

        console.log(taskInTodo, taskInProgress)

        if (taskInTodo > 1 || taskInProgress > 1) {
            Swal.fire({
                title: "Are you sure want to close this project? there are still some tasks in progress or todo and all of them will be closed too!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Yes"
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        url: `/api/project/update/status/${id}/${!condition}`,
                        method: "GET",
                        success: function (response) {
                            // Handle the successful response here
                            console.log(response);
                        },
                        error: function (xhr, status, error) {
                            // Handle the error here
                            console.log(error);
                        }
                    });
                    document.querySelectorAll('.task').forEach(a => {
                        console.log(a)
                        a.removeAttribute("draggable")
                    })
                } else {
                    $('#project-open-close').bootstrapToggle('on')
                }
            })
        } else {
            $.ajax({
                url: `/api/project/update/status/${id}/${!condition}`,
                method: "GET",
                success: function (response) {
                    // Handle the successful response here
                    console.log(response);
                },
                error: function (xhr, status, error) {
                    // Handle the error here
                    console.log(error);
                }
            });
            document.querySelectorAll('.task').forEach(a => {
                console.log(a)
                a.removeAttribute("draggable")
            })
        }

    } else {

        document.querySelector("#add-employee-list-button-container").classList.remove("d-none")

        $.ajax({
            url: `/api/project/update/status/${id}/${!condition}`,
            method: "GET",
            success: function (response) {
                // Handle the successful response here
                console.log(response);
            },
            error: function (xhr, status, error) {
                // Handle the error here
                console.log(error);
            }
        });

        document.querySelectorAll('.task').forEach(a => {
            console.log(a)
            a.setAttribute("draggable", true)
        })
    }
});

// ?
var userId;

// get the project start date and end date


//initialize jqeury ui datepicker
$(

    function () {
        const projectStartDateElement = document.getElementById("projectStartDate");
        const projectEndDateElement = document.getElementById("projectEndDate");
        $("#start_date").datepicker({
            minDate: new Date(parseInt(projectStartDateElement.innerText)),
            maxDate: new Date(parseInt(projectEndDateElement.innerText)),
            dateFormat: 'yy-mm-dd',
            onSelect: function (dateText, inst) {


                const startDate = $('#start_date').val();
                const endDate = $("#end_date").val();

                const duration = calculateWeekdayDuration(new Date(startDate).getTime(), new Date(endDate).getTime())

                $("#plan-hours").val(duration * 7)

                validatePlanEndTime();

                validatePlanStartTime()
                if (dateText !== "") {
                    var dateObject = $(this).datepicker('getDate');
                    $("#end_date").datepicker('option', 'minDate', dateObject);

                }
            }
        });

        $("#pm-task-detail-start-date").datepicker({
            minDate: new Date(parseInt(projectStartDateElement.innerText)),
            maxDate: new Date(parseInt(projectEndDateElement.innerText)),
            dateFormat: 'yy-mm-dd',
        })

        $("#end_date").datepicker({
            maxDate: new Date(parseInt(projectEndDateElement.innerText)),
            dateFormat: 'yy-mm-dd',
        });

        $("#pm-task-detail-due-date").datepicker({
            minDate: new Date(parseInt(projectStartDateElement.innerText)),
            maxDate: new Date(parseInt(projectEndDateElement.innerText)),
            dateFormat: 'yy-mm-dd',
        })

    }
);

// Get the project ID from the hidden element
const projectId = document.getElementById("projectId").innerText;

// Function to fetch the members' list from the backend API
let membersList = [];

// fetch the member list from the backend on load 
$.ajax({
    url: "/api/user/list/projectId/" + projectId,
    type: "GET",
    async: false,
    success: function (data) {
        membersList = data;
    },
});

// ??
let originalTagify;

// update the employee list when the task add modal is show
document.querySelector("#add-task").addEventListener("show.bs.modal", function () {
    let thisSuck;
    $.ajax({
        url: "/api/user/list/projectId/" + projectId,
        type: "GET",
        async: false,
        success: function (data) {
            thisSuck = data;
        },
    });
    originalTagify.whitelist = null

    let newWhitelist = thisSuck.map((member) => {
        return { id: member.id, value: member.name + " | " + member.role };
    });

    console.log("this suck", thisSuck)
    console.log(originalTagify)
    originalTagify.settings.whitelist = newWhitelist;

})

let currentTaskId;

document.querySelector('#member-task-details').addEventListener("shown.bs.modal", async function () {

    currentTaskId = this.getAttribute("current-task-id")
    console.log("current target task id : ", currentTaskId);

    currentTask = await getData("/api/task/get/" + currentTaskId)

    console.log("current target task : ", currentTask);

    document.getElementById('member-task-title').innerText = currentTask.title
    document.getElementById("member-task-description").value = currentTask.description

    if (currentTask.status === 'TODO') {

        document.getElementById('member-task-status').innerText = "TODO"
        document.getElementById('member-task-status').classList.add('bg-primary')
    } else if (currentTask.status === 'IN_PROGRESS') {

        document.getElementById('member-task-status').innerText = "IN PROGRESS"
        document.getElementById('member-task-status').classList.add('bg-info')
    } else {

        document.getElementById('member-task-status').innerText = "FINISHED"
        document.getElementById('member-task-status').classList.add('bg-success')
    }


    document.getElementById("member-assigned-member-span").innerText = currentTask.userDto.name + " | " + currentTask.userDto.role

    if (currentTask.tasksGroup === 'A') {

        document.getElementById('member-task-group-span').innerText = "A - Related With Project Development"
    } else if (currentTask.tasksGroup === 'B') {

        document.getElementById('member-task-group-span').innerText = "B - Training"
    } else {

        document.getElementById('member-task-group-span').innerText = "C - Idling"
    }


    // Convert timestamp to Date objects
    const startDate = new Date(currentTask.plan_start_time);
    const endDate = new Date(currentTask.plan_end_time);

    // Get the date strings in 'yyyy-mm-dd' format
    const startDateString = startDate.toISOString().split('T')[0];
    const endDateString = endDate.toISOString().split('T')[0];

    document.getElementById("member-task-detail-start-date").value = startDateString
    document.getElementById("member-task-detail-due-date").value = endDateString


    document.getElementById('member-task-plan-hours').innerText = currentTask.plan_hours.toString() + " hours"

    const duration = calculateWeekdayDuration(startDate, endDate);
    document.getElementById("member-task-duration").innerText = duration.toString() + " days ";

    const assignedMemberSpan = document.getElementById('assigned-member-span')
    //to check if that element exists
    if (assignedMemberSpan) {
        // Check if the user has any of the specified roles
        if (currentTaskData.userDto.role === 'FOC' || currentTaskData.userDto.role === 'EMPLOYEE' || currentTaskData.userDto.role === 'CONTRACT' || currentTaskData.userDto.role === 'PMO' || currentTaskData.userDto.role === 'DEPARTMENT_HEAD' || currentTaskData.userDto.role === 'SDQC') {
            assignedMemberSpan.innerText = currentTaskData.userDto.name + " | " + currentTaskData.userDto.role
        }
    }


})

document.querySelector("#pm-task-details").addEventListener("shown.bs.modal", async function () {

    currentTaskId = this.getAttribute("current-task-id")
    console.log("current target task id : ", currentTaskId);

    currentTask = await getData("/api/task/get/" + currentTaskId)

    console.log("current target task : ", currentTask);


    //change input value from that taskdata
    document.getElementById('pm-task-title').innerText = currentTask.title
    document.getElementById('member-task-title').innerText = currentTask.title

    document.getElementById('pm-description-editor').value = currentTask.description
    document.getElementById("member-task-description").value = currentTask.description

    if (currentTask.status === 'TODO') {
        document.getElementById('pm-status').innerText = "TODO"
        document.getElementById('pm-status').classList.add('bg-primary')

        document.getElementById('member-task-status').innerText = "TODO"
        document.getElementById('member-task-status').classList.add('bg-primary')
    } else if (currentTask.status === 'IN_PROGRESS') {
        document.getElementById('pm-status').innerText = "IN PROGRESS"
        document.getElementById('pm-status').classList.add('bg-info')

        document.getElementById('member-task-status').innerText = "IN PROGRESS"
        document.getElementById('member-task-status').classList.add('bg-info')
    } else {
        document.getElementById('pm-status').innerText = "FINISHED"
        document.getElementById('pm-status').classList.add('bg-success')

        document.getElementById('member-task-status').innerText = "FINISHED"
        document.getElementById('member-task-status').classList.add('bg-success')
    }

    document.getElementById("pm-assigned-member-span").innerText = currentTask.userDto.name + " | " + currentTask.userDto.role
    document.getElementById("member-assigned-member-span").innerText = currentTask.userDto.name + " | " + currentTask.userDto.role

    if (currentTask.tasksGroup === 'A') {
        document.getElementById('pm-task-group-span').innerText = "A - Related With Project Development"

        document.getElementById('member-task-group-span').innerText = "A - Related With Project Development"
    } else if (currentTask.tasksGroup === 'B') {
        document.getElementById('pm-task-group-span').innerText = "B - Training"

        document.getElementById('member-task-group-span').innerText = "B - Training"
    } else {
        document.getElementById('pm-task-group-span').innerText = "C - Idling"

        document.getElementById('member-task-group-span').innerText = "C - Idling"
    }

    // Convert timestamp to Date objects
    const startDate = new Date(currentTask.planStartTime);
    const endDate = new Date(currentTask.planEndTime);

    // Get the date strings in 'yyyy-mm-dd' format
    const startDateString = startDate.toISOString().split('T')[0];
    const endDateString = endDate.toISOString().split('T')[0];

    // Set the values in the input fields
    document.getElementById('pm-task-detail-start-date').value = startDateString;
    document.getElementById('pm-task-detail-due-date').value = endDateString;

    document.getElementById("member-task-detail-start-date").value = startDateString
    document.getElementById("member-task-detail-due-date").value = endDateString

    document.getElementById('pm-plan-edit-hours').value = currentTask.planHours;
    document.getElementById('member-task-plan-hours').innerText = currentTask.planHours === null ? 0 : currentTask.planHours.toString() + " hours"

    const duration = calculateWeekdayDuration(startDate, endDate);
    document.getElementById("member-task-duration").innerText = duration.toString() + " days ";
    document.getElementById("pm-task-duration").innerText = duration.toString() + " days "

    document.getElementById("pm-actual-edit-hours").value = currentTask.actualHours === null ? 0 : currentTask.actualHours;

    // Get all elements with the common class name
    const editableInputs = document.querySelectorAll(".editable-input");
    const editBtn = document.getElementById("task-edit-btn");
    const cancelEditBtn = document.getElementById("cancel-edit-btn");

    const closeTaskModalBtn = document.querySelectorAll(".btn-close-task-details");


    cancelEditBtn.addEventListener("click", function (event) {

        //hide title
        document.getElementById("pm-task-title").classList.remove("d-none");
        //show input
        document.getElementById("pm-task-title-input").classList.add("d-none");

        //hide the two button
        saveBtn.classList.add("d-none");
        cancelEditBtn.classList.add("d-none");

        document.getElementById("pm-task-group-span").classList.remove("d-none")
        document.getElementById("pm-task-group").classList.add("d-none")

        document.getElementById("pm-assigned-member-tagify").classList.add("d-none")
        document.querySelector('.tagify.editable-input').classList.add("d-none")
        document.getElementById("pm-assigned-member-span").classList.remove("d-none")

        //show the edit button
        editBtn.classList.remove("d-none");

        // don't let the button close modal
        event.preventDefault();
        editableInputs.forEach((input) => {
            input.setAttribute("disabled", "");
        });

    });

    //loop through close task modal btns
    closeTaskModalBtn.forEach((btn) => {
        btn.addEventListener("click", (event) => {
            //hide title
            document.getElementById("pm-task-title").classList.remove("d-none");
            //show input
            document.getElementById("pm-task-title-input").classList.add("d-none");

            //hide the two buttons
            saveBtn.classList.add("d-none");
            cancelEditBtn.classList.add("d-none");

            document.getElementById("pm-task-group-span").classList.remove("d-none")
            document.getElementById("pm-task-group").classList.add("d-none")

            document.getElementById("pm-assigned-member-tagify").classList.add("d-none")
            document.getElementById("pm-assigned-member-span").classList.remove("d-none")

            //show the edit button
            editBtn.classList.remove("d-none");

            // disable editable inputs
            editableInputs.forEach((input) => {
                input.setAttribute("disabled", "");
            });
        });
    });

    const assignedMemberSpan = document.getElementById('assigned-member-span')
    //to check if that element exists
    if (assignedMemberSpan) {
        // Check if the user has any of the specified roles
        if (currentTaskData.userDto.role === 'FOC' || currentTaskData.userDto.role === 'EMPLOYEE' || currentTaskData.userDto.role === 'CONTRACT' || currentTaskData.userDto.role === 'PMO' || currentTaskData.userDto.role === 'DEPARTMENT_HEAD' || currentTaskData.userDto.role === 'SDQC') {
            assignedMemberSpan.innerText = currentTaskData.userDto.name + " | " + currentTaskData.userDto.role
        }
    }


})

const saveBtn = document.getElementById("save-edit-btn");

const assignedMemberTagify = document.getElementById("pm-assigned-member-tagify");

let tagify = new Tagify(assignedMemberTagify, {
    enforceWhitelist: true,
    mode: "select",
    whitelist: membersList.map((member) => {
        return { id: member.id, value: member.name + " | " + member.role };
    }),
    originalInputValueFormat: valuesArr => valuesArr.map(item => item.value).join(', ')
});

document.querySelector("#pm-task-details").addEventListener("show.bs.modal", function () {

    tagify.DOM.scope.classList.add('d-none');

    let thisSuck;
    $.ajax({
        url: "/api/user/list/projectId/" + projectId,
        type: "GET",
        async: false,
        success: function (data) {
            thisSuck = data;
        },
    });
    tagify.whitelist = null

    let newWhitelist = thisSuck.map((member) => {
        return { id: member.id, value: member.name + " | " + member.role };
    });

    console.log("this suck", thisSuck)
    console.log(tagify)
    tagify.settings.whitelist = newWhitelist;

});


document.getElementById("task-edit-btn").addEventListener("click", function () {

    document.querySelectorAll(".editable-input").forEach((input) => {
        input.removeAttribute("disabled");
    });

    //hide title
    document.getElementById("pm-task-title").classList.add("d-none");
    //show input
    document.getElementById("pm-task-title-input").classList.remove("d-none");
    //set input value to title
    document.getElementById("pm-task-title-input").value = document.getElementById("pm-task-title").innerText;

    document.getElementById("pm-task-group-span").classList.add("d-none")
    const taskGroupSelect = document.getElementById("pm-task-group");
    taskGroupSelect.classList.remove("d-none")
    taskGroupSelect.value = currentTask.tasksGroup;

    document.getElementById("pm-assigned-member-tagify").classList.remove("d-none")
    const tagifyEditableInput = document.querySelector('.tagify.editable-input');
    if (tagifyEditableInput) {
        document.querySelector('.tagify.editable-input').classList.remove("d-none")
    }
    document.getElementById("pm-assigned-member-span").classList.add("d-none")


    tagify = new Tagify(assignedMemberTagify, {
        enforceWhitelist: true,
        mode: "select",
        whitelist: membersList.map((member) => {
            return { id: member.id, value: member.name + " | " + member.role };
        }),
        originalInputValueFormat: valuesArr => valuesArr.map(item => item.value).join(', ')
    });

    const currentAssignedUser = { id: currentTask.userDto.id, value: currentTask.userDto.name + " | " + currentTask.userDto.role }

    tagify.addTags([currentAssignedUser])

    console.log(assignedMemberTagify)

    // Function to handle the change event
    function handleTagifyChange(e) {
        // Get the member id for the added/updated tag, don't return an array, only id please
        if (tagify.value.length !== 0) {
            let tagData = tagify.value[0].id

            console.log(tagify.value)

            // Update your output value or perform any other actions here
            userId = tagData;
            console.log("where this shit", userId)
        }
    }

    // Add the change event listener
    assignedMemberTagify.addEventListener('change', handleTagifyChange)

    //hide the edit button
    this.classList.add("d-none");

    //show the two button
    saveBtn.classList.remove("d-none");
    document.getElementById("cancel-edit-btn").classList.remove("d-none");

});

saveBtn.addEventListener("click", function (event) {

    document.getElementById("pm-assigned-member-tagify").classList.add("d-none")
    console.log("what the problem", currentTaskId)

    const title = document.getElementById("pm-task-title")
    const titleInput = document.getElementById("pm-task-title-input")
    const taskGroup = document.getElementById("pm-task-group")
    const taskGroupSpan = document.getElementById("pm-task-group-span")
    const assignedMemberSpan = document.getElementById("pm-assigned-member-span")
    const assignedMemberTagify = document.getElementById("pm-assigned-member-tagify")
    const description = document.getElementById("pm-description-editor")

    console.log($("#pm-task-title-input").val(),
        $("#pm-description-editor").val(),
        $("#pm-task-detail-start-date").val(),
        $("#pm-task-detail-due-date").val(),
        $("#pm-plan-edit-hours").val(),
        $("#pm-task-group").val(),
        $("#pm-actual-edit-hours").val(),
        userId)

    if ($("#pm-task-title-input").val() === "") {
        return
    }
    if ($("#pm-description-editor").val() === "") {
        return
    }
    if ($("#pm-task-detail-start-date").val() === "") {
        return
    }
    if ($("#pm-task-detail-due-date").val() === "") {
        return
    }
    if ($("#pm-plan-edit-hours").val() === "") {
        return
    }
    if ($("#pm-task-group").val() === "") {
        return
    }
    if ($("#pm-actual-edit-hours").val() === "") {
        return
    }
    if (!userId) {
        return
    }

    //hide title
    document.getElementById("pm-task-title").classList.remove("d-none");
    //show input
    document.getElementById("pm-task-title-input").classList.add("d-none");

    //hide the two button
    saveBtn.classList.add("d-none");
    document.getElementById("cancel-edit-btn").classList.add("d-none");

    document.getElementById("pm-task-group-span").classList.remove("d-none")
    document.getElementById("pm-task-group").classList.add("d-none")

    document.querySelector('.tagify.editable-input').classList.add("d-none")
    document.getElementById("pm-assigned-member-span").classList.remove("d-none")

    //show the edit button
    document.getElementById("task-edit-btn").classList.remove("d-none");

    document.querySelectorAll(".editable-input").forEach((input) => {
        input.setAttribute("disabled", "");
    });

    const startDate = new Date($("#pm-task-detail-start-date").val());
    const dueDate = new Date($("#pm-task-detail-due-date").val());

    if (startDate > dueDate || startDate === dueDate) {

        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Start date must be before due date!',
        })
        return;
    }

    const data = {
        id: currentTaskId,
        title: $("#pm-task-title-input").val(),
        description: $("#pm-description-editor").val(),
        // Convert to Unix timestamp
        planStartTime: startDate.getTime(),
        planEndTime: dueDate.getTime(),
        planHours: $("#pm-plan-edit-hours").val(),
        tasksGroup: $("#pm-task-group").val(),
        actualHours: parseInt($("#pm-actual-edit-hours").val()),
        userId: userId,
    };

    console.log("Request Data : ", data)

    $.ajax({
        url: "/api/task/update/data",
        data: JSON.stringify(data),
        method: 'POST',
        contentType: 'application/json', // Set the content type for the request payload
        success: function (response) {
            // close modal box
            console.log(response)
            console.log(response.userDto.id)
            $(`#task-${response.id} > .modal-detail-title`).text(response.title)
            $(`#assigned-member-span-${response.id}`).text(response.userDto.name)
            title.textContent = response.title
            titleInput.value = response.title
            taskGroupSpan.innerText = response.tasksGroup
            assignedMemberSpan.innerText = response.userDto.name + " | " + response.userDto.role
            assignedMemberTagify.value = response.userDto.name + " | " + response.userDto.role
            description.value = response.description
            document.getElementById('pm-task-detail-start-date').value = new Date(response.planStartTime).toISOString().split('T')[0];
            document.getElementById("pm-task-detail-due-date").value = new Date(response.planEndTime).toISOString().split('T')[0];
            const duration = calculateWeekdayDuration(startDate, dueDate);
            document.getElementById("pm-task-duration").innerText = duration.toString() + " days "
            document.getElementById("pm-plan-edit-hours").value = response.planHours
            document.getElementById("pm-actual-edit-hours").value = response.actualHours
            $('#task-details').modal('hide')
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // Handle error here
            console.error("Error:", textStatus, errorThrown);
        }
    });
});

// initialize tagify for add memeber to task
const memberInputTag = document.querySelector('#select-member')


originalTagify = new Tagify(memberInputTag, {
    enforceWhitelist: true,
    mode: "select",
})

console.log(originalTagify)

console.log("MEMBERS LIST", membersList);


// bind events
originalTagify.on('add', validateMember)
originalTagify.DOM.input.addEventListener('focus', onSelectFocus)

// when add value to the employee add to the tagify
// function onAddTag(e) {

//     console.log(e);
//     originalTagify.DOM.scope.classList.add('border', 'border-success', 'is-valid');

// }

// when focus to the employee add tagify
function onSelectFocus(e) {
    console.log(e)
}


const todoLane = document.getElementById("TODO");
const inProgress = document.getElementById("IN_PROGRESS");
const finished = document.getElementById("FINISHED");
let currentZone = null;
let currentTask;
let currentTaskData;
const status = ['TODO', 'IN_PROGRESS', 'FINISHED']
const modal = document.getElementById("task-details");

// function to create the task div
function createTaskDiv(task) {
    console.log(task)
    const taskDiv = document.createElement("div");
    taskDiv.setAttribute("id", `task-${task.id}`);
    taskDiv.classList.add(
        "task",
        "row",
        "rounded-2",
        "text-white"
    );
    if (!task.due) {
        switch (task.status) {
            case "TODO":
                taskDiv.classList.add("bg-primary");
                break;
            case "IN_PROGRESS":
                taskDiv.classList.add("bg-info");
                break;
            case "FINISHED":
                taskDiv.classList.add("bg-success");
                break;
        }
    } else {
        taskDiv.classList.add("bg-danger")
    }

    if (!project.closed) {
        taskDiv.draggable = true;
    }

    // Add a data attribute for modal triggering (if needed)
    taskDiv.setAttribute("data-bs-toggle", "modal");
    if (currentUser.currentUser.role === 'PROJECT_MANAGER') {
        taskDiv.setAttribute("data-bs-target", "#pm-task-details");
    } else {
        taskDiv.setAttribute("data-bs-target", "#member-task-details");
    }

    const titleDiv = document.createElement("div");
    titleDiv.classList.add(
        "col-12",
        "mb-2",
        "lh-1",
        "mh-50",
        "modal-detail-title",
        "text-break"
        , "fs-5"
    );
    titleDiv.textContent = task.title;

    const imgDiv = document.createElement("div");
    imgDiv.classList.add(
        "col",
        "d-flex",
        "align-items-center"
    );

    const img = document.createElement("img");
    img.src =
        "/images/avatars/employee_avatar.svg"; // Assuming you want to change the image based on task.id
    img.classList.add("img-fluid", "rounded-circle");
    img.alt = "";
    img.style.width = "30px";

    const name = document.createElement("span");
    name.id = `assigned-member-span-${task.id}`
    name.classList.add("border-0", "p-0", "bg-transparent")
    name.textContent = task.userDto.name;

    imgDiv.appendChild(img);
    imgDiv.appendChild(name);

    taskDiv.appendChild(titleDiv);
    taskDiv.appendChild(imgDiv);

    return taskDiv;
}

let taskList = [];

// Fetch the tasks from the backend API
if (
    currentUser.currentUser.role === "PROJECT_MANAGER"
    || currentUser.currentUser.role === "PMO"
    || currentUser.currentUser.role === "SDQC"
    || currentUser.currentUser.role === "DEPARTMENT_HEAD"
) {
    $.ajax({
        url: "/api/task/list/project/" + projectId,
        type: "GET",
        async: false,
        success: function (data) {
            taskList = data;
        },
    });
} else {
    $.ajax({
        url: "/api/task/list/project/" + projectId + "/user/" + currentUser.currentUser.id,
        type: "GET",
        async: false,
        success: function (data) {
            taskList = data;
        },
    });
}

function calculateWeekdayDuration(startDate, endDate) {
    const oneDay = 24 * 60 * 60 * 1000; // hours * minutes * seconds * milliseconds
    let totalWeekdays = 0;

    for (let currentDate = new Date(startDate); currentDate <= endDate; currentDate.setDate(currentDate.getDate() + 1)) {
        const currentDay = currentDate.getDay(); // 0 = Sunday, 1 = Monday, ..., 6 = Saturday
        if (currentDay !== 0 && currentDay !== 6) {
            // Exclude Sunday (0) and Saturday (6)
            totalWeekdays++;
        }
    }

    return totalWeekdays;
}

for (let i = 0; i < taskList.length; i++) {
    const task = taskList[i];

    const taskDiv = createTaskDiv(task);

    taskDiv.setAttribute("index", i);

    // Determine the status of the task and append it to the appropriate lane
    if (task.status === "TODO") {
        todoLane.appendChild(taskDiv);
    } else if (task.status === "IN_PROGRESS") {
        inProgress.appendChild(taskDiv);
    } else if (task.status === "FINISHED") {
        finished.appendChild(taskDiv);
    }
    taskDiv.addEventListener("dragstart", (e) => {
        taskDiv.classList.add("is-dragging");
        currentZone = taskDiv.parentElement;
        console.log("dragging");
        currentTask = taskDiv;
        console.log("CURRENT TASK ", currentTask)
    });

    //!!! problem might be here !!!
    taskDiv.addEventListener("click", async (e) => {

        // currentTaskData = taskList[parseInt(taskDiv.getAttribute('index'))]

        const taskId = taskDiv.getAttribute('id').split('-').pop();

        if (currentUser.currentUser.role === 'PROJECT_MANAGER') {
            document.querySelector("#pm-task-details").setAttribute("current-task-id", taskId)
        } else {
            document.querySelector("#member-task-details").setAttribute("current-task-id", taskId)
        }

    });

    document.getElementById('actual-hours-input').addEventListener("input", function () {

        console.log(this.value)

        console.log(validateActualWorkingHour(parseInt(this.value)))

        if (validateActualWorkingHour(parseInt(this.value))) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.add('is-invalid');
        }

    })

    function validateActualWorkingHour(x) {
        return !isNaN(x)
    }
    taskDiv.addEventListener("dragend", async () => {
        taskDiv.classList.remove("is-dragging");
        currentTaskData =
            taskList[
            parseInt(currentTask.getAttribute("index"))
            ];
        if (currentZone.classList.contains("dummy-trash")) {
            // bModal.show();

            Swal.fire({
                title: "Are you sure want to delete this task?",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Yes"
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        url: `/api/task/delete`,
                        type: "DELETE",
                        data: JSON.stringify(currentTaskData.id),
                        contentType: "application/json",
                        success: function (response) {
                            console.log(response);

                            document.querySelector('#task-' + currentTaskData.id).remove()
                        },
                        error: function (response) {
                            console.log(response);
                        },
                    });
                }
            })

        } else if (status.some(a => currentZone.getAttribute("id") === a)) {
            if (currentTaskData.status !== currentZone.getAttribute("id")) {
                currentTaskData.status = currentZone.getAttribute("id")

                if (currentTaskData.status === 'IN_PROGRESS') {
                    currentTaskData.actualStartTime = new Date().getTime()
                }

                if (currentTaskData.status === 'FINISHED') {
                    //show modal box to ask for actual hours input type=number
                    $('#actual-hours-input-modal').modal('show')
                    // Wrap the modal code in a Promise to wait for actual hours input
                    const actualHoursInputPromise = new Promise((resolve) => {
                        const actualHoursInput = document.getElementById('actual-hours-input');
                        const actualHoursSubmitBtn = document.getElementById('actual-hours-input-btn');

                        const clickHandler = function () {
                            currentTaskData.actualHours = actualHoursInput.value;
                            if (validateActualWorkingHour(parseInt(actualHoursInput.value))) {
                                $('#actual-hours-input-modal').modal('hide');
                                actualHoursInput.value = '';
                                actualHoursInput.classList.remove('is-valid');
                                actualHoursSubmitBtn.removeEventListener('click', clickHandler); // Remove the event listener
                                resolve();
                            } else {
                                actualHoursInput.classList.add('is-invalid');
                            }
                        };

                        actualHoursSubmitBtn.addEventListener('click', clickHandler);
                    });

                    // Wait for the Promise to be resolved before proceeding
                    await actualHoursInputPromise;

                    currentTaskData.actualEndTime = new Date().getTime()
                }

                $.ajax({
                    url: '/api/task/update/status',
                    method: 'POST',
                    data: {
                        id: currentTaskData.id,
                        status: currentTaskData.status,
                        actualStartTime: currentTaskData.actualStartTime,
                        actualEndTime: currentTaskData.actualEndTime,
                        actualHours: currentTaskData.actualHours === null ? 0 : currentTaskData.actualHours
                    },
                })
                    .then(response => {
                        currentTaskData.status = currentZone.getAttribute('id');
                        console.log('Success for URL /api/task/update', response);
                        //change the color of the task
                        if (!currentTaskData.due) {
                            switch (currentTaskData.status) {
                                case "TODO":
                                    currentTask.classList.add("bg-primary");
                                    currentTask.classList.remove(
                                        "bg-info",
                                        "bg-success"
                                    );
                                    break;
                                case "IN_PROGRESS":
                                    currentTask.classList.add("bg-info");
                                    currentTask.classList.remove(
                                        "bg-primary",
                                        "bg-success"
                                    );
                                    break;
                                case "FINISHED":
                                    currentTask.classList.add("bg-success");
                                    currentTask.classList.remove(
                                        "bg-primary",
                                        "bg-info"
                                    );
                                    break;
                            }
                        } else {
                            currentTask.classList.add("bg-danger");
                            currentTask.classList.remove(
                                "bg-primary",
                                "bg-info",
                                "bg-success"
                            );
                        }
                    })
                    .catch(error => {
                        console.error('Error for URL /api/task/update', error);
                    });
            }
        }

        taskDiv.classList.remove("is-dragging");
    });
}


// Example of how to use the ajaxRequest function to send data with a POST request

const todoForm = document.getElementById("todo-form");
const input = document.getElementById("todo-input");
const draggables = document.querySelectorAll(".task");
const droppables = document.querySelectorAll(".swim-lane");

const trashCanContainer = document.querySelector(
    ".trash-can-container"
);

todoForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const value = input.value;

    if (!value) return;

    let newTask = document.createElement("div");
    newTask.classList.add(
        "task",
        "py-1",
        "row",
        "rounded-2",
        "border-primary",
        "border-2",
        "border"
    );
    if (!project.closed) {
        newTask.setAttribute("draggable", "true");
    }
    newTask.setAttribute("data-bs-toggle", "modal");
    newTask.setAttribute("data-bs-target", "#task-details");

    // Create the inner content of the task
    newTask.innerHTML = `
                <div class="col-12 mb-2 lh-1 mh-50 modal-detail-title text-break">
                    ${value}
                </div>
                <div class="col d-flex justify-content-between align-items-center">
                    <img src="/images/avatars/employee_avatar.svg" class="img-fluid rounded-circle" alt="" style="width: 30px;">
                </div>
            `;

    newTask.addEventListener("dragstart", (e) => {
        newTask.classList.add("is-dragging");
        currentTask = newTask;
        console.log("dragging");
    });

    newTask.addEventListener("click", (e) => {
        modal.querySelector(".modal-title").innerText =
            newTask.innerText;
    });

    newTask.addEventListener("dragend", () => {
        newTask.classList.remove("is-dragging");
        console.log(newTask);
        if (currentZone.classList.contains("dummy-trash")) {
            Swal.fire({
                title: "Are you sure want to delete this task?",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Yes"
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        url: `/api/task/delete`,
                        type: "DELETE",
                        data: JSON.stringify(currentTaskData.id),
                        contentType: "application/json",
                        success: function (response) {
                            console.log(response);

                            document.querySelector('#task-' + currentTaskData.id).remove()
                        },
                        error: function (response) {
                            console.log(response);
                        },
                    });
                }
            })
        }
        task.classList.remove("is-dragging");
    });

    todoLane.appendChild(newTask);

    input.value = "";
});


const comfirmBox = document.querySelector("#confirm-box");
const bModal = new bootstrap.Modal(comfirmBox);


comfirmBox.addEventListener("click", function (e) {
    if (e.target.innerText.toLowerCase() === "yes") {
        currentTask.remove();

        document
            .querySelector(".trash")
            .classList.remove("drag-over");
        bModal.hide();
        console.log("this task will be deleted", currentTask);
        $.ajax({
            url: `/api/task/delete`,
            type: "DELETE",
            data: JSON.stringify(currentTaskData.id),
            contentType: "application/json",
            success: function (response) {
                console.log(response);
                location.reload();
            },
            error: function (response) {
                console.log(response);
            },
        });
    } else if (e.target.innerText.toLowerCase() === "no") {
        document
            .querySelector(".trash")
            .classList.remove("drag-over");
        bModal.hide();
    }
});

// ======================= TRASH BRN ======================

if (trashCanContainer != null) {
    console.log(trashCanContainer);
    trashCanContainer.addEventListener(
        "dragover",
        function (e) {
            e.preventDefault();
            currentZone = trashCanContainer;
            currentTask = document.querySelector(".is-dragging");
            if (currentTask === null) return;
            document
                .querySelector(".trash")
                .classList.add("drag-over");
        }
    );

    trashCanContainer.addEventListener(
        "dragleave",
        function (e) {
            document
                .querySelector(".trash")
                .classList.remove("drag-over");
        }
    );
}

// =========================== TRASH BIN =============================

// ========================== AJAX FUNCTION (JUST TESTING) ===========================

droppables.forEach((zone) => {
    zone.addEventListener("dragover", (e) => {
        e.preventDefault();
        currentZone = zone;

        const bottomTask = insertAboveTask(zone, e.clientY);
        const curTask = document.querySelector(".is-dragging");

        if (curTask === null) {
            return;
        }

        const curZone = zone.getAttribute("id");

        switch (curZone) {
            case "TODO":
                curTask.classList.add("border-primary");
                curTask.classList.remove(
                    "border-info",
                    "border-success"
                );
                break;
            case "IN_PROGRESS":
                curTask.classList.add("border-info");
                curTask.classList.remove(
                    "border-primary",
                    "border-success"
                );
                break;
            case "FINISHED":
                curTask.classList.add("border-success");
                curTask.classList.remove(
                    "border-primary",
                    "border-info"
                );
                break;
        }

        if (!bottomTask) {
            zone.appendChild(curTask);
        } else {
            zone.insertBefore(curTask, bottomTask);
        }
    });
});
const insertAboveTask = (zone, mouseY) => {
    const els = zone.querySelectorAll(
        ".task:not(.is-dragging)"
    );

    let closestTask = null;
    let closestOffset = Number.NEGATIVE_INFINITY;

    els.forEach((task) => {
        const { top } = task.getBoundingClientRect();

        const offset = mouseY - top;

        if (offset < 0 && offset > closestOffset) {
            closestOffset = offset;
            closestTask = task;
        }
    });

    return closestTask;
};

console.log("TASK LIST", taskList)


//ADD TASk
//call api/tasks/save when click add button and send data as JSON.stringify to api
//use jquery ajax

function validateTitle() {
    const title = $('#title').val();
    const errorContainer = $('#title').siblings('.error-container');
    if (title.length < 1) {
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "Title is required"
                )
                .css("margin", "0");
            $('#title').after('<div class="error-container"></div>');
            $('#title').siblings('.error-container').append(p);
        }

        $('#title').addClass('is-invalid');
        $('#title').removeClass('is-valid');
        return false;
    } else {

        errorContainer.remove();
        $('#title').addClass('is-valid');
        $('#title').removeClass('is-invalid');
        return true
    }
}

function validateDescription() {
    const description = $('#description').val();
    const errorContainer = $('#description').siblings('.error-container');
    if (description.length < 1) {
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "Description is required"
                )
                .css("margin", "0");
            $('#description').after('<div class="error-container"></div>');
            $('#description').siblings('.error-container').append(p);
        }

        $('#description').addClass('is-invalid');
        $('#description').removeClass('is-valid');
        return false;
    } else {
        errorContainer.remove();
        $('#description').addClass('is-valid');
        $('#description').removeClass('is-invalid');
        return true;
    }
}

function validatePlanStartTime() {
    const start_date = $('#start_date').val();
    const errorContainer = $('#start_date').siblings('.error-container');
    console.log(start_date)
    if (start_date === "") {

        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "Start Date is required"
                )
                .css("margin", "0");
            $('#start_date').after('<div class="error-container"></div>');
            $('#start_date').siblings('.error-container').append(p);
        }

        $('#start_date').addClass('is-invalid');
        $('#start_date').removeClass('is-valid');
        return false;
    } else {
        errorContainer.remove();
        $('#start_date').addClass('is-valid');
        $('#start_date').removeClass('is-invalid');
        return true;
    }
}



function validatePlanEndTime() {
    const end_date = $('#end_date').val();
    const errorContainer = $('#end_date').siblings('.error-container');
    if (end_date === "") {
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "End Date is required"
                )
                .css("margin", "0");
            $('#end_date').after('<div class="error-container"></div>');
            $('#end_date').siblings('.error-container').append(p);
        }

        $('#end_date').addClass('is-invalid');
        $('#end_date').removeClass('is-valid');
        return false;

    } else {
        errorContainer.remove();
        $('#end_date').addClass('is-valid');
        $('#end_date').removeClass('is-invalid');
        return true;
    }
}

function validatePlanHour() {
    const plan_hours = $('#plan-hours').val();
    const errorContainer = $('#plan-hours').siblings('.error-container');

    const startDate = document.querySelector("#start_date").value;

    const endDate = document.querySelector("#end_date").value;

    const duration = calculateWeekdayDuration(new Date(startDate).getTime(), new Date(endDate).getTime())

    const durationInHour = duration * 24;

    console.log(duration)

    console.log(plan_hours)

    console.log(plan_hours, durationInHour)

    if (plan_hours > durationInHour || plan_hours == '') {

        let text

        if (plan_hours === '' || isNaN(plan_hours)) {

            text = `put some number`

        } else {
            text = `plan hour is greater than ${durationInHour} hours`
        }

        console.log('plan hours is greater than duration', duration)

        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    text
                )
                .css("margin", "0");
            $('#plan-hours').after('<div class="error-container"></div>');
            $('#plan-hours').siblings('.error-container').append(p);
        }

        $('#plan-hours').addClass('is-invalid');
        $('#plan-hours').removeClass('is-valid');
        return false;
    } else {
        errorContainer.remove();
        $('#plan-hours').addClass('is-valid');
        $('#plan-hours').removeClass('is-invalid');
        return true;

    }
}



function validateMember(e) {
    const member = $('#select-member').val();
    const errorContainer = $('#select-member').siblings('.error-container');
    console.log(e)
    if (!e.detail.data) {
        console.log("member is required")
        originalTagify.DOM.scope.classList.add(
            "border",
            "border-danger",
            "is-invalid"
        );
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "Member is required"
                )
                .css("margin", "0");
            $('#select-member').after('<div class="error-container"></div>');
            $('#select-member').siblings('.error-container').append(p);
        }

        originalTagify.DOM.scope.classList.add('border', 'border-danger', 'is-invalid');
        originalTagify.DOM.scope.classList.remove('border', 'border-success', 'is-valid');
        return false;

    } else {
        errorContainer.remove();
        originalTagify.DOM.scope.classList.add('border', 'border-success', 'is-valid');
        originalTagify.DOM.scope.classList.remove('border', 'border-danger', 'is-invalid');
        return true;
    }
}

function validateTakGroup() {
    const group = $('#group').val();
    const errorContainer = $('#group').siblings('.error-container');
    if (!group || group.length < 1 || group === "") {
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "Group is required"
                )
                .css("margin", "0");
            $('#group').after('<div class="error-container"></div>');
            $('#group').siblings('.error-container').append(p);
        }

        $('#group').addClass('is-invalid');
        $('#group').removeClass('is-valid');
        return false;
    } else {
        errorContainer.remove();
        $('#group').addClass('is-valid');
        $('#group').removeClass('is-invalid');
        return true;

    }
}

function validateAddTask() {


    return validateTitle() &&
        validateDescription() &&
        validateTakGroup() &&
        validatePlanStartTime() &&
        validatePlanEndTime() &&
        validatePlanHour()

}

$('#title').on('input', function () {
    validateTitle();
});

$('#description').on('input', function () {
    validateDescription();
});

$('#end_date').on('change', function () {
    console.log('end-input')
    validatePlanEndTime();

    const startDate = $('#start_date').val();
    const endDate = $(this).val();

    const duration = calculateWeekdayDuration(new Date(startDate).getTime(), new Date(endDate).getTime())

    $("#plan-hours").val(duration * 7)

    validatePlanHour()

});

console.log('llllllllllllllllllll', $('#end_date'))

document.querySelector('#plan-hours').addEventListener('input', function () {
    validatePlanHour();
    console.log('leelr')
});


$('#group').on('change', function () {
    validateTakGroup();
});



$("#task-add-btn").on("click", function () {
    // Get the member id for the added tag , don't return array , only id please
    console.log(document.querySelector("#select-member").value)

    if (!validateAddTask()) {
        return
    }

    const member = $('#select-member').val();
    const errorContainer = $('#select-member').siblings('.error-container');

    let tagData
    if (originalTagify.value[0]) {
        tagData = originalTagify.value[0].id
        errorContainer.remove();
        originalTagify.DOM.scope.classList.add('border', 'border-success', 'is-valid');
        originalTagify.DOM.scope.classList.remove('border', 'border-danger', 'is-invalid');
    } else {
        console.log("member is required")
        originalTagify.DOM.scope.classList.add(
            "border",
            "border-danger",
            "is-invalid"
        );
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text(
                    "Member is required"
                )
                .css("margin", "0");
            $('#select-member').after('<div class="error-container"></div>');
            $('#select-member').siblings('.error-container').append(p);
        }

        originalTagify.DOM.scope.classList.add('border', 'border-danger', 'is-invalid');
        originalTagify.DOM.scope.classList.remove('border', 'border-success', 'is-valid');
        return
    }

    console.log(tagData)


    const startDate = new Date($("#start_date").val());
    const dueDate = new Date($("#end_date").val());

    if (startDate > dueDate || startDate === dueDate) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Start date must be before due date!',
        })
        return;
    }

    const data = {
        title: $("#title").val(),
        description: $("#description").val(),
        // Convert to Unix timestamp
        planStartTime: startDate.getTime(),
        planEndTime: dueDate.getTime(),
        planHours: $("#plan-hours").val(),
        tasksGroup: $("#group").val(),
        projectId:
            parseInt(projectId)
        ,
        userId: tagData

    };
    console.log(data)
    Swal.fire({
        title: "Are you sure want to add?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/api/task/save",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    console.log(response);
                     location.reload();
                    
                },
                error: function (response) {
                    // console.log(response);
                },
            });
        }
    });
});


$('#gantt-chart-tab').on('shown.bs.tab', function (e) {
    // Check if the calendar is already initialized
    //calendar view
    const calendarEl = document.getElementById("calendar");

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: "dayGridMonth",
        initialDate: new Date(),
        timeZone: "Asis/Yangon",
        weekends: false,
        headerToolbar: {
            left: "",
            center: "title prev,next today",
            right: "",
        },
        // loop through the taskList and add events to the calendar
        events: taskList.map((task) => {
            return {
                title: task.title,
                start: new Date(task.planStartTime),
                end: new Date(task.planEndTime + 86400000),
                color: task.tasksGroup === 'A' ? "#444cf7" : task.tasksGroup === 'B' ? "#f7c744" : "#f74444",
                textColor: "white",
                allDay: true,

            }
        },
        ),
        eventClick: function (info) {
            const eventTitle = info.event.title;
            const eventStatus = taskList.find((task) => {
                return task.title === eventTitle;
            }).status;
            const eventDescription = taskList.find((task) => {
                return task.title === eventTitle;
            }).description;

            let taskGroup = taskList.find((task) => {
                return task.title === eventTitle;
            }).tasksGroup;

            if (taskGroup === 'A') {
                taskGroup = "Group A - Related With Project Development"
            } else if (taskGroup === 'B') {
                taskGroup = "Group B - Training"
            } else {
                taskGroup = "Group C - Idling"
            }

            //get plan_end_time and convert to date and do some math to show remaining days
            const planEndTime = taskList.find((task) => {
                return task.title === eventTitle;
            }).planEndTime;
            //only show date and month
            const planEndTimeDate = new Date(planEndTime).toLocaleDateString('en-US', {
                day: 'numeric',
                month: 'short',
                year: 'numeric'
            });

            // Create a modal element
            const modal = document.createElement('div');
            modal.classList.add('modal');

            modal.innerHTML = `
        <div class="modal-dialog modal-dialog-centered d-flex align-items-center">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${eventTitle} <span class="badge bg-primary">${eventStatus}</span></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p><strong>Description :</strong> ${eventDescription}</p>
                    <p><strong>Task Group :</strong> ${taskGroup}</p>
                    <p><strong>Due Date :</strong> ${planEndTimeDate}</p>
                    <!-- Add more information as needed -->
                </div>
            </div>
        </div>
    `;

            // Append the modal to the body
            document.body.appendChild(modal);

            // Show the modal using Bootstrap modal
            $(modal).modal('show');

            // Remove the modal from the DOM when it's closed
            $(modal).on('hidden.bs.modal', function () {
                $(this).remove();
            });

        },
    });

    calendar.render();

    //dynamically add bg-white class to this element .fc-view-harness
    const fcViewHarness = document.querySelector(".fc-view-harness");
    fcViewHarness.classList.add("bg-white");
    fcViewHarness.style.height = "600px";

    //dynamically change width of  .fc-col-header
    const fcColHeader = document.querySelector(".fc-col-header");
    fcColHeader.style.width = "1200px";

    const fcDayGridBody = document.querySelector(".fc-daygrid-body");
    fcDayGridBody.style.width = "1200px";

    const fcScrollgridSyncTable = document.querySelector(".fc-scrollgrid-sync-table");
    fcScrollgridSyncTable.style.width = "1200px";
    fcScrollgridSyncTable.style.height = "600px";


})

