package AddressBookSystem;
public class Address {
    private final Integer id;
    private String city;
    private String state;
    private Integer zip;

    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    Address(String city, String state, Integer zip) {
        this.id = (int) (Math.random() * 100);
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
