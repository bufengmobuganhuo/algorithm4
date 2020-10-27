package leetcode.stack;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/4 8:13 下午
 * TODO
 */
public class Ex341 implements Iterator<Integer> {
    private Stack<Integer> stack;
    private Iterator<Integer> iterator;
    public Ex341(List<NestedInteger> nestedList) {
        if (nestedList==null){
            return;
        }
        stack=new Stack<>();
        prepareStack(nestedList.iterator());
        iterator=stack.iterator();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    private void prepareStack(Iterator<NestedInteger> integerIterator){
        while (integerIterator.hasNext()){
            NestedInteger nestedInteger = integerIterator.next();
            if (nestedInteger.isInteger()){
                stack.push(nestedInteger.getInteger());
            }else{
                prepareStack(nestedInteger.getList().iterator());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    static class NestedInteger {
        Integer value;
        List<NestedInteger> list;

        public NestedInteger(int value) {
            this.value = value;
        }

        public NestedInteger() {
        }

        public Integer getInteger(){
            return this.value;
        }

        public boolean isInteger() {
            return value != null;
        }

        public List<NestedInteger> getList() {
            return list;
        }

        public void setInteger(int value) {
            this.value = value;
        }

        public void add(NestedInteger ni) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(ni);
        }

    }
}
