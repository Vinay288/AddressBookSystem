public class AddressBookService {
    Contact contact;
public Contact getContact(){
    return contact.getContact();
}
public Contact createContact(Contact contact){
    this.contact=contact;
    return  this.contact;
}
public void addContactToAddressBook(AddressBook addressBook,Contact contact){
    addressBook.addContact(contact);
}
}
