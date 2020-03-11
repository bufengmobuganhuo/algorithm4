package chapter2_Sorting.chapter2_5_Applications.exercises;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.media.sound.RIFFInvalidDataException;

/**
 * @author zhangyu
 * 2020/3/10 19:50
 * 练习2.5.19：Kendall Tau距离：两组数列中，相对顺序不同的数对的数目
 * 如0 3 1 6 2 5 4和1 0 3 6 4 2 5之间的KendallTau距离是4，分别为：0-1,3-1,4-2,5-4
 */
public class EX_2_5_19_KendallTauDistance {
    public static void main(String[] args) {
        int[] a={0,3,1,6,2,5,4};
        int[] b={1,0,3,6,4,2,5};
        System.out.println(solution(a,b));
    }

    public static int solution(int[] a,int[] b){
        if (a==null||a.length==0||b==null||b.length==0){
            return 0;
        }
        int[] aniv=new int[a.length];
        for (int i=0;i<a.length;i++){
            //aniv保存了a中的元素a[i]在a中的索引i，逆数组
            aniv[a[i]]=i;
        }
        //以a[]为标准的话，bnew保存了b中的b[i]在b中的索引
        int[] bnew=new int[a.length];
        for (int i=0;i<a.length;i++){
            /**
             * 对于一个元素b[i]以及与他相等的元素a[j],
             * 在aniv中，“以a[]为标准”的话，他本应该的索引应该是j=aniv[a[j]]，但却变成了aniv[b[i]]
             * 从而达到了“以a[]为标准”的目的
             * */
            bnew[i]=aniv[b[i]];
        }
        //以a[]为标准，计算倒置数量，即为Kendall Tau距离
        return countInversions(bnew);
    }

    public static int countInversions(int[] a){
        if (a==null||a.length==0){
            return 0;
        }
        int[] aux= new int[a.length];
        return countInversions(a,aux,0,a.length-1);
    }
    private static int countInversions(int[] a,int[] aux,int start,int end){
        int inversions=0;
        if (start>=end){
            return 0;
        }
        int mid=start+(end-start)/2;
        //左子数组归并，得到的倒置数
        inversions+=countInversions(a,aux,start,mid);
        //右子数组归并，得到的倒置数
        inversions+=countInversions(a,aux,mid+1,end);
        //本次归并得到的倒置数
        inversions+=merge(a,aux,start,mid,end);
        return inversions;
    }
    private static int merge(int[] a,int[] aux,int start,int mid,int end){
        int i=start,j=mid+1;
        int inversions=0;
        System.arraycopy(a,start,aux,start,end-start+1);
        for (int k=start;k<= end;k++){
            if (i>mid){
                a[k]=aux[j++];
            }else if(j>end){
                a[k]=aux[i++];
            }else if(aux[i]>aux[j]){
                a[k]=aux[j++];
                inversions+=(mid-i+1);
            }else{
                a[k]=aux[i++];
            }
        }
        return inversions;
    }

}
