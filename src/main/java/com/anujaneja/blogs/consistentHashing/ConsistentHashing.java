package com.anujaneja.blogs.consistentHashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing<T> {
    private final HashFunction hashFunction;
    private final int          numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    public ConsistentHashing(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            addNode(node);
        }
    }

    public void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = hashFunction.hash(key);


        if (!circle.containsKey(key)) {
            //if hash is not present on the circle then find the tail Map of hashes greater than given hash of the key
            // find its first hash....if there is no hash remaining means we wrap around to first of the circle....
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }

        return circle.get(hash);
    }

}
