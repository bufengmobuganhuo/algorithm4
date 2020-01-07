package chapter1.chapter103;

import java.util.Iterator;

public class TestMain {
    public static void main(String[] args) {
/*        StackBasedOnArr<Integer> stackBasedOnArr =new StackBasedOnArr<Integer>();
        for (int i=0;i<10;i++){
            stackBasedOnArr.push(i);
        }
        for (Integer item: stackBasedOnArr){
            System.out.println(stackBasedOnArr.pop());
        }*/
        StackBasedOnLinkedList<Integer> stackBasedOnLinkedList=new StackBasedOnLinkedList<Integer>();
        for (int i=0;i<10;i++){
            stackBasedOnLinkedList.push(i);
        }
/*        for (int i=0;i<11;i++){
            System.out.println(stackBasedOnLinkedList.pop());
        }*/
        Iterator<Integer> iterator=stackBasedOnLinkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
