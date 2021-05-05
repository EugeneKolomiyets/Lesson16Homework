package com.alevel;

public class MyTree <K extends Comparable, V> {

    private K key;
    private V value;
    private MyTree<K, V> left;
    private MyTree<K, V> right;

    public MyTree() {
    }

    private MyTree(K key, V value, MyTree<K, V> left, MyTree<K, V> right) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.key = key;
    }

    public MyTree<K, V> getLeft() {
        return left;
    }

    public MyTree<K, V> getRight() {
        return right;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public void delete(K key){
        if (key==null)
            return;
        MyTree<K,V> node = getNodeByKey(this,key);
        MyTree<K,V> parent = getParentNodeByKey(this,null,key);
        deleteRecursia(node,parent,key);
    }

    private void deleteRecursia(MyTree<K,V> node, MyTree<K,V> parent,K key){
        //Дано: дерево Т с корнем n и ключом K.
        //Задача: удалить из дерева Т узел с ключом K (если такой есть).
        if (node==null)
            //Если дерево T пусто, остановиться;
            return;

        int resultOfCompare=key.compareTo(node.key);
        if (resultOfCompare == 0) {
            //Если K=X, то необходимо рассмотреть три случая
            if (node.left == null && node.right == null) {
                //Если обоих детей нет, то удаляем текущий узел и обнуляем ссылку на него у родительского узла;
                if (parent != null) {
                    if (parent.left.equals(node))
                        parent.left = null;
                    else if (parent.right.equals(node))
                        parent.right = null;
                }
                node = null;
            } else if (node.left != null && node.right == null) {
                //Если одного из детей нет, то значения полей ребёнка m ставим вместо соответствующих значений корневого узла, затирая его старые значения, и освобождаем память, занимаемую узлом m;
                if (parent != null) {
                    if (parent.left.equals(node)) {
                        parent.left.key = node.left.key;
                        parent.left.value = node.left.value;
                    } else if (parent.right.equals(node)) {
                        parent.right.key = node.left.key;
                        parent.right.value = node.left.value;
                    }
                }
                node.left = null;
                node = null;
            } else if (node.left == null && node.right != null) {
                if (parent != null) {
                    if (parent.left.equals(node)) {
                        parent.left.key = node.right.key;
                        parent.left.value = node.right.value;
                    } else if (parent.right.equals(node)) {
                        parent.right.key = node.right.key;
                        parent.right.value = node.right.value;
                    }
                }
                node.right = null;
                node = null;
            } else if (node.left != null && node.right != null) {
                    /*Если оба ребёнка присутствуют, то
                        Если самый левый элемент правого поддерева m не имеет поддеревьев
                            Копируем значения K, V из m в удаляемый элемент
                            Удаляем m
                        Если m имеет правое поддерево
                            Копируем значения K, V из m в удаляемый элемент
                            Заменяем у родительского узла ссылку на m ссылкой на правое поддерево m
                            Удаляем m
                    */
                if (node.right.left == null) {
                    node.key = node.right.key;
                    node.value = node.right.value;
                    if (node.right.right == null) {
                        node.right = null;
                    } else {
                        node.right = node.right.right;
                        node.right.right = null;
                    }
                } else if (node.right.left != null) {
                    MyTree<K, V> nodeLeftParent = node;
                    MyTree<K, V> nodeLeft = node.right;
                    while (nodeLeft.left != null) {
                        nodeLeftParent = nodeLeft;
                        nodeLeft = nodeLeft.left;
                    }
                    node.key = nodeLeft.key;
                    node.value = nodeLeft.value;
                    if (nodeLeft.right == null) {
                        nodeLeftParent.left = null;
                    } else {
                        nodeLeftParent.left = nodeLeft.right;
                        nodeLeft.right = null;
                    }
                    nodeLeft = null;
                }
            }
        } else if(resultOfCompare<0) {
            //Если K<X, рекурсивно удалить K из левого поддерева Т;
            if (node.left == null)
                return;
            else
                deleteRecursia(node.left, node, key);
        } else if(resultOfCompare>0) {
            //Если K>X, рекурсивно удалить K из правого поддерева Т;
            if (node.right == null)
                return;
            else
                deleteRecursia(node.right, node, key);
        }
    }

    public V get(K key){

        if (key==null)
            return null;
        return getValueByKey(this, key);

    }

    private <K extends Comparable, V> V getValueByKey(MyTree<K, V> node, K key) {

        if (node==null)
            return null;
        int resultOfCompare=key.compareTo(node.key);
        V value = null;
        if(resultOfCompare==0)
            value = node.getValue();
        else if (resultOfCompare<0)
            value = getValueByKey(node.getLeft(), key);
        else if (resultOfCompare>0)
            value = getValueByKey(node.getRight(),key);
        return value;
        /*if (tree.getKey().equals(key)) {
            return tree.getValue();
        }
        V value = null;
        if (tree.getLeft() != null) {
            value = getValueByKey(tree.getLeft(), key);
        }
        if (value == null && tree.getRight() != null) {
            value = getValueByKey(tree.getRight(), key);
        }
        return value;*/
    }

    private  <K extends Comparable, V> MyTree getNodeByKey(MyTree<K, V> tree, K key) {
        if (tree.getKey().equals(key)) {
            return tree;
        }

        MyTree node = null;
        if (tree.getLeft() != null) {
            node = getNodeByKey(tree.getLeft(), key);
        }
        if (node == null && tree.getRight() != null) {
            node = getNodeByKey(tree.getRight(), key);
        }
        return node;
    }
    private  <K extends Comparable, V> MyTree getParentNodeByKey(MyTree<K, V> tree, MyTree<K, V> parent, K key) {
        if (tree.getKey().equals(key)) {
            return parent;
        }

        MyTree node = null;
        if (tree.getLeft() != null) {
            node = getParentNodeByKey(tree.getLeft(), tree, key);
        }
        if (node == null && tree.getRight() != null) {
            node = getParentNodeByKey(tree.getRight(), tree, key);
        }
        return node;
    }

    public void add(K key, V value){

        if (key==null)
            return;
        if (this.key==null){
            this.value=value;
            this.key=key;
            return;
        }
        addRecursia(this,key,value);

  }

    private void addRecursia(MyTree<K, V> node, K key, V value){
        if (node.key==null){
            node.key = key;
            node.value = value;
            return;
        }

        int resultOfCompare=key.compareTo(node.key);
        if (resultOfCompare==0)
            node.value=value;
        else if(resultOfCompare<0) {
            if (node.left == null)
                node.left = new MyTree<>();
            addRecursia(node.left, key, value);
        } else if(resultOfCompare>0) {
            if (node.right == null)
                node.right = new MyTree<>();
            addRecursia(node.right, key, value);
        }

        //System.out.println(node.key+" "+key);
    }


}
