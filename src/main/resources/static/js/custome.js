$(document).ready(function () {

    // ========================== AJAX FUNCTION (JUST TESTING) ===========================

    function ajaxRequest(url, method = 'POST', data, callback) {
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    callback(xhr.responseText);
                } else {
                    // Handle errors here
                    console.error("Request failed with status code: " + xhr.status);
                }
            }
        };

        xhr.open(method, url, true);

        if (method === "POST") {
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        }

        xhr.send(data);
    }

    // Example of how to use the ajaxRequest function to send data with a POST request

    
    //  ======================== SORTING DEPARTMENT AND PROJECT START ========================

    let sortIcon = document.querySelector(".sort-btn");
    let sortableContainer = document.getElementById("sort-container");

    function sortCards(btn, order) {
        
        let sortableElement = Array.from(sortableContainer.children);

        sortableElement.sort(function (a, b) {
            console.log('in sort')
            var nameA = a.querySelector(".card-title").textContent.toUpperCase();
            var nameB = b.querySelector(".card-title").textContent.toUpperCase();

            if (order) {
                btn.classList.remove("bi-sort-alpha-down");
                btn.classList.add("bi-sort-alpha-up");
                return nameA.localeCompare(nameB);
            } else {
                btn.classList.add("bi-sort-alpha-down");
                btn.classList.remove("bi-sort-alpha-up");
                return nameB.localeCompare(nameA);
            }
        });

        sortableElement.forEach(function (department) {
            sortableContainer.appendChild(department);
        });
    }
    if (sortIcon != null) {
        sortIcon.addEventListener("click", function () {
            let isAsc = sortIcon.classList.contains("bi-sort-alpha-down");
            sortCards(this, isAsc);
        });
    }
    $(".circle-progress").each(function () {
        const minValue = $(this).attr("data-min-value");
        const maxValue = $(this).attr("data-max-value");
        const value = $(this).attr("data-value");
        const type = $(this).attr("data-type");

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

    // ======================== SORT DEPARTMENT AND PROJECT END HERE ========================

    // ======================== FORM VALIDATION START HERE ========================

    /* =======================================
     *   form must have an id='login-form'
     *   must have data-type='type'
     *   type = email (done), password (done)
     *  =======================================
     */

    let validatedForm = $("#login-form");

    // email validation
    function validateEmail(email) {
        const emailRegex =
            /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

        return emailRegex.test(email);
    }

    // password validation
    function validatePassword(password) {
        // Minimum length of 8 characters
        const minLength = 8;

        // Check if the password contains at least one alphanumeric character
        const containsAlphanumeric = /^[a-zA-Z0-9]+$/.test(password);

        // Check all criteria
        console.log(password.length >= minLength, containsAlphanumeric);
        return password.length >= minLength && containsAlphanumeric;
    }

    // check if the email and password are validated or not on submit
    if (validatedForm != null) {
        validatedForm.on("submit", function (e) {
            let email = $(this).find("#emailInput");
            let password = $(this).find("#floatingPassword");

            if (!validateEmail(email.val()) || !validatePassword(password.val())) {
                email.parent().addClass("was-validated");
                if (validateEmail(email.val())) {
                    email.parent().addClass("was-validated");
                }
                if (password.length === 0) {
                    return;
                }
                if (password.val() < 1) {
                    password.parent().addClass("was-validated");
                }
                e.preventDefault();
                e.stopPropagation();
                console.log("either email or password is not valid");
            }
        });

        // check the validation every time when use enter value in input
        // TODO :: SHOULD VALIDATE OTHER DATA-TYPE
        validatedForm.on("input", function (e) {
            let target = $(e.target);
            let parent = target.parent();
            let type = target.attr("data-type").toLowerCase();
            switch (type) {
                // for data-type='email'
                case "email":
                    console.log(validateEmail(target.val()));
                    let p1 = $("<p>")
                        .addClass("text-danger fs-6")
                        .text("Not a valid email");

                    if (!validateEmail(target.val())) {
                        parent.removeClass("was-validated");
                        if (parent.find("p").length === 0) {
                            parent.append(p1);
                        }
                    } else {
                        let existingP = parent.find("p");
                        if (existingP.length > 0) {
                            existingP.remove();
                        }
                        if (
                            parent.find(".valid-feedback").length === 0 &&
                            validateEmail(target.val())
                        ) {
                            existingP = $("<div>")
                                .addClass("valid-feedback")
                                .text("good");
                            parent.append(existingP);
                        }
                        parent.addClass("was-validated");
                    }
                    break;

                // for data-type='password'
                case "password":
                    parent.removeClass("was-validated");
                    let p = $("<p>")
                        .addClass("text-danger fs-6")
                        .text(
                            "The password can only contain numbers and alphabets with a minimum length of 8 characters"
                        );

                    if (!validatePassword(target.val())) {
                        parent.removeClass("was-validated");
                        if (parent.find("p").length === 0) {
                            parent.append(p);
                        }
                    } else {
                        let existingP = parent.find("p");
                        if (existingP.length > 0) {
                            existingP.remove();
                        }
                        if (
                            parent.find(".valid-feedback").length === 0 &&
                            validatePassword(target.val())
                        ) {
                            existingP = $("<div>")
                                .addClass("valid-feedback")
                                .text("good");
                            parent.append(existingP);
                        }
                        parent.addClass("was-validated");
                    }
                    break;
            }
        });
    }

    // ======================== FORM VALIDATION END HERE ========================

    // ======================== SEARCH BAR BEHAVIOR START HERE ========================

    const dDSBtn = $("#drop-down-search-btn");
    const dDSbar = $("#dropdrown-search-bar");

    $(window).on("resize", function () {
        if (window.innerWidth > 768) {
            // Screen size greater than 768px, hide the element
            dDSbar.addClass("d-none d-sm-none");
        }
    });

    if (dDSBtn != null) {
        // toggle search bar when click the search button
        dDSBtn.on("click", function () {
            console.log(dDSbar.hasClass("d-none", "d-sm-none"));
            if (dDSbar.hasClass("d-none", "d-sm-none")) {
                // Classes are present, so show the element
                dDSbar.removeClass("d-none d-sm-none");
            } else {
                console.log("false so should add classes");
                // Classes are not present, so hide the element
                dDSbar.addClass("d-none d-sm-none");
            }
        });
    }

    // close search bar when window width < 768
    if (window.innerWidth < 768) {
        dDSbar.addClass("d-none d-sm-none");
    }

    // ======================== SEARCH BEHAVIOR END HERE ========================

    // ======================== SIDE NAV LINK BEHAVIOR START ========================

    let urlPath = window.location.href;
    let currentAtag;

    // const sidebarLink = document
    //     .querySelector(".navbar")
    //     .querySelector(".navbar-nav").children;

    const  sidebarLink = [...document.querySelector('.navbar').querySelector('.navbar-nav').children]
    currentAtag = sidebarLink.find(a => urlPath.includes(a.getAttribute("href")))

    if(currentAtag) {
        currentAtag.classList.add('bg-primary', 'text-white')
        currentAtag.firstChild.classList.add('bg-primary')
    }

    // ======================== SIDE NAV LINK BEHAVIOR END ========================

    // ======================== change modal title automatic =======================

    // recursive function to search paremtn element that contain data-bs-toggle
    function hasDataBsToggleAttribute(element) {

        if (element && element.hasAttribute('data-bs-toggle')) {
            return element;
        }

        if (element && element.parentElement) {
            return hasDataBsToggleAttribute(element.parentElement);
        }

        return null
    }
    if (sortableContainer != null) {
        sortableContainer.addEventListener('click', function (e) {

            let target = e.target

            if (e.target === this || target.classList.contains('swim-lane') || target.classList.contains('lane')) return

            if (e.target.tagName === 'button') return

            const grandpaDiv = hasDataBsToggleAttribute(target)

            if (grandpaDiv === null) return

            if (grandpaDiv.getAttribute('data-bs-toggle') !== 'modal') return

            if (!['#project-details', '#department-details', '#task-details'].includes(grandpaDiv.getAttribute('data-bs-target'))) return;
            const targetModal = document.querySelector(grandpaDiv.getAttribute('data-bs-target'))

            console.log(targetModal)

            targetModal.querySelector('.modal-title').innerText = grandpaDiv.querySelector('.modal-detail-title').innerText

        }, false)
    }
    // ======================== change modal title automatic =======================


    const todoForm = document.getElementById("todo-form");
    const input = document.getElementById("todo-input");
    const todoLane = document.getElementById("todo-lane");
    const draggables = document.querySelectorAll(".task");
    const droppables = document.querySelectorAll(".swim-lane");
    const modal = document.getElementById("task-details");

    const trashCan = document.querySelector('.trash')

    todoForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const value = input.value;

        if (!value) return;

        let newTask = document.createElement('div');
        newTask.classList.add('task', 'py-1', 'row', 'rounded-2', 'border-primary', 'border-2', 'border');
        newTask.setAttribute('draggable', 'true');
        newTask.setAttribute('data-bs-toggle', 'modal');
        newTask.setAttribute('data-bs-target', '#task-details');

        // Create the inner content of the task
        newTask.innerHTML = `
                <div class="col-12 mb-2 lh-1 mh-50 modal-detail-title text-break">
                    ${value}
                </div>
                <div class="col d-flex justify-content-between align-items-center">
                    <img src="https://source.unsplash.com/random/200x200?sig=1" class="img-fluid rounded-circle" alt="" style="width: 30px;">
                </div>
            `;

        newTask.addEventListener("dragstart", (e) => {
            newTask.classList.add("is-dragging");
            console.log("deagging")
        });

        newTask.addEventListener('click', (e) => {
            modal.querySelector('.modal-title').innerText = newTask.innerText
        })

        newTask.addEventListener("dragend", () => {
            newTask.classList.remove("is-dragging");
            console.log(newTask);
        });

        todoLane.appendChild(newTask);

        input.value = "";
    });

    draggables.forEach((task) => {
        task.addEventListener("dragstart", () => {
            task.classList.add("is-dragging");
        });
        task.addEventListener("dragend", () => {
            task.classList.remove("is-dragging");

            console.log("drop");
            console.log(
                task.parentNode.previousElementSibling.innerText
            );
            let postData = JSON.stringify({ key1: "value1", key2: "value2" });
            ajaxRequest("/data", "POST", postData, function (response) {
                // Handle the response data here
                console.log(response);
            });
        });
    });

    // ======================= TRASH BRN ======================

    if(trashCan != null) {
        const comfirmBox = document.querySelector('#confirm-box')
        const bModal = new bootstrap.Modal(comfirmBox)
        let currentTask;
        console.log(trashCan)
        trashCan.addEventListener('dragover', function (e) {
            e.preventDefault();
            currentTask = document.querySelector('.is-dragging')
            if(currentTask === null) return

            console.log(this)
            this.classList.add('drag-over')

        })

        trashCan.addEventListener('dragleave', function(e) {
            this.classList.remove('drag-over')
        })

        trashCan.addEventListener('drop', function (e) {
            bModal.show()
        })

        comfirmBox.addEventListener('click', function(e) {
            if(e.target.innerText.toLowerCase() === 'yes') {
                currentTask.remove()
                trashCan.classList.remove('drag-over')
                bModal.hide()
            } else if (e.target.innerText.toLowerCase() === 'no') {
                bModal.hide()
            }
            
        })
    }

    // =========================== TRASH BIN =============================

    // ========================== AJAX FUNCTION (JUST TESTING) ===========================

    droppables.forEach((zone) => {
        zone.addEventListener("dragover", (e) => {
            e.preventDefault();

            console.log(undefined)

            const bottomTask = insertAboveTask(zone, e.clientY);
            const curTask = document.querySelector(".is-dragging");

            if (curTask === null) {
                return
            }

            const curZone = zone.getAttribute("id");

            switch (curZone) {
                case "todo-lane":
                    curTask.classList.add("border-primary");
                    curTask.classList.remove(
                        "border-dark",
                        "border-success"
                    );
                    break;
                case "doing-lane":
                    curTask.classList.add("border-dark");
                    curTask.classList.remove(
                        "border-primary",
                        "border-success"
                    );
                    break;
                case "done-lane":
                    curTask.classList.add("border-success");
                    curTask.classList.remove(
                        "border-primary",
                        "border-dark"
                    );
                    break;
            }

            if (!bottomTask) {
                zone.appendChild(curTask);
            } else {
                zone.insertBefore(curTask, bottomTask);
            }
        });
    }); const insertAboveTask = (zone, mouseY) => {
        const els = zone.querySelectorAll(".task:not(.is-dragging)");

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


    const ctx = document.getElementById("department-projects-chart");

    const firstChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: [
                "January",
                "February",
                "March",
                "Apirl",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",
            ],

            datasets: [
                {
                    label: "project one",
                    backgroundColor: "rgba(255, 111, 200, 0.5)",
                    data: [0, 0, 3, 5, 2, 3],
                    fill: true,
                    borderColor: "rgb(255, 111, 200)",
                    pointRadius: 0,
                    borderWidth: 1,
                },
                {
                    label: "project two",
                    backgroundColor: "rgba(100, 111, 200, 0.5)",
                    data: [20, 10, 58, 12],
                    fill: true,
                    borderColor: "rgb(100, 111, 200)",
                    pointRadius: 0,
                    borderWidth: 1,
                },
                {
                    label: "project one",
                    backgroundColor: "rgba(255, 0, 0, 0.5)",
                    data: [23, 85, 10, 5, 25, 7, 65, 77, 10],
                    fill: true,
                    borderColor: "rgb(255, 0, 0)",
                    pointRadius: 0,
                    borderWidth: 1,
                },
                {
                    label: "project one",
                    backgroundColor: "rgba(0, 255, 0, 0.5)",
                    data: [5, 50, 10, 5, 54, 3, 65],
                    fill: true,
                    borderColor: "rgb(0, 255, 0)",
                    pointRadius: 0,
                    borderWidth: 1,
                },
                {
                    label: "project one",
                    backgroundColor: "rgba(0, 0, 255, 0.5)",
                    data: [
                        89, 45, 33, 15, 25, 3, 34, 44, 58, 89, 45, 33,
                    ],
                    fill: true,
                    borderColor: "rgb(0, 0, 255)",
                    pointRadius: 0,
                    borderWidth: 1,
                },
            ],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
            animation: {
                duration: 2000,
            },
            legend: {
                display: true,
            },
            responsive: false
        },
    });

    console.log(firstChart);
    firstChart.config.options.animation.duration = 5000;
});

