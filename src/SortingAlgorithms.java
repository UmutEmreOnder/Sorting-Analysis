import java.util.Arrays;
import java.util.Stack;

public class SortingAlgorithms {
    private long time;
    private int count;
    private int[] arrayNotSorted;
    private int[] tempArray;
    private int selected;
    private int medSelected;

    public SortingAlgorithms(int[] array) {
        this.arrayNotSorted = array;
        this.tempArray = new int[array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        this.time = 0;
        this.count = 0;
    }

    public void copyArray() {
        System.arraycopy(this.arrayNotSorted, 0, this.tempArray, 0, this.arrayNotSorted.length);
    }

    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public void insertionSortTime(int[] arr) {
        startTimer();
        insertionSort(arr);
        stopTimer();
    }

    public void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int[] arr, int l, int r) {

        if (l < r) {
            int m =l+ (r-l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void mergeSortTime(int[] arr, int l, int r) {
        startTimer();
        mergeSort(arr, l, r);
        stopTimer();
    }

    int partition (int[] a, int start, int end) {
        int pivot = a[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            if (a[j] < pivot)
            {
                i++;
                swap(a, i, j);
            }
        }
        swap(a,i+1, end);
        return (i + 1);
    }

    void quickSort(int[] a, int start, int end) {
        if (start < end) {
            int p = partition(a, start, end);
            quickSort(a, start, p - 1);
            quickSort(a, p + 1, end);
        }
    }

    public void quickSortTime(int[] a, int start, int end) {
        startTimer();
        try{
            quickSort(a, start, end);
        } catch (StackOverflowError ignored) {}

        stopTimer();
    }

    void partialSelectionSort(int[] a, int k) {
        int n = a.length;
        for(int i = 0; i < k; i++){
            int minIndex = i;
            int minValue = a[i];
            for(int j = i+1; j < n; j++){
                if(a[j] < minValue) {
                    minIndex = j;
                    minValue = a[j];
                    swap(a, minIndex, i);
                }
            }
        }
    }

    public void partialSelectionSortTime(int[] a, int k) {
        startTimer();
        partialSelectionSort(a, k + 1);
        stopTimer();
    }

    public int partitionQuickSelect(int[] arr, int low, int high) {
        int pivot = arr[high], pivotLoc = low;
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, pivotLoc);
                pivotLoc++;
            }
        }
        swap(arr, high, pivotLoc);
        return pivotLoc;
    }

    public int kthSmallest(int[] arr, int low, int high, int k) {
        int partition = partitionQuickSelect(arr, low, high);

        try {
            if (partition == k - 1)
                return arr[partition];

            else if (partition < k - 1)
                return kthSmallest(arr, partition + 1, high, k);

            else
                return kthSmallest(arr, low, partition - 1, k);
        } catch (StackOverflowError ignored) {
            Arrays.sort(arr);
            return arr[k - 1];
        }
    }

    public void quickSelectTime(int[] arr, int low, int high, int k) {
        startTimer();
        this.selected = kthSmallest(arr, low, high, k);
        stopTimer();
    }

    public void deleteRoot(int[] arr, int n) {
        int lastElement = arr[n - 1];
        arr[0] = lastElement;
        buildMaxHeap(arr, 0, n - 1);
    }

    void buildMaxHeap(int[] arr, int i, int n){
        int max = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if(l < n && arr[l] > arr[max])
            max = l;

        if(r < n && arr[r] > arr[max])
            max = r;

        if(i != max){
            swap(arr, i, max);
            buildMaxHeap(arr, max, n);
        }
    }

    void buildMaxHeap(int[] arr, int n){
        int start = (n/2) - 1;
        for(int i = start; i >= 0; i--) {
            buildMaxHeap(arr, i, n);
        }
    }

    public void partialHeapSort(int[] arr, int k) {
        buildMaxHeap(arr, arr.length);
        for (int i = 0; i < arr.length - k - 1; i++) {
            deleteRoot(arr, arr.length - i);
        }
    }

    public void heapSortTime(int[] arr, int k) {
        startTimer();
        partialHeapSort(arr, k);
        stopTimer();
    }

    public static int MedquickSelectPartition(int[] arr, int low, int high, int pivot)
    {
        int pivotLoc = low;
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, pivotLoc);
                pivotLoc++;
            }
        }
        swap(arr, high, pivotLoc);
        return pivotLoc;

    }

    static int MedianOfThree(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid])
            swap(arr, left, mid);
        if (arr[left] > arr[right])
            swap(arr, left, right);
        if (arr[mid] > arr[right])
            swap(arr, mid, right);
        swap(arr, mid, right);
        return arr[right];
    }

    public static int MedquickSelect(int[] arr, int low, int high, int k)
    {
        // find the partition
        int median = MedianOfThree(arr, low, high);
        int partition = MedquickSelectPartition(arr, low, high, median);
        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k - 1)
            return arr[partition];

            // if partition value is less than kth position,
            // search right side of the array.
        else if (partition < k - 1)
            return MedquickSelect(arr, partition + 1, high, k);

            // if partition value is more than kth position,
            // search left side of the array.
        else
            return MedquickSelect(arr, low, partition - 1, k);
    }

    public void MedquickSelectTime(int[] arr, int low, int high, int k) {
        startTimer();
        this.medSelected = MedquickSelect(arr, low, high, k);
        stopTimer();
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void startTimer() {
        this.time = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.time = System.currentTimeMillis() - this.time;
    }

    public void printArray(int[] arr) {
        for(int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    public long getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }

    public int[] getArrayNotSorted() {
        return arrayNotSorted;
    }

    public int[] getTempArray() {
        return tempArray;
    }

    public int getSelected() {
        return selected;
    }

    public int getmedSelected() {
        return medSelected;
    }
}
