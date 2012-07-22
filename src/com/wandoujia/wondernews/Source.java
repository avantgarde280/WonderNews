package com.wandoujia.wondernews;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Source implements Serializable {
    public String source;
    public Data[] data;

    public Source() {};

    public static Source parseFromJSONObject(JSONObject jo) {
        Source newSource = new Source();
        try {
            newSource.source = (String) jo.get(Consts.SOURCE);
            newSource.data = Data.parseFromJSONArray(jo
                    .getJSONArray(Consts.DATA));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newSource;
    }

    public String[] getTitles() {
        String[] arr = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            arr[i] = data[i].title;
        }
        return arr;
    }

    @Override
    public String toString() {
        return source;
    }

}
