package com.hendrix.lambdas.csvImport;

public class Participant {
    
	private String firstName;
	private String lastName;
	private String email;

	public String getFirstName() {
        return firstName;
    }	
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getFullName(){
    	return firstName + " " + lastName;
    }
    
    public Participant(String email, String firstName, String lastName) {
    	this.email = email;
    	this.firstName = firstName;
    	this.lastName = lastName;    	
    }
    
    public Participant(String email) {
    	this.email = email;
    }

}
