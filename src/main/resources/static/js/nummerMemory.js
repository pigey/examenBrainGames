var numbers = document.getElementById("numbers");
var playBtn = document.getElementById("playBtn");
var timerText = document.getElementById("timer");


//Game starting values
var round = 0;
var firstNumbers = Math.floor(Math.random() * 900) + 100;

//checks if you clicked in to the input and if you left
var type = false
function typing() {
    type = true
}
function notTyping() {
    type = false
}



//gameStart button
function gameStart() {
    var timeleft = 20
    var timer = setInterval(function() {
        if (timeleft <= 0 || type) {
            clearInterval(timer)
            numbers.textContent="xxxxxxxxxxxxxxxxx"
            timeleft = 0
        }
        timerText.textContent= timeleft + "s"
        timeleft -= 1
    }, 1000)
    playBtn.style.display = "none";
    numbers.textContent =firstNumbers;
    timeleft = 20
}



//Submit button checks first number
function submit() {
    var userInput = document.getElementById("guessNumber").value
    firstNumbers += ""
    if (userInput === firstNumbers) {
        nummerMemory();

    }
    else{
        lost()
    }

}

function nummerMemory() {
    document.getElementById("guessNumber").value = ""
    round += 1;
    timeleft = 20
    document.getElementById("round").textContent="Round " + round
    firstNumbers = "0"
    for (let index = 0; index < 3 + round; index++) {
        var randomNr = Math.floor(Math.random() * 10);
        firstNumbers += randomNr +""
    }
    numbers.textContent = firstNumbers

    var timer = setInterval(function() {
        if (timeleft <= 0 || type) {
            clearInterval(timer)
            numbers.textContent="xxxxxxxxxxxxxxxxx"
            timeleft = 0
        }
        timerText.textContent= timeleft + "s"
        timeleft -= 1
    }, 1000)
}


function lost() {
    document.getElementById("gameStartScreen").style.display="none";
    document.getElementById("gameOverScreen").style.display="flex";
    document.getElementById("endRound").value = round+""
}