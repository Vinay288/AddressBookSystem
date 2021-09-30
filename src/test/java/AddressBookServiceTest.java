import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookServiceTest {

    @Test
    public void givenContactDetails_whenCorrect_CreateContact() {
        AddressBookService addressBookService=new AddressBookService();
        Contact contact=new Contact((int)(Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        addressBookService.createContact(contact);
        Assertions.assertSame(contact, addressBookService.getContact());
    }

    @Test
    public void givenAddresBookAndContact_whenCorrect_AddContactToAddressbook(){
        AddressBookService addressBookService=new AddressBookService();
        Contact contact=new Contact((int)(Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook=new AddressBook((int) (Math.random()*100),"AddressBook1");
        addressBookService.addContactToAddressBook(addressBook,contact);
        Assertions.assertTrue(addressBook.getContactsList().contains(contact));
    }
}
