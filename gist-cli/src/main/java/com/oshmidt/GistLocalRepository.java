package com.oshmidt;

/**
 * @author oshmidt
 *         <p>
 *         Abstract class for gist local repository repository. Created for
 *         generalizing local repositories.
 */
public abstract class GistLocalRepository implements GistRepository {

	/**
	 * Path for local repository. Used through the setter and getter
	 */
	private String repoPath;

	public String getRepoPath() {
		return repoPath;
	}

	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}

	
	/**
	 * Load default path to repository. Must be defined inside inheritor class.
	 */
	abstract void loadDefaultRepoPath();

}
