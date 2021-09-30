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

    @Test
    public void givenAddressBookAndColumnToBeEdited_EditTheContact(){
        AddressBookService addressBookService=new AddressBookService();
        Contact contact=new Contact((int)(Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook=new AddressBook((int) (Math.random()*100),"AddressBook1");
        addressBookService.addContactToAddressBook(addressBook,contact);
        String updateValue="hiree";
        addressBookService.editAddressBook(addressBook,"vinay",2,updateValue);
        Assertions.assertEquals(updateValue,addressBookService.getContactfromAddressBook(addressBook,"vinay").getLastName());
    }

    @Test
    public void givenAddressbook_whenCorrect_CAnBeDelete(){
        AddressBookService addressBookService=new AddressBookService();
        Contact contact=new Contact((int)(Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook=new AddressBook((int) (Math.random()*100),"AddressBook1");
        addressBookService.addContactToAddressBook(addressBook,contact);
        addressBookService.deleteContact(addressBook,"vinay");
        Assertions.assertFalse(addressBook.getContactsList().contains(contact));
    }
    @Test
    public void givenAddressBook_AddMultipleContacts(){
        AddressBookService addressBookService=new AddressBookService();
        Contact contact1=new Contact((int)(Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2=new Contact((int)(Math.random() * 100), "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        AddressBook addressBook=new AddressBook((int) (Math.random()*100),"AddressBook1");
        addressBookService.addContactToAddressBook(addressBook,contact1);
        addressBookService.addContactToAddressBook(addressBook,contact2);
        Assertions.assertEquals(2,addressBook.getContactsList().size());
    }
}
