// Booking Time JS by James Hyun
// Handles swapping out booking time options based on movie selected
"use strict";

var showId;
var theaterStats;
var params = {};

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
    $("#ticketsSection").attr("hidden", "");
    $("#rmTicketBtn").addClass("invisible");
    $("#submitBtn").addClass("invisible");
    params = {};
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
        let thisTicketIndex = ticketIndex;
        if (thisTicketIndex == 0) {
            $("#rmTicketBtn").removeClass("invisible");
            $("#submitBtn").removeClass("invisible");
        }
        $("#ticketContainer").append(createTicket(thisTicketIndex));
        $("#ticket" + thisTicketIndex + " button").click(function(){
            let button = $(this);
            let nums   = getRowAndColumn(button.attr("data-id"));
            let row    = nums[0];
            let col    = nums[1];
            
            if (theaterStats.availability[row][col]) return;
            
            let oldVal = $("#seat" + thisTicketIndex).val();
            if (oldVal) {
                let oldNums = getRowAndColumn(oldVal);
                theaterStats.availability[oldNums[0]][oldNums[1]] = false;
                $(`[data-id=${oldVal}]`).addClass("btn-primary");
                $(`[data-id=${oldVal}]`).removeClass("btn-success");
                console.log(`[data-id=${oldVal}]`);
            }
            
            theaterStats.availability[row][col] = true;
            $("#seat" + thisTicketIndex).val(button.attr("data-id"));
            
            $(`[data-id=${button.attr("data-id")}]`).removeClass("btn-primary");
            $(`[data-id=${button.attr("data-id")}]`).addClass("btn-secondary");
            button.removeClass("btn-secondary"); // this algorithm is stupid but it's simple and i cba on saturday night
            button.addClass("btn-success");
            
            params[thisTicketIndex] = button.attr("data-id");
            writeToParam();
        });
    });
    
    $("#rmTicketBtn").click(function() {
        let thisTicketIndex = ticketIndex;
        ticketIndex--;
        if (ticketIndex == -1) {
            $("#rmTicketBtn").addClass("invisible");
            $("#submitBtn").addClass("invisible");
        }
        let seatId = $(`#seat${thisTicketIndex}`).val();
        if (seatId && seatId != "") {
            $(`[data-id=${seatId}]`).addClass("btn-primary");
            $(`[data-id=${seatId}]`).removeClass("btn-success");
            let nums = getRowAndColumn(seatId);
            theaterStats.availability[nums[0]][nums[1]] = false;
        }
        $("#ticket" + thisTicketIndex).remove();
        delete params[thisTicketIndex];
        writeToParam();
    });
    
    $("option").each(function() {
        $(this).hide();
    });
    $("#showDate").val(0);
    $("#theater").val(0);
    $("#showTime").val(0);
});

function getRowAndColumn(seatId) {
    return [
        parseInt(seatId.substring(0, seatId.indexOf('-')))  - 1,
        parseInt(seatId.substring(seatId.indexOf('-') + 1)) - 1,
    ];
}

function createTicket(num) {
    return `<div id="ticket${num}" class="form-group ticket">
    <h3>Ticket ${num + 1}</h3>
    <label for="typeOfTicket">Ticket Type:</label>
    <select name="typeOfTicket${num}" id="typeOfTicket${num}" class="form-control col-1">
        <option value="adult">Adult</option>
        <option value="child">Child</option>
        <option value="senior">Senior</option>
        <option value="student">Student</option>
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
    let str = `<div class="seatingChart"><input type="hidden" id="seat${num}">`
    for (let r = 1; r <= row; r++) {
        str += '<div class="container"><div class="btn-group">';
        for (let c = 1; c <= col; c++) {
            let btnClass;
            if (taken[r - 1][c - 1]) {
                btnClass = "btn-secondary";
            } else {
                btnClass = "btn-primary";
            }
            str += `<button type="button" class="btn ${btnClass}" data-id="${r}-${c}">${r}-${c}</button>`;
        }
        str += '</div></div>';
    }
    return str;
}

function writeToParam() {
    let str = "";
    for (let i in params) { // index:type:row:col;
        let nums = getRowAndColumn(params[i]);
        str += "" + i + ":" + $("#typeOfTicket" + i).val() + ":" + nums[0] + ":" + nums[1] + ";";
    }
    $("#ticketsParam").val(str);
}