import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookFileIOTest {
    @Test
    public void whenGivenFileName_WhenCorrect_WriteToFile() {
        AddressBookService addressBookService = new AddressBookService();
        Contact contact1 = new Contact((int) (Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact((int) (Math.random() * 100), "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook((int) (Math.random() * 100), "AddressBook1");
        addressBookService.addAdderessBook(addressBook);
        addressBookService.addContactToAddressBook(addressBook, contact1);
        addressBookService.addContactToAddressBook(addressBook, contact2);
        boolean result = addressBookService.writeService(addressBook.getName(), IOService.FILE_IO, "vinn");
        Assertions.assertTrue(result);
    }

    @Test
    public void whenGivenFileName_whenCorrect_ReadFile() {
        AddressBookService addressBookService = new AddressBookService();
        boolean result = addressBookService.readService("vinn", IOService.FILE_IO);
        Assertions.assertTrue(result);
    }
    @Test
    public void whenGivenFileName_WhenCorrect_WriteToCSV() {
        AddressBookService addressBookService = new AddressBookService();
        Contact contact1 = new Contact((int) (Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact((int) (Math.random() * 100), "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook((int) (Math.random() * 100), "AddressBook1");
        addressBookService.addAdderessBook(addressBook);
        addressBookService.addContactToAddressBook(addressBook, contact1);
        addressBookService.addContactToAddressBook(addressBook, contact2);
        boolean result = addressBookService.writeService(addressBook.getName(), IOService.CSV_IO, "vinn");
        Assertions.assertTrue(result);
    }
    @Test
    public void whenGivenFileName_whenCorrect_ReadCSVFile() {
        AddressBookService addressBookService = new AddressBookService();
        boolean result = addressBookService.readService("vinn", IOService.CSV_IO);
        Assertions.assertTrue(result);
    }
    @Test
    public void whenGivenFileName_WhenCorrect_WriteToJSON() {
        AddressBookService addressBookService = new AddressBookService();
        Contact contact1 = new Contact((int) (Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact((int) (Math.random() * 100), "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook((int) (Math.random() * 100), "AddressBook1");
        addressBookService.addAdderessBook(addressBook);
        addressBookService.addContactToAddressBook(addressBook, contact1);
        addressBookService.addContactToAddressBook(addressBook, contact2);
        boolean result = addressBookService.writeService(addressBook.getName(), IOService.JSON_IO, "vinn");
        Assertions.assertTrue(result);
    }
}
