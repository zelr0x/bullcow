## Design
* DI!!!
* GameSession storage in a map?

## Features
* Logout button & Servlet invalidating session and / or cookies
* New game button
* Error page

## Security
* Cookies for auth
* SSL

## Performance
* Buffered game-related updates to DB instead of per-game updates

## UI/UX
* Error messages in forms
* Redirect to login tab of the form from when /login#login is requested
* Scroll back with js or change to fake element id in login.jsp (fragment is added to URI causing scroll)
* Paging in /rating

## DB
* Currently using Hibernate built-in connection pool (not for production use)
* Schema improvements?
