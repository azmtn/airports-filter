package configuration;

public class AirportContext {
    private int numberColumn;

    public int getNumberColumn() {
        return numberColumn;
    }

    public void setNumberColumn(int numberColumn) {
        this.numberColumn = numberColumn;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;
}
