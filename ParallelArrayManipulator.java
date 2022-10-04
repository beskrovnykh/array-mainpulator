import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;

import java.util.concurrent.*;

public class ParallelArrayManipulator {

    private static final class MaxSum {
        private long max, sum;

        public MaxSum() {}

        public MaxSum(long sum, long max) {
            this.sum = sum;
            this.max = max;
        }
    } 

    private static final class MaxSumCalculator {
        static final MaxSum EMPTY = new MaxSum(0, 0);

        static void accumulator(MaxSum tail, long value) {
            tail.sum += value;
            if (tail.sum > tail.max) {
                tail.max = tail.sum;
            }
        }

        static void combiner(MaxSum tail, MaxSum head) {
            if (tail.sum + head.max > tail.max) {
                tail.max = tail.sum + head.max;
            }
            tail.sum += head.sum;
        }

        static long calculate(LongStream stream) {
            return stream.collect(MaxSum::new, MaxSumCalculator::accumulator, MaxSumCalculator::combiner)
                                  .max;
        }
    }

    public static long maxSumParallel(long[] series) {
        return MaxSumCalculator.calculate(Arrays.stream(series).parallel());
    }

    public static long maxSumSequential(long[] series) {
        long max = 0, sum = 0;
        for (int i = 0; i < series.length; i++) {
            sum += series[i];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
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
