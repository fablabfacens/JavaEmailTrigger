package model.entities;

import java.util.Objects;

public class EmailMessage{
	
	private final String subject = "EmailSubjectHere";
	private final String from = "mailToSetFrom";
	
	private final String content = "The email content here!";
	
	private String name;
	private String to;
	
	public EmailMessage(String name, String email) {
		this.name = name;
		this.to = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public String getFrom() {
		return from;
	}

	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailMessage other = (EmailMessage) obj;
		return Objects.equals(name, other.name) && Objects.equals(to, other.to);
	}

	@Override
	public String toString() {
		return "From: " + getFrom() +"\nTo: " + getTo() + "\nSubject: " + getSubject()
		+ "\nName: " + getName() + "\n";
	}
	
}
