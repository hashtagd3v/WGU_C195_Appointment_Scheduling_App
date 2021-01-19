package Model;

/** This is the country class.*/
public class Country {

    private int countryId;
    private String country;

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
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
