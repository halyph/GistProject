package com.oshmidt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

public class GistLocalFileManager extends GistLocalRepository {

	public static final String DEFAULT_PATH = "localRepository/";

	void loadDefaultRepoPath() {
		setRepoPath(DEFAULT_PATH);
	}

	List<Gist> readGists() {

		return null;
	}

	@Override
	void writeGists(List<Gist> gists) {

		try {
			for (Gist gist : gists) {
			FileOutputStream fos;
			fos = new FileOutputStream(getRepoPath() + gist.getId());

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
				oos.writeObject(gist);
				oos.flush();
				oos.close();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	void writeFiles(Map<Gist, List<GistFile>> files) {
		// TODO Auto-generated method stub

	}

	@Override
	Map<Gist, List<GistFile>> readFiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
