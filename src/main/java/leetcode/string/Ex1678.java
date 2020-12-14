package leetcode.string;

/**
 * @author yuzhang
 * @date 2020/12/9 上午11:20
 * TODO
 */
public class Ex1678 {
    public static void main(String[] args) {
        Ex1678 ex1678 = new Ex1678();
        String command = "(al)G(al)()()G";
        ex1678.interpret(command);
    }

    public String interpret(String command) {
        if (command == null || command.length() == 0) {
            return command;
        }
        int ptr = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (ptr < command.length() - 1) {
            char chr = command.charAt(ptr);
            if (chr == 'G') {
                stringBuilder.append("G");
                ptr++;
            } else if (command.charAt(ptr + 1) == ')') {
                stringBuilder.append('o');
                ptr += 2;
            } else {
                stringBuilder.append("al");
                ptr += 4;
            }
        }
        if (ptr < command.length()){
            stringBuilder.append('G');
        }
        return stringBuilder.toString();
    }
}
