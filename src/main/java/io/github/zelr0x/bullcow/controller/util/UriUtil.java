package io.github.zelr0x.bullcow.controller.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class UriUtil {
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
}
