package chapter2_Sorting.chapter2_2_MergeSort.exercises;

/**
 * @author zhangyu
 * 2020/6/9 11:09
 * TODO
 */
public class EX_2_2_19_1 {
    public static void main(String[] args) {
        Integer[] arr= {0,3,1,6,2,5,4};

    }
    private int count;
    private Integer[] temp;

    public void count(Integer[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        temp=new Integer[arr.length];
        count(arr,0,arr.length-1);
    }
    private void count(Integer[] arr,int start,int end){
        if (start>=end){
            return;
        }
        int mid=start+(end-start)/2;
        count(arr,start,mid);
        count(arr,mid+1,end);
        merge(arr,start,mid,end);
    }
    private void merge(Integer[] arr,int start,int mid,int end){
        int left=start,right=mid+1;
        System.arraycopy(arr,start,temp,start,end-start+1);
        for (int k = start; k <= end; k++) {
            if (start>mid){
                arr[k]=temp[right++];
            }else if(right>end){
                arr[k]=temp[left++];
            }else if(temp[left]<temp[right]){
                arr[k]=temp[left++];
            }else{
                count+=right-k;
                arr[k]=temp[right++];
            }
        }
    }
}
