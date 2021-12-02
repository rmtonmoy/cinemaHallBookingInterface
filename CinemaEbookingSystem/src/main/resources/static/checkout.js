"use strict";

var cards = 0;

$(document).ready(function() {
    $("#addCardInfo").click(function() {
        $("#addCardInfo").addClass("invisible");
        let card = $("#checkoutcard");
        let card2 = $("#oldcards")
        card.removeAttr("hidden");
        $("#checkoutcard.form-group input").attr("required", "");
    });
});