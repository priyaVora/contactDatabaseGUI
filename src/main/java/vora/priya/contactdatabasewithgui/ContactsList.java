package vora.priya.contactdatabasewithgui;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ContactsList extends Application {
	ArrayList<Contact> filteredArraylist = new ArrayList<>();
	Contact_Table_View tableView = new Contact_Table_View();
	String pathFile = "Contact_DataBase";
	ContactDatabase cd = null;
	int base = 0;
	int endOFTable = 100;
	@SuppressWarnings("restriction")
	@Override
	public void start(final Stage primaryStage) throws Exception {
		
		cd = new ContactDatabase(pathFile);
		final GridPane grid = new GridPane();
		grid.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
		tableView.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
		
		ArrayList<Contact> listOfContactsOnPage = new ArrayList<>();
		for (int i = base; i < endOFTable ; i++) {
			Contact newContact = cd.read(i);
			listOfContactsOnPage.add(newContact);
			System.out.println(newContact.toString());
			//cd.insert(newContact);
			tableView.getData().add(newContact);
		}
	
		MenuBar menuBar = makeMenuForTableViewScene(primaryStage, grid, tableView);
		
		final Button nextPage = new Button("Next");
		final Button previousPage = new Button("Previous");

		nextPage.setPrefHeight(10);
		nextPage.setPrefWidth(70);
		
		nextPage.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				base = endOFTable;
				endOFTable = endOFTable + 100;
				GridPane grid1 = new GridPane();
				Contact_Table_View tableView1 = new Contact_Table_View();
				tableView1.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
				grid1.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
				
				MenuBar menuBar = makeMenuForTableViewScene(primaryStage, grid1, tableView1);
				ArrayList<Contact> listOfContactsOnPage = new ArrayList<>();
				for (int i = base; i < endOFTable ; i++) {
					Contact newContact = null;
					try {
						newContact = cd.read(i);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listOfContactsOnPage.add(newContact);
					System.out.println((i+1) + newContact.toString());
					//cd.insert(newContact);
					tableView1.getData().add(newContact);
				}
				grid1.add(tableView1, 0, 1);
				grid1.add(menuBar, 0, 0);
				
				grid1.add(nextPage, 0, 4);
				grid1.add(previousPage, 0, 3);
				
				Group group = new Group(grid1);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);
				
			}
		});
		
		grid.add(nextPage, 0, 3);
		previousPage.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				endOFTable = base;
				base = base - 100;
				
				GridPane grid1 = new GridPane();
				Contact_Table_View tableView1 = new Contact_Table_View();
				tableView1.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
				grid1.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));
				
				MenuBar menuBar = makeMenuForTableViewScene(primaryStage, grid1, tableView1);
				ArrayList<Contact> listOfContactsOnPage = new ArrayList<>();
				for (int i = base; i < endOFTable ; i++) {
					Contact newContact = null;
					try {
						newContact = cd.read(i);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listOfContactsOnPage.add(newContact);
					System.out.println((i+1) + newContact.toString());
					//cd.insert(newContact);
					tableView1.getData().add(newContact);
				}
				grid1.add(tableView1, 0, 1);
				grid1.add(menuBar, 0, 0);
				
				grid1.add(nextPage, 0, 4);
				grid1.add(previousPage, 0, 3);
				
				Group group = new Group(grid1);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);
				
			}
		});
		
		
		
		final TextField filterField = new TextField();
		filterField.setPromptText("Enter Filter Here...");
		grid.add(filterField, 0, 4);
		
		Button filterButton = new Button("Filter");
		filterButton.setPrefHeight(10);
		filterButton.setPrefWidth(50);
		filterButton.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("null")
			@Override
			public void handle(ActionEvent event) {
									
					List<Contact> returnList = null;
					try {
						returnList = cd.searchByFirstName(filterField.getText().trim());
						for (Contact result : returnList) {
							filteredArraylist.add(result);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println(returnList);
					List<Contact> returnListLastName = null;
					try {
						returnListLastName = cd.searchByLastName(filterField.getText().trim());
						for (Contact result : returnListLastName) {
							filteredArraylist.add(result);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println(returnList);
				
					List<Contact> returnListPrimaryEmail = null;
					try {
						returnListPrimaryEmail = cd.searchByPrimaryEmail(filterField.getText().trim());
						for (Contact result : returnListPrimaryEmail) {
							filteredArraylist.add(result);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Contact_Table_View tableView2 = new Contact_Table_View();
					
					for (Contact contact : filteredArraylist) {
						tableView2.getData().add(contact);
					}
					
					GridPane gride = new GridPane();
					gride.add(tableView2, 0,1);
					Group group = new Group(gride);
					Scene scene = new Scene(group);
					primaryStage.setScene(scene);
					
					
				
			}
		});
		
		
		
		grid.add(filterButton, 0, 5);
		
		
		
		previousPage.setPrefHeight(10);
		previousPage.setPrefWidth(70);
		grid.add(previousPage, 0, 2);
		
		grid.setAlignment(Pos.CENTER);
		grid.add(menuBar, 0, 0);
		grid.add(tableView, 0, 1);
		grid.setHgap(30);
		grid.setVgap(10);

		Group group = new Group(grid);

		Scene scene = new Scene(group);
		primaryStage.setTitle("Contact List");
		primaryStage.setScene(scene);
		// primaryStage.setResizable(false);
		primaryStage.show();

	}

	public Contact_Table_View getTableView() {
		return tableView;
	}

	public void setTableView(Contact_Table_View tableView) {
		this.tableView = tableView;
	}

	public Contact makeAContact(String firstName, String lastName, String primaryEmail, String secondaryEmail,
			String primaryPhoneNumber, String secondaryPhoneNumber) {
		Contact newContact = new Contact(firstName, lastName, primaryEmail, secondaryEmail, primaryPhoneNumber,
				secondaryPhoneNumber);
		return newContact;
	}

	@SuppressWarnings("restriction")
	public MenuBar makeMenuForTableViewScene(final Stage primaryStage, GridPane grid, final Contact_Table_View tableView) {

		final Menu fileMenu = new Menu("File");
		final Menu editMenu = new Menu("Edit");
		final Menu helpMenu = new Menu("Help");

		MenuItem addContact = new MenuItem("Create New Contact");
		fileMenu.getItems().add(addContact);
		addContact.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				GridPane pane = makeGridForAddContacts(primaryStage, tableView);
				Group group = new Group(pane);
				Scene scene = new Scene(group);
				primaryStage.setScene(scene);

			}

		});
		MenuItem deleteAContact = new MenuItem("Delete A Contact");
		fileMenu.getItems().add(deleteAContact);
		deleteAContact.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Contact selectedItem = tableView.getSelectionModel().getSelectedItem();
				tableView.getItems().remove(selectedItem);
			}
		});

		MenuItem clearTableView = new MenuItem("Clear Contact List");
		fileMenu.getItems().add(clearTableView);

		MenuItem editContent = new MenuItem("Edit Mode On");
		editMenu.getItems().add(editContent);
		editContent.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(true);
			}
		});

		MenuItem editContentoff = new MenuItem("Edit Mode Off");
		editMenu.getItems().add(editContentoff);
		editContentoff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(false);
			}
		});
		
		MenuItem filterModeOn = new MenuItem("Filter Mode On");
		editMenu.getItems().add(filterModeOn);
		filterModeOn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(false);
			}
		});
		MenuBar menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
		grid.add(menuBar, 0, 0);
		grid.setAlignment(Pos.CENTER);
		Group group = new Group(grid);
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);

		MenuBar menuBar1 = new MenuBar(fileMenu, editMenu, helpMenu);
		return menuBar1;
	}

	@SuppressWarnings("restriction")
	public MenuBar makeMenuForAddContactScene(final Stage primaryStage, GridPane grid, final Contact_Table_View tableView) {

		final Menu fileMenu = new Menu("File");
		final Menu editMenu = new Menu("Edit");
		final Menu helpMenu = new Menu("Help");

		MenuItem viewContacts = new MenuItem("View Contacts");
		fileMenu.getItems().add(viewContacts);
		
		viewContacts.setOnAction(new EventHandler<ActionEvent>() {

			 public void handle(ActionEvent event) {
			 GridPane grid = new GridPane();

			 grid.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169,
			 116), null, null)));

			 tableView.setBackground(
			 new Background(new BackgroundFill(Color.rgb(250, 169, 116), null, null)));

			 grid.setAlignment(Pos.CENTER);
			 grid.add(makeMenuForTableViewScene(primaryStage, grid, tableView), 0, 0);
			 grid.add(tableView, 0, 1);

			 Group group = new Group(grid);
			 Scene scene = new Scene(group);
			 primaryStage.setScene(scene);

			 }

			 });

		MenuItem clearTableView = new MenuItem("Clear Contact List");
		fileMenu.getItems().add(clearTableView);

		MenuItem editContent = new MenuItem("Edit Mode On");
		editMenu.getItems().add(editContent);
		editContent.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(true);
			}
		});

		MenuItem editContentoff = new MenuItem("Edit Mode Off");
		editMenu.getItems().add(editContentoff);
		editContentoff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tableView.setEditable(false);
			}
		});

		MenuBar menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
		grid.add(menuBar, 0, 0);
		grid.setAlignment(Pos.CENTER);
		Group group = new Group(grid);
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);

		MenuBar menuBar1 = new MenuBar(fileMenu, editMenu, helpMenu);
		return menuBar1;
	}

	public void putAContactOnTableView(Contact_Table_View tableView, Contact theContact) {
		tableView.addContactToArrayList(theContact);

	}
	
	@SuppressWarnings("restriction")
	public GridPane makeGridForAddContacts(final Stage primaryStage, final Contact_Table_View tableView) { 
		GridPane grid = new GridPane();
		
		 Label firstNameLabel = new Label(" First Name : ");
		 firstNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		 GridPane.setConstraints(firstNameLabel, 0, 1);
		 firstNameLabel.setPrefHeight(30);
		 firstNameLabel.setPrefWidth(150);
		 grid.add(firstNameLabel, 0, 1);
		
		 final TextField firstNameTextField = new TextField();
		 firstNameTextField.setPromptText("First Name...");
		 GridPane.setConstraints(firstNameTextField, 1, 1);
		 grid.add(firstNameTextField, 1, 1);
	
		 Label lastNameLabel = new Label(" Last Name : ");
		 lastNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		 GridPane.setConstraints(lastNameLabel, 0, 2);
		 lastNameLabel.setPrefHeight(30);
		 lastNameLabel.setPrefWidth(150);
		 grid.add(lastNameLabel, 0, 2);
		
		 final TextField lastNameTextField = new TextField();
		 lastNameTextField.setPromptText("Last Name...");
		 GridPane.setConstraints(lastNameTextField, 1, 2);
		 grid.add(lastNameTextField, 1, 2);
		
		 Label primaryEmailAddressLabel = new Label(" Primary Address : ");
		 primaryEmailAddressLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		 GridPane.setConstraints(primaryEmailAddressLabel, 0, 3);
		 primaryEmailAddressLabel.setPrefHeight(30);
		 primaryEmailAddressLabel.setPrefWidth(150);
		 grid.add(primaryEmailAddressLabel, 0, 3);
		
		 final TextField primaryEmailTextField = new TextField();
		 primaryEmailTextField.setPromptText("Primary Email Address...");
		 GridPane.setConstraints(primaryEmailTextField, 1, 3);
		 grid.add(primaryEmailTextField, 1, 3);
		
		 Label secondaryEmailAddressLabel = new Label(" Secondary Address : ");
		 secondaryEmailAddressLabel.setFont(Font.font("Verdana", FontWeight.BOLD,
		 11));
		 GridPane.setConstraints(secondaryEmailAddressLabel, 0, 4);
		 secondaryEmailAddressLabel.setPrefHeight(30);
		 secondaryEmailAddressLabel.setPrefWidth(150);
		 grid.add(secondaryEmailAddressLabel, 0, 4);
		
		 final TextField secondaryEmailTextField = new TextField();
		 secondaryEmailTextField.setPromptText("Secondary Email Address...");
		 GridPane.setConstraints(secondaryEmailTextField, 1, 4);
		 grid.add(secondaryEmailTextField, 1, 4);
		
		 Label primaryPhoneLabel = new Label(" Primary Phone : ");
		 primaryPhoneLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		 GridPane.setConstraints(primaryPhoneLabel, 0, 5, 2, 1);
		 primaryPhoneLabel.setPrefHeight(30);
		 primaryPhoneLabel.setPrefWidth(150);
		 grid.add(primaryPhoneLabel, 0, 5);
		
		 final TextField primaryPhoneNumberTextField = new TextField();
		 primaryPhoneNumberTextField.setPromptText("Primary Phone Number...");
		 GridPane.setConstraints(primaryPhoneNumberTextField, 1, 5);
		 grid.add(primaryPhoneNumberTextField, 1, 5);
		
		 Label secondaryPhoneLabel = new Label(" Secondary Phone : ");
		 secondaryPhoneLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		 GridPane.setConstraints(secondaryPhoneLabel, 0, 6);
		 secondaryPhoneLabel.setPrefHeight(30);
		 secondaryPhoneLabel.setPrefWidth(150);
		 grid.add(secondaryPhoneLabel, 0, 6);
		
		 final TextField secondaryPhoneNumberTextField = new TextField();
		 secondaryPhoneNumberTextField.setPromptText("Secondary Phone Number...");
		 GridPane.setConstraints(secondaryPhoneNumberTextField, 1, 6);
		 grid.add(secondaryPhoneNumberTextField, 1, 6);
		
		 Button AddContact = new Button("Add");
		 AddContact.setPrefHeight(10);
		 AddContact.setPrefWidth(130);
		
		 AddContact.setAlignment(Pos.TOP_CENTER);
		 AddContact.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
					//tableView.getData().add(new Contact(firstNameTextField.getText().trim(), lastNameTextField.getText().trim(), primaryEmailTextField.getText().trim(), secondaryEmailTextField.getText().trim(), primaryPhoneNumberTextField.getText().trim(), secondaryPhoneNumberTextField.getText().trim()));
			try {
				cd.insert(new Contact(firstNameTextField.getText().trim(), lastNameTextField.getText().trim(), primaryEmailTextField.getText().trim(), secondaryEmailTextField.getText().trim(), primaryPhoneNumberTextField.getText().trim(), secondaryPhoneNumberTextField.getText().trim()));
				System.out.println("Added Contact To File...");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		});
		
		 grid.add(AddContact, 1, 8);
		
		 grid.setBackground(new Background(new BackgroundFill(Color.rgb(250, 169,
		 116), null, null)));
		
		 grid.setHgap(40);
		 grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		
		grid.add(makeMenuForAddContactScene(primaryStage, grid, tableView), 0, 0);
		return grid;
	}
	
	

	
}
