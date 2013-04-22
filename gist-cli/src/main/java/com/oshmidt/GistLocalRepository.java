package com.oshmidt;

public abstract class GistLocalRepository implements GistRepository {
	
	private String repoPath;

	public String getRepoPath() {
		return repoPath;
	}

	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}
	
	abstract void loadDefaultRepoPath();

}
