import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookServiceTest {

    @Test
    public void givenContactDetails_whenCorrect_CreateContact() {
        AddressBookService addressBookService=new AddressBookService();
        Contact createdContact=addressBookService.createContact((int)(Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Assertions.assertSame(createdContact, addressBookService.getContact());
    }
}
