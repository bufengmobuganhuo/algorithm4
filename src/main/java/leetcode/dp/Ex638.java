package leetcode.dp;

import huawei.Ex8;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/9/21 10:33 上午
 * TODO
 */
public class Ex638 {
    public static void main(String[] args) {
        // [2,5], [[3,0,5],[1,2,10]], [3,2]
        List<Integer> price = Arrays.asList(2,5);
        List<List<Integer>> special = new ArrayList<>();
        special.add(Arrays.asList(3,0,5));
        special.add(Arrays.asList(1,2,10));
        List<Integer> needs = Arrays.asList(3,2);
        Ex638 ex638 = new Ex638();
        System.out.println(ex638.shoppingOffers2(price,special,needs));
    }

    /**
     * 解法二：记忆化搜索，当needs相同时，花费是相同的
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shopping2(price, special, needs, new HashMap<>());
    }

    private int shopping2(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>,Integer> map) {
        if (map.containsKey(needs)){
            map.get(needs);
        }
        int res = dot(price,needs);
        int i = 0;
        for (List<Integer> pkg : special) {
            List<Integer> clone = new ArrayList<>(needs);
            for (i = 0; i < needs.size(); i++) {
                int diff = clone.get(i)-pkg.get(i);
                if (diff<0){
                    break;
                }
                clone.set(i,diff);
            }
            if (i==needs.size()){
                res = Math.min(res,pkg.get(i)+shopping2(price,special,clone,map));
                map.put(needs,pkg.get(i));
            }
        }
        return res;
    }

    /**
     * 解法一：使用递归
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shopping(price, special, needs);
    }

    private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int j = 0;
        // 只使用单个物品的花费
        int res = dot(price, needs);
        // 使用大礼包的花费
        for (List<Integer> pkg : special) {
            List<Integer> clone = new ArrayList<>(needs);
            for (j = 0; j < needs.size(); j++) {
                int diff = clone.get(j) - pkg.get(j);
                // 大礼包中的东西多余了
                if (diff < 0) {
                    break;
                }
                clone.set(j, diff);
            }
            // 说明可以购买大礼包
            if (j == needs.size()) {
                res = Math.min(res, pkg.get(j) + shopping(price, special, clone));
            }
        }
        return res;
    }

    private int dot(List<Integer> price, List<Integer> needs) {
        int res = 0;
        for (int i = 0; i < needs.size(); i++) {
            res += price.get(i) * needs.get(i);
        }
        return res;
    }
}
