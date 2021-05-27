package com.example.space.bean;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap 一个 HashMap+双向链表
 * 这里有五个属性别搞混淆的，对于 Node  next 属性，是用来维护整个集合中 Entry 的顺序。对于 Entry before，Entry after ，
 * 以及 Entry head，Entry tail，这四个属性都是用来维护保证集合顺序的链表，
 * 其中前两个before和after表示某个节点的上一个节点和下一个节点，这是一个双向链表。
 * 后两个属性 head 和 tail 分别表示这个链表的头节点和尾节点。
 *
 */
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
