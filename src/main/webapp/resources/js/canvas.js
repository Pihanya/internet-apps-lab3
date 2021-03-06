var lastX = 0;
var lastY = 0;

function drawCanvas(r) {
  let canvas = document.querySelector("#canvas");
  let context = canvas.getContext("2d");
  //очистка
  context.clearRect(0, 0, canvas.width, canvas.height);

  //прямоугольник
  context.beginPath();
  context.rect(20, 20, 130, 130);
  context.closePath();
  context.strokeStyle = "blue";
  context.fillStyle = "blue";
  context.fill();
  context.stroke();

  // сектор
  context.beginPath();
  context.moveTo(150, 150);
  context.arc(150, 150, 130, 0, Math.PI / 2, false);
  context.closePath();
  context.strokeStyle = "blue";
  context.fillStyle = "blue";
  context.fill();
  context.stroke();

  //треугольник
  context.beginPath();
  context.moveTo(150, 150);
  context.lineTo(215, 150);
  context.lineTo(150, 20);
  context.lineTo(150, 150);
  context.closePath();
  context.strokeStyle = "blue";
  context.fillStyle = "blue";
  context.fill();
  context.stroke();

  //отрисовка осей
  context.beginPath();
  context.font = "10px Verdana";
  context.moveTo(150, 0);
  context.lineTo(150, 300);
  context.moveTo(150, 0);
  context.lineTo(145, 15);
  context.moveTo(150, 0);
  context.lineTo(155, 15);
  context.fillText("Y", 160, 10);
  context.moveTo(0, 150);
  context.lineTo(300, 150);
  context.moveTo(300, 150);
  context.lineTo(285, 145);
  context.moveTo(300, 150);
  context.lineTo(285, 155);
  context.fillText("X", 290, 135);

  // деления X
  context.moveTo(145, 20);
  context.lineTo(155, 20);
  context.fillText(r, 160, 20);
  context.moveTo(145, 85);
  context.lineTo(155, 85);
  context.fillText((r / 2), 160, 78);
  context.moveTo(145, 215);
  context.lineTo(155, 215);
  context.fillText(-(r / 2), 160, 215);
  context.moveTo(145, 280);
  context.lineTo(155, 280);
  context.fillText(-r, 160, 280);
  // деления Y
  context.moveTo(20, 145);
  context.lineTo(20, 155);
  context.fillText(-r, 20, 170);
  context.moveTo(85, 145);
  context.lineTo(85, 155);
  context.fillText(-(r / 2), 70, 170);
  context.moveTo(215, 145);
  context.lineTo(215, 155);
  context.fillText((r / 2), 215, 170);
  context.moveTo(280, 145);
  context.lineTo(280, 155);
  context.fillText(r, 280, 170);

  context.closePath();
  context.strokeStyle = "black";
  context.fillStyle = "black";
  context.stroke();
}

function drawPoint() {
  let x = lastX;
  let y = lastY;

  let canvas = document.querySelector("#canvas");
  let context = canvas.getContext("2d");
  let result = document.getElementById("graph-controls:result").value;
  let isArea = (result === "true");

  context.beginPath();
  context.rect(x - 2, y - 2, 4, 4);
  context.closePath();
  if (isArea) {
    context.strokeStyle = "green";
    context.fillStyle = "green";
  } else {
    context.strokeStyle = "maroon";
    context.fillStyle = "maroon";
  }
  context.fill();
  context.stroke();

  let dbResult = document.getElementById("db-inputs:db-result");
  let dbX = document.getElementById("db-inputs:db-x");
  let dbY = document.getElementById("db-inputs:db-y");
  let dbR = document.getElementById("db-inputs:db-r");

  x = (x - 150) / 130;
  y = (150 - y) / 130;

  dbX.value = x;
  dbY.value = y;
  dbR.value = currentR;
  dbResult.value = result;
  writeDB();
}

function isArea(x, y, r) {
  return checkPoint(x, y, r); // todo
  // return checkPointLocally(x, y, r); // todo
}

// todo
function checkPoint(x, y, r) {
  let hiddenX = document.getElementById("graph-controls:hidden-x");
  let hiddenY = document.getElementById("graph-controls:hidden-y");
  let hiddenR = document.getElementById("graph-controls:hidden-r");
  let hiddenResult = document.getElementById("graph-controls:result");

  console.log(
      "[checkPoint] Hidden values: "
      + "{"
      + "X: " + hiddenX + ", "
      + "Y: " + hiddenY + ", "
      + "R: " + hiddenR + ", "
      + "Result: " + hiddenResult
      + "}");

  hiddenX.value = (r * ((x - 150) / 130));
  hiddenY.value = (r * ((150 - y) / 130));

  hiddenX.value = parseFloat(hiddenX.value.toString()).toFixed(3);
  hiddenY.value = parseFloat(hiddenY.value.toString()).toFixed(3);
  hiddenR.value = r;

  lastX = x;
  lastY = y;

  console.log(
      "[checkPoint] Hidden values after processing: "
      + "{"
      + "X: " + hiddenX + ", "
      + "Y: " + hiddenY + ", "
      + "R: " + hiddenR + ", "
      + "Result: " + hiddenResult
      + "}");

  validateGraph();
}

function checkPointLocally(x, y, r) {
  x = (x - 150) / 130;
  y = (150 - y) / 130;

  if (r < 0) {
    return false;
  }

  let res;
  if (x <= 0 && y <= 0) {
    res = (x >= -r && y >= -r / 2);
  } else if (x < 0) {
    if (Math.abs(x) > r || Math.abs(y) > r) {
      res = false;
    } else {
      res = (Math.sqrt(x * x + y * y) <= r / 2);
    }
  } else {
    res = (y <= -x + r && y >= 0);
  }

  return res;
}