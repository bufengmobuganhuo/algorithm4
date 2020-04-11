package chapter3_Searching.chapter3_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/4/11 14:04
 * TODO
 */
public class EX_3_5_2_BinarySearchSET<Key extends Comparable<Key>>{
    public static void main(String[] args) {
        EX_3_5_2_BinarySearchSET<Integer> ex_3_5_2_binarySearchSET=new EX_3_5_2_BinarySearchSET<>(3);
        ex_3_5_2_binarySearchSET.put(1);
        ex_3_5_2_binarySearchSET.put(6);
        ex_3_5_2_binarySearchSET.put(7);
        ex_3_5_2_binarySearchSET.put(3);
        ex_3_5_2_binarySearchSET.put(2);
        ex_3_5_2_binarySearchSET.put(1);
        System.out.println(ex_3_5_2_binarySearchSET.contains(7));
        System.out.println(ex_3_5_2_binarySearchSET.contains(4));
        ex_3_5_2_binarySearchSET.delete(7);
        ex_3_5_2_binarySearchSET.delete(4);
        System.out.println(ex_3_5_2_binarySearchSET.contains(7));
        System.out.println(ex_3_5_2_binarySearchSET.contains(4));
    }
    private Key[] keys;
    private int size;

    public EX_3_5_2_BinarySearchSET(int len) {
        keys= (Key[]) new Comparable[len];
    }

    public void put(Key key){
        if (key==null){
            return;
        }
        int idx=rank(key);
        if (idx<size&&keys[idx].equals(key)){
            return;
        }
        if (size>=keys.length/2){
            resize(keys.length*2);
        }
        System.arraycopy(keys,idx,keys,idx+1,size-idx+1);
        keys[idx]=key;
        size++;
    }

    public void delete(Key key){
        if (key==null){
            return;
        }
        int idx=rank(key);
        if (idx<size&&keys[idx].equals(key)){
            System.arraycopy(keys,idx+1,keys,idx,size-idx+1);
            keys[size--]=null;
        }
    }

    public boolean contains(Key key){
        if (key==null){
            return false;
        }
        int idx= rank(key);
        return idx < size && keys[idx].equals(key);
    }


    private void resize(int len){
        Key[] temp= (Key[]) new Comparable[len];
        System.arraycopy(keys,0,temp,0,size);
        keys=temp;
    }

    private int rank(Key key){
        int lo=0,hi=size-1;
        int mid=0;
        while (lo<=hi){
            mid=lo+(hi-lo)/2;
            if (keys[mid].compareTo(key)==0){
                return mid;
            }else if(keys[mid].compareTo(key)>0){
                hi=mid-1;
            }else{
                lo=mid+1;
            }
        }
        //如果没找到，则返回较大值
        return lo;
    }

}
