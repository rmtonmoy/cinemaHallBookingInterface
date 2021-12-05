// Booking Time JS by James Hyun
// Handles swapping out booking time options based on movie selected
"use strict";

var showId;
var theaterStats;

function doAttributesMatch(a, b, attributes) {
    for (let attr of attributes) {
        if (a.attr(attr) != b.attr(attr)) {
            return false;
        }
    }
    return true;
}

function resetTickets() {
    $(".ticket").remove();
    $("#ticketsSection").addAttr("hidden");
    $("#rmTicketBtn").addClass("invisible");
    $("#submitBtn").addClass("invisible");
}

$(document).ready(function() {
    let ticketIndex = -1;
    
    $(".movieRadioBtn").change(function() {
        let btn = $(this);
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
        if (ticketIndex != -1) {
            resetTickets();
            ticketIndex = -1;
        }
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
            if (ticketIndex != -1) {
                resetTickets();
                ticketIndex = -1;
            }
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
            if (ticketIndex != -1) {
                resetTickets();
                ticketIndex = -1;
            }
        });
    });
    
    $("#showTime").change(function() {
        showId = $(this).val();
        updateTheaterStats();
    });
    
    $("#addTicketBtn").click(function() {
        ticketIndex++;
        if (ticketIndex == 0) {
            $("#rmTicketBtn").removeClass("invisible");
            $("#submitBtn").removeClass("invisible");
        }
        $("#ticketContainer").append(createTicket(ticketIndex));
        $("#ticket" + ticketIndex + " button").click(function(){
            // TODO button click event
            // Use a hidden input (or other element) to save the seat selection for form submission
            let button = $(this);
            $("#seat" + ticketIndex).val(button.attr("data-id"));
            
            // TODO make it look nice
        });
    });
    
    $("#rmTicketBtn").click(function() {
        $("#ticket" + ticketIndex).remove();
        ticketIndex--;
        if (ticketIndex == -1) {
            $("#rmTicketBtn").addClass("invisible");
            $("#submitBtn").addClass("invisible");
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
    return `<div id="ticket${num}" class="form-group ticket">
    <h3>Ticket ${num + 1}</h3>
    <label for="typeOfTicket">Ticket Type:</label>
    <select th:field="*{tickets[${num}].type}" name="typeOfTicket${num}" id="typeOfTicket${num}" class="form-control col-1">
        <option value="adult">Adult</option>
        <option value="child">Child</option>
    </select>

    <h3>Seat:</h3>
    <!-- <h4>Available Seating</h4> -->
    ${createSeatingChart(num)}
</div>`;
}

function createSeatingChart(num) {
    return _createSeatingChart(num, theaterStats.rows, theaterStats.cols, theaterStats.availability);
}

function updateTheaterStats() {
    $.get("/book/gettheaterstats?id=" + showId, null, function(data) {
        theaterStats = data;
        $("#ticketsSection").removeAttr("hidden");
    }, "json");
}

// `taken` is a 2D array of booleans representing if each seat is taken or not. Access a seat via taken[row][column].
// false indicates the seat is FREE, true indicates the seat is TAKEN.
function _createSeatingChart(num, row, col, taken) {
    let str = `<div class="seatingChart"><input type="hidden" id="seat${num}" name="seat${num}" value="">` // `
            //+ `th:field="*{tickets[${num}].REPLACE_THIS_WITH_THE_RIGHT_MEMBER}" >`; // TODO TODO TODO TODO TODO TODO
    for (let r = 1; r <= row; r++) {
        str += '<div class="container"><div class="btn-group">';
        for (let c = 1; c <= col; c++) {
            let disabled = "";
            if (taken[r - 1][c - 1]) {
                disabled = " disabled";
            }
            str += `<button type="button" class="btn btn-primary" data-id="${r}-${c}"`
                +  `${disabled}>${r}-${c}</button>`;
        }
        str += '</div></div>';
    }
    return str;
}