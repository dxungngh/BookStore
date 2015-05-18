package com.dungnh8.truyen_ngon_tinh.parser;

import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BookParser {
    public static final String TAG = BookParser.class.getSimpleName();

    public List<Book> parseListOfBooks(String response) throws Exception {
        JSONObject object = new JSONObject(response);
        JSONArray data = object.getJSONArray("data");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<Book> books = mapper.readValue(data.toString(), new TypeReference<List<Book>>() {
        });
        return books;
    }

    public List<Chap> parseChaps(String response) throws Exception {
        JSONObject object = new JSONObject(response);
        JSONArray data = object.getJSONObject("data").getJSONObject("chaps").getJSONArray("data");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<Chap> chaps = mapper.readValue(data.toString(), new TypeReference<List<Chap>>() {
        });
        return chaps;
    }
}