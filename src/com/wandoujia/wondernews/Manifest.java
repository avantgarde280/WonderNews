package com.wandoujia.wondernews;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Manifest implements Serializable {
    public Source[] sources;

    public Manifest() {};

    public static Manifest parseFromJSON(String json) {
        Manifest manifest = null;
        try {
            JSONArray ja = new JSONArray(json);
            manifest = new Manifest();
            manifest.sources = new Source[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                manifest.sources[i] = Source.parseFromJSONObject(ja.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return manifest;
    }

}
