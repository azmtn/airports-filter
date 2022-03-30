import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import configuration.AirportYAMLConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AiroportApplication {
    private static String inputQuery() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String query = scanner.nextLine();
        while (query.isEmpty()) {
            System.out.println("Поисковый запрос не может быть пустым");
            query = scanner.nextLine();
        }
        return query;
    }

    public static void main(String[] args) throws FileNotFoundException {
        var config = AirportYAMLConfig.loadConfig().getContext();
        String filePath = config.getFilePath();
        int numColumn = config.getNumberColumn();

        if (args.length == 1) {
            int userNumberColumn = Integer.parseInt(args[0]);
            if (userNumberColumn > 0 && userNumberColumn < 13)
                numColumn = userNumberColumn;
        }

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        String query = inputQuery();
        CsvSearch search = new CsvSearch(numColumn - 1, query);

        long startTime = System.currentTimeMillis();

        settings.setProcessor(search);
        CsvParser parser = new CsvParser(settings);
        try {
            parser.parse(new FileReader(filePath));

            List<String[]> results = search.getRows();
            int finalNumColumn = numColumn;
            results.stream()
                    .sorted(Comparator.comparing(o -> o[finalNumColumn - 1]))
                    .forEach(o -> System.out.println(Arrays.stream(o)
                            .collect(Collectors.joining(", "))));


            System.out.println("Количество найденых строк: " + results.size());
            System.out.println("Время, затраченное на поиск: " + (System.currentTimeMillis() - startTime) + " ms");
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}
