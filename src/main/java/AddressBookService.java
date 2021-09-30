import java.util.List;
import java.util.Scanner;

public class AddressBookService {
    Contact contact;
public Contact getContact(){
    return contact.getContact();
}
public Contact getContactfromAddressBook(AddressBook addressBook,String firstName){
    for(Contact contact:addressBook.getContactsList())
        if (contact.getFirstName().equals(firstName)) {
           return  contact;
        }
    return null;
}
public Contact createContact(Contact contact){
    this.contact=contact;
    return  this.contact;
}
public void addContactToAddressBook(AddressBook addressBook,Contact contact){
    addressBook.addContact(contact);
}
public void editContact(Contact contact,int columnIndex,String value){
    switch (columnIndex){
        case 1:contact.setFirstName(value);
                break;
        case 2:contact.setLastName(value);
                break;
        case 3:contact.setAddress(value);
                break;
        case 4: contact.setPhoneNumber(Long.valueOf(value));
                break;
        case 5:contact.setEmail(value);
                break;
        case 6:contact.getPlace().setCity(value);
                break;
        case 7:contact.getPlace().setState(value);
                break;
        case 8:contact.getPlace().setZip(Integer.valueOf(value));
                break;
        default:break;
    }
}
public void editAddressBook(AddressBook addressBook,String contactName,int columnIndex,String value){
    List<Contact> contactList=addressBook.getContactsList();
    for(Contact contact:contactList)
        if (contact.getFirstName().equals(contactName)) {
            System.out.println("ada");
            this.editContact(contact, columnIndex,value);
        }
}
public void deleteContact(AddressBook addressBook,String contactName){
    Contact contact=getContactfromAddressBook(addressBook,contactName);
    addressBook.getContactsList().remove(contact);
}
}
