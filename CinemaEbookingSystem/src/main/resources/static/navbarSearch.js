// Navbar Search JS by James Hyun
// Handles search bar in navbar - doing via JS to avoid really obnoxious Thymeleaf crap
"use strict";

function foo() {
    window.location.href = "/search?title=" + $("#searchBox").val();
}

$(document).ready(function() {
    $("#searchBtn").click(foo);
    $("#searchBox").on("keypress", function(e) {
        if (e.which == 13) { // enter key
            foo();
        }
    })
});