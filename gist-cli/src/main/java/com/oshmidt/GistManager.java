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

	public Logger managerLogger;

	public GistManager() {
		user = new User();
		managerLogger = Logger.getLogger("logfile");
		gistFetcher = new GistFetcher();
		glfm = new GistLocalFileManager();
	}

	public void initUser(String username, String password) {
		user.setLogin(username);
		user.setPassword(password);
	}

	public void importUser() {
		user.importUser();
	}
	
	public void loadAndSaveRemoteGists(){
		loadGists();
		writeLocalGists();
	}

	public void writeLocalGists() {
		glfm.writeGists(gists);
	}

	public void readLocalGists() {
		gists = glfm.readGists();
	}
	
	public void downloadGists(String key){
		if (key.equals("all")) {
			for (Gist gist : gists) {
				glfm.writeFiles(gist);
			}
		} else {
			glfm.writeFiles(findGist(key));
		}
	}

	public Gist findGist(String s) {
		for (Gist gist : gists) {
			if (gist.getId().equals(s)) {
				return gist;
			}
		}
		return null;
	}

	public void showGists() throws IOException {
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
