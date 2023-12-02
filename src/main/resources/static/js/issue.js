


$(document).ready(function () {
    setupLogoutConfirmation();
});

$(document).ready(function () {

    const issueDetialEdit = document.querySelector("#details")

    const saveButton = issueDetialEdit.querySelector("#issue-detail-save-btn-container")

    saveButton.addEventListener("click", function (e) {

        const issueId = issueDetialEdit.getAttribute("data-currentEditingIssueId")
        const issueTitle = issueDetialEdit.querySelector("#issueTitleDetails").value
        const issueCategory = issueDetialEdit.querySelector("#issue_categoryDetails").value
        const directCause = issueDetialEdit.querySelector("#direct_causeDetails").value
        const rootCause = issueDetialEdit.querySelector("#root_causeDetails").value
        const place = issueDetialEdit.querySelector("#placeDetails").value
        const impact = issueDetialEdit.querySelector("#impactDetails").value

        const issue = {
            id: issueId,
            title: issueTitle,
            issueCategory: issueCategory,
            direct_cause: directCause,
            root_cause: rootCause,
            place: place,
            impact: impact,
            updated_date: Date.now()
        }

        $.ajax({
            url: `/api/issue/update/${issueId}`, // Replace with your actual URL
            type: 'PUT', // Change to 'PUT' or 'PATCH' if needed
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(issue),
            success: function (response) {
                // Handle success response
                saveButton.classList.add('d-none')

                const issue = createIssueCard(response);

                const oldIssue = $(`#issue-${response.id}`)

                oldIssue.replaceWith(issue)

                $("#alert-text").text(
                    "Issue Updaed Successfully"
                );
                $("#alert-modal").modal("show");

            },
            error: function (xhr, status, error) {
                console.log('Ajax request error:', error);
                // Handle error response
            }
        });

    })

    issueDetialEdit.addEventListener("input", function (e) {


        if (saveButton.classList.contains("d-none")) {
            saveButton.classList.remove("d-none")
        }

    })
    setupLogoutConfirmation();
});

document.querySelectorAll("button[data-bs-target=\"#all-issue-list\"]").forEach(button => {
    button.addEventListener("show.bs.tab", async function (e) {

        const issueListContainer = document.querySelector("#sort-container")

        const issueList = await getData('/api/issue/list')

        issueListContainer.innerHTML = ''

        for (let i = issueList.length - 1; i >= 0; i--) {

            issueListContainer.appendChild(createIssueCard(issueList[i]));
        }

    })
})

$("button[data-bs-target=\"#issue-table\"]").on("show.bs.tab", function (e) {
    $("#issue-table-container").removeClass("d-none");
})

if (document.querySelector("button[data-bs-target=\"#unsolved-issues\"]")) {

    document.querySelector("button[data-bs-target=\"#unsolved-issues\"]").addEventListener("show.bs.tab", async function (e) {

        document.querySelector('#sort-container').innerHTML = ''

        const unsolvedIssueListContainer = document.querySelector("#unsolved-issues-container")

        unsolvedIssueListContainer.innerHTML = ''

        const unsolvedIssueList = await getData(`/api/issue/list/unsolved/${getLoginUser.currentUser.id}`)

        for (let i = unsolvedIssueList.length - 1; i >= 0; i--) {

            unsolvedIssueListContainer.appendChild(createIssueCard(unsolvedIssueList[i]));

        }

    })

    // Author: Habib Mhamadi
    // Email: habibmhamadi@gmail.comd

}

import { getUser } from "/js/currentLoginUser.js";
import { formatDate, getTimeElapsed } from "/js/time.js";
import { getData } from "/js/api-function.js";

let quillEditors = document.querySelectorAll(
    ".quill-editor-bubble"
);
let toolbaroptions = [
    // Test-style
    ["bold", "italic", "underline"],

    // Bullet points style...
    [{ list: "ordered" }, { list: "bullet" }],

    // Sub and super script...
    [{ script: "sub" }, { script: "super" }],

    // Indentation...
    [{ indent: "+1" }, { indent: "-1" }],

    // Alignment
    [{ align: [] }],

    // Header for test, e.g., h1, h2...
    [{ header: [1, 2, 3, 4, 5, 6, false] }],

    // Adding image, link, video, or formula
    ["link"],

    // Adding code snippet or blockquote
    ["code-block", "blockquote"],
];
// If you want to initialize Quill for a specific element, you can do it like this:
let getLoginUser = await getUser();
let data = [];
const currentLoginUserRole = getLoginUser.currentUser.role;

if (currentLoginUserRole === 'PROJECT_MANAGER' || currentLoginUserRole === 'SDQC' || currentLoginUserRole === 'PMO') {
    document.querySelector("#issue-link").classList.remove("d-none")
}

