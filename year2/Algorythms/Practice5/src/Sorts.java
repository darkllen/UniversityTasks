import java.lang.reflect.Array;
import java.util.Comparator;

public class Sorts {

    //not stable
    static <T extends Comparable> void mergeSort(T[] arr, int n){
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        T[] l = (T[]) Array.newInstance(arr[0].getClass(), mid);
        T[] r = (T[]) Array.newInstance(arr[0].getClass(), n-mid);

        System.arraycopy(arr, 0, l, 0, mid);
        if (n - mid >= 0) System.arraycopy(arr, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(arr, l, r, mid, n - mid);
    }
    public static <T extends Comparable> void merge(
            T[] a, T[] l, T[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].compareTo(r[j]) < 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    static <T> void mergeSort(T[] arr, int n, Comparator<T> comparator){
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        T[] l = (T[]) Array.newInstance(arr[0].getClass(), mid);
        T[] r = (T[]) Array.newInstance(arr[0].getClass(), n-mid);

        System.arraycopy(arr, 0, l, 0, mid);
        if (n - mid >= 0) System.arraycopy(arr, mid, r, 0, n - mid);
        mergeSort(l, mid, comparator);
        mergeSort(r, n - mid, comparator);

        mergeComp(arr, l, r, mid, n - mid, comparator);
    }

    public static <T> void mergeComp(
            T[] a, T[] l, T[] r, int left, int right, Comparator<T> comparator) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (comparator.compare(l[i], r[j]) < 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    static <T extends Comparable> void quickSort(T[] arr, int begin, int end){
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    private static <T extends Comparable> int partition(T arr[], int begin, int end) {
        T pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <0) {
                i++;

                T swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        T swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    static <T> void quickSort(T[] arr, int begin, int end, Comparator<T> comparator ){
        if (begin < end) {
            int partitionIndex = partitionComp(arr, begin, end, comparator);

            quickSort(arr, begin, partitionIndex-1, comparator);
            quickSort(arr, partitionIndex+1, end, comparator);
        }
    }
    private static <T> int partitionComp(T arr[], int begin, int end, Comparator<T> comparator ) {
        T pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (comparator.compare(arr[j], pivot)<0) {
                i++;

                T swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        T swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    static <T extends Comparable> void treeSort(T[] arr){

    }

    static <T> void treeSort(T[] arr, Comparator<T> comparator){

    }
}
