package chapter3_Searching.chapter3_4_HashTables;

/**
 * @author yuzhang
 * @date 2020/11/13 9:38 上午
 * TODO
 */
public class SequentialSearchST2<Key extends Comparable<Key>, Value> {
    private SequentialSearchNode2 first;
    private int size;

    public void put(Key key, Value value) {
        if (key == null){
            return;
        }
        if (first == null) {
            first = new SequentialSearchNode2(key, value, null);
            size++;
            return;
        }
        for (SequentialSearchNode2 tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.key.equals(key)) {
                tmp.value = value;
                return;
            }
        }
        SequentialSearchNode2 node = new SequentialSearchNode2(key, value, first);
        first = node;
        size++;
    }

    public Value get(Key key) {
        if (size == 0 || key == null) {
            return null;
        }
        for (SequentialSearchNode2 tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.key.equals(key)) {
                return tmp.value;
            }
        }
        return null;
    }

    public void delete(Key key){
        if (key == null){
            return;
        }
        if (first.equals(key)){
            first=first.next;
            size--;
            return;
        }
        SequentialSearchNode2 cur = first;
        SequentialSearchNode2 lastCur = first;
        while (cur!=null){
            if (cur.equals(key)){
                lastCur.next=cur.next;
                size--;
                break;
            }else {
                lastCur = cur;
            }
            cur = cur.next;
        }
    }

    public SequentialSearchNode2 reverse(){
        SequentialSearchNode2 last = null;
        SequentialSearchNode2 head = first;
        while (head != null){
            SequentialSearchNode2 tmp = head.next;
            head.next = last;
            last = head;
            head = tmp;
        }
        return last;
    }

    class SequentialSearchNode2 {
        Key key;
        Value value;
        SequentialSearchNode2 next;

        public SequentialSearchNode2(Key key, Value value, SequentialSearchNode2 next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
