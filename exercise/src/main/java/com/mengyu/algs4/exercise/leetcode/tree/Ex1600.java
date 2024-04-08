package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1600 {

    private Node root;

    private Map<String, Node> nodeMap;

    public Ex1600(String kingName) {
        root = new Node(kingName);
        nodeMap = new HashMap<>();
        nodeMap.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        Node node = nodeMap.get(parentName);
        Node newNode = new Node(childName);
        nodeMap.put(childName, newNode);
        node.children.add(newNode);
    }

    public void death(String name) {
        nodeMap.get(name).isDeath = true;
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new LinkedList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(Node node, List<String> track) {
        if (node == null) {
            return;
        }
        if (!node.isDeath) {
            track.add(node.name);
        }
        for (Node children : node.children) {
            dfs(children, track);
        }
    }

    private static class Node {
        private String name;

        private boolean isDeath;

        private List<Node> children;

        public Node(String name) {
            this.name = name;
            this.children = new LinkedList<>();
        }
    }
}
