$(document).ready(function () {



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
            console.log(isAsc)
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
        const containsAlphanumeric = /^[a-zA-Z0-9!@#$%^&*]+$/.test(password);

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
                        .text("Not a valid email")
                        .css("margin", "0");

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
                            "The password can only contain a-z, A-Z, !@#$%^&* with a minimum length of 8 characters"
                        )
                        .css("margin", "0");

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

    const dDSBtn = document.getElementById("drop-down-search-btn");
    const dDSbar = document.getElementById("dropdrown-search-bar");

    if (dDSbar != null) {
        window.addEventListener("resize", function () {
            if (window.innerWidth > 768) {
                // Screen size greater than 768px, hide the element
                dDSbar.classList.add("d-none", "d-sm-none");
            }
        });
    }

    if (dDSBtn != null) {
        // toggle search bar when click the search button
        dDSBtn.addEventListener("click", function () {
            if (dDSbar.classList.contains("d-none", "d-sm-none")) {
                // Classes are present, so show the element
                dDSbar.classList.remove("d-none", "d-sm-none");
            } else {
                // Classes are not present, so hide the element
                dDSbar.classList.add("d-none", "d-sm-none");
            }
        });


        dDSbar.addEventListener('input', function (e) {

            let target = e.target

            if (target.getAttribute('type') === 'search') {

                let inputText = target.value.toLowerCase();
                let allData = sortableContainer.children;

                for (let i = 0; i < allData.length; i++) {
                    let text = allData[i].querySelector('.card-title').textContent.toLowerCase(); // Use .textContent to get the text

                    if (text.includes(inputText)) {
                        allData[i].style.display = 'block';
                    } else {
                        allData[i].style.display = 'none';
                    }
                }
            }
        });

    }

    // close search bar when window width < 768
    if (dDSbar != null) {
        if (window.innerWidth < 768) {
            dDSbar.classList.add("d-none", "d-sm-none");
        }
    }

    // ======================== SEARCH BEHAVIOR END HERE ========================

    // ======================== SIDE NAV LINK BEHAVIOR START ========================

    let urlPath = window.location.href;
    let currentAtag;

    // const sidebarLink = document
    //     .querySelector(".navbar")
    //     .querySelector(".navbar-nav").children;

    const sidebarLink = [...document.querySelector('.navbar').querySelector('.navbar-nav').children]
    currentAtag = sidebarLink.find(a => urlPath.includes(a.getAttribute("href")))

    if (currentAtag) {
        currentAtag.classList.add('bg-primary', 'text-white')
        currentAtag.firstChild.classList.add('bg-primary')
    }

    // ======================== SIDE NAV LINK BEHAVIOR END ========================

    // ======================== change modal title automatic =======================

    // recursive function to search paremtn element that contain data-bs-toggle
    function hasDataBsToggleAttribute(element) {

        if (element && element.classList.contains('card')) {
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

            console.log('i don\'t know')

            const grandpaDiv = hasDataBsToggleAttribute(target)

            console.log('grandpadiv', grandpaDiv)

            if (grandpaDiv === null) return

            console.log('grandpadiv', grandpaDiv)

            const targetModal = document.querySelector(target.getAttribute('data-bs-target'))
            console.log(target.getAttribute('data-bs-target'))

            console.log(targetModal)

            if (targetModal === null) return

            console.log(grandpaDiv.querySelector('.modal-detail-title').innerText)

            targetModal.querySelector('.modal-title').innerText = grandpaDiv.querySelector('.modal-detail-title').innerText

        }, false)
    }
    // ======================== change modal title automatic =======================


    // Get all date input elements using a common class name or another method
    const dateInputs = document.querySelectorAll('input[type="date"]');

    // Get the current date and format it as yyyy-MM-dd
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    const minDate = `${year}-${month}-${day}`;

    // Set the min attribute for each date input
    dateInputs.forEach((dateInput) => {
        dateInput.setAttribute('min', minDate);
    });

});

