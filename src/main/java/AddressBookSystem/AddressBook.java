package AddressBookSystem;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final Integer id;
    private String name;
    private List<Contact> contactsList;
    private List<AddressBookType> addressBookTypeList;

    public List<AddressBookType> getAddressBookTypeList() {
        return addressBookTypeList;
    }

    public void setAddressBookTypeList(List<AddressBookType> addressBookTypeList) {
        this.addressBookTypeList = addressBookTypeList;
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

    public List<Contact> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    AddressBook(String name) {
        this.id = (int) (Math.random() * 100);
        this.name = name;
        contactsList = new ArrayList<>();
        addressBookTypeList = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contactsList.add(contact);
    }
}
