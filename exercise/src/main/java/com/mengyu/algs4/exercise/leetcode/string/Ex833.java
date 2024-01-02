package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex833 {

    public static void main(String[] args) {
        System.out.println(new Ex833().findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg","ggq","mo"},
                new String[]{"s","so","bfr"}));
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int l = s.length();
        int[][] map = new int[l][2];
        for (int i = 0; i < indices.length; i++) {
            int indice = indices[i];
            if (s.startsWith(sources[i], indice)) {
                map[indice] = new int[]{indice + sources[i].length(), i};
            }
        }
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        for (int i = 0; i < l; i++) {
            if (map[i][0] != 0) {
                sb.append(targets[map[i][1]]);
                pre = map[i][0];
            } else if (pre <= i) {
                sb.append(s.charAt(pre));
                pre++;
            }
        }
        return sb.toString();
    }
}
