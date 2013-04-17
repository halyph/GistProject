package com.oshmidt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;

public class GistManager {

	private List<Gist> gists;
	
	private GistFetcher gistFetcher = new GistFetcher();
	
	private User user = new User();

	private static final String TYPE_GIST_ID = "com.oshmidt.gistManager.typeGistID";

	private static Scanner scanner = new Scanner(System.in);

	public void loadFiles(String login) throws IOException {

		System.out.print(Messages.getString(TYPE_GIST_ID));
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

		System.out.print(Messages.getString(TYPE_GIST_ID));
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
								user.getPassword());
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


	public void showGists() throws IOException {
		if (gists != null) {
			printSeparator();
			for (Gist gist : gists) {
				printSeparator();
				System.out.println();
				System.out.println( );
				Set<String> sett = gist.getFiles().keySet();
				for (String s : sett) {
					GistFile gf = gist.getFiles().get(s);
					System.out.println(gf.getRawUrl());
				}
			}	
		} else {
			System.out.println(Messages.getString("com.oshmidt.gistManager.noLoadedGists"));
		}
	}

	public String[] readContent() throws IOException {
		System.out.print(Messages.getString("com.oshmidt.gistManager.typeFilepath"));
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


	public String readString(String message) {
		System.out.print(" " + message);
		return scanner.nextLine();
	}

	private void printSeparator() {
		for (int i = 0; i < 50; i++) {
			System.out.print("-");
		}
	}
	
	public void addNewGist(Gist gist){
		try {
			gistFetcher.addNewGist(user, gist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadGists(){
		try {
			gists = gistFetcher.loadGists(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Gist loadGist(String gistId){
		try {
			return gistFetcher.loadGist(gistId, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateGist(Gist gist) {
		try {
			gistFetcher.updateGist(user, gist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void deleteGist(String gistId) {
		try {
			gistFetcher.deleteGist(user, gistId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		


}
