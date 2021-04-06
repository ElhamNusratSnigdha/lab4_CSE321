import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Assignment3Task3 {
    public static void main(String[] args){
        Scanner ens = new Scanner(System.in);

        int[] array = {50, 90, 5, 6, 7, 1, 0, 600, 65465, 89, 6455, 4564, 64556, 98, 2};


        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.invoke(new quickSort(0,array.length-1,array));

        for (int i=0; i<array.length;i++){
            System.out.print(array[i]+ " ");
        }
    }
}

class quickSort extends RecursiveTask<Integer>{

    int start, end;
    int[] arr;

    public quickSort(int start, int end, int[] arr){
        this.start=start;
        this.end=end;
        this.arr=arr;
    }

    @Override
    protected Integer compute() {
        if (start >= end)return null;

        int p = partition(start, end, arr);

        quickSort left = new quickSort(start,p-1,arr);
        quickSort right = new quickSort(p+1,end,arr);

        left.fork();
        right.compute();

        left.join();

        return null;
    }

    private int partition(int start, int end, int[] arr){
        int i = start;
        int j = end;
        int p = new Random().nextInt(j-i)+i;

        int t = arr[j];
        arr[j]=arr[p];
        arr[p]=t;
        j--;

        while(i <= j){
            if (arr[i] <= arr[end]){
                i++;
                continue;
            }
            if (arr[j] >= arr[end]){
                j--;
                continue;
            }

            t = arr[j];
            arr[j]=arr[i];
            arr[i]=t;
            j--;
            i++;
        }

        t=arr[j+1];
        arr[j+1]=arr[end];
        arr[end]=t;
        return j+1;
    }
}
