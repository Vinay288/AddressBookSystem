package AddressBookSystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookFileIOTest {
    @Test
    public void whenGivenFileName_WhenCorrect_WriteToFile() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact1 = new Contact("vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact("vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook("AddressBook1");
        addressBookServiceProvider.addAdderessBook(addressBook);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact1);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact2);
        boolean result = addressBookServiceProvider.writeService(addressBook.getName(), IOServiceEnum.FILE_IO, "vinn");
        Assertions.assertTrue(result);
    }

    @Test
    public void whenGivenFileName_whenCorrect_ReadFile() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        boolean result = addressBookServiceProvider.readService("vinn", IOServiceEnum.FILE_IO);
        Assertions.assertTrue(result);
    }


    @Test
    public void whenGivenFileName_WhenCorrect_WriteToJSON() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact1 = new Contact("vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact("vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook("AddressBook1");
        addressBookServiceProvider.addAdderessBook(addressBook);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact1);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact2);
        boolean result = addressBookServiceProvider.writeService(addressBook.getName(), IOServiceEnum.JSON_IO, "vinn");
        Assertions.assertTrue(result);
    }
    @Test
    public void whenGivenFileName_whenCorrect_ReadJSONFile() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        boolean result = addressBookServiceProvider.readService("vinn", IOServiceEnum.JSON_IO);
        Assertions.assertTrue(result);
    }
}
