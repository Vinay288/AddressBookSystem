package AddressBookSystem;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AddressBookFileIOServiceProvider implements AddressBookFileServiceProviderIF {
    private static AddressBookFileIOServiceProvider addressBookFileIOServiceProvider;

    private AddressBookFileIOServiceProvider() {

    }

    public static AddressBookFileIOServiceProvider getIoInstance() {
        addressBookFileIOServiceProvider = new AddressBookFileIOServiceProvider();
        return addressBookFileIOServiceProvider;
    }

    public void writeToFile(AddressBook addressBook, String fileName) {
        StringBuffer contactBuffer = new StringBuffer();
        addressBook.getContactsList().forEach(contact -> {
            String contactString = contact.toString().concat("\n");
            contactBuffer.append(contactString);
        });

        try {
            Files.write(Paths.get(fileName.concat(".txt")), contactBuffer.toString().getBytes());
        } catch (IOServiceException | java.io.IOException e) {
            throw new IOServiceException(IOServiceException.ExceptionType.ENTERED_WRONG, e.getMessage());
        }
    }

    public void readFromFile(String fileName) {
        try {
            Files.lines(Paths.get(fileName.concat(".txt"))).forEach(System.out::println);
        } catch (Exception e) {
            throw new IOServiceException(IOServiceException.ExceptionType.ENTERED_WRONG, e.getMessage());
        }
    }
}
