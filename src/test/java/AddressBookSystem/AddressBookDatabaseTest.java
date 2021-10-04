package AddressBookSystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AddressBookDatabaseTest {
    @Test
    public void givenAddressBookName_CheckIfAllContactsAreFetched() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        boolean result = addressBookServiceProvider.readService("address_book1", IOServiceEnum.DB_IO);
        Assertions.assertTrue(result);
    }
    @Test
    public void insertContactToGivenAddressBook_checkIfInserted() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact = new Contact("qwerty", "hii", "mangalore", 99787L, "karnataks@hhj", "mangalore", "kar",
                87845);
        addressBookServiceProvider.createContact(contact);
        addressBookServiceProvider.writeService("address_book1", IOServiceEnum.DB_IO,"");
        addressBookServiceProvider.readService("address_book1", IOServiceEnum.DB_IO);
        boolean result = addressBookServiceProvider.compareContactSync("address_book1");
        Assertions.assertTrue(result);
    }
    @Test
    public void givenDateRange_WhenCorrect_RetrieveAllContactsAdded() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        LocalDate startDate = LocalDate.of(2020, 4, 19);
        LocalDate endDate = LocalDate.of(2020, 6, 19);
        List<Contact> contacts = addressBookServiceProvider.readConatctsAddedInRange(Date.valueOf(startDate),
                Date.valueOf(endDate));
        Assertions.assertEquals(0, contacts.size());
    }
    @Test
    public void givenCityAndState_WhenCorrect_RetrieveAllContactsInCityOrState() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        String city = "badami";
        String state = "karnataka";
        List<Contact> contacts = addressBookServiceProvider.readConatctsAddedInGivenCityOrState(city, state);
        Assertions.assertEquals(12, contacts.size());
    }
    @Test
    public void insertMultipleContactToGivenAddressBook_checkIfInserted() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact1 = new Contact("qwerty", "hii", "mangalore", 99787L, "karnataks@hhj", "mangalore", "kar",
                87845);
        Contact contact2 = new Contact( "qwerty", "hii", "mangalore", 99787L, "karnataks@hhj", "mangalore", "kar",
                87845);
        addressBookServiceProvider.createContact(contact1);
        addressBookServiceProvider.addContact(contact1);
        addressBookServiceProvider.addContact(contact2);
        addressBookServiceProvider.writeService("address_book1", IOServiceEnum.DB_IO,"");
        addressBookServiceProvider.readService("address_book1", IOServiceEnum.DB_IO);
        boolean result = addressBookServiceProvider.compareContactSync("address_book1");
        Assertions.assertTrue(result);
    }
}
