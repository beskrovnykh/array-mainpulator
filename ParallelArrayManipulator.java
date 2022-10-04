import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;

import java.util.concurrent.*;

public class ParallelArrayManipulator {

    private static final class PartialSumCalculator implements Runnable {
        private final int threadId;
        private final int chunckSize;
        private final long[] max, sum, series;

        public PartialSumCalculator(int threadId, long[] series, long[] max, long[] sum, int chunckSize) {
            this.threadId = threadId;
            this.series = series;
            this.sum = sum;
            this.max = max;
            this.chunckSize = chunckSize;
        }

        @Override
        public void run() {
            for (int i = threadId * chunckSize; i < (threadId + 1) * chunckSize; i++) {
                sum[threadId] += series[i];
                if (sum[threadId] > max[threadId]) {
                    max[threadId] = sum[threadId];
                }
            }
        }
    }

    private static final int numThreads = 4;

    private static final ExecutorService executor = Executors.newFixedThreadPool(numThreads);

    public static long maxSumParallel(long[] series) {
        long[] max = new long[numThreads+1], sum = new long[numThreads+1];

        for (int i = 0; i < numThreads; i++) {
            executor.execute(new PartialSumCalculator(i, series, max, sum, series.length / numThreads));
        }
        executor.shutdown();
        while (!executor.isTerminated());

        for (int i = 0; i < numThreads; i++) {
            max[numThreads] = Math.max(max[numThreads], sum[numThreads] + max[i]);
            sum[numThreads] += sum[i];
        }
        return max[numThreads];
    }
    
    /*
     * Complete the arrayManipulation function below.
     */
    public static long arrayManipulation(int n, int[][] queries) {
        long[] series = new long[n+1];
        for (int i = 0; i < queries.length; i++) { 
            series[queries[i][0]-1] += queries[i][2];
            series[queries[i][1]] -= queries[i][2];
        }
        return maxSumParallel(series);
    }

    private static final File file = new File("inputs/input09.txt");    

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(file);

        try (scanner) {

            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0].trim());

            int m = Integer.parseInt(nm[1].trim());

            int[][] queries = new int[m][3];

            for (int queriesRowItr = 0; queriesRowItr < m; queriesRowItr++) {
                String[] queriesRowItems = scanner.nextLine().split(" ");

                for (int queriesColumnItr = 0; queriesColumnItr < 3; queriesColumnItr++) {
                    int queriesItem = Integer.parseInt(queriesRowItems[queriesColumnItr].trim());
                    queries[queriesRowItr][queriesColumnItr] = queriesItem;
                }
            }

            long startTime = System.currentTimeMillis();
            
            long result = arrayManipulation(n, queries);

            long endTime = System.currentTimeMillis();
            
            System.out.println("Total execution time: " + (endTime-startTime) + " ms"); 
            System.out.println("Result: " + result);

        }


    }
}
