$(document).ready(function () {
    //  ======================== SORTING DEPARTMENT AND PROJECT START ========================



    let sortIcon = $(".sort-btn");
    function sortCards(order) {

        let sortableContainer = $("#sort-container");
        let sortableElement = sortableContainer.children().toArray();

        sortableElement.sort(function (a, b) {
            var nameA = $(a).find(".card-title").text().toUpperCase();
            var nameB = $(b).find(".card-title").text().toUpperCase();

            if (order) {
                sortIcon
                    .removeClass("bi-sort-alpha-down")
                    .addClass("bi-sort-alpha-up");
                return nameA.localeCompare(nameB);
            } else {
                sortIcon
                    .addClass("bi-sort-alpha-down")
                    .removeClass("bi-sort-alpha-up");
                return nameB.localeCompare(nameA);
            }
        });

        $.each(sortableElement, function (_, department) {
            sortableContainer.append(department);
        });
    }

    sortIcon.on("click", function () {
        let isAsc = sortIcon.hasClass("bi-sort-alpha-down");
        sortCards(isAsc);
    });
    $(".circle-progress").each(function () {
        const minValue = $(this).attr("data-min-value");
        const maxValue = $(this).attr("data-max-value");
        const value = $(this).attr("data-value");
        const type = $(this).attr("data-type");

        console.log(this.id)
        console.log(value);
        console.log(maxValue)

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

    let form = $("#login-form");

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
    form.on("submit", function (e) {
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
    form.on("input", function (e) {
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

    // close search bar when window width < 768
    if (window.innerWidth < 768) {
        dDSbar.addClass("d-none d-sm-none");
    }

    // ======================== SEARCH BEHAVIOR END HERE ========================

    // ======================== SIDE NAV LINK BEHAVIOR START ========================

    let urlPath = window.location.href;

    const sidebarLink = document
        .querySelector(".navbar")
        .querySelector(".navbar-nav").children;

    console.log(urlPath);
    console.log(sidebarLink[0]);
    console.log(urlPath.includes("dashboard"));

    if (urlPath.includes("dashboard")) {
        sidebarLink[0].classList.add("bg-primary", "text-white");
        sidebarLink[0].firstChild.classList.add("bg-primary");
    } else if (urlPath.includes("department", "text-white")) {
        sidebarLink[1].classList.add("bg-primary", "text-white");
        sidebarLink[1].firstChild.classList.add("bg-primary");
    } else if (urlPath.includes("project")) {
        sidebarLink[2].classList.add("bg-primary", "text-white");
        sidebarLink[2].firstChild.classList.add("bg-primary");
    }

    // ======================== SIDE NAV LINK BEHAVIOR END ========================

    // ======================== change modal title automatic =======================

    let sortableContainer = document.querySelector("#sort-container");
    const modal = document.querySelector('div[id$="-details"]')

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

    sortableContainer.addEventListener('click', function (e) {

        let target = e.target

        if (e.target === this || target.classList.contains('swim-lane') || target.classList.contains('lane')) return

        if (e.target.tagName === 'button') return

        console.log('true')

        const grandpaDiv = hasDataBsToggleAttribute(target)

        if (grandpaDiv === null) return

        if (grandpaDiv.getAttribute('data-bs-toggle') !== 'modal') return

        if (!['#project-details', '#department-details', '#task-details'].includes(grandpaDiv.getAttribute('data-bs-target'))) return;

        console.log(grandpaDiv)
        console.log(grandpaDiv.tagName)

        modal.querySelector('.modal-title').innerText = grandpaDiv.querySelector('.modal-detail-title').innerText

    }, false)

    // ======================== change modal title automatic =======================
});

