package com.oshmidt;

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

	abstract void loadDefaultRepoPath();

}
