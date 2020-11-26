package utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;

import java.io.FileReader;

public class JsonParsingUtil {
    @SneakyThrows
    public static String getValue(String filePath, String key) {
        return new JsonParser().parse(new JsonReader(new FileReader(filePath))).getAsJsonObject().get(key).getAsString();
    }

    @SneakyThrows
    public static String[] getArray(String filePath, String key) {
        return new Gson().fromJson(new JsonParser().parse(new JsonReader(new FileReader(filePath))).getAsJsonObject().get(key), String[].class);
    }
}

