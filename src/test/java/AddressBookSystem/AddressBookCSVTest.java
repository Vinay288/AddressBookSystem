package AddressBookSystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressBookCSVTest {
    @Test
    public void whenGivenFileName_WhenCorrect_WriteToCSV() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        Contact contact1 = new Contact((int) (Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
        Contact contact2 = new Contact((int) (Math.random() * 100), "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
        AddressBook addressBook = new AddressBook((int) (Math.random() * 100), "AddressBook1");
        addressBookServiceProvider.addAdderessBook(addressBook);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact1);
        addressBookServiceProvider.addContactToAddressBook(addressBook, contact2);
        boolean result = addressBookServiceProvider.writeService(addressBook.getName(), IOServiceEnum.CSV_IO, "vinn");
        Assertions.assertTrue(result);
    }

    @Test
    public void whenGivenCSVFileName_WhenWrong_CatchException() {
        try {
            AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
            Contact contact1 = new Contact((int) (Math.random() * 100), "vinay", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "badami", "karnataka", 587201);
            Contact contact2 = new Contact((int) (Math.random() * 100), "vishal", "hiremath", "street 2", 9874512420L, "vvj@gmail.com", "bagalkot", "karnataka", 587201);
            AddressBook addressBook = new AddressBook((int) (Math.random() * 100), "AddressBook1");
            addressBookServiceProvider.addAdderessBook(addressBook);
            addressBookServiceProvider.addContactToAddressBook(addressBook, contact1);
            addressBookServiceProvider.addContactToAddressBook(addressBook, contact2);
            boolean result = addressBookServiceProvider.writeService(addressBook.getName(), IOServiceEnum.CSV_IO, "vinn.txt");
        } catch (IOServiceException exception) {
            Assertions.assertEquals(exception, IOServiceException.ExceptionType.ENTERED_WRONG);
        }
    }

    @Test
    public void whenGivenFileName_whenCorrect_ReadCSVFile() {
        AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
        boolean result = addressBookServiceProvider.readService("vinn", IOServiceEnum.CSV_IO);
        Assertions.assertTrue(result);
    }

    @Test
    public void whenGivenFileName_whenWrong_ReadCSVFile() {
        try {
            AddressBookServiceProvider addressBookServiceProvider = new AddressBookServiceProvider();
            boolean result = addressBookServiceProvider.readService("vinn", IOServiceEnum.CSV_IO);
            Assertions.assertTrue(result);
        } catch (IOServiceException exception) {
            Assertions.assertEquals(exception, IOServiceException.ExceptionType.ENTERED_WRONG);
        }
    }
}
