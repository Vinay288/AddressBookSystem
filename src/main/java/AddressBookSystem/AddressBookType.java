package AddressBookSystem;

import java.util.List;

public class AddressBookType {
    private final Integer id;
    private String name;

    AddressBookType(String name) {
        this.id = (int) (Math.random() * 100);
        this.name = name;
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
