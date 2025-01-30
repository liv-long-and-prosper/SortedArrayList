import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class YearlyWaterRecordTest {

    @Test
    public void compareTo() {
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        YearlyWaterRecord testVal2 = new YearlyWaterRecord("AFG2000",27.4418560941262,3.29920308776308,43.8567769973269,25.4021638207839);

        int expected = testVal.compareTo(testVal2);
        assertTrue(expected < 0);
    }

    @Test
    public void testEquals() {
        YearlyWaterRecord testVal = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        YearlyWaterRecord testVal2 = new YearlyWaterRecord("ABW2000",94.4888063555081,0,5.41957660372234,0);
        assertEquals(testVal, testVal2);
    }
}