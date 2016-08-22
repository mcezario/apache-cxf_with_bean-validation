package org.poc.cxf.beanvalidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "person")
@XmlType(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	
	@NotNull
	@Size(min = 3, max = 4, message = "Tamanho de 3 a 4")
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	private String email;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