// const currentUser = await getUser();
const dataForIssue = await getData(`/api/project/list/ID/${getLoginUser.currentUser.id}/false`)
$(document).ready(async function () {


    if (currentLoginUserRole === 'EMPLOYEE' || currentLoginUserRole === 'FOC' || currentLoginUserRole === 'CONTRACT') {
        $.get(`api/project/list/ID/${getLoginUser.currentUser.id}/IN_PROGRESS`, function (userData) {
            data = userData; // Set the data array with the retrieved user data
            const user_pic = $('#user_pic');

            user_pic.append($('<option>', {
                value: getLoginUser.currentUser.projectManager.id,
                text: getLoginUser.currentUser.projectManager.name,
                data: { picUrl: getLoginUser.currentUser.projectManager.picUrl },

                user: getLoginUser.currentUser.projectManager,
            }));
        });

        let clientData = await getData(`/api/project/list/ID/${getLoginUser.currentUser.id}/IN_PROGRESS`);

        function populateClientName() {
            const responsiblePartySelect = $('#responsible_party');
            responsiblePartySelect.empty();
            responsiblePartySelect.append($('<option>', {
                value: `client_${clientData.client.id}`,
                text: `${clientData.client.name}`,
            }));
        }

        // Function to populate the responsible party select box with user names
        function populateUserNames() {
            const responsiblePartySelect = $('#responsible_party');
            responsiblePartySelect.empty();

            responsiblePartySelect.append($('<option>', {
                text: 'Select Responsible Party',
            }));

            if (clientData.userList) {
                clientData.userList.forEach(user => {
                    responsiblePartySelect.append($('<option>', {
                        value: `user_${user.id}`,
                        text: `${user.name}`,
                        data: { picUrl: user.picUrl },
                        user: user,
                    }));
                });
            }
        }

        // Fetch client data and populate the select box initially
        $.get(`api/project/list/ID/${getLoginUser.currentUser.id}/false`, function (data) {
            clientData = data;
            // Check the "Client" checkbox initially
            if (typeof clientData === 'object' && clientData !== null) {
                $('#responsible_type').prop('checked', true);
                populateClientName();
                document.querySelector("button[data-bs-target='#add-issue']").classList.remove('d-none')
            } else {
                document.querySelector("button[data-bs-target='#add-issue']").classList.add('d-none')
            }
        });
        let actual_responsible_type = 'CLIENT';

        // Event handler for the "Client" checkbox
        $('#responsible_type').on('change', function () {
            const isChecked = $(this).is(':checked');

            if (isChecked) {
                // If the "Client" checkbox is checked, show only the client's name
                populateClientName();
                actual_responsible_type = 'CLIENT'
            } else {
                // If the "Client" checkbox is not checked, show user names
                populateUserNames();
                actual_responsible_type = 'EMPLOYEE'
            }
        });


        // validation For Issue

        let validatedForm = false;

        function validateTitle() {
            const title = $('#title').val();
            const errorContainer = $('#title').siblings('.error-container');
            if (title.length < 1) {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text("Title is required")
                        .css("margin", "0")
                        .addClass('error-message');

                    $('#title').after('<div class="error-container"></div>');
                    $('#title').siblings('.error-container').append(p);
                }

                $('#title').addClass('is-invalid');
                $('#title').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#title').addClass('is-valid');
                $('#title').removeClass('is-invalid');
            }
        }


        let descriptionEditor;

        function initializeQuillEditor() {
            descriptionEditor = new Quill('.quill-editor-bubble', {
                modules: {
                    toolbar: toolbaroptions
                },
                theme: 'snow'
            });

        }

        function validateDescription() {
            if (!descriptionEditor) {
                console.error("Quill editor not initialized");
                return;
            }

            const description = descriptionEditor.getText().trim();
            const errorContainer = $('.quill-editor-bubble').siblings('.error-container');

            if (!description || description.length === 0) {
                validatedForm = false;

                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text("Description is required")
                        .css("margin", "0")
                        .addClass('error-message');

                    $('.quill-editor-bubble').after('<div class="error-container"></div>').siblings('.error-container').append(p);
                } else {
                    errorContainer.show();
                }

                $('.quill-editor-bubble').addClass('is-invalid');
                $('.quill-editor-bubble').removeClass('is-valid');
            } else {
                validatedForm = true;

                if (errorContainer.length > 0) {
                    errorContainer.hide();
                }

                $('.quill-editor-bubble').addClass('is-valid');
                $('.quill-editor-bubble').removeClass('is-invalid');
            }
        }

        if (document.readyState === 'complete' || document.readyState === 'interactive') {
            initializeQuillEditor();
        } else {

            document.addEventListener('DOMContentLoaded', initializeQuillEditor);
        }

        let validationPerformed = false;


        function validatePlace() {
            const place = $('#place').val();
            const errorContainer = $('#place').siblings('.error-container');
            if (place.length < 1) {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Place is required"
                        )
                        .css("margin", "0");

                    $('#place').after('<div class="error-container"></div>');
                    $('#place').siblings('.error-container').append(p);
                }
                $('#place').addClass('is-invalid');
                $('#place').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#place').addClass('is-valid');
                $('#place').removeClass('is-invalid');
            }
        }

        function validateImpact() {
            const impact = $('#impact').val();
            const errorContainer = $('#impact').siblings('.error-container');
            if (impact.length < 1) {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Impact is required"
                        )
                        .css("margin", "0");
                    $('#impact').after('<div class="error-container"></div>');
                    $('#impact').siblings('.error-container').append(p);
                }

                $('#impact').addClass('is-invalid');
                $('#impact').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#impact').addClass('is-valid');
                $('#impact').removeClass('is-invalid');
            }
        }

        function validateRootCause() {
            const rootCause = $('#root_cause').val();
            const errorContainer = $('#root_cause').siblings('.error-container');
            if (rootCause.length < 1) {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Root Cause is required"
                        )
                        .css("margin", "0");
                    $('#root_cause').after('<div class="error-container"></div>');
                    $('#root_cause').siblings('.error-container').append(p);
                }

                $('#root_cause').addClass('is-invalid');
                $('#root_cause').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#root_cause').addClass('is-valid');
                $('#root_cause').removeClass('is-invalid');
            }
        }

        function validateDirectCause() {
            const directCause = $('#direct_cause').val();
            const errorContainer = $('#direct_cause').siblings('.error-container');
            if (directCause.length < 1) {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Direct Cause is required"
                        )
                        .css("margin", "0");
                    $('#direct_cause').after('<div class="error-container"></div>');
                    $('#direct_cause').siblings('.error-container').append(p);
                }


                $('#direct_cause').addClass('is-invalid');
                $('#direct_cause').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#direct_cause').addClass('is-valid');
                $('#direct_cause').removeClass('is-invalid');
            }
        }

        function validateIssueCategory() {
            const issueCategory = $('#issue_category').val();
            const errorContainer = $('#issue_category').siblings('.error-container');

            if (!issueCategory || issueCategory === "") {

                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Issue Category is required"
                        )
                        .css("margin", "0");
                    $('#issue_category').after('<div class="error-container"></div>');
                    $('#issue_category').siblings('.error-container').append(p);
                }

                $('#issue_category').addClass('is-invalid');
                $('#issue_category').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#issue_category').addClass('is-valid');
                $('#issue_category').removeClass('is-invalid');
            }
        }

        function validateResponsibleParty() {
            const responsibleParty = $('#responsible_party').val();
            const errorContainer = $('#responsible_party').siblings('.error-container');
            if (!responsibleParty || responsibleParty === "") {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Responsible Party is required"
                        )
                        .css("margin", "0");
                    $('#responsibleParty').after('<div class="error-container"></div>');
                    $('#responsibleParty').siblings('.error-container').append(p);
                }
                $('#responsibleParty').addClass('is-invalid');
                $('#responsibleParty').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#responsibleParty').addClass('is-valid');
                $('#responsibleParty').removeClass('is-invalid');
            }
        }

        function validateUserPic() {
            const userPic = $('#user_pic').val();
            const errorContainer = $('#user_pic').siblings('.error-container');
            if (!userPic || userPic === "") {
                validatedForm = false;
                if (errorContainer.length === 0) {
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "Person In Charge is required"
                        )
                        .css("margin", "0");
                    $('#user_pic').after('<div class="error-container"></div>');
                    $('#user_pic').siblings('.error-container').append(p);
                }
                $('#user_pic').addClass('is-invalid');
                $('#user_pic').removeClass('is-valid');
            } else {
                validatedForm = true;
                errorContainer.remove();
                $('#user_pic').addClass('is-valid');
                $('#user_pic').removeClass('is-invalid');
            }
        }

        function validateIssue() {
            validateTitle();
            validateDescription();
            validatePlace();
            validateImpact();
            validateRootCause();
            validateDirectCause();
            validateIssueCategory();
            validateResponsibleParty();
            validateUserPic();
            return validatedForm;

        }

        $('#title').on('input', function () {
            validateTitle();
        });

        $('.quill-editor-bubble').on('input', function () {
            validateDescription();
        });

        $('#place').on('input', function () {
            validatePlace();
        });

        $('#impact').on('input', function () {
            validateImpact();
        });

        $('#root_cause').on('input', function () {
            validateRootCause();
        });

        $('#direct_cause').on('input', function () {
            validateDirectCause();
        });

        $('#issue_category').on('change', function () {
            validateIssueCategory();
        });

        $('#responsible_party').on('change', function () {
            validateResponsibleParty();
        });

        $('#user_pic').on('change', function () {
            validateUserPic();
        });

        function clearIssueValidationStylesAndMessages(fieldSelector) {
            $(fieldSelector).removeClass("is-valid is-invalid");
            $(fieldSelector).siblings(".error-container").remove();
        }

        if (dataForIssue !== null) {
            //for add issue
            $('#addIssue').on('click', async function () {
                const title = $('#title').val();
                const description = descriptionEditor.root.innerHTML;
                const place = $('#place').val();
                const impact = $('#impact').val();
                const root_cause = $('#root_cause').val();
                const direct_cause = $('#direct_cause').val();
                const responsible_party = $('#responsible_party').val();
                const issue_category = $('#issue_category').val();
                const responsible_type = actual_responsible_type;
                const selectedUserId = $('#user_pic').val();
                const issueId = $('#issue-edit-btn').data('issueId');

                if (!validateIssue()) {
                    showerror('Please fill in all the required fields.');
                    return;
                }

                const selectedResponsibleParty = parseInt(responsible_party.split("_")[1])
                const uploaderId = getLoginUser.currentUser.id;

                const requestData = {
                    title: title,
                    description: description,
                    place: place,
                    impact: impact,
                    created_date: new Date().getTime(),
                    updated_date: new Date().getTime(),
                    root_cause: root_cause,
                    direct_cause: direct_cause,
                    responsible_party: selectedResponsibleParty,
                    issueCategory: issue_category,
                    responsible_type: responsible_type,
                    project_id: dataForIssue.projectId,
                    user_uploader: uploaderId,
                    user_pic: selectedUserId,


                };

                $.ajax({
                        url: '/api/issue/save',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(requestData),
                        dataType: 'json',
                        success: function (data) {
                            $("#alert-text").text(
                                "Issue Created Successfully"
                            );
                            $("#alert-modal").modal("show");

                            const title = $('#title').val('');
                            const description = descriptionEditor.root.innerHTML = '';
                            const place = $('#place').val('');
                            const impact = $('#impact').val('');
                            const root_cause = $('#root_cause').val('');
                            const direct_cause = $('#direct_cause').val('');

                            clearIssueValidationStylesAndMessages('#title');
                            clearIssueValidationStylesAndMessages('.quill-editor-bubble');
                            clearIssueValidationStylesAndMessages('#place');
                            clearIssueValidationStylesAndMessages('#impact');
                            clearIssueValidationStylesAndMessages('#root_cause');
                            clearIssueValidationStylesAndMessages('#direct_cause');
                            clearIssueValidationStylesAndMessages('#issue_category');
                            clearIssueValidationStylesAndMessages('#responsible_party');
                            clearIssueValidationStylesAndMessages('#user_pic');


                            $("#add-issue").modal("hide")


                    },
                    error: function (xhr, status, error) {

                        if (xhr.getResponseHeader('Content-Type') === 'application/json') {
                            // Handle JSON response
                            try {
                                const response = JSON.parse(xhr.responseText);
                            } catch (e) {
                            }
                        } else {
                            // Handle non-JSON response
                        }
                        showError('Error creating issue: ' + xhr.responseText);
                    },
                });
            });
        }
    }
});

