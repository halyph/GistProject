package com.oshmidt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class App {

	private static Scanner scanner = new Scanner(System.in);

	private User user = new User();

	private static App app;

	private static final String CONFIG_FILE_NAME = "config.properties";

	private static GistManager gistManager = new GistManager();

	public static void main(String[] args) throws IOException {
		// resource = ResourceBundle.getBundle("strings", Locale.getDefault());
		app = new App();
		// user = new User();

		while (true) {
			System.out.println("");
			System.out.print(Messages.getString("com.oshmidt.gistManager.typeCommand"));
			String command = scanner.nextLine();
			app.doCommand(command);
		}
	}

	public void doCommand(String command) throws IOException {
		if (command.equals("login")) {
			readLogin();
		} else if (command.equals("exit")) {
			System.out.println(Messages.getString("com.oshmidt.gistManager.leaveMessage"));
			System.exit(0);
		} else if (command.equals("status")) {
			System.out.println(Messages.getString("com.oshmidt.gistManager.login") + user.getLogin());
			System.out.println(Messages.getString("com.oshmidt.gistManager.pass") + user.getPassword());
		} else if (command.toLowerCase().equals("creategist")) {
			gistManager.createNewGist(user);
		} else if (command.toLowerCase().equals("loadgists")) {
			gistManager.loadGists(user);
		} else if (command.toLowerCase().equals("showgists")) {
			gistManager.showGists();
		} else if (command.toLowerCase().equals("savelp")) {
			saveLoginAndPassword();
		} else if (command.toLowerCase().equals("loadlp")) {
			loadLoginAdnPassword();
		} else if (command.toLowerCase().equals("loadfiles")) {
			gistManager.loadFiles(user.getLogin());
		} else if (command.toLowerCase().equals("uploadfiles")) {
			gistManager.uploadFiles(user);
		} else if (command.equals("") || command.equals("help")) {
			showHelp();
		} else {
			System.out.println(Messages.getString("com.oshmidt.gistManager.unknownCommand"));
			System.out.println();
		}
	}

	public void saveLoginAndPassword() {
		Properties prop = new Properties();
		try {
			if (user.getLogin() != null) {
				prop.setProperty("login", user.getLogin());
			}
			if (user.getPassword() != null) {
				prop.setProperty("password", user.getPassword());
			}
			prop.store(new FileOutputStream(CONFIG_FILE_NAME), null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void loadLoginAdnPassword() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIG_FILE_NAME));
			user.setLogin(prop.getProperty("login"));
			user.setPassword(prop.getProperty("password"));

			System.out.println(user.getLogin());
			System.out.println(user.getPassword());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readLogin() {
		System.out.print(Messages.getString("com.oshmidt.gistManager.typeLogin"));
		user.setLogin(scanner.nextLine());
		readPassword();
	}

	public void readPassword() {
		System.out.print(Messages.getString("com.oshmidt.gistManager.typePassword"));
		user.setPassword(scanner.nextLine());
	}

	public void showHelp() {
		System.out.println(Messages.getString("com.oshmidt.gistManager.commandList"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.statusDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.loginDescription"));
		System.out
				.println(Messages.getString("com.oshmidt.gistManager.uploadLoginPasswordDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.loadLoginPasswordDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.loadGistsDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.showGistsDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.createGistDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.loadFilesDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.uploadFilesDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.exit"));
	}

}