package com.oshmidt;

/**
 * @author oshmidt
 *         <p>
 *         Abstract class for gist local repository repository. Created for
 *         generalizing local repositories.
 */
public abstract class GistLocalRepository implements GistRepository {
    /**Path for local repository. Used through the setter and getter*/
    private String repoPath;

    /**
     * @return String - current path to repository
     */
    public final String getRepoPath() {
        return repoPath;
    }

    /**
     * Setter for repoPath field.
     * @param repoPath
     *            - String path to repository
     */
    public final void setRepoPath(final String repoPath) {
        this.repoPath = repoPath;
    }

    /**
     * Load default path to repository. Must be defined inside inheritor class.
     */
    abstract void loadDefaultRepoPath();

}
