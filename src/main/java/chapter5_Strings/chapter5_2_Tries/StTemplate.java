package chapter5_Strings.chapter5_2_Tries;

import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/26 上午8:48
 * TODO
 */
public interface StTemplate<Value> {
    /**
     * 删除键
     * @param key
     */
    void delete(String key);

    /**
     * @param str
     * @return 给定一个字符串，从查找树的键中找到这个字符串的最长前缀
     */
    String longestPrefixOf(String str);

    /**
     * @param pattern
     * @return 查找匹配pattern的所有键（带有通配符）
     */
    Queue<String> keysThatMatch(String pattern);

    /**
     * @return 获取所有键
     */
    Queue<String> keys();

    /**
     * @param pre
     * @return 所有以pre开头的键
     */
    Queue<String> keysWithPrefix(String pre);

    /**
     * @param key
     * @return 根据键查找
     */
    Value get(String key);

    void put(String key,Value value);

    boolean containsKey(String key);
}
