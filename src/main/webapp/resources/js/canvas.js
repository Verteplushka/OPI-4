const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");

function getR() {
    let r1 = PF('widget1').isChecked() ? 1 : 0;
    let r2 = PF('widget2').isChecked() ? 2 : 0;
    let r3 = PF('widget3').isChecked() ? 3 : 0;
    let r4 = PF('widget4').isChecked() ? 4 : 0;
    let r5 = PF('widget5').isChecked() ? 5 : 0;

    return (r1 + r2 + r3 + r4 + r5);
}

function redrawPicture() {
    redrawGraph();
    if (getR() !== 0) {
        drawAllDots();
    }
}

function drawGraph() {

    let r = getR() * 20;

    // Рисование оси X с стрелкой на конце
    ctx.beginPath();
    ctx.moveTo(20, canvas.height / 2);
    ctx.lineTo(canvas.width - 20, canvas.height / 2);
    ctx.strokeStyle = "black";
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(canvas.width - 20, canvas.height / 2);
    ctx.lineTo(canvas.width - 30, canvas.height / 2 - 5);
    ctx.lineTo(canvas.width - 30, canvas.height / 2 + 5);
    ctx.fillStyle = "black";
    ctx.fill();

    // Рисование делений на оси X
    for (let x = 30; x < canvas.width - 30; x += 20) {
        ctx.moveTo(x, canvas.height / 2 - 3);
        ctx.lineTo(x, canvas.height / 2 + 3);
        ctx.stroke();
    }

    // Рисование оси Y с стрелкой на конце
    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, 20);
    ctx.lineTo(canvas.width / 2, canvas.height - 20);
    ctx.strokeStyle = "black";
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, 20);
    ctx.lineTo(canvas.width / 2 - 5, 30);
    ctx.lineTo(canvas.width / 2 + 5, 30);
    ctx.fillStyle = "black";
    ctx.fill();

    // Рисование делений на оси Y
    for (let y = 30; y < canvas.height - 30; y += 20) {
        ctx.moveTo(canvas.width / 2 - 3, y);
        ctx.lineTo(canvas.width / 2 + 3, y);
        ctx.stroke();
    }

    // Добавление меток
    ctx.fillStyle = "black";
    ctx.fillText("X", canvas.width - 10, canvas.height / 2 - 10);
    ctx.fillText("Y", canvas.width / 2 + 10, 10);
    ctx.fillText("R", canvas.width / 2 + 3 + r, canvas.height / 2 + 20);


    ctx.fillStyle = "rgba(20, 60, 200, 0.5)"; // Голубой с прозрачностью 0.5

    ctx.fillRect(canvas.width / 2, canvas.height / 2, r, r / 2);

    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, canvas.height / 2);
    ctx.lineTo(canvas.width / 2, canvas.height / 2 - r);
    ctx.lineTo(canvas.width / 2 - r / 2, canvas.height / 2);
    ctx.lineTo(canvas.width / 2, canvas.height / 2);
    ctx.closePath(); // Замкнуть треугольник
    ctx.fill();

    ctx.beginPath();
    ctx.arc(canvas.width / 2, canvas.height / 2, r, 3 * Math.PI / 2, 2 * Math.PI);
    ctx.lineTo(canvas.width / 2, canvas.height / 2); // Соединить с центром для закрытия фигуры
    ctx.closePath(); // Завершить четверть круга
    ctx.fill();
}

function redrawGraph() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawGraph();
}


function drawAllDots() {
    let dotsList = getDotsList();
    for (const dot of dotsList) {
        drawDot(dot);
    }
}

function getDotsList() {
    let dotsList = [];

    $("#valuesTable tbody tr").each(function () {
        let dot = {};

        dot.x = parseFloat($(this).find("td:eq(0)").text().trim());
        dot.y = parseFloat($(this).find("td:eq(1)").text().trim());
        dot.r = parseInt($(this).find("td:eq(2)").text().trim());
        dot.result = $(this).find("td:eq(5)").text().trim() === "hit";

        dotsList.push(dot);
    });

    return dotsList;
}

function drawDot(dot) {
    let x = 20 * dot.x / dot.r * getR() + canvas.height / 2;
    let y = canvas.width / 2 - 20 * dot.y / dot.r * getR();
    ctx.fillStyle = dot.result ? "green" : "red";
    ctx.fillRect(x, y, 3, 3);
}


canvas.addEventListener("click", function (event) {
    let x = (event.offsetX - canvas.height / 2) / 20;
    let y = (canvas.width / 2 - event.offsetY) / 20;
    // if (!checkRange(x, y)) {
    //     return;
    // }
    document.getElementById("form:hiddenX").value = x;
    document.getElementById("form:hiddenY").value = y;
    document.getElementById("form:hiddenR").value = getR();
    callAddDot();
});

function checkRange(x, y) {
    if (!(-5 < x && x < 3)) {
        alert("You're out of range!\nX must be in (-5, 3)");
        return false;
    }
    if (!(-5 < y && y < 5)) {
        alert("You're out of range!\nY must be in (-5, 5)");
        return false;
    }
    return true;
}
