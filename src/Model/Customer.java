package Model;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private int divisionId;
    private int countryId;
    private String postalCode;
    private String customerPhoneNo;

    public Customer(int customerId, String customerName, String customerAddress, int divisionId, int countryId, String postalCode, String customerPhoneNo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.postalCode = postalCode;
        this.customerPhoneNo = customerPhoneNo;
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

    public int getDivisionId() {
        return divisionId;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }
}
