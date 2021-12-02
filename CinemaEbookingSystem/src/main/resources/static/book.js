// Booking Time JS by James Hyun
// Handles swapping out booking time options based on movie selected
"use strict";

var showId;

function doAttributesMatch(a, b, attributes) {
    for (let attr of attributes) {
        if (a.attr(attr) != b.attr(attr)) {
            return false;
        }
    }
    return true;
}

$(document).ready(function() {
    let ticketIndex = -1;
    
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
        $("#theater").val(0);
        $("#showTime").val(0);
    });
    
    $("#showDate").change(function() {
        let selected = $(this).find(":selected");
        let id       = selected.attr("data-movie");
        $("#theater > option").each(function() {
            console.log("showing theaters");
            let opt = $(this);
            if (doAttributesMatch(opt, selected, ["data-movie", "data-showing-date"])) {
                opt.show();
            } else {
                opt.hide();
            }
            $("#theater").val(0);
            $("#showTime").val(0);
        });
    });
    
    $("#theater").change(function() {
        let selected = $(this).find(":selected");
        let id       = selected.attr("data-movie");
        $("#showTime > option").each(function() {
            console.log("showing show times");
            let opt = $(this);
            if (doAttributesMatch(opt, selected, ["data-movie", "data-showing-date", "data-theater"])) {
                opt.show();
            } else {
                opt.hide();
            }
            $("#showTime").val(0);
        });
    });
    
    $("#showTime").change(function() {
        showId = $(this).val();
    })
    
    $("#addTicketBtn").click(function() {
        ticketIndex++;
        if (ticketIndex == 0) {
            $("#rmTicketBtn").removeClass("invisible");
        }
        $("#ticketContainer").append(createTicket(ticketIndex));
    });
    
    $("#rmTicketBtn").click(function() {
        $("#ticket" + ticketIndex).remove();
        ticketIndex--;
        if (ticketIndex == -1) {
            $("#rmTicketBtn").addClass("invisible");
        }
    });
    
    $("option").each(function() {
        $(this).hide();
    });
    $("#showDate").val(0);
    $("#theater").val(0);
    $("#showTime").val(0);
});

function createTicket(num) {
    return `<div id="ticket${num}" class="form-group">
    <h3>Ticket ${num + 1}</h3>
    <label for="typeOfTicket">Ticket Type:</label>
    <select th:field="*{tickets[${num}].type}" name="typeOfTicket" id="typeOfTicket" class="form-control col-1">
        <option value="adult">Adult</option>
        <option value="child">Child</option>
    </select>

    <h3>Seat:</h3>
    <!-- <h4>Available Seating</h4> -->
    ${createSeatingChart(num)}
</div>`;
}

function createSeatingChart(num) {
    let arr = getTheaterStats();
    return _createSeatingChart(num, arr[0], arr[1], arr[2]);
}

function getTheaterStats() {
    $.get("/book/gettheaterstats?id=" + showId, null, function(data, status, jqXHR) {
        // TODO: process response
        console.log(data);
    }, "json");
    return [5, 5];
}

function _createSeatingChart(num, row, col, taken) {
    let str = '<div class="seatingChart">';
    for (let r = 1; r <= row; r++) {
        str += '<div class="container"><div class="btn-group">'
        for (let c = 1; c <= col; c++) {
            str += `<button th:field="*{tickets[${num}].type}" type="button" class="btn btn-primary" data-id="${r}-${c}">${r}-${c}</button>`;
        }
        str += '</div></div>';
    }
    return str;
}