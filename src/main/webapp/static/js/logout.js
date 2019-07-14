document.getElementById('logout-btn').addEventListener('click', () => {
    return fetch('/logout', {
        method: 'POST',
        credentials: 'same-origin',
        redirect: 'follow',
        referrer: 'no-referrer',
    });
});
