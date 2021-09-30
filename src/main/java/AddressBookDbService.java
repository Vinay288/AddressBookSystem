import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddressBookDbService {
    private static AddressBookDbService addressBookDbService;
    private java.sql.PreparedStatement readContactPreparedStatement;
    private PreparedStatement contactAddedGivenRangeStatement;
    private PreparedStatement contactsInGivenCityOrStateStatement;


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

    public Contact writeAddressBookDB(Contact contact, String addressBookName) {
        Contact updatedContact;
        String insertQueryString = "INSERT INTO contact_details values(" + contact.getId() + ",\""
                + contact.getFirstName() + "\",\"" + contact.getLastName() + "\",\"" + contact.getAddress() + "\",\""
                + contact.getEmail() + "\"," + contact.getPlace().getId() + ",\"" + contact.getPhoneNumber()
                + "\",\""+"2020-09-19"+"\")";
        String insertPlaceQueryString = "INSERT into place values(" + contact.getPlace().getId() + ",\""
                + contact.getPlace().getCity() + "\",\"" + contact.getPlace().getZip() + "\",\"" + contact.getPlace().getState() + "\")";
        String insertIntermediateTableString = "insert into addressbook_contact values((select address_book_id from address_book where address_book_name=\""
                + addressBookName + "\")," + contact.getId() + ")";
        Connection connection;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertPlaceQueryString);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DBException(e.getMessage());
            }
            throw new DBException(e.getMessage());
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQueryString);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DBException(e.getMessage());
            }
            throw new DBException(e.getMessage());
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertIntermediateTableString);
            connection.commit();
            updatedContact = contact;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return updatedContact;
    }
    private void preparedStatementToretriveContactsInRange() {
        try {
            Connection connection = this.getConnection();
            String query = "select * from contact_details where date_added between ? and ?";
            contactAddedGivenRangeStatement = connection.prepareStatement(query);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }
    public List<Contact> readConatctsAddedInRange(Date startDate, Date endDate) {
        if (contactAddedGivenRangeStatement == null) {
            this.preparedStatementToretriveContactsInRange();
        }
        try {
            contactAddedGivenRangeStatement.setDate(1, (java.sql.Date) startDate);
            contactAddedGivenRangeStatement.setDate(2, (java.sql.Date) endDate);
            ResultSet resultSet = contactAddedGivenRangeStatement.executeQuery();
            return this.getContactList(resultSet);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

}


