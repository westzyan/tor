package com.tor.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;

public class JsonTes {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSON jsonObject = new JSONObject();
        JSON jsonObject2 = new JSON() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        ((JSONObject) jsonObject).put("000","000");
        ((JSONObject) jsonObject).put("111","222");
        JSON jsonObject1 = new JSONObject();
        ((JSONObject) jsonObject1).put("000","000");
        ((JSONObject) jsonObject1).put("111","222");
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);

        System.out.println(jsonArray);
    }
}
