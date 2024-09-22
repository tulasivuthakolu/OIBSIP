let correctNumber = Math.floor(Math.random() * 100) + 1;
let attempts = 5;

function checkGuess() {
    let guess = document.getElementById("guess").value;
    attempts--;
    if (guess == correctNumber) {
        document.getElementById("result").innerHTML = "Congratulations! You guessed correctly.";
    } else if (attempts == 0) {
        document.getElementById("result").innerHTML = "Sorry, you've used all your attempts. The correct number was " + correctNumber + ".";
    } else if (guess > correctNumber) {
        document.getElementById("result").innerHTML = "Too high! You have " + attempts + " attempts left.";
    } else {
        document.getElementById("result").innerHTML = "Too low! You have " + attempts + " attempts left.";
    }
}
