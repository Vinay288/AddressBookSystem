package AddressBookSystem;

public interface AddressBookFileServiceProviderIF {
    public void writeToFile(AddressBook addressBook, String fileName);

    public void readFromFile(String fileName);
}