function createIssueCard(issue) {

    const main = document.createElement("div");
    main.classList.add("card", "p-0", 'issue-card');
    main.id = `issue-${issue.id}`
    main.style.minWidth = "320px";

    const cardHeader = `<div class="card-header justify-content-between">
                                                    <div
                                                        class="d-flex justify-content-between align-items-center flex-wrap"
                                                    >
                                                        <div class="d-flex gap-3 align-items-center flex-wrap">
                                                            <span class="issue-title card-title issueDetails" data-id="${issue.id}" style="font-size: 32px;cursor: pointer;font-family: " Lucida Sans", "Lucida Sans Regular", "Lucida Grande","Lucida Sans Unicode", Geneva, Verdana, sans-serif;">${issue.title}</span>
                                                    </div>

                                                    <div class="d-flex justify-content-between gap-2">
                                                        <div>
                                                            ${issue.status === 'DECLINE' ? '<span class="badge bg-danger">Declined</span>' : ''}
                                                            ${issue.status === 'DUPLICATE' ? '<span class="badge bg-warning">Duplicate</span>' : ''}
                                                            <span class="badge bg-success">${issue.issueCategory}</span>
                                                            <span class="badge bg-primary bg-${issue.solved === true ? 'danger' : 'primary'}">
                                                                ${issue.solved === true ? 'Closed' : 'Open'}
                                                            </span>

                                                        </div>
                                                        ${getLoginUser.currentUser.id === issue.user_pic.id ?
            `<button type="button" class="btn btn-info see-more-button text-white" data-id="${issue.id}">` +
            '<i class="fa-solid fa-pen-to-square"></i>' +
            '</button>'
            : ''}
                                                    </div>
                                                </div>
                                                <div class="col issue-time-line d-flex gap-4 flex-wrap">
                                                    <div class="d-flex align-items-center gap-2">
                                                        <p style="padding: 0;margin: 0;opacity: 0.5;">Uploader</p>
                                                        <span class="card-text" style="font-size: 0.9em;opacity: 1;font-weight: 600;">${issue.user_uploader.name}</span>
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
                                                    data-bs-target="#collapse-${issue.id}"
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
        const quillContainer = document.querySelector(`#quill-editor-${issue.id}`);

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
    }

    cardBody.innerHTML = h2;
    collapse.appendChild(accordionBody);

    cardBody.appendChild(collapse);

    main.innerHTML = cardHeader;
    main.appendChild(cardBody);

    setTimeout(initializeQuill, 1000);

    return main;

}

