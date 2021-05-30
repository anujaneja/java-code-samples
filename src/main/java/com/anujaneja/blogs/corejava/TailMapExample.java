package com.anujaneja.blogs.corejava;

import java.util.SortedMap;
import java.util.TreeMap;

public class TailMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "By");
        treeMap.put(2, "Hook");
        treeMap.put(3, "Or");
        treeMap.put(4, "Crook");

        System.out.println("Original Map: " + treeMap);

        SortedMap<Integer, String> tailMap = treeMap.tailMap(2);

        System.out.println("Tail Map: " + tailMap);

        SortedMap<Integer, String> tailMap1 = treeMap.tailMap(2, false);

        System.out.println("Tail Map excluding from key : " + tailMap1);
    }
}
