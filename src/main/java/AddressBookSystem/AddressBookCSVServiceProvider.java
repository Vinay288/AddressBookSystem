package AddressBookSystem;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSVServiceProvider implements AddressBookCSVServiceProviderIF {
    private static AddressBookCSVServiceProvider addressBookCSVServiceProvider;

    private AddressBookCSVServiceProvider() {

    }

    public static AddressBookCSVServiceProvider getIoInstance() {
        addressBookCSVServiceProvider = new AddressBookCSVServiceProvider();
        return addressBookCSVServiceProvider;
    }

    public void writeToCSVFile(AddressBook addressBook, String fileName) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName.concat(".csv")));
            List<String[]> contactsArrayList = new ArrayList();
            for (Contact contact : addressBook.getContactsList()) {
                contactsArrayList.add(getStringArray(contact));
            }
            writer.writeAll(contactsArrayList);
            writer.close();
        } catch (Exception e) {
            throw new IOServiceException(IOServiceException.ExceptionType.ENTERED_WRONG, e.getMessage());
        }

    }

    public void readFromCSVFile(String fileName) {
        try {
            FileReader filereader = new FileReader(fileName + ".csv");
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new IOServiceException(IOServiceException.ExceptionType.ENTERED_WRONG, e.getMessage());
        }
    }

    public String[] getStringArray(Contact contact) {
        String[] stringArray = new String[8];
        stringArray[0] = contact.getFirstName();
        stringArray[1] = contact.getLastName();
        stringArray[2] = contact.getPlace().getCity();
        stringArray[3] = contact.getContactAddress();
        stringArray[4] = contact.getPlace().getState();
        stringArray[5] = contact.getEmail();
        stringArray[6] = String.valueOf(contact.getPlace().getZip());
        stringArray[7] = Long.toString(contact.getPhoneNumber());
        return stringArray;
    }
}
