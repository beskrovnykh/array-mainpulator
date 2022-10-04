import org.junit.Test;
import static org.junit.Assert.*;

 
public class ParallelArrayManipulatorTest {
 
    @Test
    public void testOne() {
        int[][] input = new int[3][3];

        input[0] = new int[] {1, 2, 100};
        input[1] = new int[] {2, 5, 100};
        input[2] = new int[] {3, 4, 100};
        
        assertEquals(200, ParallelArrayManipulator.arrayManipulation(5, input));
    }

    @Test
    public void testMaxSum_whenRandomSeries() {
        long[] seriesRandom = new long[] { 122, 60, -2, 135, 73, 11, 148 };
        assertEquals(547, ParallelArrayManipulator.maxSumParallel(seriesRandom));
        assertEquals(547, ParallelArrayManipulator.maxSumSequential(seriesRandom));
    }

    @Test
    public void testMaxSum_whenPositiveSeries() {
        long[] seriesPositive = new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertEquals(45, ParallelArrayManipulator.maxSumParallel(seriesPositive));
        assertEquals(45, ParallelArrayManipulator.maxSumSequential(seriesPositive));

    }

    @Test
    public void testMaxSum_whenNegativeSeries() {
        long[] seriesNegative = new long[] { -1, -2, -3, -4, -5, -6, -7, -8, -9 }; 
        assertEquals(0, ParallelArrayManipulator.maxSumParallel(seriesNegative));
        assertEquals(0, ParallelArrayManipulator.maxSumSequential(seriesNegative));
    }
    
    @Test
    public void testMaxSum_whenHighLowSeries() {
        long[] seriesHighLow = new long[] { 1, 2, 3, 4, -1, -2, -3, -4, -4 }; 
        assertEquals(10, ParallelArrayManipulator.maxSumParallel(seriesHighLow));
        assertEquals(10, ParallelArrayManipulator.maxSumSequential(seriesHighLow));
    }

    @Test
    public void testMaxSum_whenHighLogSeries() {
        long[] seriesLowHigh = new long[] { -1, -2, -3, -4, 1, 2, 3 }; 
        assertEquals(0, ParallelArrayManipulator.maxSumParallel(seriesLowHigh));
        assertEquals(0, ParallelArrayManipulator.maxSumSequential(seriesLowHigh));
    }

        @Test
    public void testMaxSum_whenDeltoidSeries() {
        long[] seriesDelta = new long[] { 0, 0, 3, 10, -3, 0, -5 }; 
        assertEquals(13, ParallelArrayManipulator.maxSumParallel(seriesDelta));
        assertEquals(13, ParallelArrayManipulator.maxSumSequential(seriesDelta));
    }
}