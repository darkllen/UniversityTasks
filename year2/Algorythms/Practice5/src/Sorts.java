import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    static <T extends Comparable> void quickSort(T[] arr, int begin, int end){
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
    static <T> void quickSort(T[] arr, int begin, int end, Comparator<T> comparator ){
        if (begin < end) {
            int partitionIndex = partitionComp(arr, begin, end, comparator);

            quickSort(arr, begin, partitionIndex-1, comparator);
            quickSort(arr, partitionIndex+1, end, comparator);
        }
    }
    static <E> void treeSort(E[] arr, Comparator<E> comparator){
        BinaryTreeComp<E> heap = ofComp(arr, comparator);
        int ind = 0;
        while (!heap.isEmpty()) {
            arr[ind++] = heap.pop();
        }
    }
    static <E extends Comparable<E>> void treeSort(E[] elements) {
        BinaryTree<E> heap = of(elements);
        int ind = 0;
        while (!heap.isEmpty()) {
            elements[ind++] = heap.pop();
        }
    }

    private static <T extends Comparable> void merge(T[] a, T[] l, T[] r, int left, int right) {

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
    private static <T> void mergeComp(T[] a, T[] l, T[] r, int left, int right, Comparator<T> comparator) {

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
    private static <E extends Comparable<E>> BinaryTree<E> of(E[] elements) {
        BinaryTree<E> result = new BinaryTree<>();
        for (E element : elements) {
            result.add(element);
        }
        return result;
    }
    private static <E> BinaryTreeComp<E> ofComp(E[] elements, Comparator<E> comparator) {
        BinaryTreeComp<E> result = new BinaryTreeComp<>(comparator);
        for (E element : elements) {
            result.add(element);
        }
        return result;
    }
    private static class BinaryTree<E extends Comparable> {

        List<E> elements = new ArrayList<>();

        void add(E e) {
            elements.add(e);
            int elementIndex = elements.size() - 1;
            while (!isRoot(elementIndex) && !isCorrectChild(elementIndex)) {
                int parentIndex = parentIndex(elementIndex);
                swap(elementIndex, parentIndex);
                elementIndex = parentIndex;
            }
        }
        boolean isRoot(int index) {
            return index == 0;
        }
        boolean isCorrectChild(int index) {
            return isCorrect(parentIndex(index), index);
        }
        boolean isCorrect(int parentIndex, int childIndex) {
            if (!isValidIndex(parentIndex) || !isValidIndex(childIndex)) {
                return true;
            }

            return elementAt(parentIndex).compareTo(elementAt(childIndex)) < 0;
        }

        boolean isValidIndex(int index) {
            return index < elements.size();
        }

        void swap(int index1, int index2) {
            E element1 = elementAt(index1);
            E element2 = elementAt(index2);
            elements.set(index1, element2);
            elements.set(index2, element1);
        }

        boolean isEmpty() {
            return elements.isEmpty();
        }

        E elementAt(int index) {
            return elements.get(index);
        }

        int parentIndex(int index) {
            return (index - 1) / 2;
        }

        int leftChildIndex(int index) {
            return 2 * index + 1;
        }

        int rightChildIndex(int index) {
            return 2 * index + 2;
        }

        E pop() {
            if (isEmpty()) {
                throw new IllegalStateException("You cannot pop from an empty heap");
            }

            E result = elementAt(0);

            int lasElementIndex = elements.size() - 1;
            swap(0, lasElementIndex);
            elements.remove(lasElementIndex);

            int elementIndex = 0;
            while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
                int smallerChildIndex = smallerChildIndex(elementIndex);
                swap(elementIndex, smallerChildIndex);
                elementIndex = smallerChildIndex;
            }

            return result;
        }

        boolean isLeaf(int index) {
            return !isValidIndex(leftChildIndex(index));
        }

        boolean isCorrectParent(int index) {
            return isCorrect(index, leftChildIndex(index)) && isCorrect(index, rightChildIndex(index));
        }

        int smallerChildIndex(int index) {
            int leftChildIndex = leftChildIndex(index);
            int rightChildIndex = rightChildIndex(index);

            if (!isValidIndex(rightChildIndex)) {
                return leftChildIndex;
            }

            if (elementAt(leftChildIndex).compareTo(elementAt(rightChildIndex)) < 0) {
                return leftChildIndex;
            }

            return rightChildIndex;
        }

    }
    private static class BinaryTreeComp<E> {

        Comparator comparator;
        List<E> elements = new ArrayList<>();

        public BinaryTreeComp(Comparator comparator) {
            this.comparator = comparator;
        }

        void add(E e) {
            elements.add(e);
            int elementIndex = elements.size() - 1;
            while (!isRoot(elementIndex) && !isCorrectChild(elementIndex)) {
                int parentIndex = parentIndex(elementIndex);
                swap(elementIndex, parentIndex);
                elementIndex = parentIndex;
            }
        }
        boolean isRoot(int index) {
            return index == 0;
        }
        boolean isCorrectChild(int index) {
            return isCorrect(parentIndex(index), index);
        }
        boolean isCorrect(int parentIndex, int childIndex) {
            if (!isValidIndex(parentIndex) || !isValidIndex(childIndex)) {
                return true;
            }

            return comparator.compare(elementAt(parentIndex), elementAt(childIndex)) < 0;
        }

        boolean isValidIndex(int index) {
            return index < elements.size();
        }

        void swap(int index1, int index2) {
            E element1 = elementAt(index1);
            E element2 = elementAt(index2);
            elements.set(index1, element2);
            elements.set(index2, element1);
        }

        boolean isEmpty() {
            return elements.isEmpty();
        }

        E elementAt(int index) {
            return elements.get(index);
        }

        int parentIndex(int index) {
            return (index - 1) / 2;
        }

        int leftChildIndex(int index) {
            return 2 * index + 1;
        }

        int rightChildIndex(int index) {
            return 2 * index + 2;
        }

        E pop() {
            if (isEmpty()) {
                throw new IllegalStateException("You cannot pop from an empty heap");
            }

            E result = elementAt(0);

            int lasElementIndex = elements.size() - 1;
            swap(0, lasElementIndex);
            elements.remove(lasElementIndex);

            int elementIndex = 0;
            while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
                int smallerChildIndex = smallerChildIndex(elementIndex);
                swap(elementIndex, smallerChildIndex);
                elementIndex = smallerChildIndex;
            }

            return result;
        }

        boolean isLeaf(int index) {
            return !isValidIndex(leftChildIndex(index));
        }

        boolean isCorrectParent(int index) {
            return isCorrect(index, leftChildIndex(index)) && isCorrect(index, rightChildIndex(index));
        }

        int smallerChildIndex(int index) {
            int leftChildIndex = leftChildIndex(index);
            int rightChildIndex = rightChildIndex(index);

            if (!isValidIndex(rightChildIndex)) {
                return leftChildIndex;
            }

            if (comparator.compare(elementAt(leftChildIndex), elementAt(rightChildIndex)) < 0) {
                return leftChildIndex;
            }

            return rightChildIndex;
        }

    }
}
