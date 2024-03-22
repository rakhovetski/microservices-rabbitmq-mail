package ru.stashevskaya.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stashevskaya.adapter.LocalDateTimeAdapter;

import java.time.LocalDateTime;

public class GsonFactory {
    public static Gson generateGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    }
}

