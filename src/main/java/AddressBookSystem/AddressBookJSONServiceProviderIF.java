package AddressBookSystem;

public interface AddressBookJSONServiceProviderIF {
    public void writeToJsonFile(AddressBook addressBook, String fileName);

    public void readJsonFile(String fileName);
}
