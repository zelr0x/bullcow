const inputField = document.getElementById('guess-input');
const clearButton = document.getElementById('clear-button');
const guessButton = document.getElementById('guess-button');
const resultDiv = document.getElementById('result');
const historyBody = document.getElementById('history-table__body');
const guessPromptMessage = resultDiv.innerText;
const hiddenClassName = 'hidden';

// Removes non-numeric items from input.
inputField.addEventListener('keypress', (kbEvent) => {
    if (isNaN(inputField.value)) {
        inputField.value = inputField.value.match(/[0-9]*/);
    }
});

// Ensures only 4 characters can be used (hardcoded for now)
// TODO: obtain length from other source (session?)
const keys = document.querySelectorAll('.key');
const maxGuessLength = 4;
keys.forEach(key => {
    key.addEventListener('click', () => {
        if (inputField.value.length < maxGuessLength) inputField.value += key.value;
    });
});

// Clear button handling
clearButton.addEventListener('click', () => inputField.value = '');

// Guess result and history handling
const historyTable = document.getElementById('history-table');
function addHistoryEntry(turn, guess, result) {
    if (historyTable.classList.contains(hiddenClassName)) {
        makeVisible(historyTable);
    }

    resultDiv.innerText = result;

    const row = historyBody.insertRow(0);
    const turnCell = insertNumericCell(row, 0);
    const guessCell = insertNumericCell(row, 1);
    const resultCell = row.insertCell(2);

    turnCell.innerText = turn;
    guessCell.innerText = guess;
    resultCell.innerText = result;
}

function insertNumericCell(rowElement, cellIndex) {
    const cell = rowElement.insertCell(cellIndex);
    cell.classList.add('td--numeric')
    return cell;
}

function hide(element) {
//    element.style.visibility = 'hidden';
    element.classList.add(hiddenClassName);
}

function makeVisible(element) {
//    element.style.visibility = 'visible';
    element.classList.remove(hiddenClassName);
}

function suggestNewGame() {
    hide(clearButton);
    hide(guessButton);
    makeVisible(newGameButton);
}

const newGameButton = document.getElementById('new-game-button');
newGameButton.addEventListener('click', (event) => {
    event.preventDefault();
    fetch('/game?new=1', {
        method: 'POST'
    }).then((resp) => {
        hide(newGameButton);
        for (let i = 0; i < historyTable.rows.length; i++) {
            historyTable.deleteRow(i);
        }
        hide(historyTable);
        makeVisible(clearButton);
        makeVisible(guessButton);
        resultDiv.innerText = guessPromptMessage;
    });
});

document.forms['guess-form'].addEventListener('submit', (event) => {
    event.preventDefault();
    fetch(event.target.action, {
        method: 'POST',
        body: new URLSearchParams(new FormData(event.target)) // event.target is the form
    }).then((resp) => {
        return resp.json();
    }).then((body) => {
        if (body.error !== "") {
            resultDiv.innerText = body.error;
        } else {
            resultDiv.innerText = body.result;
            addHistoryEntry(body.turn, body.guess, body.result);
            inputField.value = '';
        }

        if (body.isWinning) {
            suggestNewGame();
        }
    }).catch((error) => {
        // Catch fire
    });
});
