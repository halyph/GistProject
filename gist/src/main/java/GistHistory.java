package java;

import java.util.Date;

public class GistHistory {
	
	private String url;
	
	private String version;
	
	private GistUser user;
	
	private GistHistoryChangeStatus change_status;
	
	private Date committed_at;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public GistUser getUser() {
		return user;
	}

	public void setUser(GistUser user) {
		this.user = user;
	}

	public GistHistoryChangeStatus getChange_status() {
		return change_status;
	}

	public void setChange_status(GistHistoryChangeStatus change_status) {
		this.change_status = change_status;
	}

	public Date getCommitted_at() {
		return committed_at;
	}

	public void setCommitted_at(Date committed_at) {
		this.committed_at = committed_at;
	}
	
	

}
