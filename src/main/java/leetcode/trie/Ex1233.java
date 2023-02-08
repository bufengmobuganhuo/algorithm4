package leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1233 {
    public static void main(String[] args) {
        String[] folder = {"/a/b/c","/a/b/ca","/a/b/d"};
        System.out.println(new Ex1233().removeSubfolders2(folder));
    }
    public List<String> removeSubfolders1(String[] folder) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(folder);
        for (int i = 0; i < folder.length;) {
            String root = folder[i];
            while (++i < folder.length && folder[i].startsWith(root) && (folder[i].length() == root.length() || folder[i].charAt(root.length()) == '/')) {

            }
            ans.add(root);
        }
        return ans;
    }

    /**
     * 使用字典树，将每个文件路径按照'/'拆分后插入字典树，每个节点ref表示folder[i]，那么构建完字典树后，可以使用深度优先搜索
     * 在深度优先搜索中遇到的第一个ref != -1的节点就是父文件夹（因为更深的节点肯定是子文件夹），如果ref=-1，说明它是一个中间节点，则继续遍历
     * @param folder
     * @return
     */
    public List<String> removeSubfolders2(String[] folder) {
        Trie root = new Trie();
        for (int i = 0; i < folder.length; i++) {
            String path = folder[i];
            List<String> paths = split(path);
            Trie cur = root;
            for (String subPath : paths) {
                cur.children.putIfAbsent(subPath, new Trie());
                cur = cur.children.get(subPath);
            }
            // 到达了节点位置
            cur.ref = i;
        }
        List<String> ans = new ArrayList<>();
        dfs(root, folder, ans);
        return ans;
    }


    private void dfs(Trie trie, String[] folder, List<String> ans) {
        if (trie.ref != -1) {
            ans.add(folder[trie.ref]);
            return;
        }
        for (String name : trie.children.keySet()) {
            dfs(trie.children.get(name), folder, ans);
        }
    }

    // 按照'/'将路径拆分
    private List<String> split(String path) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        for (char chr : path.toCharArray()) {
            if (chr == '/') {
                res.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(chr);
            }
        }
        res.add(sb.toString());
        return res;
    }

    static class Trie {
        int ref = -1;

        Map<String, Trie> children;

        public Trie() {
            this.children = new HashMap<>();
        }
    }

}
