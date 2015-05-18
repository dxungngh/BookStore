package com.dungnh8.truyen_ngon_tinh.parser;

import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

public class ChapParser {
    public static final String TAG = ChapParser.class.getSimpleName();

    public Chap parse(String response) throws Exception {
        JSONObject object = new JSONObject(response);
        JSONObject data = object.getJSONObject("data");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Chap chap = mapper.readValue(data.toString(), new TypeReference<Chap>() {
        });

        if (data.has("next")) {
            JSONObject nextObject = data.getJSONObject("next");
            long nextChapId = nextObject.getLong("id");
            String nextChapApi = nextObject.getString("api");
            chap.setNextChap(nextChapId);
            chap.setNextApi(nextChapApi);
        }
        if (data.has("prev")) {
            JSONObject prevObject = data.getJSONObject("prev");
            long prevChapId = prevObject.getLong("id");
            String prevChapApi = prevObject.getString("api");
            chap.setPrevChap(prevChapId);
            chap.setPrevApi(prevChapApi);
        }

        return chap;
    }
}