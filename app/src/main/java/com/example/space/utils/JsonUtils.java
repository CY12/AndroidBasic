package com.example.space.utils;

import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {

    public static <T> T parseToJson(Class<T> cls, String json) {
        T t = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            t = cls.newInstance();
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                try {
                    String name = (String) iterator.next();
                    Log.e("Test", "name:" + name);
                    Field field = cls.getDeclaredField(name);
                    if (field != null) {
                        Class c = field.getType();
                        field.setAccessible(true);
                        if (String.class.isAssignableFrom(c)) {
                            Log.e("Test", "value:" + jsonObject.getString(name));
                            field.set(t, jsonObject.getString(name));
                        } else if (Boolean.class.isAssignableFrom(c)) {
                            field.setBoolean(t, jsonObject.getBoolean(name));
                        } else if (Integer.class.isAssignableFrom(c)) {
                            field.setInt(t, jsonObject.getInt(name));
                        } else if (Double.class.isAssignableFrom(c)) {
                            field.setDouble(t, jsonObject.getDouble(name));
                        } else if (Float.class.isAssignableFrom(c)) {
                            String f = jsonObject.getString(name);
                            field.set(t, Float.valueOf(f));
                        } else if (Long.class.isAssignableFrom(c)) {
                            field.setLong(t, jsonObject.getLong(name));
                        } else if (List.class.isAssignableFrom(c)) {
                            Class<?> listType = (Class<?>) ((ParameterizedType) cls.getDeclaredField(
                                    name).getGenericType()).getActualTypeArguments()[0];
                            List list = new ArrayList();
                            JSONArray jsonArray = jsonObject.optJSONArray(name);
                            decodeList(list, jsonArray, listType);
                            field.set(t, list);
                        }

                    } else {
                        Log.e("Test", "name:" + name + "方法为空");
                    }
                } catch (Exception e) {
                    Log.e("Test", "error " + e.toString());
                }
            }
        } catch (Exception e) {
            Log.e("Test", "error " + e.toString());
        }

        return t;
    }


    public static <T> T parseToJson(Class<T> cls, JSONObject jsonObject) {
        T t = null;
        try {
            t = cls.newInstance();
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                try {
                    String name = (String) iterator.next();
                    Log.e("Test", "name:" + name);
                    Field field = cls.getDeclaredField(name);
                    if (field != null) {
                        Class c = field.getType();
                        field.setAccessible(true);
                        if (String.class.isAssignableFrom(c)) {
                            Log.e("Test", "value:" + jsonObject.getString(name));
                            field.set(t, jsonObject.getString(name));
                        } else if (Boolean.class.isAssignableFrom(c)) {
                            field.setBoolean(t, jsonObject.getBoolean(name));
                        } else if (Integer.class.isAssignableFrom(c)) {
                            field.setInt(t, jsonObject.getInt(name));
                        } else if (Double.class.isAssignableFrom(c)) {
                            field.setDouble(t, jsonObject.getDouble(name));
                        } else if (Float.class.isAssignableFrom(c)) {
                            String f = jsonObject.getString(name);
                            field.set(t, Float.valueOf(f));
                        } else if (Long.class.isAssignableFrom(c)) {
                            field.setLong(t, jsonObject.getLong(name));
                        } else if (List.class.isAssignableFrom(c)) {
                            Class<?> listType = (Class<?>) ((ParameterizedType) cls.getDeclaredField(
                                    name).getGenericType()).getActualTypeArguments()[0];
                            List list = new ArrayList();
                            JSONArray jsonArray = jsonObject.optJSONArray(name);
                            decodeList(list, jsonArray, listType);
                            field.set(t, list);
                        }

                    } else {
                        Log.e("Test", "name:" + name + "方法为空");
                    }
                } catch (Exception e) {
                    Log.e("Test", "error " + e.toString());
                }
            }
        } catch (Exception e) {
            Log.e("Test", "error " + e.toString());
        }

        return t;
    }

    private static void decodeList(List list, JSONArray jsonArray, Class type) throws Exception {
        if (jsonArray == null) {
            return;
        }
        // 递归调用，把Json转为Java对象并添加到List中
        for (int i = 0; i < jsonArray.length(); i++) {
            if (type == String.class){
                list.add(jsonArray.getString(i));
            }else if (type == Integer.class){
                list.add(jsonArray.getInt(i));
            }
            // TODO: 2021/7/23 more type 
        }
    }
}
