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

function onClickReserveHandler(evt) {
    evt.preventDefault();
    let form = evt.target.closest("form");

    let username = form.userName.value
    let day = form.date.value
    let startTime = form.startTime.value
    let endTime = form.endTime.value
    let roomName = form.roomName.value
    let count = form.repeat.value

    if(username == "" || day == "" || startTime == "" || endTime == "" || roomName == "") {
        console.log("Empty Input Detected");
        alert("올바른 값을 입력해주세요!");
        return;
    }

    fetchManager({
            url: '/api/reservations',
            method: 'POST',
            headers: { 'content-type': 'application/json'},
            body: JSON.stringify({
                "username": username,
                "day": day,
                "startTime": startTime,
                "endTime": endTime,
                "roomName": roomName,
                "count": count
            }),
            callback: onSuccessReserve,
            errCallback: onFailReserve
        })
}

function onSuccessReserve() {
    alert("successfully reserved!");
    location.href = "/";
}

function onFailReserve() {
    console.log("Reserve Failed");
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
};



document.addEventListener("DOMContentLoaded", function() {
    $("#reserve").addEventListener("click", onClickReserveHandler);
    setInputDate("#date");
});

