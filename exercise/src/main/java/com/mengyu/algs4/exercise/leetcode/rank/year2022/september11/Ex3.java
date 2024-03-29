package com.mengyu.algs4.exercise.leetcode.rank.year2022.september11;

/**
 * @author yuzhang
 * @date 2022/9/11 10:40
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] intervals = {{229966,812955},{308778,948377},{893612,952735},{395781,574123},{478514,875165},{766513,953839},{460683,491583},{133951,212694},{376149,838265},{541380,686845},{461394,568742},{804546,904032},{422466,467909},{557048,758709},{680460,899053},{110928,267321},{470258,650065},{534607,921875},{292993,994721},{645020,692560},{898840,947977},{33584,330630},{903142,970252},{17375,626775},{804313,972796},{582079,757160},{785002,987823},{599263,997719},{486500,527956},{566481,813653},{211239,863969},{808577,883125},{21880,516436},{264747,412144},{327175,772333},{984807,988224},{758172,916673},{23583,406006},{954674,956043},{379202,544291},{688869,785368},{841735,983869},{99836,916620},{332504,740696},{740840,793924},{896607,920924},{868540,922727},{125849,550941},{433284,685766}};
        System.out.println(new Ex3().minGroups(intervals));
    }

    public int minGroups(int[][] intervals) {
        int[] hash = new int[1000005];
        for (int[] interval : intervals) {
            // 区间开始，就是要多分一个组
            hash[interval[0]] += 1;
            // 区间结束，就是少分一个组
            // 因为是闭区间，所以边界也算区间没有结束
            hash[interval[1] + 1] -= 1;
        }
        int ans = 0, sum = 0;
        for (int i = 0; i < hash.length; i++) {
            sum += hash[i];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
