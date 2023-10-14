$(document).ready(function () {
    let sortIcon = $(".sort-btn");

    function sortCards(order) {
        let departmentContainer = $("#sort-container");
        let departments = departmentContainer.children().toArray();

        departments.sort(function (a, b) {
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

        $.each(departments, function (_, department) {
            departmentContainer.append(department);
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

        if (this.id !== "" && this.id !== null) {
            new CircleProgress("#" + this.id, {
                min: minValue,
                max: maxValue,
                value: value,
                textFormat: type,
            });
        }
    });
    let form = $("#login-form");

    function validateEmail(email) {
        const emailRegex =
            /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

        return emailRegex.test(email);
    }

    function validatePassword(password) {
        // Minimum length of 8 characters
        const minLength = 8;

        // Check if the password contains at least one alphanumeric character
        const containsAlphanumeric = /^[a-zA-Z0-9]+$/.test(password);

        // Check all criteria
        console.log(password.length >= minLength, containsAlphanumeric);
        return password.length >= minLength && containsAlphanumeric;
    }

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

    form.on("input", function (e) {
        let target = $(e.target);
        let parent = target.parent();
        let type = target.attr("data-type").toLowerCase();
        switch (type) {
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

    const dDSBtn = $("#drop-down-search-btn");
    const dDSbar = $("#dropdrown-search-bar");

    $(window).on("resize", function() {
      if (window.innerWidth > 768) {
        // Screen size greater than 768px, hide the element
        dDSbar.addClass("d-none d-sm-none");
      }
    });

    // Add click event listener to toggle visibility on button click
    dDSBtn.on("click", function() {
      console.log(dDSbar.hasClass('d-none', 'd-sm-none'))
      if (dDSbar.hasClass('d-none', 'd-sm-none')) {
        // Classes are present, so show the element
        dDSbar.removeClass("d-none d-sm-none");
      } else {
        console.log('false so should add classes')
        // Classes are not present, so hide the element
        dDSbar.addClass("d-none d-sm-none");
      }
    });

    if (window.innerWidth < 768) {
      // Screen size less than 768px, hide the element
      dDSbar.addClass("d-none d-sm-none");
    }
});
