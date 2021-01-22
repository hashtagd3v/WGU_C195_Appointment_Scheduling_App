package Model;

import javafx.collections.ObservableList;

import static DBAccess.DBCountry.getAllCountry;

/** This is the country class.*/
public class Country {

    private int countryId;
    private String country;

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /** This method searches the first level division combo box for matching country ID of the customer selected.
     * @param id The customer country ID.
     * @return Returns the country with matching ID.*/
    public static Country getCountryIdMatch(int id) {
        ObservableList<Country> divisions =  getAllCountry();

        Country country = null;

        for (int i = 0; i < divisions.size(); i++) {
            Country selectCountry = divisions.get(i);

            if (selectCountry.getCountryId() != id) {
                continue;
            } else {
                country = selectCountry;
                break;
            }

        }

        return country;

    }

    /** @return Returns country Id.*/
    public int getCountryId() {
        return countryId;
    }

    /** @return Returns String country.*/
    public String getCountry() {
        return country;
    }

    /** @return Returns country name in String format.*/
    @Override
    public String toString() {
        return "[" + countryId + "] " + country;
    }

}