$("#table").bootstrapTable()
// Function to load data into the Bootstrap Table
function loadTable() {
    $.ajax({
        url: '/api/issue/list',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            data.forEach(function (issue) {
                const formattedCreatedDate = formatDate(issue.created_date);

                $('#table').bootstrapTable('append', {
                    id: issue.id,
                    title: issue.title,
                    issue_category: issue.issueCategory,
                    project_name: issue.projectDto.name,

                    responsible_party: issue.responsible_party.name,
                    user_pic: issue.user_pic.name,
                    status: issue.status,
                    solved: issue.solved,
                    created_date: formattedCreatedDate,
                });
            });
        }
    });
}

loadTable()

const exportDropdown = document.getElementById("exportSelect");

exportDropdown.addEventListener("change", function () {
    const exportOption = this.value;
    if (exportOption === "all") {
        exportAllRows();
    } else if (exportOption === "selected") {
        const selectedRows = $("#table").bootstrapTable("getSelections");
        exportSelectedRows(selectedRows);
    }
});

const exportExcelDropdown = document.getElementById("exportExcelSelect");

exportExcelDropdown.addEventListener("change", function () {
    const exportOption = this.value;
    if (exportOption === "all") {
        exportAllRowsToExcel();
    } else if (exportOption === "selected") {
        const selectedRows = $("#table").bootstrapTable("getSelections");
        exportSelectedRowsToExcel(selectedRows);
    }
});


