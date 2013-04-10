package com.oshmidt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;

public class GistManager {

	private static List<Gist> gists;

	private static Scanner scanner;

	public void loadFiles(String login) throws IOException {

		System.out.print(Messages.getString("typeGistID"));
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
					createUserFolder(login);
					createUserFolder(login + "\\" + gist.getId());
					String filename = login + "\\" + gist.getId() + "\\"
							+ gf.getFilename();
					FileOutputStream fos = new FileOutputStream(filename);
					fos.getChannel().transferFrom(rbc, 0, 1 << 24);
					fos.close();
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

	public void uploadFiles(User user) throws IOException {

		System.out.print(Messages.getString("typeGistID"));
		String gistId = scanner.nextLine();

		for (Gist gist : gists) {
			if (gist.getId().equalsIgnoreCase(gistId)) {
				Map<String, GistFile> gistFiles = gist.getFiles();
				Set<String> set = gistFiles.keySet();
				for (String s : set) {
					GistFile gf = gistFiles.get(s);
					String filename = user.getLogin() + "\\" + gist.getId()
							+ "\\" + gf.getFilename();
					String con = readFileAsString(filename);
					if (con != null) {
						gf.setContent(con);
						GistService service = new GistService();
						service.getClient().setCredentials(user.getLogin(),
								new String(user.getPassword()));
						service.updateGist(gist);
						System.out.println(Messages.getString("fileWasUpdated",
								filename));
					} else {
						System.out.println(Messages.getString(
								"localFileNotExist", filename));
					}

				}
			}
		}
	}

	public void loadGists(User user) throws IOException {
		GistService service = new GistService();
		service.getClient().setCredentials(user.getLogin(),
				new String(user.getPassword()));
		if (service.getClient() != null) {
			gists = service.getGists(user.getLogin());
		} else {
			System.out.println(Messages.getString("wrongLoginOrPassword"));
		}

	}

	public void showGists() {
		if (gists != null) {
			printSeparator();
			for (Gist gist : gists) {
				System.out.println(gist);
				System.out.println(Messages.getString("gistID") + gist.getId());
				System.out.println(Messages.getString("description")
						+ gist.getDescription());
			}
			printSeparator();
		} else {
			System.out.println(Messages.getString("noLoadedGists"));
		}
	}

	public String[] readContent() throws IOException {
		System.out.print(Messages.getString("typeFilepath"));
		String filepath = scanner.nextLine();
		String[] a = { filepath, readFileAsString(filepath) };
		return a;
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

	public void createNewGist(User user) throws IOException {
		GistFile file = new GistFile();
		String[] a = readContent();
		String content = a[1];
		if (content != null) {
			file.setContent(content);
			Gist gist = new Gist();
			gist.setDescription(readString(Messages
					.getString("typeGistDescription")));
			gist.setFiles(Collections.singletonMap(a[0], file));
			GistService service = new GistService();
			service.getClient().setCredentials(user.getLogin(),
					new String(user.getPassword()));
			gist = service.createGist(gist);
		}
	}

	public String readString(String message) {
		System.out.print(" " + message);
		return scanner.nextLine();
	}

	private void printSeparator() {
		for (int i = 0; i < 50; i++) {
			System.out.print("-");
		}
	}

}
