package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

import java.util.Iterator;

/**
 * 动态调整数组大小的栈
 */
public class StackBasedOnArr<T> implements Iterable<T>{
    private int count=0;
    private T[] arr= (T[]) new Object[1];

    public boolean isEmpty(){
        return count==0;
    }

    public T pop(){
        if (isEmpty()){
            throw new  IndexOutOfBoundsException("栈为空");
        }
        T item=arr[--count];
        arr[count]=null;
        //如果实际大小<=数组大小/4，则将其减半
        if (count>0&&count==arr.length/4){
            resize(arr.length/2);
        }
        return item;
    }

    public void push(T item){
        //如果数组满，则扩容两倍
        if (count==arr.length){
            resize(arr.length*2);
        }
        arr[count++]=item;
    }

    public T peek(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("栈为空");
        }
        return arr[count-1];
    }

    public void resize(int size){
        T[] temp= (T[]) new Object[size];
        System.arraycopy(arr,0,temp,0,arr.length<size?arr.length:size);
        arr=temp;
    }
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int index=count;
        public boolean hasNext() {
            return index>0;
        }

        public T next() {
            if (!hasNext()){
                throw new IndexOutOfBoundsException("栈为空");
            }
            return arr[--index];
        }

        public void remove() {
            throw new UnsupportedOperationException("不支持的操作");
        }
    }
}
