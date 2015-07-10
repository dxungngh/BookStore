package com.dungnh8.tieu_thuyet_ngon_tinh.parser;

import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.BookType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BookTypeParser {
    public static final String TAG = BookTypeParser.class.getSimpleName();

    public List<BookType> parseListOfBookTypes(String response) throws Exception {
        JSONObject object = new JSONObject(response);
        JSONArray data = object.getJSONArray("data");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<BookType> bookTypeList = mapper.readValue(
            data.toString(),
            new TypeReference<List<BookType>>() {
            });
        return bookTypeList;
    }
}
