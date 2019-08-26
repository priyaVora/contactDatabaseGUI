package vora.priya.contactdatabasewithgui;

public interface Storable {
	String serialize();
	Object deserialize(String data);
	int serializedSize();
	
	
}