function exportAllRows() {
    const docDefinition = {
        pageOrientation: 'landscape',
        content: [
            { text: "Issue List Report", style: "header" },
            {
                table: {
                    headerRows: 1,
                    widths: "auto",
                    body: [
                        ["ID", "Title", "Category", "Project Name", "Status", "Personal In Charge", "Responsible Party", "Solved", "Created Date"],
                        ...$("#table").bootstrapTable("getData").map((row) => [
                            row.id,
                            row.title,
                            row.issue_category,
                            row.project_name,
                            row.status,
                            row.user_pic,
                            row.responsible_party,
                            row.solved,
                            row.created_date
                        ])
                    ]
                }
            }
        ],
        styles: {
            header: {
                fontSize: 18,
                bold: true,
                margin: [0, 0, 0, 10]
            }
        }
    };
    pdfMake.createPdf(docDefinition).open();
}


function exportSelectedRows(selectedRows) {


    const docDefinition = {
        pageOrientation: 'landscape',
        content: [
            {
                text: "Selected Issues",
                style: "header"
            },
            {
                table: {
                    widths: "auto",

                    body: [
                        [
                            "ID",
                            "Title",
                            "Category",
                            "Project Name",
                            "Status",
                            "Personal In Charge",
                            "Responsible Party",
                            "Solved",
                            "Created Date"
                        ],
                        ...selectedRows.map((row) => [
                            row.id,
                            row.title,
                            row.issue_category,
                            row.project_name,
                            row.status,
                            row.user_pic,
                            row.responsible_party,
                            row.solved,
                            row.created_date
                        ])
                    ],
                    layout: 'lightHorizontalLines'
                }
            }
        ],
        styles: {
            header: {
                fontSize: 18,
                bold: true,
                margin: [0, 0, 0, 10]
            }
        }
    };
    pdfMake.createPdf(docDefinition).open();

}

function exportAllRowsToExcel() {
    fetch('/api/issue/list')
        .then(response => response.json())
        .then(data => {
            const headers = ['ID', 'Title', 'Category', 'Project Name', 'Status', 'Personal In Charge', 'Responsible Party', 'Solved', 'Created Date'];
            const rows = data.map(issue => [
                issue.id,
                issue.title,
                issue.issue_category,
                issue.project_name,
                issue.status,
                issue.user_pic,
                issue.responsible_party,
                issue.solved,
                issue.created_date
            ]);

            const worksheet = XLSX.utils.aoa_to_sheet([headers, ...rows]);
            const workbook = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet 1');
            XLSX.writeFile(workbook, 'IssueList.xlsx');
        })
        .catch(error => console.error('Error fetching data:', error));
}

function exportSelectedRowsToExcel(selectedRows) {
    const headers = ['ID', 'Title', 'Category', 'Project Name', 'Status', 'Personal In Charge', 'Responsible Party', 'Solved', 'Created Date'];
    const rows = selectedRows.map(row => [
        row.id,
        row.title,
        row.issue_category,
        row.project_name,
        row.status,
        row.user_pic,
        row.responsible_party,
        row.solved,
        row.created_date
    ]);

    const worksheet = XLSX.utils.aoa_to_sheet([headers, ...rows]);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet 1');
    XLSX.writeFile(workbook, 'SelectedIssues.xlsx');
}

