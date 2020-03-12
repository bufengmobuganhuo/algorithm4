package chapter2_Sorting.chapter2_5_Applications.exercises;

import java.util.*;

/**
 * @author zhangyu
 * 2020/3/12 10:00
 * 练习2.5.22：股票交易
 * 输入：buy 2 3：表示买家愿意以2元价格买3笔
 *      sell 2 3：表示卖家愿意以2元价格卖3笔
 */
public class EX_2_5_22 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        List<String> tickets=new ArrayList<String>(){
            {
                add("sell 10 100");
                add("buy 8 10");
                add("buy 12 50");
                add("sell 7 5");
                add("buy 11 100");
            }
        };

        EX_2_5_22 ex_2_5_22=new EX_2_5_22();
        ex_2_5_22.solution(tickets);
    }
    public void solution(List<String> tickets){
        PriorityQueue<Ticket> minSellersPQ=new PriorityQueue<>(tickets.size());
        PriorityQueue<Ticket> maxBuyersPQ=new PriorityQueue<>(tickets.size(), new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i=0;i<tickets.size();i++){
            String[] params=tickets.get(i).split(" ");
            Ticket ticket=new Ticket(Float.parseFloat(params[1]),Integer.parseInt(params[2]));
            if ("buy".equals(params[0])) {
                maxBuyersPQ.add(ticket);
            } else {
                minSellersPQ.add(ticket);
            }
        }
        while (!minSellersPQ.isEmpty()&&!maxBuyersPQ.isEmpty()){
            //如果买家最高出价<卖家最低出价，则所以交易都无法达成
            if (maxBuyersPQ.peek().price<minSellersPQ.peek().price){
                break;
            }
            Ticket buyer=maxBuyersPQ.poll();
            Ticket seller=minSellersPQ.poll();
            //看交易笔数
            if (buyer.count>seller.count){
                buyer.count-=seller.count;
                System.out.println("卖家价格："+seller.price+" 买家价格："+buyer.price+" 交易量："+seller.count);
                maxBuyersPQ.add(buyer);
            }else if(buyer.count<seller.count){
                seller.count-=buyer.count;
                System.out.println("卖家价格："+seller.price+" 买家价格："+buyer.price+" 交易量："+buyer.count);
                minSellersPQ.add(seller);
            }else{
                System.out.println("卖家价格："+seller.price+" 买家价格："+buyer.price+" 交易量："+seller.count);
            }
        }
    }
    class Ticket implements Comparable<Ticket>{
        Float price;
        int count;

        public Ticket(Float price, int count) {
            this.price = price;
            this.count = count;
        }

        @Override
        public int compareTo(Ticket o) {
            return price.compareTo(o.price);
        }
    }
}
