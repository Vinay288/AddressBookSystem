import java.util.*;

public class AddressBookService {
    HashSet<String> addressBookName;
    List<AddressBook> addressBooks;
    HashMap<String, List<Contact>> cityContactsList;
    HashMap<String, List<Contact>> stateContactsList;
    Contact contact;

    AddressBookService() {
        addressBookName = new HashSet<>();
        addressBooks = new ArrayList<>();
        cityContactsList = new HashMap<>();
        stateContactsList = new HashMap<>();
    }

    public Contact getContact() {
        return contact.getContact();
    }

    public Contact getContactfromAddressBook(AddressBook addressBook, String firstName) {
        for (Contact contact : addressBook.getContactsList())
            if (contact.getFirstName().equals(firstName)) {
                return contact;
            }
        return null;
    }

    public Contact createContact(Contact contact) {
        this.contact = contact;
        return this.contact;
    }

    public void addContactToAddressBook(AddressBook addressBook, Contact contact) {
        if (cityContactsList.containsKey(contact.getPlace().getCity()))
            cityContactsList.get(contact.getPlace().getCity()).add(contact);
        else
            cityContactsList.put(contact.getPlace().getCity(), List.of(contact));

        if (stateContactsList.containsKey(contact.getPlace().getState()))
            stateContactsList.get(contact.getPlace().getState()).add(contact);

        else
            stateContactsList.put(contact.getPlace().getCity(), List.of(contact));

        addressBook.addContact(contact);
    }

    public void editContact(Contact contact, int columnIndex, String value) {
        switch (columnIndex) {
            case 1:
                contact.setFirstName(value);
                break;
            case 2:
                contact.setLastName(value);
                break;
            case 3:
                contact.setAddress(value);
                break;
            case 4:
                contact.setPhoneNumber(Long.valueOf(value));
                break;
            case 5:
                contact.setEmail(value);
                break;
            case 6:
                contact.getPlace().setCity(value);
                break;
            case 7:
                contact.getPlace().setState(value);
                break;
            case 8:
                contact.getPlace().setZip(Integer.valueOf(value));
                break;
            default:
                break;
        }
    }

    public void editAddressBook(AddressBook addressBook, String contactName, int columnIndex, String value) {
        List<Contact> contactList = addressBook.getContactsList();
        for (Contact contact : contactList)
            if (contact.getFirstName().equals(contactName)) {
                System.out.println("ada");
                this.editContact(contact, columnIndex, value);
            }
    }

    public void deleteContact(AddressBook addressBook, String contactName) {
        Contact contact = getContactfromAddressBook(addressBook, contactName);
        addressBook.getContactsList().remove(contact);
    }

    public boolean addAdderessBook(AddressBook addressBook) {
        if (addressBookName.contains(addressBook.getName()))
            return false;
        addressBookName.add(addressBook.getName());
        addressBooks.add(addressBook);
        return true;
    }

    public List<Contact> searchContact(String cityName, String stateName) {
        List<Contact> contactList = new ArrayList<>();
        addressBooks.forEach(addressBook -> addressBook.getContactsList().stream().filter(contact -> (contact.getPlace().getCity().equals(cityName) || contact.getPlace().getCity().equals(stateName))).forEach(contactList::add));
        return contactList;
    }
}
