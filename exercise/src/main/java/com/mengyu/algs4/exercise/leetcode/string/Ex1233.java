package com.mengyu.algs4.exercise.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1233 {

    public static void main(String[] args) {
        String[] folders = {"/a/b/c","/a/b/ca","/a/b/d"};
        String[] folders1 = {"/a/b/c","/a/b/c/e","/a/b/d"};
        System.out.println(new Ex1233().removeSubfolders(folders));
        System.out.println(new Ex1233().removeSubfolders(folders1));
    }


    public List<String> removeSubfolders2(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < folder.length; i++) {
            String root = folder[i];
            while (++i < folder.length && folder[i].startsWith(root) && (folder[i].length() == root.length() || folder[i].charAt(root.length()) == '/')){

            }
            ans.add(root);
        }
        return ans;
    }

    private Node root;

    private boolean needDeleted;

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new LinkedList<>();
        for (String s : folder) {
            needDeleted = false;
            root = insert(root, s, 0, "");
            if (!needDeleted) {
                ans.add(s);
            }
        }
        return ans;
    }

    private Node insert(Node node, String path, int index, String name) {
        if (node == null) {
            node = new Node();
        }
        if (index == path.length()) {
            node.name = name;
            return node;
        }
        if (name.equals(node.name)) {
            needDeleted = true;
        }
        char chr = path.charAt(index);
        if (chr == '/') {
            index++;
            chr = path.charAt(index);
            int endIdx = path.indexOf('/', index) < 0 ? path.length() : path.indexOf('/', index);
            name = path.substring(index,  endIdx);
        }
        node.next[chr - 'a'] = insert(node.next[chr - 'a'], path, index + 1, name);
        return node;
    }

    private static class Node {
        private String name;

        private Node[] next = new Node[27];
    }
}
