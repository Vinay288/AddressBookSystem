public class AddressBookService {
    Contact contact;
public Contact getContact(){
    return contact.getContact();
}
public Contact createContact(Integer id, String firstName, String lastName, String address, Long phoneNumber, String email, String city, String state, Integer zip){
    contact=new Contact(id,firstName,lastName,address,phoneNumber,email,city,state,zip);
    return  contact;
}
}
