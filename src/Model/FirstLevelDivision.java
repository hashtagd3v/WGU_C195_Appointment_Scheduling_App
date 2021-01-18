package Model;

public class FirstLevelDivision {

    private int firstLevelDivId;
    private String firstLevelDivName;

    public FirstLevelDivision(int firstLevelDivId) {
        this.firstLevelDivId = firstLevelDivId;
    }

    public FirstLevelDivision(String firstLevelDivName) {
        this.firstLevelDivName = firstLevelDivName;
    }

    public FirstLevelDivision(int firstLevelDivId, String firstLevelDivName) {
        this.firstLevelDivId = firstLevelDivId;
        this.firstLevelDivName = firstLevelDivName;
    }

    public int getFirstLevelDivId() {
        return firstLevelDivId;
    }

    public String getFirstLevelDivName() {
        return firstLevelDivName;
    }

    @Override
    public String toString() {
        return (firstLevelDivName);
    }

    //FIXME: How do I show just the firstLevelDiveName?; fix connection successful appearing multiple times!

}
