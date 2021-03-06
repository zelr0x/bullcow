package io.github.zelr0x.bullcow.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.zelr0x.bullcow.model.dto.GuessDto;

import java.util.List;

/**
 * JsonSerializer encapsulates JSON serialization.
 */
public final class JsonSerializer {
    /**
     * Serializes a specified GuessDto to a JSON object.
     * @param guessDto a GuessDto object to serialize.
     * @return JSON representation of a specified GuessDto object
     */
    public static String serialize(final GuessDto guessDto) {
        return Holder.GSON.toJson(guessDto);
    }

    /**
     * Serializes a specified List to a JSON object.
     * The resulting objects contains the list as an array
     * with a specified name: { "propertyName": [...list] }
     *
     * @param propertyName the name of a resulting json array
     * @param list a List object to serialize
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
     * Prevents instantiation.
     */
    private JsonSerializer() {
        throw new AssertionError();
    }
}
