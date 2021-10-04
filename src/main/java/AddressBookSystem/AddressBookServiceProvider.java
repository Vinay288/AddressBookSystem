package AddressBookSystem;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookServiceProvider extends Thread implements AddressBookServiceProviderIF {
    HashSet<String> addressBookName;
    List<AddressBook> addressBooks;
    List<Contact> contactList;
    HashMap<String, List<Contact>> cityContactsList;
    HashMap<String, List<Contact>> stateContactsList;
    Contact contact, updateContact;

    public AddressBookServiceProvider() {
        addressBookName = new HashSet<>();
        addressBooks = new ArrayList<>();
        cityContactsList = new HashMap<>();
        stateContactsList = new HashMap<>();
        contactList = new ArrayList<>();
    }

    public Contact getContact() {
        return contact.getContact();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
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
                contact.setContactAddress(value);
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

    public void sortContacts(HashMap<String, Contact> addressBook, int sortKey) {
        ArrayList<Contact> personList = new ArrayList<>(addressBook.values());
        if (sortKey == 0) {
            List<Contact> sortedPersonList = personList.stream()
                    .sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());
            System.out.println("contacts after sorting ");
            System.out.println(sortedPersonList);
        }
        switch (sortKey) {
            case 0:
                List<Contact> sortedPersonList = personList.stream()
                        .sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());
                System.out.println("contacts after sorting ");
                System.out.println(sortedPersonList);
                break;
            case 1:
                List<Contact> sortedPersonByCityList = personList.stream()
                        .sorted(Comparator.comparing(s -> s.getPlace().getCity())).collect(Collectors.toList());
                System.out.println("contacts after sorting ");
                System.out.println(sortedPersonByCityList);
                break;
            case 2:
                List<Contact> sortedPersonByStateList = personList.stream()
                        .sorted(Comparator.comparing(s -> s.getPlace().getState())).collect(Collectors.toList());
                System.out.println("contacts after sorting ");
                System.out.println(sortedPersonByStateList);
                break;
            case 3:
                List<Contact> sortedPersonByZipList = personList.stream()
                        .sorted(Comparator.comparing(s -> s.getPlace().getZip()))
                        .collect(Collectors.toList());
                System.out.println("contacts after sorting ");
                System.out.println(sortedPersonByZipList);
                break;
            default:
                break;
        }


    }

    public AddressBook getAddressBook(String addressBookName) {
        for (AddressBook addressBook : addressBooks)
            if (addressBook.getName().equals(addressBookName)) return addressBook;
        return null;
    }

    public boolean writeService(String addressBookName, IOServiceEnum ioServiceEnum, String fileName) {
        switch (ioServiceEnum) {
            case FILE_IO:
                AddressBookFileIOServiceProvider.getIoInstance().writeToFile(getAddressBook(addressBookName), fileName);
                return true;
            case CSV_IO:
                AddressBookCSVServiceProvider.getIoInstance().writeToCSVFile(getAddressBook(addressBookName), fileName);
                return true;
            case JSON_IO:
                AddressBookJSONServiceProvider.getIoInstance().writeToJsonFile(getAddressBook(addressBookName), fileName);
                return true;
            case DB_IO:
                updateContact = AddressBookDataBaseServiceProvider.getIoInstance().writeAddressBookDB(this.getContact(), addressBookName);
                return true;
            default:
                break;
        }
        return false;
    }

    List<Contact> contactsListFromDB;

    public boolean readService(String fileName, IOServiceEnum ioServiceEnum) {
        switch (ioServiceEnum) {
            case FILE_IO:
                AddressBookFileIOServiceProvider.getIoInstance().readFromFile(fileName);
                return true;
            case CSV_IO:
                AddressBookCSVServiceProvider.getIoInstance().readFromCSVFile(fileName);
                return true;
            case JSON_IO:
                AddressBookJSONServiceProvider.getIoInstance().readJsonFile(fileName);
                return true;
            case DB_IO:
                contactsListFromDB = AddressBookDataBaseServiceProvider.getIoInstance().readContacts(fileName);
                return true;
            default:
                break;
        }
        return false;
    }

    public boolean compareContactSync(String addressBokkName) {
        readService(addressBokkName, IOServiceEnum.DB_IO);
        for (Contact contact : contactsListFromDB) {
            if (contact.toString().equals(updateContact.toString()))
                return true;
        }
        return false;
    }

    public List<Contact> readConatctsAddedInRange(Date startDate, Date endDate) {
        return AddressBookDataBaseServiceProvider.getIoInstance().readConatctsAddedInRange(startDate, endDate);
    }

    public List<Contact> readConatctsAddedInGivenCityOrState(String city, String state) {
        return AddressBookDataBaseServiceProvider.getIoInstance().readContactsInGivenCityOrState(city, state);
    }
}
