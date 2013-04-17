package com.oshmidt;

public abstract class GistLocalRepository extends GistRepository {
	
	private String repoPath;

	public String getRepoPath() {
		return repoPath;
	}

	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}
	
	abstract void loadDefaultRepoPath();

}
