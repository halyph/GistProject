package com.oshmidt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

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
			app.createNewGist();
		} else if (command.toLowerCase().equals("loadgists")) {
			app.loadGists();
		} else if (command.toLowerCase().equals("showgists")) {
			app.showGists();
		} else if (command.toLowerCase().equals("savelp")) {
			app.saveLP();
		} else if (command.toLowerCase().equals("loadlp")) {
			app.loadLP();
		} else if (command.toLowerCase().equals("loadfiles")) {
			app.loadFiles();
		} else if (command.toLowerCase().equals("uploadfiles")) {
			app.uploadFiles();
		} else if (command.equals("") || command.equals("help")) {
			app.showHelp();
		} else {
			System.out.println("Unknown command, type help");
			System.out.println("");
		}
	}

	public void loadFiles() throws IOException {
		//app.cheatPrepare();

		System.out.print("  Type gist id: ");
		String gid = scanner.nextLine();

		for (Gist gist : gists) {
			if (gist.getId().equalsIgnoreCase(gid)) {
				Map<String, GistFile> gistFiles = gist.getFiles();
				Set<String> set = gistFiles.keySet();
				for (String s : set) {
					GistFile gf = gistFiles.get(s);
					System.out.println(gf.getRawUrl());
					URL website = new URL(gf.getRawUrl());
					ReadableByteChannel rbc = Channels.newChannel(website
							.openStream());
					app.createUserFolder(login);
					app.createUserFolder(login + "\\" + gist.getId());
					String filename = login + "\\" + gist.getId() + "\\"
							+ gf.getFilename();
					FileOutputStream fos = new FileOutputStream(filename);
					fos.getChannel().transferFrom(rbc, 0, 1 << 24);
					fos.close();
				}
			}
		}
	}

	public void uploadFiles() throws IOException {
		//app.cheatPrepare();

		System.out.print("  Type gist id: ");
		String gistId = scanner.nextLine();

		for (Gist gist : gists) {
			if (gist.getId().equalsIgnoreCase(gistId)) {
				Map<String, GistFile> gistFiles = gist.getFiles();
				Set<String> set = gistFiles.keySet();
				for (String s : set) {
					GistFile gf = gistFiles.get(s);
					String filename = login + "\\" + gist.getId() + "\\"
							+ gf.getFilename();
					String con = app.readFileAsString(filename);
					if (con != null) {
						gf.setContent(con);
						GistService service = new GistService();
						service.getClient().setCredentials(login,
								new String(password));
						service.updateGist(gist);
						System.out
								.println("file: " + filename + " was updated");
					} else {
						System.out.println("local file: " + filename
								+ " not exist");
					}

				}
			}
		}
	}

	public Boolean createUserFolder(String dir) {
		File theDir = new File(dir);
		if (!theDir.exists()) {
			return theDir.mkdir();
		}
		return true;
	}

	public void saveLP() {
		Properties prop = new Properties();
		try {
			if (login != null) {
				prop.setProperty("login", login);
			}
			if (password != null) {
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
		service.getClient().setCredentials(login, new String(password));
		if (service.getClient() != null) {
			gists = service.getGists(login);
		} else {
			System.out.println("wrong login or password");
		}

	}

	public void showGists() {
		if (gists != null) {
			printSeparator();
			for (Gist gist : gists) {
				System.out.println();
				System.out.println("Gist ID = " + gist.getId());
				System.out.println("  Description: " + gist.getDescription());
			}
			printSeparator();
		} else {
			System.out.println("there is no loaded gists");
		}
	}

	public void createNewGist() throws IOException {
		GistFile file = new GistFile();
		String [] a = app.getContent();
		String content = a[1];
		if(content != null){
			file.setContent(content);
			Gist gist = new Gist();
			gist.setDescription(getString("type gist description"));
			gist.setFiles(Collections.singletonMap(a[0], file));
			GistService service = new GistService();
			service.getClient().setCredentials(login, new String(password));
			gist = service.createGist(gist);			
		}

	}
	
	public String getString(String message){
		System.out.print(" " + message);
		return scanner.nextLine();
	}
	

	public String [] getContent() throws IOException{
		System.out.print("  Type filepath: ");
		String filepath = scanner.nextLine();
		String [] a = {filepath, readFileAsString(filepath)};
		return  a;
	}
	
	public void cheatPrepare() throws IOException {
		loadLP();
		loadGists();
		showGists();
	}

	private String readFileAsString(String filePath) throws java.io.IOException {
		if (new File(filePath).exists()) {
			StringBuffer fileData = new StringBuffer(1000);
			FileReader fr = new FileReader(filePath);
			BufferedReader reader = new BufferedReader(fr);
			char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
			reader.close();
			return fileData.toString();
		}
		return null;

	}

	private void printSeparator() {
		for (int i = 0; i < 50; i++) {
			System.out.print("-");
		}
	}

}
