package Model;

public class Country {

    private int countryId;
    private String country;

    public Country (int countryId) {
        this.countryId = countryId;
    }

    public Country (String country) {
        this.country = country;
    }

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return (country);
    }

}
