import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AddressBookDBTest {
    @Test
    public void givenAddressBookName_CheckIfAllContactsAreFetched() {
        AddressBookService addressBookService = new AddressBookService();
        boolean result = addressBookService.readService("address_book1", IOService.DB_IO);
        Assert.assertTrue(result);
    }
    @Test
    public void insertContactToGivenAddressBook_checkIfInserted() {
        AddressBookService addressBookService = new AddressBookService();
        Contact contact = new Contact(1897, "qwerty", "hii", "mangalore", 99787L, "karnataks@hhj", "mangalore", "kar",
                87845);
        addressBookService.createContact(contact);
        addressBookService.writeService("address_book1",IOService.DB_IO,"");
        addressBookService.readService("address_book1",IOService.DB_IO);
        boolean result = addressBookService.compareContactSync("address_book1");
        Assertions.assertTrue(result);
    }
    @Test
    public void givenDateRange_WhenCorrect_RetrieveAllContactsAdded() {
        AddressBookService addressBookService = new AddressBookService();
        LocalDate startDate = LocalDate.of(2020, 4, 19);
        LocalDate endDate = LocalDate.of(2020, 6, 19);
        List<Contact> contacts = addressBookService.readConatctsAddedInRange(Date.valueOf(startDate),
                Date.valueOf(endDate));
        Assert.assertEquals(0, contacts.size());
    }
    @Test
    public void givenCityAndState_WhenCorrect_RetrieveAllContactsInCityOrState() {
        AddressBookService addressBookService = new AddressBookService();
        String city = "badami";
        String state = "karnataka";
        List<Contact> contacts = addressBookService.readConatctsAddedInGivenCityOrState(city, state);
        Assert.assertEquals(12, contacts.size());
    }
}
