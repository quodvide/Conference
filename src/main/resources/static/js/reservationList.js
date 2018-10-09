let date;

function $(selector) {
    return document.querySelector(selector);
}

function fetchManager({url, method, body, headers, callback, errCallback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            if (response.status === 200 || response.status === 201 || response.status === 302) {
                callback(response);
                return false;
            } else {
                errCallback(response);
            }
        })
}

function onChangeDateListener(evt) {
    if(date != evt.target.value) {
        date = evt.target.value;
        console.log(date)

        fetchManager({
                    url: '/api/reservations/' + date,
                    method: 'GET',
                    headers: { 'content-type': 'application/json'},
                    callback: onSuccessGetReserve,
                    errCallback: onFailGetReserve
                })
    }
}

function onSuccessGetReserve(response) {
    clearTable("table");
    response.json().then((reservationlists) => {
            reservationlists.forEach((reservation) => {
                insertReservationIntoTable(reservation);
            })
        })
    console.log("done");
}

function clearTable(id) {
	var table = document.getElementById("table");
    for(var i = 1; i<=48; i++) {
        for(var j = 1; j<=10; j++) {
            table.rows[i].cells[j].innerHTML = "";
        }
    }
}

function insertReservationIntoTable(reservation){
	var table = document.getElementById("table");
    var name = reservation.username;
    var id = reservation.room.id;
    var startTime = reservation.startTime;
    var startIndex = startTime.substring(0,2)*2 + startTime.substring(3,5)/30;
    var endTime = reservation.endTime;
    var endIndex = endTime.substring(0,2)*2 + endTime.substring(3,5)/30;

    for(var i = startIndex; i<endIndex; i++) {
        table.rows[i + 1].cells[id].innerHTML = "<center>" + name + "</center>";
    }
}

function onFailGetReserve() {
    alert("no");
}

function setInputDate(_id){
    var _dat = $(_id);
    var hoy = new Date(),
        d = hoy.getDate(),
        m = hoy.getMonth()+1,
        y = hoy.getFullYear(),
        data;

    if(d < 10){
        d = "0"+d;
    };
    if(m < 10){
        m = "0"+m;
    };

    data = y+"-"+m+"-"+d;
    console.log(data);
    _dat.value = data;
    date = data;
};

function init() {
setInputDate("#date");
    fetchManager({
        url: '/api/reservations/' + date,
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccessGetReserve,
        errCallback: onFailGetReserve
    })
}

document.addEventListener("DOMContentLoaded", function() {
    $("#date").addEventListener("change", onChangeDateListener);
    init();
});

