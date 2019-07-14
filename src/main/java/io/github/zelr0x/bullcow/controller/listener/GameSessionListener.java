package io.github.zelr0x.bullcow.controller.listener;

import io.github.zelr0x.bullcow.controller.util.SessionAttrStore;
import io.github.zelr0x.bullcow.model.dto.GameSession;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import io.github.zelr0x.bullcow.service.IPlayerService;
import io.github.zelr0x.bullcow.service.PlayerService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public final class GameSessionListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(final HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeRemoved(final HttpSessionBindingEvent event) {
        process(event);
    }

    @Override
    public void attributeReplaced(final HttpSessionBindingEvent event) {
        process(event);
    }

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
