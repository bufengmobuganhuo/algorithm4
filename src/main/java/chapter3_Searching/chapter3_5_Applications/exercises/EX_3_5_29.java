package chapter3_Searching.chapter3_5_Applications.exercises;

import java.util.*;

/**
 * @author zhangyu
 * 2020/4/19 14:17
 * TODO
 */
public class EX_3_5_29<Key,Value> {
    public static void main(String[] args) {
        EX_3_5_29<String,String> ex_3_5_29=new EX_3_5_29<>();
        ex_3_5_29.put("A","A");
        ex_3_5_29.put("B","B");
        ex_3_5_29.put("C","C");
        ex_3_5_29.put("D","D");
        ex_3_5_29.put("E","E");
        ex_3_5_29.put("F","F");
        ex_3_5_29.delete("C");
        System.out.println(ex_3_5_29.sample());
    }
    private Map<Key,Value> map;
    private List<Key> keys;

    public EX_3_5_29() {
        map=new HashMap<>();
        keys=new ArrayList<>();
    }

    public void put(Key key,Value value){
        map.put(key,value);
        keys.add(key);
    }

    public Value get(Key key){
        return map.get(key);
    }

    public void delete(Key key){
        map.remove(key);
        keys.remove(key);
    }

    public Value sample(){
        Random random=new Random();
        int index=random.nextInt(keys.size());
        return map.get(keys.get(index));
    }
}
