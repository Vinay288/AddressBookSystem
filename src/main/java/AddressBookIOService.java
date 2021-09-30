import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddressBookIOService {
    private static AddressBookIOService addressBookIOService;

    private AddressBookIOService() {

    }

    public static AddressBookIOService getIoInstance() {
        addressBookIOService = new AddressBookIOService();
        return addressBookIOService;
    }

    public void writeToFile(AddressBook addressBook, String fileName) {
        StringBuffer contactBuffer = new StringBuffer();
        addressBook.getContactsList().forEach(contact -> {
            String contactString = contact.toString().concat("\n");
            contactBuffer.append(contactString);
        });

        try {
            Files.write(Paths.get(fileName.concat(".txt")), contactBuffer.toString().getBytes());
        } catch (FileIOException | IOException e) {
            e.printStackTrace();
        }
    }
}
