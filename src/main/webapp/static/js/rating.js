const ratingTable = document.getElementById('rating-table__body');
const tableEndIndex = -1;

const players = JSON.parse(playersJson).players;
players.forEach(player => {
    const row = ratingTable.insertRow(tableEndIndex);
    const rankCell = row.insertCell(0);
    const nameCell = row.insertCell(1);
    const averageGuessesCell = row.insertCell(2);
    const totalGamesCell = row.insertCell(3);

    rankCell.innerHTML = player.rank;
    nameCell.innerHTML = player.name;
    averageGuessesCell.innerHTML = player.averageGuesses;
    averageGuessesCell.className = 'td--numeric';
    totalGamesCell.innerHTML = player.totalGames;
    totalGamesCell.className = 'td--numeric';
});
