package com.wandoujia.wondernews;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data implements Serializable {
    public String title;
    public String url;

    public Data() {};

    public static Data[] parseFromJSONArray(JSONArray ja) {
        Data[] datas = new Data[ja.length()];
        for (int i = 0; i < ja.length(); i++) {
            datas[i] = new Data();
            try {
                datas[i].title = ((JSONObject) ja.get(i))
                        .getString(Consts.TITLE);
                datas[i].url = ((JSONObject) ja.get(i)).getString(Consts.URL);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return datas;
    }
}
