package com.oshmidt;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

public class GistManager {

	private List<Gist> gists;

	private GistFetcher gistFetcher;

	private GistRepository glfm;

	private User user;

	private Logger managerLogger;

	/** GistManager constructor. Initialize self component. */
	public GistManager() {
		user = new User();
		managerLogger = Logger.getLogger("logfile");
		gistFetcher = new GistFetcher();
		glfm = new GistLocalFileManager();
	}

	/**
	 * Method setting user name and password
	 * 
	 * @param username
	 *            - user name for Github
	 * @param password
	 *            - password for Github
	 */
	public void initUser(String username, String password) {
		user.setLogin(username);
		user.setPassword(password);
	}

	/**
	 * Method tries import user name and password. Used
	 * {@link com.oshmidt.User#importUser()}
	 */
	public void importUser() {
		user.importUser();
	}

	/**
	 * Method load gists from Github and save them to local file system. Used
	 * {@link com.oshmidt.GistManager#loadGists()}
	 * {@link com.oshmidt.GistManager#writeLocalGists()}
	 */
	public void loadAndSaveRemoteGists() {
		loadGists();
		writeLocalGists();
	}

	/**
	 * Method serialize gists to local file system. Used
	 * {@link com.oshmidt.GistLocalFileManager#writeGists(List)}
	 */
	public void writeLocalGists() {
		glfm.writeGists(gists);
	}

	/**
	 * Method deserialize gists from local file system. Used
	 * {@link com.oshmidt.GistLocalFileManager#readGists()}
	 */
	public void readLocalGists() {
		gists = glfm.readGists();
	}

	
	/**
	 * Method download gist files from Github and save them to local file system. Used
	 * {@link com.oshmidt.GistLocalFileManager#writeFiles(Gist)}
	 * @param key - gistId 
	 */
	public void downloadGists(String key) {
		if (key.equals("all")) {
			for (Gist gist : gists) {
				glfm.writeFiles(gist);
			}
		} else {
			glfm.writeFiles(findGist(key));
		}
	}

	
	/**
	 * Method find and return gist by his ID. Method searching in {@link com.oshmidt.GistManager#gists}. Used
	 * @param key - gistId 
	 */
	public Gist findGist(String key) {
		for (Gist gist : gists) {
			if (gist.getId().equals(key)) {
				return gist;
			}
		}
		return null;
	}

	public void showGists() {
		if (gists != null) {
			Messages.getString("com.oshmidt.gistManager.lineSeparator");
			for (Gist gist : gists) {
				showGist(gist);
			}
		} else {
			System.out.println(Messages
					.getString("com.oshmidt.gistManager.noLoadedGists"));
		}
	}

	public void showGist(Gist gist) {
		System.out.println(Messages
				.getString("com.oshmidt.gistManager.lineSeparator"));
		System.out.println(Messages.getString("com.oshmidt.gistManager.gistID")
				+ gist.getId());

		Set<String> sett = gist.getFiles().keySet();
		int i = 0;
		for (String s : sett) {
			GistFile gf = gist.getFiles().get(s);
			i++;
			System.out.println(i + ": " + gf.getFilename());
		}

	}

	public void addNewGist(Gist gist) {
		try {
			gistFetcher.addNewGist(user, gist);
		} catch (IOException e) {
			managerLogger.error(e);
		}
	}

	public void loadGists() {
		try {
			gists = gistFetcher.loadGists(user);
		} catch (IOException e) {
			managerLogger.error(e);
		}
	}

	public Gist loadGist(String gistId) {
		try {
			return gistFetcher.loadGist(gistId, user);
		} catch (IOException e) {
			managerLogger.error(e);
			return null;
		}
	}

	public void updateGist(Gist gist) {
		try {
			gistFetcher.updateGist(user, gist);
		} catch (IOException e) {
			managerLogger.error(e);
		}
	}

	public void deleteGist(String gistId) {
		try {
			gistFetcher.deleteGist(user, gistId);
		} catch (IOException e) {
			managerLogger.error(e);
		}
	}

}
