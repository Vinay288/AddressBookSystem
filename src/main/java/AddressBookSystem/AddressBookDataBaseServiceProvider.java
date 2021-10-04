package AddressBookSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddressBookDataBaseServiceProvider implements AddressBookDatabaseServiceProviderIF {
    private static AddressBookDataBaseServiceProvider addressBookDataBaseServiceProvider;
    private PreparedStatement preparedStatement;


    private AddressBookDataBaseServiceProvider() {

    }

    Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("driver loaded ");
        String jdbcURL = "jdbc:mysql://localhost:3306/Address_Book_DB";
        String userName = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
        return connection;
    }

    public static AddressBookDataBaseServiceProvider getIoInstance() {
        addressBookDataBaseServiceProvider = new AddressBookDataBaseServiceProvider();
        return addressBookDataBaseServiceProvider;
    }

    private void preparedStatementToReadContacts() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT c.contact_id,first_name,last_name,city,address,state,zip,phone_number,email from address_book a,contact_details c,addressbook_contact ac,place p  where p.place_id=c.place_id and a.address_book_id=ac.address_book_id and c.contact_id=ac.contact_id and address_book_name=?";
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<Contact> readContacts(String addressBookName) {
        ResultSet resultSet;
        if (preparedStatement == null)
            this.preparedStatementToReadContacts();
        try {
            preparedStatement.setString(1, addressBookName);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return getContactList(resultSet);
    }

    public List<Contact> getContactList(ResultSet resultSet) {
        List<Contact> contactsList = new ArrayList<Contact>();
        try {
            while (resultSet.next()) {
                contactsList.add(new Contact(resultSet.getString("first_name"), resultSet.getString("last_name"),
                        resultSet.getString("address"), resultSet.getLong("phone_number"), resultSet.getString("email"), resultSet.getString("city"), resultSet.getString("state"),
                        resultSet.getInt("zip")));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return contactsList;

    }

    public Contact writeAddressBookDB(Contact contact, String addressBookName) {
        Contact updatedContact;
        String insertQueryString = "INSERT INTO contact_details values(" + contact.getId() + ",\""
                + contact.getFirstName() + "\",\"" + contact.getLastName() + "\",\"" + contact.getContactAddress() + "\",\""
                + contact.getEmail() + "\"," + contact.getPlace().getId() + ",\"" + contact.getPhoneNumber()
                + "\",\"" + "2020-09-19" + "\")";
        String insertPlaceQueryString = "INSERT INTO place values(" + contact.getPlace().getId() + ",\""
                + contact.getPlace().getCity() + "\",\"" + contact.getPlace().getZip() + "\",\"" + contact.getPlace().getState() + "\")";
        String insertIntermediateTableString = "INSERT INTO addressbook_contact values((select address_book_id from address_book where address_book_name=\""
                + addressBookName + "\")," + contact.getId() + ")";
        Connection connection;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertPlaceQueryString);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DatabaseException(e.getMessage());
            }
            throw new DatabaseException(e.getMessage());
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQueryString);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DatabaseException(e.getMessage());
            }
            throw new DatabaseException(e.getMessage());
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertIntermediateTableString);
            connection.commit();
            updatedContact = contact;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
        return updatedContact;
    }

    public void preparedStatementToretriveContactsInRange() {
        try {
            Connection connection = this.getConnection();
            String query = "select * from contact_details where date_added between ? and ?";
            preparedStatement = connection.prepareStatement(query);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Contact> readConatctsAddedInRange(Date startDate, Date endDate) {
        if (preparedStatement == null) {
            this.preparedStatementToretriveContactsInRange();
        }
        try {
            preparedStatement.setDate(1, (java.sql.Date) startDate);
            preparedStatement.setDate(2, (java.sql.Date) endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            return this.getContactList(resultSet);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void preparedStatementToretriveContactsInGivenCityOrState() {
        try {
            Connection connection = this.getConnection();
            String query = "select * from contact_details c ,place p where c.place_id = p.place_id and city =? or state=?";
            preparedStatement = connection.prepareStatement(query);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Contact> readContactsInGivenCityOrState(String city, String state) {
        if (preparedStatement == null) {
            this.preparedStatementToretriveContactsInGivenCityOrState();
        }
        try {
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, state);
            ResultSet resultSet = preparedStatement.executeQuery();
            return this.getContactList(resultSet);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}


