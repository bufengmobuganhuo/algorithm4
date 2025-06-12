package com.mengyu.algs4.interview.ant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2025/4/29 11:06
 * TODO
 */
public class Solution {

    public static void main(String[] args) {
        UserAsset userAsset1 = new UserAsset(1L, BigDecimal.valueOf(10000));
        UserAsset userAsset2 = new UserAsset(2L, BigDecimal.valueOf(18001.21));
        UserAsset userAsset3 = new UserAsset(3L, BigDecimal.valueOf(16003.52));
        UserAsset userAsset4 = new UserAsset(4L, BigDecimal.valueOf(604));
        UserAsset userAsset5 = new UserAsset(5L, BigDecimal.valueOf(8010.19));

        List<UserAsset> userAssets = Arrays.asList(userAsset1, userAsset2, userAsset3, userAsset4, userAsset5);
        BigDecimal profit = BigDecimal.valueOf(6);
        allocate(userAssets, profit);
        System.out.println(userAssets);
    }

    public static void allocate(List<UserAsset> userAssets, BigDecimal profit) {
        if (userAssets == null || userAssets.isEmpty() || profit == null || profit.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        BigDecimal totalPrinAmount = BigDecimal.ZERO;
        for (UserAsset userAsset : userAssets) {
            totalPrinAmount = totalPrinAmount.add(userAsset.prinAmount);
        }
        BigDecimal allocatedAmount = BigDecimal.ZERO;
        for (UserAsset userAsset : userAssets) {
            if (userAsset.prinAmount == null) {
                continue;
            }
            BigDecimal totalProfitAmount = userAsset.prinAmount.divide(totalPrinAmount, 14, RoundingMode.HALF_UP).multiply(profit);
            BigDecimal profitAmount = BigDecimal.valueOf(totalProfitAmount.doubleValue())
                    .setScale(2, RoundingMode.DOWN);
            BigDecimal tailProfitAmount = totalProfitAmount.subtract(profitAmount);
            allocatedAmount = allocatedAmount.add(profitAmount);
            userAsset.profitAmount = profitAmount;
            userAsset.tailProfitAmount = tailProfitAmount;
        }
        userAssets = userAssets.stream().sorted(new Comparator<UserAsset>() {
            @Override
            public int compare(UserAsset o1, UserAsset o2) {
                return o2.tailProfitAmount.compareTo(o1.tailProfitAmount);
            }
        }).collect(Collectors.toList());
        int idx = 0;
        // 1, 2, 3 5
        while (allocatedAmount.compareTo(profit) < 0) {
            UserAsset userAsset = userAssets.get(idx % userAssets.size());
            userAsset.isAllocatedTailProfit = true;
            userAsset.profitAmount = userAsset.profitAmount.add(BigDecimal.valueOf(0.01));
            allocatedAmount = allocatedAmount.add(BigDecimal.valueOf(0.01));
            idx++;
        }
    }

    private static class UserAsset{
        private long userId;

        private BigDecimal prinAmount;

        private BigDecimal profitAmount;

        private BigDecimal tailProfitAmount;

        private boolean isAllocatedTailProfit = false;

        public UserAsset(long userId, BigDecimal prinAmount) {
            this.userId = userId;
            this.prinAmount = prinAmount;
        }

        @Override
        public String toString() {
            return "UserAsset{" +
                    "userId=" + userId +
                    ", prinAmount=" + prinAmount +
                    ", profitAmount=" + profitAmount +
                    ", tailProfitAmount=" + tailProfitAmount +
                    ", isAllocatedTailProfit=" + isAllocatedTailProfit +
                    '}';
        }
    }
}
