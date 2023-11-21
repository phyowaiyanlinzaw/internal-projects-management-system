// logout-confirmation.js

function setupLogoutConfirmation() {
    // Add a click event listener to the "Log Out" link
    $("#logout").click(function (e) {
        e.preventDefault(); // Prevent the default link behavior

        Swal.fire({
            title: "Are you sure want to log out!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirect to the logout URL if the user confirms
                window.location.href = $(this).attr("href");
            }
        });
    });
}

function confirmLogout() {
    // Add a click event listener to the "Log Out" link
    $("#logout-with-search").click(function (e) {
        e.preventDefault(); // Prevent the default link behavior

        Swal.fire({
            title: "Are you sure want to log out!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirect to the logout URL if the user confirms
                window.location.href = $(this).attr("href");
            }
        });
    });
}