let currentIssueId;
$(document).ready(async function () {

    function loadData() {
        $.ajax({
            url: 'api/issue/list',
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                const sortContainer = document.querySelector('#sort-container');
                for (let i = data.length - 1; i >= 0; i--) {
                    const issueDiv = createIssueCard(data[i]);
                    sortContainer.appendChild(issueDiv)
                }
            },
            error: function (error) {
                console.log('Error fetching data:', error);
            }
        });
    }

    $("#reset-btn").on("click", function () {
        $("#status-filter").val('');
        $("#solved-filter").val('');
        $("#category-filter").val('');
        document.querySelector("#sort-container").innerHTML = ''
        document.querySelector('#no-result').classList.add('d-none')
        loadData();
    });

    loadData();

    $('.tab-content').on('click', '.see-more-button', function () {
        // Get the issue ID directly from the button's data-id attribute
        const issueId = $(this).attr('data-id'); // Use $(this) instead of $('#issue-edit-btn')

        $.ajax({
            url: '/api/issue/list/byId/' + issueId,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data) {
                    $('#corrective_actionInput').val(data.corrective_action);
                    $('#preventive_actionInput').val(data.preventive_action);
                    if (data.solved === false) {
                        $('#solve-btn').hide();
                        $('#issue-edit-btn').show();
                    } else {
                        $('#solve-btn').show();
                        $('#issue-edit-btn').hide();
                    }

                    $('#issue-edit-btn').data('issueId', issueId);
                    $('#solve-btn').data('issueId', issueId);// Set the issueId

                    $('#issueEditModal').modal('show');

                } else {
                    console.log('No data received for issue ID:', issueId);
                }
            },
            error: function (xhr, status, error) {
                console.log('AJAX error:', error);
            }
        });
    });

    $('.tab-content').on('click', '.issueDetails', function () {

        // Get the issue ID directly from the button's data-id attribute
        const issueId = $(this).data('id'); // Use $(this) instead of $('#issue-edit-btn')

        $.ajax({
            url: 'api/issue/list/byId/' + issueId,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data) {
                    $("#details").attr("data-currentEditingIssueId", data.id);
                    $('#issueTitleDetails').val(data.title);
                    $('#issue_categoryDetails').val(data.issueCategory);
                    $('#picDetails').text(data.user_pic.name);
                    $('#rpDetails').text(data.responsible_party.name);
                    const issueDetail = new Quill(document.querySelector('#descriptionDetails'), {
                        modules: {
                            toolbar: toolbaroptions,
                        },
                        theme: 'bubble',
                    });
                    issueDetail.root.innerHTML = '';
                    issueDetail.clipboard.dangerouslyPasteHTML(
                        0,
                        data.description
                    );
                    issueDetail.on('text-change', function (delta, source) {
                        $("#issue-description-save-btn-container").removeClass("d-none")
                    });
                    $("#issue-description-save-btn").on('click', function () {

                        const issue = {
                            id: data.id,
                            description: issueDetail.root.innerHTML,
                            updated_date: Date.now()
                        }

                        $.ajax({
                            url: `/api/issue/update/${data.id}`, // Replace with your actual URL
                            type: 'PUT', // Change to 'PUT' or 'PATCH' if needed
                            dataType: 'json',
                            contentType: 'application/json',
                            data: JSON.stringify(issue),
                            success: function (response) {
                                // Handle success response
                                $("#issue-description-save-btn-container").addClass('d-none')

                                const issue = createIssueCard(response);

                                const oldIssue = $(`#issue-${response.id}`)

                                oldIssue.replaceWith(issue)

                                $("#alert-text").text(
                                    "Issue Updated Successfully"
                                );
                                $("#alert-modal").modal("show");

                            },
                            error: function (xhr, status, error) {
                                // Handle error response
                            }
                        });
                    })
                    $('#direct_causeDetails').val(data.direct_cause);
                    $('#root_causeDetails').val(data.root_cause);
                    $('#placeDetails').val(data.place);
                    $('#impactDetails').val(data.impact);
                    $('#correctiveDetails').text(data.corrective_action);
                    $('#preventiveDetails').text(data.preventive_action);
                    $('#solveDateDetails').text(formatDate(data.created_date));
                    $('#issue-edit-btn').data('issueId', issueId); // Set the issueId
                    $('#issueDetailsModal').modal('show');

                    const notSamePerson = getLoginUser.currentUser.id !== data.user_uploader.id

                    if (notSamePerson) {
                        $('#issueTitleDetails').prop('disabled', true);
                        $('#issue_categoryDetails').prop('disabled', true);
                        $('#descriptionDetails').prop('disabled', true);
                        $('#direct_causeDetails').prop('disabled', true);
                        $('#root_causeDetails').prop('disabled', true);
                        $('#placeDetails').prop('disabled', true);
                        $('#impactDetails').prop('disabled', true);
                        issueDetail.enable(false)
                    } else {
                        if (data.solved) {
                            $('#issueTitleDetails').prop('disabled', true);
                            $('#issue_categoryDetails').prop('disabled', true);
                            $('#descriptionDetails').prop('disabled', true);
                            $('#direct_causeDetails').prop('disabled', true);
                            $('#root_causeDetails').prop('disabled', true);
                            $('#placeDetails').prop('disabled', true);
                            $('#impactDetails').prop('disabled', true);
                            issueDetail.enable(false)
                        } else {
                            $('#issueTitleDetails').prop('disabled', false);
                            $('#issue_categoryDetails').prop('disabled', false);
                            $('#descriptionDetails').prop('disabled', false);
                            $('#direct_causeDetails').prop('disabled', false);
                            $('#root_causeDetails').prop('disabled', false);
                            $('#placeDetails').prop('disabled', false);
                            $('#impactDetails').prop('disabled', false);
                            issueDetail.enable(true)
                        }
                    }

                } else {
                    console.log('No data received for issue ID:', issueId);
                }
            },
            error: function (xhr, status, error) {
                console.log('AJAX error:', error);
            }
        });
    });

    function createPendingIssueCard(issue) {

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
                                                                    <span class="issue-title issueDetails" data-id="${issue.id}" style="font-size: 32px;cursor: pointer;font-family: " Lucida Sans", "Lucida Sans Regular", "Lucida Grande","Lucida Sans Unicode", Geneva, Verdana, sans-serif;">${issue.title}</span>
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
                                                            data-bs-target="#collapse-${issue.id}"
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
            const quillContainer = document.querySelector(`#quill-editor-${issue.id}`);

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
        }

        cardBody.innerHTML = h2;
        collapse.appendChild(accordionBody);

        cardBody.appendChild(collapse);

        main.innerHTML = cardHeader;
        main.appendChild(cardBody);

        setTimeout(initializeQuill, 1000);

        outerDiv.appendChild(approvalContainer);
        outerDiv.appendChild(main);

        return outerDiv;

    }

    const pendingIssueList = await getData(`/api/issue/list/pending/${getLoginUser.currentUser.id}`)

    if (pendingIssueList.length !== 0) {
        document.getElementById("notification-light-for-new-issue").classList.remove("d-none")
    }

    document.querySelector("button[data-bs-target='#new-issue-list-container']").addEventListener('show.bs.tab', async () => {

        document.querySelector("#notification-light-for-new-issue").classList.add('d-none')

        const newIssueContainer = document.querySelector("#new-issue-list")

        newIssueContainer.innerHTML = ''

        const aaa = await getData(`/api/issue/list/pending/${getLoginUser.currentUser.id}`)

        if (aaa.length === 0) {
            document.querySelector('#no-new-issue').classList.remove('d-none')
        } else {
            for (let i = aaa.length - 1; i >= 0; i--) {

                const currentIssue = aaa[i];

                newIssueContainer.appendChild(createPendingIssueCard(currentIssue))
            }
        }
    })

    const allApprovalRadios = document.querySelectorAll('.all-approval-input');

    // Attach a click event listener to each radio button in #all-approval
    allApprovalRadios.forEach(allApprovalRadio => {
        allApprovalRadio.addEventListener('click', function () {
            // Get the value of the clicked radio button
            const selectedValue = this.value;

            // Set the corresponding radio buttons in .item sections to the same value
            document.querySelectorAll('.issue-approval input[value="' + selectedValue + '"]').forEach(itemRadio => {
                itemRadio.checked = true;
            });
        });
    });

    document.querySelector("#all-approval-btn").addEventListener("click", function () {

        const updateIssueList = [];

        const issueList = document.querySelectorAll(".item-issue-approval");

        issueList.forEach((item, index) => {
            // Get the value of the selected radio button in the corresponding .item section
            const selectedRadio = item.querySelector(".issue-approval input[type='radio']:checked");
            // Check if a radio button is selected
            if (selectedRadio) {
                // Add data to the array
                updateIssueList.push({
                    id: parseInt(item.getAttribute("id").split("-")[1]), // Assuming each item has a unique identifier
                    status: selectedRadio.value
                });
            } else {
                // Handle the case where no radio button is selected for an item
                console.log(`No radio button selected for item ${index + 1}`);
            }
        });

        if (updateIssueList.length != 0) {
            $.ajax({
                url: `/api/issue/update/status/issuelist`, // Update the URL to include the issue ID
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updateIssueList),
                dataType: 'json',
                success: function (data) {
                    // alert("Issue Updated Successfully");
                    showSuccess("Issue Updated Successfuly.");
                    $.ajax({
                        url: `/api/issue/list/pending/${getLoginUser.currentUser.id}`, // Update the URL to include the issue ID
                        type: 'GET',
                        success: function (data) {
                            const newIssueList = document.getElementById("new-issue-list")
                            newIssueList.innerHTML = ''
                            for (let i = data.length - 1; i >= 0; i--) {

                                const currentIssue = data[i];

                                newIssueList.appendChild(createPendingIssueCard(currentIssue))
                            }
                        },

                    });
                },

            });
        }

    })
});
// for update
const issueEditBtn = document.getElementById('issue-edit-btn');
const solveBtn = document.getElementById('solve-btn');

