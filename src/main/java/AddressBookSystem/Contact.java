package AddressBookSystem;

public class Contact {
    private final Integer id;
    private String firstName;
    private String lastName;
    private String contactAddress;
    private Long phoneNumber;
    private String email;
    private Address address;

    public Integer getId() {
        return id;
    }

    public Contact getContact() {
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getPlace() {
        return address;
    }

    public void setPlace(Address address) {
        this.address = address;
    }

    public Contact(String firstName, String lastName, String address, Long phoneNumber, String email, String city, String state, Integer zip) {
        this.id = (int) (Math.random() * 100);
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactAddress = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = new Address(city, state, zip);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + contactAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", place=" + contactAddress +
                '}';
    }
}
