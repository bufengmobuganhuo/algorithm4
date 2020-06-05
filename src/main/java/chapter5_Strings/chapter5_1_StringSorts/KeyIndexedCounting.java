package chapter5_Strings.chapter5_1_StringSorts;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/27 15:28
 * 键索引计数法
 */
public class KeyIndexedCounting {
    public static void main(String[] args) {
        Student[] students={
                new Student("Anderson",2),
                new Student("Brown",3),
                new Student("Davis",3),
                new Student("Garcia",4),
                new Student("Harris",1),
                new Student("Jackson",3),
                new Student("Johnson",4),
                new Student("Martin",1),
        };
        KeyIndexedCounting keyIndexedCounting=new KeyIndexedCounting();
        keyIndexedCounting.count(students,5);
        System.out.println(Arrays.toString(students));
    }

    /**
     * @param students
     * @param R student.key为[0,R)内的一个整数
     */
    public void count(Student[] students,int R){
        //第一步：频率统计
        int[] count=new int[R+1];
        for (int i=0;i<students.length;i++){
            count[students[i].key+1]+=1;
        }
        //第二步：将count[]转化为组号对应的起始索引
        for (int i=0;i<R;i++){
            count[i+1]+=count[i];
        }
        //第三步：使用aux数组，将元素分类
        Student[] aux=new Student[students.length];
        for (int i = 0; i < aux.length; i++) {
            aux[count[students[i].key]++]=students[i];
        }
        //第四步：回写
        System.arraycopy(aux,0,students,0,aux.length);
    }

    public static class Student{
        public String name;
        public int key;

        public Student(String name, int key) {
            this.name = name;
            this.key = key;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", key=" + key +
                    '}';
        }
    }

}
