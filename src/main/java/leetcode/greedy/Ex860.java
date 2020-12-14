package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/10 上午9:46
 * TODO
 */
public class Ex860 {
    public static void main(String[] args) {
        Ex860 ex860 = new Ex860();
        int[] bills = {5, 5, 10, 10, 20};
        System.out.println(ex860.lemonadeChange(bills));
    }

    public boolean lemonadeChange2(int[] bills) {
        if (bills == null || bills.length == 0) {
            return true;
        }
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                ten++;
                five--;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five > 2) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) {
            return true;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int bill : bills) {
            if (!canChange(map, bill - 5)) {
                return false;
            }
        }
        return true;
    }

    private boolean canChange(Map<Integer, Integer> map, int bill) {
        switch (bill) {
            case 5:
                int left = map.getOrDefault(5, 0);
                if (left > 0) {
                    map.put(5, left - 1);
                    map.put(10, map.getOrDefault(10, 0) + 1);
                    return true;
                } else {
                    return false;
                }
            case 15:
                // 使用第一种组合
                int tenLeft = map.getOrDefault(10, 0);
                int fiveLeft = map.getOrDefault(5, 0);
                if (tenLeft > 0 && fiveLeft > 0) {
                    map.put(10, tenLeft - 1);
                    map.put(5, fiveLeft - 1);
                    map.put(20, map.getOrDefault(20, 0) + 1);
                    return true;
                } else if (fiveLeft > 2) {
                    map.put(20, map.getOrDefault(20, 0) + 1);
                    map.put(5, fiveLeft - 3);
                    return true;
                }
                return false;
            default:
                map.put(5, map.getOrDefault(5, 0) + 1);
                return true;
        }
    }
}
