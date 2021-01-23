package Model;

/** This is the customer class.*/
public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String postalCode;
    private String customerPhoneNo;
    private int divisionId;
    private int countryId;

    public Customer(int customerId, String customerName, String customerAddress, String postalCode, String customerPhoneNo, int divisionId, int countryId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.customerPhoneNo = customerPhoneNo;
        this.divisionId = divisionId;
        this.countryId = countryId;
    }

    /** @return Returns customer Id.*/
    public int getCustomerId() {
        return customerId;
    }

    /** @return Returns customer name.*/
    public String getCustomerName() {
        return customerName;
    }

    /** @return Returns customer address.*/
    public String getCustomerAddress() {
        return customerAddress;
    }

    /** @return Returns customer postal code.*/
    public String getPostalCode() {
        return postalCode;
    }

    /** @return Returns customer phone number.*/
    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    /** @return Returns customer division Id.*/
    public int getDivisionId() {
        return divisionId;
    }

    /** @return Returns customer country.*/
    public int getCountryId() {
        return countryId;
    }

    /** @return Returns customer id and customer name in String format.*/
    @Override
    public String toString() {
        return "[" + customerId + "] " + customerName;
    }

}
