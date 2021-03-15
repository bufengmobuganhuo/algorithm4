package leetcode.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuzhang
 * @date 2021/4/8 上午9:39
 * TODO
 */
public class Ex1279 {
}

class TrafficLight {
    private ReentrantLock lock = new ReentrantLock();
    // road1的绿灯是否开启
    private boolean road1GreenOn = true;

    public TrafficLight() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        lock.lock();
        try{
            // 如果当前要走road1，但是没有开启绿灯，则开启
            if (roadId == 1 && !road1GreenOn){
                turnGreen.run();
                road1GreenOn = true;
                // 如果当前要走road2，但是没有开启绿灯，则开启
            }else if (roadId == 2 && road1GreenOn){
                turnGreen.run();
                road1GreenOn = false;
            }
            crossCar.run();
        }finally {
            lock.unlock();
        }
    }
}
