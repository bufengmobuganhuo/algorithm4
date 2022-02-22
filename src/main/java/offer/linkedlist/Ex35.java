package offer.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex35 {

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // A -> A' -> B -> B' -> C -> C'
        for (Node node = head; node != null ; node = node.next.next) {
            Node newNode = new Node(node.val);
            Node next = node.next;
            node.next = newNode;
            newNode.next = next;
        }
        // 构建random
        for (Node node = head; node != null; node = node.next.next) {
            Node newRandom = node.random != null ? node.random.next : null;
            node.next.random = newRandom;
        }
        // 拆出新链表
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> old2NewMap = new HashMap<>();
        Node newHead = getOrCreate(old2NewMap, head);
        while (head != null) {
            Node node = getOrCreate(old2NewMap, head);
            Node nextNode = getOrCreate(old2NewMap, head.next);
            Node randomNode = getOrCreate(old2NewMap, head.random);
            node.next = nextNode;
            node.random = randomNode;
            head = head.next;
        }
        return newHead;
    }

    private Node getOrCreate(Map<Node, Node> old2NewMap, Node node) {
        if (node == null) {
            return null;
        }
        return old2NewMap.computeIfAbsent(node, key -> new Node(node.val));
    }
}
