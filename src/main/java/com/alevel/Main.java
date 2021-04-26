package com.alevel;

public class Main {

    public static void main(String[] args) {
        //System.out.println("ss".compareTo("sss"));
        //System.out.println("end");
        MyTree<String, String> tree = new MyTree<>();
        tree.add("b","вв");
        tree.add("b","вв1");
        tree.add("a","вв");
        tree.add("a1","вв1");
        tree.add("a","в1");
        tree.add("e","в12");
        tree.add("d","в13");
        tree.add("c","в14");
        tree.add("c1","в15");
        tree.delete("b");
        //System.out.println(tree);
        //System.out.println(MyTree.getNodeByKey(tree,"а").getValue());
        System.out.println(tree.get("a2"));
        System.out.println(tree.get("a1"));


        /*TreeIterator iterator = new TreeIterator();
        System.out.println(iterator.searchInWidth(tree, "5.0"));
        System.out.println(iterator.searchInDeep(tree, "5.0"));*/
    }
}
