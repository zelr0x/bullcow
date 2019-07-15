package io.github.zelr0x.bullcow.controller.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * UriUtil contains utility methods related to URIs.
 */
public final class UriUtil {
    /**
     * Tries to retrieve a fragment (part after "#") from a specified
     * String representation of a URI.
     *
     * @param uriString a String representation of a URI.
     * @return a String containing a fragment or nothing
     */
    public static Optional<String> getFragment(final String uriString) {
       String fragment = null;
        try {
            final URI uri = new URI(uriString);
            fragment = uri.getFragment();
        } catch (URISyntaxException e) {
            // Invalid uriString. Do nothing because it's (currently)
            // intended for use with valid string only.
        }
        return Optional.ofNullable(fragment);
    }

    /**
     * Prevents instantiation.
     */
    private UriUtil() {
        throw new AssertionError();
    }
}
