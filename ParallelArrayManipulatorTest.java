import org.junit.Test;
import java.util.stream.*;
import java.util.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.experimental.runners.Enclosed;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ParallelArrayManipulatorTest {

    public static class ArrayManipulatorTest {
        @Test
        public void testOne() {
            int[][] input = new int[3][3];

            input[0] = new int[] {1, 2, 100};
            input[1] = new int[] {2, 5, 100};
            input[2] = new int[] {3, 4, 100};

            assertEquals(200, ParallelArrayManipulator.arrayManipulation(5, input));
        }
    }

    @RunWith(Parameterized.class)
    public static class MaxSumParrallelTest {
        @Parameter
        public long[] series;

        @Parameters(name = "{index}: series - {0}")
        public static List<long[]> seriesProvider() {
            return Arrays.asList(
                new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                new long[] {-1,-2,-3,-4,-5,-6,-7,-8,-9 },
                new long[] { 1, 2, 3, 4, -1, -2, -3, -4, -4 },
                new long[] { -1, -2, -3, -4, 1, 2, 3 },
                new long[] { 0, 0, 3, 10, -3, 0, -5 },
                LongStream.range(1, 10_000_000).map(i -> (i * 137) % 199 - 99).toArray()
            );
        }

        @Test
        public void testMaxSum() {
            assertEquals(
                ParallelArrayManipulator.maxSumParallel(series),
                ParallelArrayManipulator.maxSumSequential(series)
            );
        }
    }
}