package com.ss;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;

public class App {

	private static Scanner scanner;

	private static String login;

	private static char[] password;

	private static App app;

	private static List<Gist> gists;

	public static void main(String[] args) throws IOException {
		app = new App();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("");
			System.out.print("Type command: ");
			String command = scanner.nextLine();

			app.doCommand(command);
		}
	}

	public void doCommand(String command) throws IOException {
		if (command.equals("login")) {
			app.getLogin();
		} else if (command.equals("exit")) {
			System.out.println("bye bye");
			System.exit(0);
		} else if (command.equals("status")) {
			System.out.println("	login = " + login);
			System.out.println("	pass = " + new String(password));
		} else if (command.toLowerCase().equals("creategist")) {
			app.createNewGists();
		} else if (command.toLowerCase().equals("loadgists")) {
			app.loadGists();
		} else if (command.toLowerCase().equals("showGists")) {
			app.showGists();
		} else if (command.toLowerCase().equals("savelp")) {
			app.saveLP();
		} else if (command.toLowerCase().equals("loadlp")) {
			app.loadLP();
		} else if (command.equals("") || command.equals("help")) {
			app.showHelp();
		} else {
			System.out.println("Unknown command, type help");
			System.out.println("");
		}
	}

	public void saveLP() {
		Properties prop = new Properties();
		try {
			if(login != null){
				prop.setProperty("login", login);
			}
			if(password != null){
				prop.setProperty("password", new String(password));
			}
			prop.store(new FileOutputStream("config.properties"), null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void loadLP() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
			login = prop.getProperty("login");
			password = prop.getProperty("password").toCharArray();
			
			System.out.println(login);
			System.out.println(password);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void getLogin() {
		System.out.print("Type Login: ");
		login = scanner.nextLine();
		getPassword();
	}

	public static void getPassword() {
		System.out.print("Type Password: ");
		password = scanner.nextLine().toCharArray();
	}

	public void showHelp() {
		System.out.println("Suppoted commands:");
		System.out.println(" status - show current user");
		System.out.println(" login - set new login/password");
		System.out.println(" saveLP - saving login/passwort to property file");
		System.out.println(" loadLP - loading login/password to property file");
		System.out.println(" loadGists - loading gists for current user");
		System.out.println(" showGists - show loaded gists");
		System.out.println(" exit");
	}

	public void loadGists() throws IOException {
		GistService service = new GistService();
		gists = service.getGists(login);
	}

	public void showGists() {
		if (gists != null) {
			for (Gist gist : gists) {
				System.out.println(gist.getId() + " " + gist.getDescription());
			}
		} else {
			System.out.println("there is no loaded gists");
		}
	}

	public void createNewGists() throws IOException {
		GistFile file = new GistFile();
		file.setContent("System.out.println(\"Hello World\");");
		Gist gist = new Gist();
		gist.setDescription("Prints a string to standard out");
		gist.setFiles(Collections.singletonMap("Hello.java", file));
		GistService service = new GistService();
		service.getClient().setCredentials(login, new String(password));
		gist = service.createGist(gist);
	}

}
