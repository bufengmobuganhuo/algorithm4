package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

/**
 * @author zhangyu
 * 2020/6/10 20:29
 * 练习2.4.29：第一次尝试
 */
public class EX_2_4_29_1 {
    public static void main(String[] args) {
        Integer[] values={1,5,7,6,2,8,4,3,0};
        EX_2_4_29_1 ex_2_4_29_1=new EX_2_4_29_1();
        for (int i = 0; i < values.length; i++) {
            ex_2_4_29_1.insert(values[i]);
        }
        System.out.println(ex_2_4_29_1.delMax());
        System.out.println(ex_2_4_29_1.delMin());
    }
    private MaxPriorityQueue maxPriorityQueue;
    private MinPriorityQueue minPriorityQueue;
    private int size;

    public EX_2_4_29_1() {
        maxPriorityQueue=new MaxPriorityQueue();
        minPriorityQueue=new MinPriorityQueue();
    }

    public Integer delMin(){
        QueueElement res=minPriorityQueue.delMin();
        maxPriorityQueue.remove(res.pair.index);
        size--;
        return res.val;
    }

    public Integer delMax(){
        QueueElement res=maxPriorityQueue.delMax();
        minPriorityQueue.remove(res.pair.index);
        size--;
        return res.val;
    }

    public void insert(Integer val){
        QueueElement minQueueEle=new QueueElement(val,++size);
        QueueElement maxQueueEle=new QueueElement(val,size);
        minQueueEle.pair=maxQueueEle;
        maxQueueEle.pair=minQueueEle;

        minPriorityQueue.add(minQueueEle);
        maxPriorityQueue.add(maxQueueEle);
    }

    class MaxPriorityQueue{
        private QueueElement[] priorityQue;
        private int size;

        public MaxPriorityQueue() {
            priorityQue=new QueueElement[16];
        }

        public QueueElement remove(int index){
            exch(index,size);
            QueueElement res=priorityQue[size];
            priorityQue[size--]=null;
            sink(index);
            return res;
        }

        public QueueElement delMax(){
            exch(1,size);
            QueueElement res=priorityQue[size];
            priorityQue[size--]=null;
            sink(1);
            return res;
        }

        public void add(QueueElement element){
            priorityQue[++size]=element;
            swim(size);
        }

        private void swim(int k){
            while (k>1&&priorityQue[k/2].val<priorityQue[k].val){
                exch(k,k/2);
                k/=2;
            }
        }

        private void sink(int k){
            while (2*k<=size){
                int j=2*k;
                if (j+1<size&&priorityQue[j].val<priorityQue[j+1].val){
                    j++;
                }
                if (priorityQue[k].val>priorityQue[j].val){
                    break;
                }
                exch(k,j);
                k=j;
            }
        }

        private void exch(int i, int j){
            //交换另一队列中对该队列中的引用
            priorityQue[i].pair.pair=priorityQue[j];
            priorityQue[j].pair.pair=priorityQue[i];

            Integer tempValueI=priorityQue[i].val;
            QueueElement tempPairI=priorityQue[i].pair;

            //交换本队列中的value
            priorityQue[i].val=priorityQue[j].val;
            priorityQue[j].val=tempValueI;

            priorityQue[i].pair=priorityQue[j].pair;
            priorityQue[j].pair=tempPairI;
        }
    }

    class MinPriorityQueue {
        private QueueElement[] priorityQue;
        private int size;

        public MinPriorityQueue() {
            priorityQue=new QueueElement[16];
        }

        public QueueElement remove(int index){
            exch(index,size);
            QueueElement res=priorityQue[size];
            priorityQue[size--]=null;
            sink(index);
            return res;
        }

        public QueueElement delMin(){
            exch(1,size);
            QueueElement res=priorityQue[size];
            priorityQue[size--]=null;
            sink(1);
            return res;
        }

        public void add(QueueElement ele){
            priorityQue[++size]=ele;
            swim(size);
        }


        private void sink(int k){
            while (2*k<size){
                int j=2*k;
                //二者取最小值
                if (j+1<size&&priorityQue[j+1].val<priorityQue[j].val){
                    j++;
                }
                if (priorityQue[k].val<priorityQue[j].val){
                    break;
                }
                exch(k,j);
                k=j;
            }
        }

        /**
         * @param k 上浮k
         */
        private void swim(int k){
            while (k>1&&priorityQue[k].val<priorityQue[k/2].val){
                exch(k,k/2);
                k/=2;
            }
        }

        private void exch(int i, int j){
            //替换另一队列内对于本队列的指针
            priorityQue[i].pair.pair=priorityQue[j];
            priorityQue[j].pair.pair=priorityQue[i];

            Integer tempValueI=priorityQue[i].val;
            QueueElement tempPairI=priorityQue[i].pair;

            //替换value
            priorityQue[i].val=priorityQue[j].val;
            priorityQue[j].val=tempValueI;

            //替换同一队列内的pair
            priorityQue[i].pair=priorityQue[j].pair;
            priorityQue[j].pair=tempPairI;
        }
    }

    class QueueElement {
        Integer val;
        int index;
        QueueElement pair;

        public QueueElement(Integer val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
