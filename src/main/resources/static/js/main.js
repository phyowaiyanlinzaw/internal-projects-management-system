(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    //ajax call api/user/profile to get current user name to set #username
    $.ajax({
        url: '/api/user/profile',
        type: 'GET',
        success: function (data) {
            console.log("current user",data);
            //set #avatar-nav according to data.role
            if(data.role === "PMO" || data.role === "SDQC"){
                $('.avatar-nav').attr("src","/images/avatars/pmo_avatar.svg");
            }else if(data.role === "DEPARTMENT_HEAD"){
                $('.avatar-nav').attr("src","/images/avatars/departmenthead_avatar.svg");
            }else if (data.role === "PROJECT_MANAGER"){
                $('.avatar-nav').attr("src","/images/avatars/pm_avatar.svg");
            }else{
                $('.avatar-nav').attr("src","/images/avatars/employee_avatar.svg");
            }
            $('#navbar-username').text(data.name);
            $('#navbar-username-without-search').text(data.name);
        },
        error: function (data,xhr) {
            console.log("error",data,xhr);
        }
    });

    
})(jQuery);





