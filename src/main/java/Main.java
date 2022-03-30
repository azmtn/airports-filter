import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        long start = System.currentTimeMillis();

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true); //extract headers from the first row

        CsvSearch search = new CsvSearch(2, "Bo");

        settings.setProcessor(search);
        CsvParser parser = new CsvParser(settings);

        parser.parse(new FileReader("/home/border/Desktop/airports-filter/src/main/resources/airports.dat"));


        List<String[]> results = search.getRows();

        for (String[] strArr : results) {
            for (String str : strArr) {
                System.out.print(str + ";    ");
            }
            System.out.println();
        }

        System.out.println("Rows matched: " + results.size());
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");
    }
}
