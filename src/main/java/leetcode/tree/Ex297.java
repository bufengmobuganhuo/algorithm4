package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex297 {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("Null");
            sb.append(",");
        } else {
            sb.append(root.val);
            sb.append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        LinkedList<String> dataList = new LinkedList<>();
        for (String node : nodes) {
            dataList.offerLast(node);
        }
        return deserialize(dataList);
    }

    private TreeNode deserialize(LinkedList<String> dataList) {
        if (dataList.get(0).equals("Null")){
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        root.left = deserialize(dataList);
        root.right = deserialize(dataList);
        return root;
    }
}
