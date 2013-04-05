package java;

import java.util.ArrayList;
import java.util.Date;


public class Gist {
	private String url;
	
	private Integer id;
	
	private String description;
	
	private Boolean isPublic;
	
	private GistUser gistUser;

	private ArrayList<GistFile> files;
	  
	private Integer comments;
	
	private String comments_url;
	
	private String html_url;
	
	private String git_pull_url;
	
	private String git_push_url;
	
	private Date created_at;
	
	private ArrayList<GistFork> forks; 
	
	private ArrayList<GistHistory> history;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public GistUser getGistUser() {
		return gistUser;
	}

	public void setGistUser(GistUser gistUser) {
		this.gistUser = gistUser;
	}

	public ArrayList<GistFile> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<GistFile> files) {
		this.files = files;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String getComments_url() {
		return comments_url;
	}

	public void setComments_url(String comments_url) {
		this.comments_url = comments_url;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getGit_pull_url() {
		return git_pull_url;
	}

	public void setGit_pull_url(String git_pull_url) {
		this.git_pull_url = git_pull_url;
	}

	public String getGit_push_url() {
		return git_push_url;
	}

	public void setGit_push_url(String git_push_url) {
		this.git_push_url = git_push_url;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public ArrayList<GistFork> getForks() {
		return forks;
	}

	public void setForks(ArrayList<GistFork> forks) {
		this.forks = forks;
	}

	public ArrayList<GistHistory> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<GistHistory> history) {
		this.history = history;
	}
	

	  

}
