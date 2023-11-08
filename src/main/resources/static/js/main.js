(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 5000);
    };
    spinner();
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 500, 'easeInOutExpo');
        return false;
    });


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    // Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});


    //calendar



    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav : false
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
        },
        error: function (data,xhr) {
            console.log("error",data,xhr);
        }
    });

    
})(jQuery);





