// Signup Form JS by James Hyun
// Handles showing and hiding credit card fields on the signup form
"use strict";

var cards = 0;

$(document).ready(function() {
    $("#addCardBtn").click(function() {
        if (cards >= 3) return;
        cards++;
        if (cards == 1) {
            $("#rmCardBtn").removeClass("invisible");
        }
        else if (cards == 3) {
            $("#addCardBtn").addClass("invisible");
        }
        let card = $("#card" + cards);
        card.removeAttr("hidden");
        $("#card" + cards + " .form-group input").attr("required", ""); // for some reason card.children() won't work right?
    });
    $("#rmCardBtn").click(function() {
        if (cards <= 0) return;
        let card = $("#card" + cards);
        card.attr("hidden", "");
        $("#card" + cards + " .form-group input").val("").removeAttr("required");
        cards--;
        if (cards == 0) {
            $("#rmCardBtn").addClass("invisible");
        }
        else if (cards == 2) {
            $("#addCardBtn").removeClass("invisible");
        }
    });
});