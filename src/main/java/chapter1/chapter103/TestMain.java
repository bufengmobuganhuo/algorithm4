package chapter1.chapter103;

public class TestMain {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<Integer>();
        for (int i=0;i<10;i++){
            stack.push(i);
        }
        for (Integer item:stack){
            System.out.println(stack.pop());
        }
    }
}
