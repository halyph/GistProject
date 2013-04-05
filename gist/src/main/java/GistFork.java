package java;

import java.util.Date;

public class GistFork {
	
	private GistUser user;
	
	private String url;
	
	private Date created_at;

	public GistUser getUser() {
		return user;
	}

	public void setUser(GistUser user) {
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
}
