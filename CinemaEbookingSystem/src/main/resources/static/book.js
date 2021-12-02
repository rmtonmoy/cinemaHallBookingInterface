// Booking Time JS by James Hyun
// Handles swapping out booking time options based on movie selected
"use strict";

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
        // placeholder
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
    <h4>Available Seating</h4>

    <div class="container">
        <div class="btn-group">
            <button type="button" class="btn btn-primary">A1</button>
            <button type="button" class="btn btn-primary">A2</button>
            <button type="button" class="btn btn-primary">A3</button>
            <button type="button" class="btn btn-primary">A4</button>
        </div>
    </div>
    <div class="container">
        <div class="btn-group">
            <button type="button" class="btn btn-primary">B1</button>
            <button type="button" class="btn btn-primary">B2</button>
            <button type="button" class="btn btn-primary">B3</button>
            <button type="button" class="btn btn-primary">B4</button>
        </div>
    </div>
    <div class="container">
        <div class="btn-group">
            <button type="button" class="btn btn-primary">C1</button>
            <button type="button" class="btn btn-primary">C2</button>
            <button type="button" class="btn btn-primary">C3</button>
            <button type="button" class="btn btn-primary">C4</button>
        </div>
    </div>
    <div class="container">
        <div class="btn-group">
            <button type="button" class="btn btn-primary">D1</button>
            <button type="button" class="btn btn-primary">D2</button>
            <button type="button" class="btn btn-primary">D3</button>
            <button type="button" class="btn btn-primary">D4</button>
        </div>
    </div>
</div>`;
}