import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize a null scanner object to be used to read files
        Scanner scanner = null;

        try{
            scanner = fileScanner("./datafiles/CountriesAndIsoCodes.txt");
        }catch(FileNotFoundException e){
            System.out.println("Error: "+e);
        }

        // Gather desired line count for the following arrays: countries and isoCodes
        int lineCount = scanner.nextInt();
        String[] countries = new String[lineCount];
        String[] isoCodes = new String[lineCount];

        // Skip column labels
        scanner.nextLine();
        scanner.nextLine();

        for(int i = 0; i < lineCount; i++){
            // Separate each column into separate fields
            String line = scanner.nextLine();
            String[] fields = line.split("#");

            countries[i] = fields[0];
            isoCodes[i] = fields[1];
        }

        try{
            scanner = fileScanner("./datafiles/IsoYearWaterData.txt");
        }catch(FileNotFoundException e){
            System.out.println("Error: "+e);
        }

        SortedArrayList<YearlyWaterRecord> yearlyWaterRecordData = new SortedArrayList<>();
        ArrayList<Integer> yearsList = new ArrayList<>();
        scanner.nextLine();
        // Skip column labels

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] fields = line.split(",");

            YearlyWaterRecord current = new YearlyWaterRecord(fields[0], strToDouble(fields[1]), strToDouble(fields[2]), strToDouble(fields[3]), strToDouble(fields[4]));
            yearlyWaterRecordData.add(current);

            if(!yearsList.contains(getYear(fields[0]))){
                yearsList.add(getYear(fields[0]));
            }
        }

        int[] years = new int[yearsList.size()];
        for(int i = 0; i < years.length; i++){
            years[i] = yearsList.get(i);
        }

        System.out.println(yearlyWaterRecordData);

        WaterComparisonGui gui  = new WaterComparisonGui(yearlyWaterRecordData, countries, isoCodes, years);
    }

    public static Scanner fileScanner(String filename) throws FileNotFoundException {
        File file = new File(filename);
        return new Scanner(file);
    }

    public static double strToDouble(String data){
        if(data.equals("-") || data.equals("<1")){
            return 0;
        }else if (data.equals(">99")) {
            return 100;
        }
        return Double.parseDouble(data);
    }

    public static int getYear(String isoYear){
        String yearStr = isoYear.substring(3);
        return Integer.parseInt(yearStr);
    }
}
