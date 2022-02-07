package offer.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yu zhang
 */
public class Ex104 {
    public static void main(String[] args) {
        List<A> as = new ArrayList<>();
        as.add(new A(1));
        as.add(new A(2));
        as.add(new A(3));
        List<A> aList = as.stream().map(new Function<A, A>() {
            @Override
            public A apply(A a) {
                if (a.field != 2) {
                    return null;
                }
                return a;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(aList);
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 1;
        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (num < i) {
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    private static class A {
        private int field;

        public A(int field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "A{" +
                    "field=" + field +
                    '}';
        }
    }
}
