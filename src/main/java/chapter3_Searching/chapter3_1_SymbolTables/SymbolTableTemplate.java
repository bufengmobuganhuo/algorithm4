package chapter3_Searching.chapter3_1_SymbolTables;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * @author zhangyu
 * 2020/3/13 10:27
 * TODO
 */
public interface SymbolTableTemplate<Key,Value> extends Iterable<Key>{
    Value get(Key key);
    void put(Key key,Value value);
    int size();
    Iterator<Key> keys();
    void delete(Key key);

    /**
     * @param key 小于key的键的数量，因为键的索引从0开始，所以也是键的排名
     * @return
     */
    default int rank(Key key){
        return 0;
    }

    /**
     * @param key
     * @return 获取>=key的最小键
     */
    default Key ceiling(Key key){
        throw new  NotImplementedException();
    }

    /**
     * @param key
     * @return 获取<=key的最大键
     */
    default Key floor(Key key){
        throw new NotImplementedException();
    }

    default boolean isEmpty(int size){
        return size==0;
    }

}
