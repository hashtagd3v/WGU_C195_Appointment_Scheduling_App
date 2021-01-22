package Model;

import javafx.collections.ObservableList;

import static DBAccess.DBFirstLevelDiv.getAllFirstLevelDiv;

/**This is the first level division class.*/
public class FirstLevelDivision {

    private int firstLevelDivId;
    private String firstLevelDivName;

    public FirstLevelDivision(int firstLevelDivId, String firstLevelDivName) {
        this.firstLevelDivId = firstLevelDivId;
        this.firstLevelDivName = firstLevelDivName;
    }

    /** This method searches the first level division combo box for matching division ID of the customer selected.
     * @param divId The customer's division ID.
     * @return Returns the first level division with matching ID.*/
    public static FirstLevelDivision getDivisionIdMatch(int divId) {
        ObservableList<FirstLevelDivision> divisions =  getAllFirstLevelDiv();

        FirstLevelDivision division = null;

        for (int i = 0; i < divisions.size(); i++) {
            FirstLevelDivision firstLevelDivision = divisions.get(i);

            if (firstLevelDivision.getFirstLevelDivId() != divId) {
                continue;
            } else {
                division = firstLevelDivision;
                break;
            }

        }

        return division;

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
        return "[" + firstLevelDivId + "] " + firstLevelDivName;
    }

}
