package com.example.space.utils;

import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;

public class JsonUtils {

    public static <T> T parseToJson(Class<T> cls, String json) {
        T t = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            t = cls.newInstance();
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                try {
                    String name = (String)iterator.next();
                    Log.e("Test","name:"+name);
                    Field field = cls.getDeclaredField(name);
                    if (field != null) {
                        Class c = field.getType();
                        field.setAccessible(true);
                        if (String.class.isAssignableFrom(c)) {
                            Log.e("Test","value:"+jsonObject.getString(name));
                            field.set(t, jsonObject.getString(name));
                        } else if (Boolean.class.isAssignableFrom(c)) {
                            field.setBoolean(t, jsonObject.getBoolean(name));
                        }

                    }else {
                        Log.e("Test","name:"+name+"方法为空");
                    }
                } catch (Exception e) {
                    Log.e("Test","error "+e.toString());
                }
            }
        } catch (Exception e) {
            Log.e("Test","error "+e.toString());
        }

        return t;
    }
}
