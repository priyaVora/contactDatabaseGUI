package vora.priya.contactdatabasewithgui;

import java.io.IOException;
import java.util.ArrayList;

public class Contact_Data_Base_GUI_Tester {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Contact_Data_Base_GUI_Tester t = new Contact_Data_Base_GUI_Tester();
		t.run(args);
	}
	
	public void run(String[] args) throws ClassNotFoundException, IOException { 
		ContactsList.launch(ContactsList.class, args);
		//ContactsList list = new ContactsList(args);
		//System.out.println("Start...");
		//ImportContacts theImport = new ImportContacts();

		
		//Contact newContact = cd.read(0);
		//System.out.println(newContact.toString());
		
		//theImport.makeContactToDatabase();
		
		
		//System.out.println("Finish...");
	}
}
