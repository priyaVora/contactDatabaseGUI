package vora.priya.contactdatabasewithgui;

import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;


public class Contact_Table_View extends TableView<Contact> {
	ArrayList<Contact> contacts;

	@SuppressWarnings("restriction")
	private ObservableList<Contact> data =
	        FXCollections.observableArrayList(
	              //  new Contact("Priya", "Vora", "prvora89@gmail.com", "prvora89@yahoo.com", "1-415-410-4891", "1-415-989-3434"),
	               // new Contact("Priya", "Vora", "prvora89@gmail.com", "prvora89@yahoo.com", "1-415-410-4891", "1-415-989-3434"),
	                //new Contact("Priya", "Vora", "prvora89@gmail.com", "prvora89@yahoo.com", "1-415-410-4891", "1-415-989-3434"),
	                //new Contact("Priya", "Vora", "prvora89@gmail.com", "prvora89@yahoo.com", "1-415-410-4891", "1-415-989-3434"),
	                //new Contact("Priya", "Vora", "prvora89@gmail.com", "prvora89@yahoo.com", "1-415-410-4891", "1-415-989-3434")
	        );

  
	public ObservableList<Contact> getData() {
		return data;
	}

	public void setData(ObservableList<Contact> data) {
		this.data = data;
	}

	@SuppressWarnings({ "unchecked", "restriction" })
	public Contact_Table_View() {
		this.setEditable(true);
		tableMenuButtonVisibleProperty();
		contacts = new ArrayList<Contact>();
		this.setPrefHeight(350);
	//	this.setPrefWidth(350);

		TableColumn<Contact, String> firstNameCol = makeTableColumn("First Name", "firstName");
		this.setItems(data);
		
		TableColumn<Contact, String> lastNameCol = makeTableColumn("Last Name", "lastName");
		
		TableColumn<Contact, String> ContactNumber = makeTableColumn("Contact Number", "ContactNumber");
		TableColumn<Contact, String> primaryContactNumber = makeTableColumn("Primary", "primaryPhone");
		TableColumn<Contact, String> secondaryContactNumber = makeTableColumn("Secondary", "secondaryPhone");
		

		TableColumn<Contact, String> ContactEmail= makeTableColumn("Contact Email", "ContactEmail");	
		TableColumn<Contact, String> primaryEmailAddress = makeTableColumn("Primary", "primaryEmailAddress");
		TableColumn<Contact, String> secondaryEmailAddress = makeTableColumn("Secondary", "secondaryEmailAddress");
	
		ContactNumber.getColumns().addAll(primaryContactNumber, secondaryContactNumber);
		ContactEmail.getColumns().addAll(primaryEmailAddress, secondaryEmailAddress);
		this.getColumns().setAll(firstNameCol, lastNameCol, ContactNumber, ContactEmail);

	}
	
	@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
	public TableColumn<Contact, String> makeTableColumn(String columnName, String propertyValue) {
		TableColumn<Contact, String> newTableColumn = new TableColumn<Contact, String>(columnName);
		newTableColumn.maxWidthProperty();
		newTableColumn.setResizable(false);
		newTableColumn.setCellFactory(TextFieldTableCell.<Contact>forTableColumn());
		newTableColumn.setCellValueFactory(new PropertyValueFactory(propertyValue));
		
		return newTableColumn; 
	}
	
	
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContactToArrayList(Contact c) {
		this.contacts.add(c);
	}
	
	public void removeContactFromData(Contact c) { 
		this.data.remove(c);
	}
	public ObservableList<Contact> getTeamMembers() {
		return data;
	}
	
	public void addAContactToDataList(Contact newContact) { 
		this.data.add(newContact);
	}

	public void setTeamMembers(ObservableList<Contact> teamMembers) {
		this.data = teamMembers;
	}

}
