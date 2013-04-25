package com.oshmidt;

import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

/**
 * @author oshmidt
 *         <p>
 *         Interface for gist repository. Created for unify the use of
 *         inheritance repository.
 * 
 * @see org.eclipse.egit.github.core.Gist
 * @see org.eclipse.egit.github.core.GistFile
 */
public interface GistRepository {

	/**
	 * Deserialize and return gists.
	 * 
	 * @return Deserialized gists.
	 */
	List<Gist> readGists();

	/**
	 * Serialize gists.
	 * 
	 * @param gists
	 *            - gist list.
	 */
	void writeGists(List<Gist> gists);

	/**
	 * Download and save Gist files.
	 * 
	 * @param gist
	 *            gist item.
	 */
	void writeFiles(Gist gist);

	/**
	 * Load saved gists.
	 * 
	 * @return Map <Gist, List<GistFile>>
	 */
	Map<Gist, List<GistFile>> readFiles();

}