solveBtn.addEventListener('click', function (e) {
    //alert("Your issue solved");

    const corrective_action = $('#corrective_actionInput').val();
    const preventive_action = $('#preventive_actionInput').val();
    const issueId = $(this).data('issueId');
    if (corrective_action === "" || preventive_action === "") {
        // alert('Please fill in all the required fields.');
        showError('Please fill in all the required fields.');
        return;
    }

    const updatedDetails = {
        corrective_action: corrective_action,
        preventive_action: preventive_action,
        updated_date: new Date().getTime(),
    };
    $.ajax({
        url: `/api/issue/update/${issueId}`, // Update the URL to include the issue ID
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(updatedDetails),
        dataType: 'json',
        success: function (data) {
            // Update 'solved' to true after a successful AJAX request
            Swal.fire({
                title: "Success!",
                text: "You have successfully solved the issue.",
                icon: "success"
            });
            $("#issue-edit-btn").hide();
            $("#solve-btn").show();
        },
        error: function (xhr, status, error) {
            console.log('Status:', status);
            console.log('Error:', error);
            console.log('Server response:', xhr.responseText);
            if (xhr.getResponseHeader('Content-Type') === 'application/json') {
                // Handle JSON response
                try {
                    const response = JSON.parse(xhr.responseText);
                    console.log('JSON Response:', response);
                } catch (e) {
                    console.log('JSON parsing error:', e);
                }
            } else {
                // Handle non-JSON response
                console.log('Non-JSON Response:', xhr.responseText);
            }
            alert("Error updating issue: " + xhr.responseText);
        },
        complete: function () {
            // Optionally, close the modal or perform other actions upon completion
            $('#issueDetailModal').modal('hide');
        }
    });
});

issueEditBtn.addEventListener('click', function (e) {
    const corrective_action = $('#corrective_actionInput').val();
    const preventive_action = $('#preventive_actionInput').val();
    const solved = $('#solve-btn').val();
    const issueId = $(this).data('issueId');
    if (corrective_action === "" || preventive_action === "") {
        // alert('Please fill in all the required fields.');
        showError('Please fill in all the required fields.');
        return;
    }

    if (solved === "true") {
        // If solved, hide the "issue-edit-btn" and show the "solve-btn"
        $("#issue-edit-btn").hide();
        $("#solveBtn").show();
    } else {
        // If not solved, show the confirmation modal
        Swal.fire({
            title: "Are you sure?",
            text: "Are you sure you want to solve?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                const updatedDetails = {
                    corrective_action: corrective_action,
                    preventive_action: preventive_action,
                    updated_date: new Date().getTime(),
                    solved_date: new Date().getTime(),
                    solved: solved,
                };

                $.ajax({
                    url: `/api/issue/update/${issueId}`, // Update the URL to include the issue ID
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(updatedDetails),
                    dataType: 'json',
                    success: function (data) {
                        // Update 'solved' to true after a successful AJAX request

                        Swal.fire({
                            title: "Success!",
                            text: "You have successfully solved the issue.",
                            icon: "success"
                        });
                        $("#issue-edit-btn").hide();
                        $("#solve-btn").show();


                    },
                    error: function (xhr, status, error) {
                        console.log('Status:', status);
                        console.log('Error:', error);
                        console.log('Server response:', xhr.responseText);

                        if (xhr.getResponseHeader('Content-Type') === 'application/json') {
                            // Handle JSON response
                            try {
                                const response = JSON.parse(xhr.responseText);
                                console.log('JSON Response:', response);
                            } catch (e) {
                                console.log('JSON parsing error:', e);
                            }
                        } else {
                            // Handle non-JSON response
                            console.log('Non-JSON Response:', xhr.responseText);
                        }
                        alert("Error updating issue: " + xhr.responseText);
                        console.log("Error updating issue");
                    },
                    complete: function () {
                        // Optionally, close the modal or perform other actions upon completion
                        $('#issueDetailModal').modal('hide');
                    }
                });

            } else {
                // User clicked "No," stop the process and close the modal
                return false;
            }
        });

        // Hide the "solve-btn" regardless of user choice
        $("#solveBtn").hide();
    }
});


