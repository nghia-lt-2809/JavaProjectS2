package Models;

public class User {
    private String fullName;
    private String dOB;
    private String  identityCardNumber;
    private String address;
    private String PhoneNumber;






    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", dOB='" + dOB + '\'' +
                ", identityCardNumber=" + identityCardNumber +
                ", address='" + address + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }

    public User(String fullName, String dOB, String identityCardNumber, String address, String phoneNumber) {
        this.fullName = fullName;
        this.dOB = dOB;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.PhoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
