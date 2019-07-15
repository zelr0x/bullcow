package io.github.zelr0x.bullcow.controller.util;

import io.github.zelr0x.bullcow.util.CollectionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

public class RouteStoreTest {
    /**
     * Specifies route starts that require authentication.
     * Helps performing redundant checks.
     */
    private static final Set<String> AUTH_REQUIRED_ROUTE_STARTS =
            CollectionUtil.immutableSetOf(
                    RouteStore.GAME,
                    RouteStore.PLAY);

    /**
     * Specifies route endpoints that require authentication.
     * Helps performing redundant checks.
     */
    private static final Set<String> AUTH_REQUIRED_ENDPOINTS =
            CollectionUtil.immutableSetOf();

    @Test
    public void MalformedRouteChecksTest() {
        Assert.assertTrue(Collections.disjoint(
                RouteStore.NO_AUTH_ROUTE_STARTS, RouteStore.NO_AUTH_ENDPOINTS));
        Assert.assertTrue(Collections.disjoint(
                RouteStore.NO_AUTH_ROUTE_STARTS, AUTH_REQUIRED_ENDPOINTS));
        Assert.assertTrue(Collections.disjoint(
                RouteStore.NO_AUTH_ROUTE_STARTS, AUTH_REQUIRED_ROUTE_STARTS));
        Assert.assertTrue(Collections.disjoint(
                RouteStore.NO_AUTH_ENDPOINTS, AUTH_REQUIRED_ENDPOINTS));
        Assert.assertTrue(Collections.disjoint(
                RouteStore.NO_AUTH_ENDPOINTS, AUTH_REQUIRED_ROUTE_STARTS));
        Assert.assertTrue(Collections.disjoint(
                AUTH_REQUIRED_ENDPOINTS, AUTH_REQUIRED_ROUTE_STARTS));
    }
}
