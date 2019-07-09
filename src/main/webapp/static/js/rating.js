const ratingTable = document.getElementById('rating-table__body');
const tableEndIndex = -1;

const users = JSON.parse(usersJson).users;
users.forEach(user => {
    const row = ratingTable.insertRow(tableEndIndex);
    const rankCell = row.insertCell(0);
    const nameCell = row.insertCell(1);
    const averageGuessesCell = row.insertCell(2);
    const totalGamesCell = row.insertCell(3);

    rankCell.innerHTML = user.rank;
    nameCell.innerHTML = user.name;
    averageGuessesCell.innerHTML = user.averageGuesses;
    averageGuessesCell.className = 'td--numeric';
    totalGamesCell.innerHTML = user.totalGames;
    totalGamesCell.className = 'td--numeric';
});
