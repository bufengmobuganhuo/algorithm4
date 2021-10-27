package leetcode.rank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/9/4 下午11:31
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        Ex3 ex3 = new Ex3(parent);
        System.out.println(ex3.lock(2, 2));
        System.out.println(ex3.unlock(2, 3));
        System.out.println(ex3.unlock(2, 2));
        System.out.println(ex3.lock(4, 5));
        System.out.println(ex3.upgrade(0, 1));
        System.out.println(ex3.lock(0, 1));
    }
    private int[] locks;

    private List<Integer>[] children;

    private int[] parents;

    private boolean hasLock;

    public Ex3(int[] parent) {
        int len = parent.length;
        children =  new List[len];
        locks = new int[len];
        parents = parent;
        children[0] = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            if (children[parent[i]] == null) {
                children[parent[i]] = new ArrayList<>();
            }
            children[parent[i]].add(i);
        }
    }

    public boolean lock(int num, int user) {
        if (locks[num] == 0) {
            locks[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if (locks[num] != user) {
            return false;
        }
        locks[num] = 0;
        return true;
    }

    public boolean upgrade(int num, int user) {
        hasLock = false;
        if (locks[num] != 0) {
            return false;
        }
        hasLock(num);
        if (!hasLock) {
            return false;
        }
        int parent = parents[num];
        while (parent != -1) {
            if (locks[parent] != 0) {
                return false;
            }
            parent = parents[parent];
        }
        locks[num] = user;
        unlock(num);
        return true;
    }

    private void unlock(int parent) {
        if (children[parent] == null) {
            return;
        }
        for (int child : children[parent]) {
            locks[child] = 0;
            unlock(child);
        }
    }

    private void hasLock(int parent) {
        if (hasLock) {
            return;
        }
        if (children[parent] == null) {
            return;
        }
        for (int child : children[parent]) {
            if (locks[child] != 0) {
                hasLock = true;
                return;
            }
            hasLock(child);
        }
    }
}
