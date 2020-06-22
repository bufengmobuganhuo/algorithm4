package chapter5_Strings.chapter5_5_DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author yuzhang
 * @date 2020/6/16 7:24 下午
 * 游程编码
 */
public class RunLengthEncoding {

    /**
     * 解压缩
     */
    public static void expand(){
        boolean bit=false;
        while(!BinaryStdIn.isEmpty()){
            char cnt=BinaryStdIn.readChar();
            for (int i = 0; i < cnt; i++) {
                BinaryStdOut.write(bit);
            }
            bit=!bit;
        }
    }

    /**
     * 压缩
     */
    public static void compress(){
        char cnt=0;
        boolean bit,lastBit=false;
        while(!StdIn.isEmpty()){
            bit= BinaryStdIn.readBoolean();
            //如果读取的bit与上次读取的不一样
            if (bit!=lastBit){
                BinaryStdOut.write(cnt);
                cnt=0;
                //记录当前读取的bit
                lastBit=!lastBit;
                //如果连续出现的bit超过了255个，则置零，并写入一个0
            }else if (cnt==255){
                BinaryStdOut.write(cnt);
                cnt=0;
                BinaryStdOut.write(cnt);
            }
            cnt++;
        }
    }
}
