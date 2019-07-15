package io.github.zelr0x.bullcow.controller.listener;

import io.github.zelr0x.bullcow.controller.util.SessionAttrStore;
import io.github.zelr0x.bullcow.model.dto.GameSession;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import io.github.zelr0x.bullcow.service.IPlayerService;
import io.github.zelr0x.bullcow.service.PlayerService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * GameSessionListener listens on the session-related requests
 * and performs according actions if necessary.
 */
@WebListener
public final class GameSessionListener implements HttpSessionAttributeListener {
    /**
     * Does nothing.
     *
     * @param event an event that just happened
     */
    @Override
    public void attributeAdded(final HttpSessionBindingEvent event) {
    }

    /**
     * Calls process() which contains all the logic.
     *
     * @param event an event that just happened
     */
    @Override
    public void attributeRemoved(final HttpSessionBindingEvent event) {
        process(event);
    }

    /**
     * Calls process() which contains all the logic.
     *
     * @param event an event that just happened
     */
    @Override
    public void attributeReplaced(final HttpSessionBindingEvent event) {
        process(event);
    }

    /**
     * Handles changes in game statistics when the player finishes a game.
     * For that to work, there should be a controller removing or replacing
     * GameSession in the session associated with the player.
     * That implies that game sessions should be stored in user's sessions.
     *
     * @param event an event that just happened
     */
    private void process(final HttpSessionBindingEvent event) {
        final IPlayerService playerService = new PlayerService();
        final String changedAttr = event.getName();
        if (!changedAttr.equals(SessionAttrStore.GAME_SESSION)) {
            return;
        }

        final GameSession gameSession = (GameSession) event.getValue();
        if (gameSession.isFinished()) {
            final Object userIdObj = event.getSession()
                    .getAttribute(SessionAttrStore.USER_ID);
            // TODO: Optimize with buffered updates
            final StatDifference diff = StatDifference.of(1, gameSession.getTurn());
            if (userIdObj != null) {
                playerService.updateStatistics((Long) userIdObj, diff);
            }
        }
    }
}
