package AddressBookSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AddressBookServiceProviderIF {
    public Contact getContact();

    public void addContact(Contact contact);

    public Contact getContactfromAddressBook(AddressBook addressBook, String firstName);

    public Contact createContact(Contact contact);

    public void addContactToAddressBook(AddressBook addressBook, Contact contact);

    public void editContact(Contact contact, int columnIndex, String value);

    public void editAddressBook(AddressBook addressBook, String contactName, int columnIndex, String value);

    public void deleteContact(AddressBook addressBook, String contactName);

    public boolean addAdderessBook(AddressBook addressBook);

    public List<Contact> searchContact(String cityName, String stateName);

    public void sortContacts(HashMap<String, Contact> addressBook, int sortKey);

    public AddressBook getAddressBook(String addressBookName);

    public boolean writeService(String addressBookName, IOServiceEnum ioServiceEnum, String fileName);

    public boolean readService(String fileName, IOServiceEnum ioServiceEnum);

    public boolean compareContactSync(String addressBokkName);

    public List<Contact> readConatctsAddedInRange(Date startDate, Date endDate);

    public List<Contact> readConatctsAddedInGivenCityOrState(String city, String state);
}
