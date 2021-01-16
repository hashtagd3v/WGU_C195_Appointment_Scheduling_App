package Model;

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

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public int getCountryId() {
        return countryId;
    }

}
