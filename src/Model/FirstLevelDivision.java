package Model;

/**This is the first level division class.*/
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
    /** @return Returns the first level division ID.*/
    public int getFirstLevelDivId() {
        return firstLevelDivId;
    }

    /** @return Returns the first level division name.*/
    public String getFirstLevelDivName() {
        return firstLevelDivName;
    }

    /** @return Returns first level division Name in String format.*/
    @Override
    public String toString() {
        return (firstLevelDivName);
    }

}
