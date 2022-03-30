import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.RowListProcessor;

public class CsvSearch  extends RowListProcessor {
    private final String stringToMatch;
    private int indexToMatch;

    public CsvSearch(int columnToMatch, String stringToMatch){
        this.stringToMatch = stringToMatch;
        this.indexToMatch = columnToMatch;
    }


    @Override
    public void rowProcessed(String[] row, ParsingContext context) {
        String value = row[indexToMatch - 1];
        if(value != null && value.indexOf(stringToMatch) == 0) {
            super.rowProcessed(row, context);
        }
    }
}
