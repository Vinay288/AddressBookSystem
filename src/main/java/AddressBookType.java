import java.util.List;

public class AddressBookType {
    private final Integer id;
    private String name;
    AddressBookType(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
