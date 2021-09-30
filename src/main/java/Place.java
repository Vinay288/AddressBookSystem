public class Place {
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

    Place(Integer id, String city, String state, Integer zip) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
