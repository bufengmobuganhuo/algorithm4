package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import java.util.*;

/**
 * @author zhangyu
 * 2020/2/25 10:04
 * 练习2.4.25：计算数论
 */
public class EX_2_4_25<T extends Comparable<T>> {
    public static void main(String[] args) {
        EX_2_4_25<Double> ex_2_4_25=new EX_2_4_25<>();
        ex_2_4_25.solution(5);
    }
    public void solution(int N){
        MinPriorityQueue<Element> minPriorityQueue=new MinPriorityQueue();
        for(int i=0;i<=N;i++){
            for (int j=0;j<=N;j++){
                minPriorityQueue.insert(new Element(i,j,Math.pow(i,3)+Math.pow(j,3)));
            }
        }
        Map<String,List<String>> map = new HashMap<>();
        while (!minPriorityQueue.isEmpty()){
            Element element=minPriorityQueue.delMin();
            List<String> list=map.getOrDefault(String.valueOf(element.res),new ArrayList<>());
            list.add("("+element.i+","+element.j+")");
            map.put(String.valueOf(element.res),list);
        }
        for (String key:map.keySet()){
            List<String> list=map.get(key);
            for (String item:list){
                System.out.print(item+" ");
            }
            System.out.println();
        }
    }

    class Element implements Comparable<Element>{
        int i;
        int j;
        Double res;

        public Element(int i, int j, Double res) {
            this.i = i;
            this.j = j;
            this.res = res;
        }

        @Override
        public int compareTo(Element o) {
            return res.compareTo(o.res);
        }
    }

    class MinPriorityQueue<T extends Comparable<T>>{
        private T[] priorityQue;
        private int size;

        public MinPriorityQueue() {
            priorityQue=(T[])new Comparable[5];
        }

        public T delMin(){
            if (isEmpty()){
                throw new ArrayIndexOutOfBoundsException();
            }
            T res=priorityQue[1];
            exch(size,1);
            priorityQue[size--]=null;
            sink(1);
            if (size<=priorityQue.length/4){
                resize(priorityQue.length/2);
            }
            return res;
        }

        public void insert(T item){
            if (size==priorityQue.length-1){
                resize(priorityQue.length*2);
            }
            priorityQue[++size]=item;
            swim(size);
        }

        private void sink(int k){
            while (2*k<=size){
                int j=2*k;
                if (j<size&&greater(j,j+1)){
                    j++;
                }
                if (greater(j,k)){
                    break;
                }
                exch(j,k);
                k=j;
            }
        }

        private void swim(int k){
            while (k>1&&greater(k/2,k)){
                exch(k/2,k);
                k/=2;
            }
        }

        public boolean isEmpty(){
            return size==0;
        }
        private void exch(int i, int j){
            T temp=priorityQue[i];
            priorityQue[i]=priorityQue[j];
            priorityQue[j]=temp;
        }
        private void resize(int len){
            T[] temp=(T[])new Comparable[len];
            System.arraycopy(priorityQue,1,temp,1,size);
            priorityQue=temp;
        }
        private boolean greater(int i,int j){
            return priorityQue[i].compareTo(priorityQue[j])>0;
        }
    }
}
