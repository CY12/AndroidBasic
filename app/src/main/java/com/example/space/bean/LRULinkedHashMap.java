package com.example.space.bean;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap extends LinkedHashMap {
    private int maxSize;
    LRULinkedHashMap(int maxSize){
        super(16,0.75f,true);//accessOrder false 基于插入顺序 true 基于访问顺序
        this.maxSize = maxSize;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry entry){
        Log.e("Test","最后一个 key "+entry.getKey()+" value"+entry.getValue());
        return size()>maxSize;
    }
//    Map<Integer,Integer> map=new LRULinkedHashMap<>(4);
//    map.put(9,3);
//    map.put(7,4);
//    map.put(5,9);
//    map.put(3,4);
//    map.put(6,6);
//    //总共put了5个元素，超过了指定的缓存最大容量
//    //遍历结果
//        for(Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();it.hasNext();){
//        System.out.println(it.next().getKey());
//    }
//}
}
