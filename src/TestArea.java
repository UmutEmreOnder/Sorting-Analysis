public class TestArea {
    public static void main(String[] args) {
        int[] array = new int[5];
        SortingAlgortihms sa = new SortingAlgortihms(array);

        sa.insertionSort(sa.getTempArray());
        System.out.println(sa.getTime());
        sa.copyArray();

        sa.mergeSort(sa.getTempArray(),0, sa.getTempArray().length - 1);
        System.out.println(sa.getTime());
        sa.copyArray();
    }
}