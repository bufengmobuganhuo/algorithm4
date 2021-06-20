package leetcode.heap;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/5/30 上午10:50
 * TODO
 */
public class Ex1882 {
    public static void main(String[] args) {
        int[] servers = {10, 63, 95, 16, 85, 57, 83, 95, 6, 29, 71};
        int[] tasks = {70, 31, 83, 15, 32, 67, 98, 65, 56, 48, 38, 90, 5};
        Ex1882 ex1882 = new Ex1882();
        System.out.println(Arrays.toString(ex1882.assignTasks(servers, tasks)));
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        // {服务器权重，服务器下标}
        PriorityQueue<int[]> service = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        for (int i = 0; i < servers.length; i++) {
            service.offer(new int[]{servers[i], i});
        }
        // {到期时间，服务器权重，服务器下标}
        PriorityQueue<int[]> time = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int current = 0;
        int[] ans = new int[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            current = Integer.max(current, i);
            // 将i之后
            while (!time.isEmpty() && time.peek()[0] <= current) {
                int[] c = time.poll();
                service.offer(new int[]{c[1], c[2]});
            }
            // 如果没有备选服务器
            if (service.isEmpty()) {
                // 找出最小的到期时间的服务器(记到期时间为p)，他可以为task[p....x]服务
                current = time.peek()[0];
                while (!time.isEmpty() && time.peek()[0] <= current) {
                    int[] c = time.poll();
                    service.offer(new int[]{c[1], c[2]});
                }
            }
            // 能为i服务器的就是堆顶服务器
            int[] s = service.poll();
            ans[i] = s[1];
            time.offer(new int[]{current + tasks[i], s[0], s[1]});
        }
        return ans;
    }

    private Server getServer(Map<Integer, PriorityQueue<Server>> serverMap, int i) {
        Server server = null;
        while (i >= 0) {
            if (!serverMap.containsKey(i)) {
                i--;
                continue;
            }
            if (server == null) {
                server = serverMap.get(i).poll();
                i--;
                continue;
            }
            Server server1 = serverMap.get(i).peek();
            if (server1!=null && server1.compareTo(server) < 0) {
                serverMap.get(server.expireTime).offer(server);
                server = serverMap.get(i).poll();
            }
            i--;
        }
        return server;
    }

    static class Task implements Comparable<Task> {
        int idx;
        int cost;

        public Task(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Task o) {
            return idx - o.idx;
        }
    }

    static class Server implements Comparable<Server> {
        int idx;
        int weight;
        int expireTime;

        public Server(int idx, int weight, int expireTime) {
            this.idx = idx;
            this.weight = weight;
            this.expireTime = expireTime;
        }

        @Override
        public int compareTo(Server o) {
            if (weight != o.weight) {
                return weight - o.weight;
            }
            return idx - o.idx;
        }
    }
}
