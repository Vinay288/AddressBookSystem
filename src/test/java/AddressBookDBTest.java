import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressBookDBTest {
    @Test
    public void givenAddressBookName_CheckIfAllContactsAreFetched() {
        AddressBookService addressBookService = new AddressBookService();
        boolean result = addressBookService.readService("address_book1", IOService.DB_IO);
        Assert.assertTrue(result);
    }
}
