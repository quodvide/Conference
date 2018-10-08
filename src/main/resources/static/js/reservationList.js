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
    var arr = new Array(48)
    arr.forEach((v) => {
        v = new Array(10);
    })
    arr.forEach((v) => {
        v.forEach((v) => {
            v = 1
        console.log(v)
        })
    })
    response.json().then((lists) => {
            lists.forEach((reservation) => {
                console.log("reservation : ", reservation)
            })
        })
    console.log("success");
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

