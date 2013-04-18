package com.oshmidt;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

public class App {

	private static Scanner scanner = new Scanner(System.in);

	private User user = new User();

	// private static App app;

	private static final String CONFIG_FILE_NAME = "config.properties";

	private static GistManager gistManager;

	public static void main(String[] args) throws IOException,
			org.apache.commons.cli.ParseException, ClassNotFoundException {

		gistManager = new GistManager(args);

		/*
		 * app = new App(); // user = new User();
		 * 
		 * while (true) { System.out.println(""); System.out.print(Messages
		 * .getString("com.oshmidt.gistManager.typeCommand")); String command =
		 * scanner.nextLine(); app.doCommand(command); }
		 */

	}

	public void doCommand(String command) throws IOException {
		if (command.equals("login")) {
			readLogin();
		} else if (command.equals("exit")) {
			System.out.println(Messages
					.getString("com.oshmidt.gistManager.leaveMessage"));
			System.exit(0);
		} else if (command.equals("status")) {
			System.out.println(Messages
					.getString("com.oshmidt.gistManager.login")
					+ user.getLogin());
			System.out.println(Messages
					.getString("com.oshmidt.gistManager.pass")
					+ user.getPassword());
		} /*
		 * else if (command.toLowerCase().equals("creategist")) {
		 * gistManager.createNewGist(user); }
		 */else if (command.toLowerCase().equals("loadgists")) {
			System.out.println("load User");
			loadLoginAdnPassword();
			System.out.println("fetching");
			GistFetcher gf = new GistFetcher();
			List<Gist> gs = gf.loadGists(user);
			System.out.println("output to display");
			showGists(gs);
			System.out.println("serialization");
			GistRepository glfm = new GistLocalFileManager();
			glfm.writeGists(gs);
			// gistManager.loadGists();
		} else if (command.toLowerCase().equals("showgists")) {
			GistFetcher gf = new GistFetcher();
			gf.loadGists(user);
			// gistManager.showGists();
		} else if (command.toLowerCase().equals("savelp")) {
			GistRepository glfm = new GistLocalFileManager();
			for (Gist gist : glfm.readGists()) {
				glfm.writeFiles(gist);
			}

			/* saveLoginAndPassword(); */
		} else if (command.toLowerCase().equals("loadlp")) {
			loadLoginAdnPassword();
		} else if (command.toLowerCase().equals("loadfiles")) {
			gistManager.loadFiles(user.getLogin());
		} else if (command.toLowerCase().equals("uploadfiles")) {
			gistManager.uploadFiles(user);
		} else if (command.equals("") || command.equals("help")) {
			showHelp();
		} else {
			System.out.println(Messages
					.getString("com.oshmidt.gistManager.unknownCommand"));
			System.out.println();
		}
	}

	public void showGists(List<Gist> gists) throws IOException {
		if (gists != null) {
			System.out
					.println("----------------------------------------------");
			for (Gist gist : gists) {
				System.out
						.println("----------------------------------------------");
				System.out.println();
				System.out.println();
				Set<String> sett = gist.getFiles().keySet();
				for (String s : sett) {
					GistFile gf = gist.getFiles().get(s);
					System.out.println(gf.getRawUrl());
				}
			}
		} else {
			System.out.println(Messages
					.getString("com.oshmidt.gistManager.noLoadedGists"));
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
		System.out.print(Messages
				.getString("com.oshmidt.gistManager.typeLogin"));
		user.setLogin(scanner.nextLine());
		readPassword();
	}

	public void readPassword() {
		System.out.print(Messages
				.getString("com.oshmidt.gistManager.typePassword"));
		user.setPassword(scanner.nextLine());
	}

	public void showHelp() {
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.commandList"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.statusDescription"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.loginDescription"));
		System.out
				.println(Messages
						.getString("com.oshmidt.gistManager.uploadLoginPasswordDescription"));
		System.out
				.println(Messages
						.getString("com.oshmidt.gistManager.loadLoginPasswordDescription"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.loadGistsDescription"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.showGistsDescription"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.createGistDescription"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.loadFilesDescription"));
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.uploadFilesDescription"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.exit"));
	}

}