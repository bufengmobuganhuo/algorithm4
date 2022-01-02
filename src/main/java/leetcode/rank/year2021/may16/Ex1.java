package leetcode.rank.year2021.may16;

/**
 * @author yuzhang
 * @date 2021/5/15 下午10:31
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        String s = "sentence4 a3 is2 This1";
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.sortSentence(s));
    }
    public String sortSentence(String s) {
        if (s==null||s.length()==0){
            return s;
        }
        String[] params = s.split(" ");
        String[] res = new String[params.length];
        for (String param : params) {
            int idx = param.length() - 1;
            StringBuilder sb = new StringBuilder();
            while (Character.isDigit(param.charAt(idx))) {
                sb.append(param.charAt(idx--));
            }
            res[Integer.parseInt(sb.reverse().toString()) - 1] = param.substring(0, idx + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i]);
            if (i!=res.length-1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
