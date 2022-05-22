import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestArea {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int[] array = null;

        File arrayFile = new File("textFiles/arrayValues.txt");
        File arrayLengths = new File("textFiles/arrayLengths.txt");
        File kValues = new File("textFiles/kValues.txt");
        File arrayTypes = new File("textFiles/arrayTypes.txt");

        File isSpeeds = new File("textFiles/insertionSortSpeeds.txt");
        File msSpeeds = new File("textFiles/mergeSortSpeeds.txt");
        File qsSpeeds = new File("textFiles/quickSortSpeeds.txt");
        File pssSpeeds = new File("textFiles/partialSelectionSortSpeeds.txt");
        File phsSpeeds = new File("textFiles/partialHeapSortSpeeds.txt");
        File qseSpeeds = new File("textFiles/quickSelectSpeeds.txt");
        File mqseSpeeds = new File("textFiles/medianQuickSelectSpeeds.txt");

        File isCounts = new File("textFiles/insertionSortCount.txt");
        File msCounts = new File("textFiles/mergeSortCounts.txt");
        File qsCounts = new File("textFiles/quickSortCounts.txt");
        File pssCounts = new File("textFiles/partialSelectionSortCounts.txt");
        File phsCounts = new File("textFiles/partialHeapSortCounts.txt");
        File qseCounts = new File("textFiles/quickSelectCounts.txt");
        File mqseCounts = new File("textFiles/medianQuickSelectCounts.txt");



        BufferedWriter fw = new BufferedWriter(new FileWriter(arrayFile));
        BufferedWriter arrlength = new BufferedWriter(new FileWriter(arrayLengths));
        BufferedWriter kvalue = new BufferedWriter(new FileWriter(kValues));
        BufferedWriter arraytype = new BufferedWriter(new FileWriter(arrayTypes));

        BufferedWriter iss = new BufferedWriter(new FileWriter(isSpeeds));
        BufferedWriter mss = new BufferedWriter(new FileWriter(msSpeeds));
        BufferedWriter qss = new BufferedWriter(new FileWriter(qsSpeeds));
        BufferedWriter psss = new BufferedWriter(new FileWriter(pssSpeeds));
        BufferedWriter phs = new BufferedWriter(new FileWriter(phsSpeeds));
        BufferedWriter qses = new BufferedWriter(new FileWriter(qseSpeeds));
        BufferedWriter mqses = new BufferedWriter(new FileWriter(mqseSpeeds));

        BufferedWriter isc = new BufferedWriter(new FileWriter(isCounts));
        BufferedWriter msc = new BufferedWriter(new FileWriter(msCounts));
        BufferedWriter qsc = new BufferedWriter(new FileWriter(qsCounts));
        BufferedWriter pssc = new BufferedWriter(new FileWriter(pssCounts));
        BufferedWriter phsc = new BufferedWriter(new FileWriter(phsCounts));
        BufferedWriter qsec = new BufferedWriter(new FileWriter(qseCounts));
        BufferedWriter mqsec = new BufferedWriter(new FileWriter(mqseCounts));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of Tests: ");
        int count = scanner.nextInt();
        int error = 0;

        while (count-- > 0) {
            System.out.println(count + " arrays left");

            int length = random.nextInt(40000) + 10000;
            arrlength.write(String.valueOf(length));
            arrlength.newLine();

            int k = random.nextInt(length - 1);
            kvalue.write(String.valueOf(k));
            kvalue.newLine();

            switch (random.nextInt(3) + 1) {
                case 1:
                    array = createRandomArray(length);
                    arraytype.write("Random Array");
                    arraytype.newLine();
                    break;
                case 2:
                    array = createSortedArray(length);
                    arraytype.write("Sorted Array");
                    arraytype.newLine();
                    break;
                default:
                    array = createReverselySortedArray(length);
                    arraytype.write("Reversely Sorted Array");
                    arraytype.newLine();
                    break;
            }

            SortingAlgorithms sa = new SortingAlgorithms(array);
            fw.write(sa.printArray(sa.getTempArray()));
            fw.newLine();

            sa.insertionSortTime(sa.getTempArray());
            iss.write(String.valueOf(sa.getTime()));
            iss.newLine();
            isc.write(String.valueOf(sa.getCount()));
            isc.newLine();
            sa.reset();

            sa.mergeSortTime(sa.getTempArray(),0, sa.getTempArray().length - 1);
            mss.write(String.valueOf(sa.getTime()));
            mss.newLine();
            msc.write(String.valueOf(sa.getCount()));
            msc.newLine();
            sa.reset();

            sa.quickSortTime(sa.getTempArray(), 0, array.length - 1);
            qss.write(String.valueOf(sa.getTime()));
            qss.newLine();
            qsc.write(String.valueOf(sa.getCount()));
            qsc.newLine();
            sa.reset();

            sa.partialSelectionSortTime(sa.getTempArray(), k);
            psss.write(String.valueOf(sa.getTime()));
            psss.newLine();
            pssc.write(String.valueOf(sa.getCount()));
            pssc.newLine();
            sa.reset();

            sa.heapSortTime(sa.getTempArray(), k);
            phs.write(String.valueOf(sa.getTime()));
            phs.newLine();
            phsc.write(String.valueOf(sa.getCount()));
            phsc.newLine();
            sa.reset();

            sa.quickSelectTime(sa.getTempArray(), 0, sa.getTempArray().length - 1, k + 1);
            qses.write(String.valueOf(sa.getTime()));
            qses.newLine();
            qsec.write(String.valueOf(sa.getCount()));
            qsec.newLine();
            sa.reset();

            sa.MedQuickSelectTime(sa.getTempArray(), 0, sa.getTempArray().length - 1, k + 1);
            mqses.write(String.valueOf(sa.getTime()));
            mqses.newLine();
            mqsec.write(String.valueOf(sa.getCount()));
            mqsec.newLine();
            sa.reset();
        }
        System.out.println("Total error found = " + error);

        closeAll(fw, arrlength, kvalue, arraytype, iss, mss, qss, psss, phs);
        closeAll(qses, mqses, isc, msc, qsc, pssc, phsc, qsec, mqsec);
    }

    private static void closeAll(BufferedWriter bw1, BufferedWriter bw2, BufferedWriter bw3, BufferedWriter bw4, BufferedWriter bw5, BufferedWriter bw6, BufferedWriter bw7, BufferedWriter bw8, BufferedWriter bw9) throws IOException {
        bw1.close();
        bw2.close();
        bw3.close();
        bw4.close();
        bw5.close();
        bw6.close();
        bw7.close();
        bw8.close();
        bw9.close();
    }

    public static int[] createRandomArray(int length) {
        Random random = new Random();
        return random.ints(length, 1,length).toArray();
    }

    public static int[] createSortedArray(int length) {
        Random random = new Random();
        int[] arr = random.ints(length, 1, length).toArray();
        Arrays.sort(arr);
        return arr;
    }

    public static int[] createReverselySortedArray(int length) {
        Random random = new Random();
        int[] arr = random.ints(length, 1, length).toArray();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int num : arr) pq.add(num);

        for(int i = 0; i < pq.size(); i++) arr[i] = pq.poll();

        return arr;
    }

}
