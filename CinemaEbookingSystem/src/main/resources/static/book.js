// Booking Time JS by James Hyun
// Handles swapping out booking time options based on movie selected
"use strict";

function showHide(element, id) {
    if (element.attr("data-movie") == id) {
        element.show();
    } else {
        element.hide();
    }
}

$(document).ready(function() {
    $(".movieRadioBtn").change(function() {
        let id = $(this).attr("data-movie");
        $("#showDate > option").each(function() {
            console.log("showing show date");
            showHide($(this), id);
        });
        $("#showTime > option").each(function() {
            console.log("showing show time");
            showHide($(this), id);
        });
        $("#showDate").val(0);
        $("#showTime").val(0);
    });
    
    $("option").each(function() {
        $(this).hide();
    })
    $("#showDate").val(0);
    $("#showTime").val(0);
});