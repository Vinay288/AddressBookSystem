import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
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

    public void readFromFile(String fileName) {
        try {
            Files.lines(Paths.get(fileName.concat(".txt"))).forEach(System.out::println);
        } catch (Exception e) {
            throw new FileIOException(e.getMessage());
        }
    }
    public String[] getStringArray(Contact contact) {
        String[] stringArray = new String[8];
        stringArray[0] = contact.getFirstName();
        stringArray[1] = contact.getLastName();
        stringArray[2] = contact.getPlace().getCity();
        stringArray[3] = contact.getAddress();
        stringArray[4] = contact.getPlace().getState();
        stringArray[5] = contact.getEmail();
        stringArray[6] = String.valueOf(contact.getPlace().getZip());
        stringArray[7] =Long.toString(contact.getPhoneNumber());
        return stringArray;
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
        }  catch (Exception e) {
            throw new FileIOException(e.getMessage());
        }

    }
    public void readFromCSVFile(String fileName){
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
            throw new FileIOException(e.getMessage());
        }
    }
    public void writeToJsonFile(AddressBook addressBook,String fileName){
        try {
            Gson gson = new Gson();
            String json = gson.toJson(addressBook);
            FileWriter writer = new FileWriter(fileName.concat(".json"));
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            throw new FileIOException(e.getMessage());
        }

    }
}
