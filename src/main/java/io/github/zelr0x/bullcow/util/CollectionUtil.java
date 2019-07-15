package io.github.zelr0x.bullcow.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * CollectionUtil contains utility methods related to collections.
 */
public final class CollectionUtil {
    /**
     * Java 8 substitute for Set.of() added in Java 9.
     *
     * @param items the items that need to be added to the resulting set.
     * @param <T> the type of the set's elements.
     * @return the set of specified items.
     */
    @SafeVarargs
    public static <T> Set<T> immutableSetOf(final T... items) {
        return Stream.of(items)
                .collect(ImmutableCollector.toImmutableSet());
    }

    /**
     * Collector for CollectionUtil#immutableSetOf .
     * @see <a href="https://stackoverflow.com/a/37406054">https://stackoverflow.com/a/37406054</a>
     */
    private static final class ImmutableCollector {
        @SuppressWarnings("checkstyle:JavadocMethod")
        private static <T> Collector<T, Set<T>, Set<T>> toImmutableSet() {
            return Collector.of(HashSet::new, Set::add, (l, r) -> {
                l.addAll(r);
                return l;
            }, Collections::unmodifiableSet);
        }
    }

    /**
     * Prevents instantiation.
     */
    private CollectionUtil() {
        throw new AssertionError();
    }
}
