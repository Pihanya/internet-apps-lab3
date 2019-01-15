var currentR = 1;

function onFormSubmit() {
  let form = document.querySelector("#data-form");

  let valid = true;

  // let message = "<b>Форма содержит следующие ошибки:</b><br>";
  let message = "<b>There are the following errors in the form:</b><br>";

  let valueX;
  let valueY = form.y.value;
  let valueR = form.r.value;

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
  }

  return valid;
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