package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.sendemail.MailService;
import com.sendemail.OutlookMailService;

import model.entities.EmailMessage;

public class Program {

	public static void main(String[] Args) {
		
		final String hostEmail = "yourEmail"; //your mail and your pass to acess Outlook services
		final String hostPassword = "yourPassword";
		
		MailService sm = new OutlookMailService(); //starts a new MailService using Outlook.
		//you can change the service.. by creating a new class that implements the MailService class
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the file path: ");
		String path = sc.nextLine();
		
		Set<EmailMessage> set = new HashSet<>(); //hashset prevents the repetition
		
		System.out.println("Reading the file...");
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			
			while(line != null) {
				String[] fields = line.split(";");
				String name = fields[0];
				String email = fields[1];
				set.add(new EmailMessage(name, email));
				line = br.readLine(); //colects all data from a file
			}
			
			System.out.println("The file has been read!");

			sm.sendMail(set, hostEmail, hostPassword);
			
		}
		catch(IOException e) {
			System.out.println("Error opening the file: " + e.getMessage());
			e.getStackTrace();
		}
		
		sc.close();
		
	}
		
}

