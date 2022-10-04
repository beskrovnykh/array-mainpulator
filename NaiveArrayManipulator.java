import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class NaiveArrayManipulator {

    /*
     * Complete the arrayManipulation function below.
     */
    public static long arrayManipulation(int n, int[][] queries) {

        long[] sum = new long[n];

        for (int i = 0; i < queries.length; i++) {

            int startIndex = queries[i][0];
            int endIndex = queries[i][1];

            for (int j = startIndex - 1; j < endIndex; j++) {
                sum[j] += queries[i][2];
            }
        }

        long max = 0;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > max) {
                max = sum[i];
            }
        }

        return max;

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
