package chapter2_Sorting.chapter2_1_ElementarySorts;

/**
 * @author zhangyu
 * 2020/2/7 9:37
 * 插入排序
 */
public class InsertionSort implements Template{
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        //i：即将要排序元素的索引，初始时标记arr[0]已排序
        for (int i=1;i<arr.length;i++){
            //如果将要排序的元素<已排序的元素，则一直向前交换
            for (int j=i;j>0&&less(arr[j],arr[j-1]);j--){
                exchange(arr,j,j-1);
            }
        }
    }

    public int sortCountExchange(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return 0;
        }
        int exchangeCount=0;
        //i：即将要排序元素的索引，初始时标记arr[0]已排序
        for (int i=1;i<arr.length;i++){
            //如果将要排序的元素<已排序的元素，则一直向前交换
            for (int j=i;j>0&&less(arr[j],arr[j-1]);j--){
                exchangeCount++;
                exchange(arr,j,j-1);
            }
        }
        return exchangeCount;
    }

}
