package com.oshmidt;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;

public class GistFetcher {
	private GistService service = new GistService();

	private void setClientCredentials(User user) {
		service.getClient().setCredentials(user.getLogin(), user.getPassword());
	}

	public Gist addNewGist(User user, Gist gist) throws IOException {
		setClientCredentials(user);
		return service.createGist(gist);
	}

	public List<Gist> loadGists(User user) throws IOException {
		setClientCredentials(user);
		return service.getGists(user.getLogin());
	}

	public Gist loadGist(String gistId, User user) throws IOException {
		setClientCredentials(user);
		return service.getGist(gistId);
	}

	public Gist updateGist(User user, Gist gist) throws IOException {
		setClientCredentials(user);
		return service.updateGist(gist);
	}

	public void deleteGist(User user, String gistId) throws IOException {
		setClientCredentials(user);
		service.deleteGist(gistId);
	}

	public void deleteGists(User user, List<Gist> gists) throws IOException {
		for (Gist gist : gists) {
			deleteGist(user, gist.getId());
		}
	}

}
