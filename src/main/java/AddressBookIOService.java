import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
            throw new FileIOException(e.getMessage());
        }
    }
    public void readFromFile(String fileName){
        try {
            Files.lines(Paths.get(fileName.concat(".txt"))).forEach(System.out::println);
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }
}
