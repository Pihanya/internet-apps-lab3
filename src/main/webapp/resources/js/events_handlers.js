var currentR = 1;

function onFormSubmit() {
  let xField = document.getElementById("data-form:x");
  let yField = document.getElementById("data-form:y");
  let rField = document.getElementById("data-form:r");
  let form = document.querySelector("#data-form");

  let valid = true;

  // let message = "<b>Форма содержит следующие ошибки:</b><br>";
  let message = "<b>There are the following errors in the form:</b><br>";

  let valueX = xField.options[xField.selectedIndex].value;
  let valueY = yField.value;

  if (isNaN(+(valueY))) {
    // message += "Значение Y должно быть числом<br>";
    message += "Y values should be a number<br>";
    valid = false;
  } else if (valueY.length > 12) {
    // message += "Длина строки с Y не должна превышать 12 символов<br>";
    message += "The length of the Y should not exceed 12 symbols<br>";
    valid = false;
  } else if (parseFloat(valueY) < -3 || parseFloat(valueY) > 3) {
    // message += "Y должен принадлежать промежутку [-5; 5]<br>";
    message += "Y value should be in interval [-5; 5]<br>";
    valid = false;
  }

  if (!valid) {
    document.getElementById("errors").innerHTML = message;
    return;
  }

  let x = valueX * 130 + 150;
  let y = 150 - valueY * 130;
  console.log("Checking point: " + x + " " + y);
  isArea(x, y, currentR);

}

function onRadiusInput() {
  let rField = document.getElementById("data-form:r");
  currentR = rField.options[rField.selectedIndex].value;
  document.getElementById("graph-controls:hidden-r").value = currentR;
  drawCanvas(currentR);
}

function onCanvasClick(event) {
  var canvas = document.querySelector("#canvas");
  var rect = canvas.getBoundingClientRect();

  var left = rect.left;
  var top = rect.top;

  var x = event.clientX - left;
  var y = event.clientY - top;

  isArea(x, y, currentR);
}