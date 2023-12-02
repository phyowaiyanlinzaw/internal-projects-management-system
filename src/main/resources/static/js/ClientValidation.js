function validateName() {
    const name = $("#client-name").val();
    const errorContainer = $("#client-name").siblings(".error-container");
    if (name.length < 1) {
        
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text("Name is required")
                .css("margin", "0")
                .addClass("error-message");

            $("#client-name").after('<div class="error-container"></div>');
            $("#client-name").siblings(".error-container").append(p);
        }

        $("#client-name").addClass("is-invalid");
        $("#client-name").removeClass("is-valid");
        return false;
    } else {
        errorContainer.remove();
        $("#client-name").addClass("is-valid");
        $("#client-name").removeClass("is-invalid");
        return true;
    }
}

function validateEmail() {
    const email = $("#client-email").val();
    const emailRegex =
        /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    const errorContainer = $("#client-email").siblings(".error-container");
    if (emailRegex.test(email) === false) {
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text("Email is required")
                .css("margin", "0")
                .addClass("error-message");

            $("#client-email").after('<div class="error-container"></div>');
            $("#client-email").siblings(".error-container").append(p);
        }

        $("#client-email").addClass("is-invalid");
        $("#client-email").removeClass("is-valid");
        return false;
    } else {
        errorContainer.remove();
        $("#client-email").addClass("is-valid");
        $("#client-email").removeClass("is-invalid");
        return true;
    }
}
function validatePhone() {
    const phone = $("#client-phone").val();
    const errorContainer = $("#client-phone").siblings(".error-container");

    // Check for the phone number pattern
    const isValidPhone = /^[0-9]{11}$/.test(phone);
    if (!isValidPhone) {
        if (errorContainer.length === 0) {
            let p = $("<p>")
                .addClass("text-danger fs-6")
                .text("Please enter a valid phone number with 11 digits.")
                .css("margin", "0")
                .addClass("error-message");

            $("#client-phone").after('<div class="error-container"></div>');
            $("#client-phone").siblings(".error-container").append(p);
        }

        $("#client-phone").addClass("is-invalid");
        $("#client-phone").removeClass("is-valid");
        return false;
    } else {
        errorContainer.remove();
        $("#client-phone").addClass("is-valid");
        $("#client-phone").removeClass("is-invalid");
        return true;
    }
}

export {validateName, validateEmail, validatePhone}