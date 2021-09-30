import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDbService {
    private static AddressBookDbService addressBookDbService;
    private java.sql.PreparedStatement readContactPreparedStatement;

    private AddressBookDbService() {

    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("driver loaded ");
        String jdbcURL = "jdbc:mysql://localhost:3306/Address_Book_DB";
        String userName = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
        return connection;
    }

    public static AddressBookDbService getIoInstance() {
        addressBookDbService = new AddressBookDbService();
        return addressBookDbService;
    }

    private void preparedStatementToReadContacts() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT c.contact_id,first_name,last_name,city,address,state,zip,phone_number,email from address_book a,contact_details c,addressbook_contact ac,place p  where p.place_id=c.place_id and a.address_book_id=ac.address_book_id and c.contact_id=ac.contact_id and address_book_name=?";
            readContactPreparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }


    public List<Contact> readContacts(String addressBookName) {
        ResultSet resultSet;
        if (readContactPreparedStatement == null)
            this.preparedStatementToReadContacts();
        try {
            readContactPreparedStatement.setString(1, addressBookName);
            resultSet = readContactPreparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return getContactList(resultSet);
    }

    public List<Contact> getContactList(ResultSet resultSet) {
        List<Contact> contactsList = new ArrayList<Contact>();
        try {
            while (resultSet.next()) {
                contactsList.add(new Contact(resultSet.getInt("contact_id"), resultSet.getString("first_name"), resultSet.getString("last_name"),
                        resultSet.getString("address"), resultSet.getLong("phone_number"), resultSet.getString("email"), resultSet.getString("city"), resultSet.getString("state"),
                        resultSet.getInt("zip")));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return contactsList;

    }
}

