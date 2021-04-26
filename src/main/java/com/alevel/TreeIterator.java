package com.alevel;

import java.util.LinkedList;
import java.util.Queue;

public class TreeIterator {

    public <K, V> V searchInWidth(MyTree<K, V> tree, K key) {
        Queue<MyTree<K, V>> queue = new LinkedList<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            MyTree<K, V> element = queue.poll();
            if (element.getKey().equals(key)) {
                return element.getValue();
            }

            if (element.getLeft() != null) {
                queue.offer(element.getLeft());
            }
            if (element.getRight() != null) {
                queue.offer(element.getRight());
            }
        }
        return null;
    }

    public <K, V> V searchInDeep(MyTree<K, V> tree, K key) {
        if (tree.getKey().equals(key)) {
            return tree.getValue();
        }

        V value = null;
        if (tree.getLeft() != null) {
            value = searchInDeep(tree.getLeft(), key);
        }
        if (value == null && tree.getRight() != null) {
            value = searchInDeep(tree.getRight(), key);
        }
        return value;
    }
}