// for search bar
$("#status-filter").on("change", function () {
    filterIssues();
});

var userRole = getLoginUser.currentUser.role;

// Check if the user role is not equal to "Project-Manager"
if (userRole !== "PROJECT_MANAGER") {
    // Hide the status filter container
    $("#solved-filter-container").hide();
}


$("#solved-filter").on("change", function () {
    filterSolvedIssues();
});


$("#category-filter").on("change", function () {
    filterCategory();
})


function filterIssues() {
    var status = $('#status-filter').val();

    $.ajax({
        url: '/api/issue/list/status/' + status,
        type: 'GET',
        success: function (data) {

            const sortContainer = $('#sort-container');
            sortContainer.empty();

            // Check if data is not empty
            if (data && data.length > 0) {
                // Loop through the retrieved data and create content for each item
                document.querySelector('#no-result').classList.add('d-none')
                data.forEach(item => {
                    const issueDiv = createIssueCard(item);
                    sortContainer.append(issueDiv);

                });
            } else {
                document.querySelector('#no-result').classList.remove('d-none')
            }
        },

        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            console.log('XHR:', xhr);
            console.log('Status:', status);

            // Display the error response in the console
            try {
                const response = JSON.parse(xhr.responseText);
                console.log('Error response:', response);
            } catch (e) {
                console.log('Error parsing response:', e);
            }
            showError('Error fetching data.');
            //alert('Error fetching data.');
        }
    });
}

// for solved
function filterSolvedIssues() {
    var solved = $('#solved-filter').val();

    $.ajax({
        url: '/api/issue/list/solved/' + solved,
        type: 'GET',
        success: function (data) {

            const sortContainer = $('#sort-container');
            sortContainer.empty(); // Clear previous content

            // Check if data is not empty
            if (data && data.length > 0) {
                // Loop through the retrieved data and create content for each item
                document.querySelector('#no-result').classList.add('d-none')
                data.forEach(item => {
                    const issueDiv = createIssueCard(item);
                    sortContainer.append(issueDiv);
                });

            } else {
                // Handle case when data is empty
                document.querySelector('#no-result').classList.remove('d-none')
            }
        },

        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            console.log('XHR:', xhr);
            console.log('Status:', status);

            // Display the error response in the console
            try {
                const response = JSON.parse(xhr.responseText);
                console.log('Error response:', response);
            } catch (e) {
                console.log('Error parsing response:', e);
            }

            alert('Error fetching data.');
        }
    });
}


// for Category filter
function filterCategory() {
    var category = $('#category-filter').val();

    $.ajax({
        url: '/api/issue/list/category/' + category,
        type: 'GET',
        success: function (data) {

            const sortContainer = $('#sort-container');
            sortContainer.empty(); // Clear previous content

            // Check if data is not empty
            if (data && data.length > 0) {
                // Loop through the retrieved data and create content for each item
                document.querySelector('#no-result').classList.add('d-none')
                data.forEach(item => {
                    const issueDiv = createIssueCard(item);
                    sortContainer.append(issueDiv);
                });

            } else {
                // Handle case when data is empty
                document.querySelector('#no-result').classList.remove('d-none')
            }
        },

        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            console.log('XHR:', xhr);
            console.log('Status:', status);

            // Display the error response in the console
            try {
                const response = JSON.parse(xhr.responseText);
                console.log('Error response:', response);
            } catch (e) {
                console.log('Error parsing response:', e);
            }

            alert('Error fetching data.');
        }
    });
}

// for issue list
function showError(message) {
    $("#errorText").text(message);
    $("#errorAlert").show();

    setTimeout(function () {
        $("#errorAlert").hide();
    }, 3000);

    //close alert box
    $(".btn-close").on("click", function () {
        $("#errorAlert").hide();
    });
}

function showSuccess(message) {
    $("#successText").text(message);
    $("#successAlert").show();

    setTimeout(function () {
        $("#successAlert").hide();
    }, 3000);

    //close alert box
    $(".btn-close").on("click", function () {
        $("#successAlert").hide();
    });
}

function showerror(message) {
    $("#error-text").text(message);
    $("#error-alert").show();

    setTimeout(function () {
        $("#error-alert").hide();
    }, 3000);

    //close alert box
    $(".btn-close").on("click", function () {
        $("#error-alert").hide();
    });
}

function showsuccess(message) {
    $("#success-text").text(message);
    $("#success-alert").show();

    setTimeout(function () {
        $("#success-alert").hide();
    }, 3000);

    //close alert box
    $(".btn-close").on("click", function () {
        $("#success-alert").hide();
    });
}


