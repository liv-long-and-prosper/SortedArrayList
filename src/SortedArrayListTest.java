import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.*;


public class SortedArrayListTest {

    public SortedArrayList<YearlyWaterRecord> createSortedArrayList() {
        File waterData = new File("./datafiles/IsoYearWaterData.txt");
        Scanner scanner;

        try{
            scanner = new Scanner(waterData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        SortedArrayList<YearlyWaterRecord> yearlyWaterRecordData = new SortedArrayList<>();
        scanner.nextLine();
        // Skip column labels

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] fields = line.split(",");

            YearlyWaterRecord current = new YearlyWaterRecord(fields[0], Main.strToDouble(fields[1]), Main.strToDouble(fields[2]), Main.strToDouble(fields[3]), Main.strToDouble(fields[4]));
            yearlyWaterRecordData.add(current);
        }
        return yearlyWaterRecordData;
    }

@Test
    public void testSize() {
        assertThrows(IllegalArgumentException.class, ()-> new SortedArrayList<YearlyWaterRecord>(-1));
        SortedArrayList<YearlyWaterRecord> test = createSortedArrayList();
        assertEquals(5359, test.size());
    }

    @Test
    public void testIsEmpty() {
        SortedArrayList<YearlyWaterRecord> empty = new SortedArrayList<>();
        SortedArrayList<YearlyWaterRecord> notEmpty = createSortedArrayList();
        assertTrue(empty.isEmpty());
        assertFalse(notEmpty.isEmpty());
    }

    @Test
    public void testClear() {
        SortedArrayList<YearlyWaterRecord> test = createSortedArrayList();
        test.clear();
        assertEquals(0, test.size());
    }

    @Test
    public void testContains() {
        SortedArrayList<YearlyWaterRecord> test = createSortedArrayList();
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        assertTrue(test.contains(testVal));
    }

    @Test
    public void testIndexOf() {
        SortedArrayList<YearlyWaterRecord> test = createSortedArrayList();
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2025",94.4888063555081,0,5.41957660372234,0);
        assertEquals(22, test.indexOf(testVal));
    }

    @Test
    public void testGet() {
        SortedArrayList<YearlyWaterRecord> test = createSortedArrayList();
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        assertTrue(test.get(0).equals(testVal));
    }

    @Test
    public void testAdd() {
        SortedArrayList<YearlyWaterRecord> test = new SortedArrayList<>(1);
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        test.add(testVal);

        YearlyWaterRecord testAtZero = test.get(0);
        assertEquals(1, test.size());
        assertEquals(testAtZero, testVal);
    }

    @Test
    public void testRemove() {
        SortedArrayList<YearlyWaterRecord> test = new SortedArrayList<>(1);
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        YearlyWaterRecord testVal2 = new YearlyWaterRecord("AFG2000",27.4418560941262,3.29920308776308,43.8567769973269,25.4021638207839);
        test.add(testVal);
        test.add(testVal2);
        assertEquals(2, test.size());

        test.remove(0);
        assertEquals(1, test.size());
        assertTrue(test.contains(testVal2));
    }

    @Test
    public void testIterator() {
       SortedArrayList<YearlyWaterRecord> test = new SortedArrayList<>();
        Iterator<YearlyWaterRecord> testIterator = test.iterator();

        assertFalse(testIterator.hasNext());

        test.add(new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0));
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        assertTrue(testIterator.hasNext());

        assertEquals(testVal, testIterator.next());
    }

    @Test
    public void testToArray() {
        SortedArrayList<String> test = new SortedArrayList<>();
        test.add("one");
        test.add("two");
        test.add("three");

        String[] testArray = new String[3];
        testArray[0] = "one";
        testArray[1] = "two";
        testArray[2] = "three";

        String[] testToArray = (String[]) test.toArray(testArray);

        assertArrayEquals(testToArray, testArray);
    }
}