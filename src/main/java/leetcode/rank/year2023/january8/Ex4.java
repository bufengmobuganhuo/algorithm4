package leetcode.rank.year2023.january8;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2023/1/8 11:39
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {

    }
    public int findCrossingTime(int n, int k, int[][] time) {
        // 左边等待过桥的人，等待过桥的顺序由效率决定，效率低的人先过桥，如果效率一样，则id大的人先过桥
        PriorityQueue<Worker> waitOnLeft = new PriorityQueue<>((o1, o2) -> {
            if (o1.priority != o2.priority) {
                return o2.priority - o1.priority;
            }
            return o2.id - o1.id;
        });
        // 右边等待过桥的人
        PriorityQueue<Worker> waitOnRight = new PriorityQueue<>(waitOnLeft.comparator());
        for (int i = 0; i < k; i++) {
            Worker worker = new Worker(i, 0, time[i][0] + time[i][2]);
            // 一开始都在等待从左边过桥
            waitOnLeft.offer(worker);
        }
        // 左边正在放箱子的人，堆顶表示最先完成当前搬箱子/放箱子的人
        PriorityQueue<Worker> workingOnLeft = new PriorityQueue<>(Comparator.comparingInt(o -> o.finishedTime));
        // 右边正在搬箱子的人
        PriorityQueue<Worker> workingOnRight = new PriorityQueue<>(workingOnLeft.comparator());
        int curTime = 0;
        while (n > 0) {
            // 如果有已经完成搬运箱子的，则将他们加入对应的过桥队列中
            while (!workingOnLeft.isEmpty() && workingOnLeft.peek().finishedTime <= curTime) {
                waitOnLeft.offer(workingOnLeft.poll());
            }
            while (!workingOnRight.isEmpty() && workingOnRight.peek().finishedTime <= curTime) {
                waitOnRight.offer(workingOnRight.poll());
            }
            // 如果右边有人在等待（已经完成搬箱子），则让右边的人先过桥，更新curTime为过完桥的时间
            if (!waitOnRight.isEmpty() && waitOnRight.peek().finishedTime <= curTime) {
                Worker worker = waitOnRight.poll();
                curTime += time[worker.id][2];
                // 过完桥可以立即放箱子
                worker.finishedTime = curTime + time[worker.id][3];
                workingOnLeft.offer(worker);
            } else if (!waitOnLeft.isEmpty() && waitOnLeft.peek().finishedTime <= curTime) {
                // 如果右边没人等待，则让左边有人等待过桥，更新curTime为过完桥的时间
                Worker worker = waitOnLeft.poll();
                curTime += time[worker.id][0];
                // 过完桥可以立即搬箱子, 结束时间就是搬完箱子的时间
                worker.finishedTime = curTime + time[worker.id][1];
                workingOnRight.offer(worker);
                n--;
            } else if (workingOnLeft.isEmpty()) {
                // 如果没人正在过桥，并且左边没人放箱子，将当前时间更新为右边正在搬箱子人的截止时间
                curTime = workingOnRight.peek().finishedTime;
            } else if (workingOnRight.isEmpty()) {
                // 如果没人正在过桥，且右边没人搬箱子，将当前时间更新为左边正在放箱子的截止时间
                curTime = workingOnLeft.peek().finishedTime;
            } else {
                // 如果二者都不为空，那就说明当前时间下，所有人都没完成工作，则更新为最先完成搬运箱子的人的截止时间
                curTime = Math.min(workingOnLeft.peek().finishedTime, workingOnRight.peek().finishedTime);
            }
        }
        // 等待箱子从左边全部搬完
        while (!workingOnRight.isEmpty()) {
            Worker worker = workingOnRight.poll();
            // 当前所有人都从右边搬完箱子，并且要都过了桥
            curTime = Math.max(worker.finishedTime, curTime) + time[worker.id][2];
        }
        return curTime;
    }
}

class Worker {
    int id;
    // 完成当前搬运工作的截止时间(比如正在左边放箱子，正在右边搬箱子)
    int finishedTime;
    // leftToRight + rightToLeft，表示效率的优先级
    int priority;

    public Worker(int id, int finishedTime, int priority) {
        this.id = id;
        this.finishedTime = finishedTime;
        this.priority = priority;
    }
}
