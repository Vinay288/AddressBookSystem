package AddressBookSystem;

public interface AddressBookCSVServiceProviderIF {
    public void writeToCSVFile(AddressBook addressBook, String fileName);

    public void readFromCSVFile(String fileName);

    public String[] getStringArray(Contact contact);
}
