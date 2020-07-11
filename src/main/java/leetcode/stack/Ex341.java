package leetcode.stack;

import leetcode.stack.NestedInteger;

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


}
