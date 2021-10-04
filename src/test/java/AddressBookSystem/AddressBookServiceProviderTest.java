package AddressBookSystem;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AddressBookServiceProviderTest {

    @org.junit.Test
    public void givenContactDetails_whenCorrect_CreateContact() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact = new Contact("vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        addressBookServiceProvider.createContact(contact);
        Assertions.assertSame(contact, addressBookServiceProvider.getContact());
    }

    @Test
    public void givenAddresBookAndContact_whenCorrect_AddContactToAddressbook() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact = new Contact( "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook = new AddressBook( "AddressBook1");
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact);
        Assertions.assertTrue(addressBook.getContactsList().contains(contact));
    }

    @Test
    public void givenAddressBookAndColumnToBeEdited_EditTheContact() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact = new Contact( "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook = new AddressBook( "AddressBook1");
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact);
        String updateValue = "hiree";
        addressBookServiceProvider.editAddressBook(addressBook, "vinay", 2, updateValue);
        Assertions.assertEquals(updateValue, addressBookServiceProvider.getContactfromAddressBook(addressBook, "vinay").getLastName());
    }

    @Test
    public void givenAddressbook_whenCorrect_CAnBeDelete() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact = new Contact("vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook = new AddressBook( "AddressBook1");
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact);
        addressBookServiceProvider.deleteContact(addressBook, "vinay");
        Assertions.assertFalse(addressBook.getContactsList().contains(contact));
    }

    @Test
    public void givenAddressBook_AddMultipleContacts() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact1 = new Contact( "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact( "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook = new AddressBook("AddressBook1");
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact1);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact2);
        Assertions.assertEquals(2, addressBook.getContactsList().size());
    }

    @Test
    public void muktipleAddressbooksCanBeAdded() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        AddressBook addressBook1 = new AddressBook( "AddressBook1");
        AddressBook addressBook2 = new AddressBook( "AddressBook2");
        addressBookServiceProvider.addAdderessBook(addressBook1);
        addressBookServiceProvider.addAdderessBook(addressBook2);
        Assertions.assertEquals(2, addressBookServiceProvider.addressBooks.size());

    }

    @Test
    public void onlyUniqueNamedAddressBookAdded_WhenFalse_returnFlase() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        AddressBook addressBook1 = new AddressBook( "AddressBook1");
        AddressBook addressBook2 = new AddressBook( "AddressBook1");
        addressBookServiceProvider.addAdderessBook(addressBook1);
        boolean result = addressBookServiceProvider.addAdderessBook(addressBook2);
        Assertions.assertFalse(result);
    }

    @Test
    public void searchContactWithByCityOrStateName(){
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact1 = new Contact( "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact( "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook( "AddressBook1");
        AddressBook addressBook2 = new AddressBook(("AddressBook2"));
        addressBookServiceProvider.addAdderessBook(addressBook);
        addressBookServiceProvider.addAdderessBook(addressBook2);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact1);
        addressBookServiceProvider.addContactToAddressBook(addressBook2, contact2);
        List<Contact> contactList= addressBookServiceProvider.searchContact("badami","karnataka");
        Assertions.assertEquals(2,contactList.size());
    }
}

