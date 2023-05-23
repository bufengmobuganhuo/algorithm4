package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

import java.util.*;

/**
 * @author zhangyu
 * 2020/4/19 11:23
 * 练习3.5.27：元素不允许重复
 */
public class EX_3_5_27_List<Item> {
    public static void main(String[] args) {
        EX_3_5_27_List<String> ex_3_5_27_list=new EX_3_5_27_List<>();
        ex_3_5_27_list.addFront("A");
        ex_3_5_27_list.addFront("B");
        ex_3_5_27_list.addBack("C");
        ex_3_5_27_list.add(4,"D");
        ex_3_5_27_list.add(5,"E");
        ex_3_5_27_list.add(3,"F");
        ex_3_5_27_list.add(5,"G");
        ex_3_5_27_list.add(7,"G");
        ex_3_5_27_list.delete(3);
        ex_3_5_27_list.delete(2);
        ex_3_5_27_list.delete("B");
        ex_3_5_27_list.delete("E");
        ex_3_5_27_list.deleteFront();
        ex_3_5_27_list.deleteBack();
    }
    //存储值-在列表中的位置，因为值可以重复，故为set
    Map<Item,TreeSet<Integer>> itemIndexMap;
    //存储索引-值
    TreeMap<Integer,Item> indexItemMap;

    public EX_3_5_27_List() {
        itemIndexMap=new HashMap<>();
        indexItemMap=new TreeMap<>();
    }

    public void addFront(Item item){
        checkItem(item);
        moveEleRight(0);
        TreeSet<Integer> indexList=itemIndexMap.getOrDefault(item,new TreeSet<>());
        indexList.add(0);
        itemIndexMap.put(item,indexList);
        indexItemMap.put(0,item);
    }

    public void addBack(Item item){
        checkItem(item);
        int index=indexItemMap.lastKey();
        indexItemMap.put(index+1,item);
        TreeSet<Integer> indexList=itemIndexMap.getOrDefault(item,new TreeSet<>());
        indexList.add(index+1);
        itemIndexMap.put(item,indexList);
    }

    public void deleteFront(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        Item item= indexItemMap.remove(0);
        TreeSet<Integer> indexSet=itemIndexMap.get(item);
        //如果有重复值，则只删除对应索引
        if (indexSet.size()>1){
            indexSet.remove(0);
            //如果没有，则直接删除该值
        }else{
            itemIndexMap.remove(item);
        }
        moveEleLeft(1);
    }

    public void deleteBack(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        int index=indexItemMap.lastKey();
        Item item=indexItemMap.remove(index);
        Set<Integer> indexSet=itemIndexMap.get(item);
        if (indexSet.size()>1){
            indexSet.remove(index);
        }else{
            itemIndexMap.remove(item);
        }
    }

    /**
     * @param item 删除后将后面的元素向前移动,如果删除的是重复元素，则删除第一个命中的
     * */
    public void delete(Item item){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if (!itemIndexMap.containsKey(item)){
            return ;
        }
        TreeSet<Integer> indexSet=itemIndexMap.get(item);
        int index=indexSet.first();
        //如果是重复元素，则只删除最小的
        if (indexSet.size()>1){
            indexSet.remove(index);
        }else{
            itemIndexMap.remove(item);
        }
        indexItemMap.remove(index);
        moveEleLeft(index+1);
    }

    /**
     * @param i 如果包含i，则将i以及i后的元素向后移动
     * @param item
     */
    public void add(int i,Item item){
        checkItem(item);
        if (i<0){
            throw new IllegalArgumentException();
        }
        if (indexItemMap.containsKey(i)){
            moveEleRight(i);
        }
        indexItemMap.put(i,item);
        TreeSet<Integer> indexSet=itemIndexMap.getOrDefault(item,new TreeSet<>());
        indexSet.add(i);
        itemIndexMap.put(item,indexSet);
    }

    /**
     * @param i 删除后，将i前面的元素向前移动
     * @return
     */
    public Item delete(int i){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if (!indexItemMap.containsKey(i)){
            return null;
        }
        Item item= indexItemMap.remove(i);
        TreeSet<Integer> indexSet=itemIndexMap.get(item);
        if (indexSet.size()>1){
            indexSet.remove(i);
        }else{
            itemIndexMap.remove(item);
        }
        moveEleLeft(i+1);
        return item;
    }

    public boolean contains(Item item){
        return itemIndexMap.containsKey(item);
    }

    public int size(){
        return itemIndexMap.size();
    }

    private void moveEleRight(int index){
        Map.Entry<Integer,Item> curEntry= indexItemMap.lastEntry();
        Set<Item> modifiedItem=new HashSet<>();
        while (curEntry!=null&&curEntry.getKey()>=index){
            modifiedItem.add(curEntry.getValue());
            indexItemMap.remove(curEntry.getKey());
            indexItemMap.put(curEntry.getKey()+1,curEntry.getValue());
            curEntry=indexItemMap.floorEntry(curEntry.getKey());
        }
        for (Item item:modifiedItem){
            TreeSet<Integer> modifiedIndex=new TreeSet<>();
            for (Integer integer : itemIndexMap.get(item)) {
                if (integer>=index){
                    //只有>=index的需要后移
                    modifiedIndex.add(integer + 1);
                }else{
                    modifiedIndex.add(integer);
                }
            }
            itemIndexMap.put(item,modifiedIndex);
        }
    }

    private void moveEleLeft(int index){
        Map.Entry<Integer,Item> curEntry=indexItemMap.ceilingEntry(index);
        Set<Item> modifiedItem=new HashSet<>();
        while (curEntry!=null&&curEntry.getKey()>=index){
            modifiedItem.add(curEntry.getValue());
            indexItemMap.remove(curEntry.getKey());
            indexItemMap.put(curEntry.getKey()-1,curEntry.getValue());
            curEntry=indexItemMap.ceilingEntry(curEntry.getKey());
        }
        for (Item item:modifiedItem){
            TreeSet<Integer> modifiedIndex=new TreeSet<>();
            for (Integer integer : itemIndexMap.get(item)) {
                if (integer>=index){
                    //只有>=index的需要前移
                    modifiedIndex.add(integer - 1);
                }else{
                    modifiedIndex.add(integer);
                }
            }
            itemIndexMap.put(item,modifiedIndex);
        }
    }

    private boolean isEmpty(){
        return indexItemMap.isEmpty();
    }

    private void checkItem(Item item){
        if (item==null){
            throw new IllegalArgumentException("元素不可为空");
        }
    }
}
