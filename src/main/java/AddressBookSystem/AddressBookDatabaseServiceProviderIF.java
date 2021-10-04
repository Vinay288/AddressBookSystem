package AddressBookSystem;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public interface AddressBookDatabaseServiceProviderIF {
    public List<Contact> readContacts(String addressBookName);

    public List<Contact> getContactList(ResultSet resultSet);

    public Contact writeAddressBookDB(Contact contact, String addressBookName);

    public void preparedStatementToretriveContactsInRange();

    public List<Contact> readConatctsAddedInRange(Date startDate, Date endDate);

    public void preparedStatementToretriveContactsInGivenCityOrState();

    public List<Contact> readContactsInGivenCityOrState(String city, String state);
}
