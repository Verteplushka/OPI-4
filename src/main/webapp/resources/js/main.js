window.onload = function(){
    redrawPicture();
};

function checkY(){
    let y = document.getElementById('form:y').value;
    return !(y === "" || !(y > -5 && y < 5) || isNaN(y)) && (getR() !== 0);
}