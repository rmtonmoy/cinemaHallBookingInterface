// Booking Time JS by James Hyun
// Handles swapping out booking time options based on movie selected
"use strict";

$(document).ready(function() {
    $(".movieRadioBtn").change(function() {
        let btn = $(this)
        let id  = btn.attr("data-movie");
        $("#showDate > option").each(function() {
            console.log("showing show date");
            if ($(this).attr("data-movie") == id) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
        $("#showDate").val(0);
        $("#showTime").val(0);
    });
    
    $("#showDate").change(function() {
        let selected = $(this).find(":selected");
        let id       = selected.attr("data-movie");
        $("#showTime > option").each(function() {
            console.log("showing show time");
            let opt = $(this);
            if (opt.attr("data-movie") == id && opt.attr("data-showing-date") == selected.attr("data-showing-date")) {
                opt.show();
            } else {
                opt.hide();
            }
            $("#showTime").val(0);
        });
    });
    
    $("option").each(function() {
        $(this).hide();
    });
    $("#showDate").val(0);
    $("#showTime").val(0);
});