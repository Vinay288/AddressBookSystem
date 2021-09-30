public class Contact {
    private final Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;
    private String email;
    private Place place;

    public Integer getId() {
        return id;
    }
public Contact getContact(){
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    Contact(Integer id, String firstName, String lastName, String address, Long phoneNumber, String email, String city, String state, Integer zip){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.email=email;
        place=new Place((int) (Math.random()*100),city,state,zip);
    }
}
