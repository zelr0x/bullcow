## Design
* DI!!!
* GameSession storage in a map?

## Performance
* Buffered game-related updates to DB instead of per-game updates

## UI/UX
* Make history adaprive, probably move to the side on low height but OK width.
* Make game keys better adaptive.
* Show history after refresh too. Probably save it to the session.
* Error messages in forms
* Redirect to login tab of the form from when /login#login is requested
* Scroll back with js or change to fake element id in login.jsp (fragment is added to URI causing scroll)
* Paging in /rating
* Make css not that awful

## DB
* Currently using Hibernate built-in connection pool (not for production use)
* Schema improvements?

## Security
* Cookies instead of sessions for auth?
* SSL

## Other
* Make js prettier with jquery or something else. Improve consistency. Probably separate code.
