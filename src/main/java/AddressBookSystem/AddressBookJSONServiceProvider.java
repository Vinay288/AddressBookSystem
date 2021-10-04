package AddressBookSystem;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class AddressBookJSONServiceProvider implements AddressBookJSONServiceProviderIF {
    private static AddressBookJSONServiceProvider addressBookJSONServiceProvider;

    private AddressBookJSONServiceProvider() {

    }

    public static AddressBookJSONServiceProvider getIoInstance() {
        addressBookJSONServiceProvider = new AddressBookJSONServiceProvider();
        return addressBookJSONServiceProvider;
    }

    public void writeToJsonFile(AddressBook addressBook, String fileName) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(addressBook);
            FileWriter writer = new FileWriter(fileName.concat(".json"));
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            throw new IOServiceException(IOServiceException.ExceptionType.ENTERED_WRONG, e.getMessage());
        }

    }

    public void readJsonFile(String fileName) {
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(fileName.concat(".json")));
            Contact[] contactsFile = gson.fromJson(br, Contact[].class);
            List<Contact> addressbook = Arrays.asList(contactsFile);
            System.out.println(addressbook);
        } catch (Exception e) {
            throw new IOServiceException(IOServiceException.ExceptionType.ENTERED_WRONG, e.getMessage());
        }
    }
}
