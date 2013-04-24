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
	abstract List<Gist> readGists();

	/**
	 * Serialize gists.
	 * 
	 * @paramTakes Gists list.
	 */
	abstract void writeGists(List<Gist> gists);

	/**
	 * Download and save Gist files
	 * 
	 * @paramTakes Gist item.
	 */
	abstract void writeFiles(Gist gist);

	/**
	 * Load saved gists.
	 * 
	 * @return Map <Gist, List<GistFile>>
	 */
	abstract Map<Gist, List<GistFile>> readFiles();

}
