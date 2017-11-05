// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require_self

var CKEDITOR_BASEPATH = '/assets/ckeditor/';

var pattern = new RegExp("^[a-zA-Z0-9]*[ ]*[a-zA-Z0-9]*$");

if (typeof jQuery !== 'undefined') {
    (function ($) {
        $(document).ajaxStart(function () {
            $(window).load(function () {
                setTimeout(function () {
                    removeExistingMessageHolder();
                }, 2000)
            });
            $('#spinner').fadeIn();
        }).ajaxStop(function () {
            $('#spinner').fadeOut();
        });
        $(document).ajaxComplete(function (event, xhr, settings) {
            $('#spinner').fadeOut();
            if (xhr.status == 401 && xhr.statusText === "Unauthorized") {
                bootbox.alert({
                    message: "You have been logged out due to inactivity.",
                    buttons: {
                        ok: {
                            label: 'Login',
                            className: 'btn-success'
                        }
                    },
                    callback: function () {
                        window.location.href = "/login/auth"
                    }
                });
            }
        });

        $(".dropdown-menu > li > a.trigger").on("click", function (e) {
            var current = $(this).next();
            var grandparent = $(this).parent().parent();
            if ($(this).hasClass('dropdown-toggle'))
                $(this).toggleClass('open');
            grandparent.find('.dropdown-toggle').not(this).toggleClass('open');
            grandparent.find(".sub-menu:visible").not(current).hide();
            current.toggle();
            e.stopPropagation();
        });

        $(".dropdown-menu > li > a:not(.trigger)").on("click", function () {
            var root = $(this).closest('.dropdown');
            root.find('.dropdown-toggle').toggleClass('open');
            root.find('.sub-menu:visible').hide();
        });
    })(jQuery);
}
$(document).ready(function(){
    $("table tr th input[type='checkbox']").parents("table").addClass('checkbox-contain');
    $('#navbar .multi-level .dropdown-submenu > a').after('<span class="fa fa-caret-right"></span>');
    $('#navbar .multi-level .dropdown-submenu > .fa-caret-right').click(function(){
        $('#navbar .multi-level .dropdown-submenu  .dropdown-menu').css('display','none');
        $(this).next('.dropdown-menu').css('display','block');
        return false;
    });
    $('#navbar .multi-level .dropdown-submenu > a').on('click', function(e){
        $('#navbar .multi-level .dropdown-submenu > .fa-caret-right').click();
        e.stopPropagation();
    });
    $("#navbar").click(function(){
        $('.multi-level .dropdown-submenu  .dropdown-menu').css('display','none');
    })

});