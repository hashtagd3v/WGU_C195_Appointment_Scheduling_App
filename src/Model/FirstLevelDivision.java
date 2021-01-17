package Model;

public class FirstLevelDivision {

    private int firstLevelDivId;
    private String firstLevelDivName;

    public FirstLevelDivision(int firstLevelDivId, String firstLevelDivName) {
        this.firstLevelDivId = firstLevelDivId;
        this.firstLevelDivName = firstLevelDivName;
    }

    @Override
    public String toString() {
        return (Integer.toString(firstLevelDivId) + " " + firstLevelDivName);
    }

    //FIXME: How do I show just the firstLevelDiveName?; fix connection successful appearing multiple times!

}
