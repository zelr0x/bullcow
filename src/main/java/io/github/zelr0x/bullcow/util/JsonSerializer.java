package io.github.zelr0x.bullcow.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.zelr0x.bullcow.model.dto.GuessDto;

import java.util.List;

/**
 * Encapsulates JSON serialization.
 */
public final class JsonSerializer {
    public static String serialize(final GuessDto guessDto) {
        return Holder.GSON.toJson(guessDto);
    }

    /**
     * Serializes list to JSON object containing it as an array
     * with a specified name: { "propertyName": [...list] }
     * @param propertyName the name of a resulting json array
     * @param list a list to serialize
     * @return JSON object containing the list as an array
     */
    public static String serialize(final String propertyName,
                            final List<?> list) {
        final JsonArray jsonArray = Holder.GSON
                .toJsonTree(list)
                .getAsJsonArray();
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add(propertyName, jsonArray);
        return jsonObject.toString();
    }

    /**
     * Holder singleton for Gson object.
     */
    private static final class Holder {
        private static final Gson GSON = new GsonBuilder().create();
    }

    /**
     * Restricts instantiation.
     */
    private JsonSerializer() {
        throw new AssertionError();
    }
}
