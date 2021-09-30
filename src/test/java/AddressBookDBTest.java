import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
