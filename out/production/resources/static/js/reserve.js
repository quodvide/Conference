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
    console.log("handlerIn");
    evt.preventDefault();
    let form = evt.target.closest("form");

    fetchManager({
            url: '/api/reservations',
            method: 'POST',
            headers: { 'content-type': 'application/json'},
            body: JSON.stringify({
                "username": form.userName.value,
                "day": form.date.value,
                "startTime": form.startTime.value,
                "endTime": form.endTime.value,
                "roomName": form.roomName.value,
                "count": form.repeat.value
            }),
            callback: onSuccessReserve,
            errCallback: onFailReserve
        })
}

function onSuccessReserve() {
    alert("success");
}

function onFailReserve(response) {
response.then((response) => {
    alert("fail" + response);
    })
}

document.addEventListener("DOMContentLoaded", function() {
    $("#reserve").addEventListener("click", onClickReserveHandler);
});

