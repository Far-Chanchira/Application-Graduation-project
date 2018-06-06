package com.example.mint.applicationtest.Hashmap;

import android.content.Context;
import android.util.Log;

import com.example.mint.applicationtest.DataClass.DataClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Hashmap {
    private final static String TAG = "main";
    private Context context;
    private HashMap<String, String> hashMap = new HashMap<>();
    private String key;
    private String value;
    private String amount;
    private String total;

    public Hashmap(Context context, String key, String value) {
        this.context = context;
        this.key = key;
        this.value = value;

        addData(key, value);
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void addData(String mKey, String mValue) {
        hashMap.put(mKey, mValue);
    }

    public void readData() {
        Log.d(TAG, hashMap.keySet().toString() + " " + hashMap.values().toString());
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) (iterator.next());
            String val = hashMap.get(key);
            Log.d(TAG, key + " = " + val);

        }
    }

    public void clearData() {
        hashMap.clear();
    }

    public List<DataClass> hashmapToArray() {
        List<DataClass> list = new ArrayList<>();
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) (iterator.next());
            String val = hashMap.get(key);
            list.add(new DataClass(key, val));
        }
        return list;
    }
}