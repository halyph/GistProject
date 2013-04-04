package java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Gist {
	  private String url;
	  
	  private Integer id;
	  private String description;
	  private Boolean isPublic;
	  private String user; /* {
	    "login": "octocat",
	    "id": 1,
	    "avatar_url": "https://github.com/images/error/octocat_happy.gif",
	    "gravatar_id": "somehexcode",
	    "url": "https://api.github.com/users/octocat"
	  },*/
	  private String files; /* {
	    "ring.erl": {
	      "size": 932,
	      "filename": "ring.erl",
	      "raw_url": "https://gist.github.com/raw/365370/8c4d2d43d178df44f4c03a7f2ac0ff512853564e/ring.erl"
	    }
	  },*/
	  private Integer comments;
	  private String comments_url;
	  private String html_url;
	  private String git_pull_url;
	  private String git_push_url;
	  private Date created_at;
	  private ArrayList<String> forks; /* [
	    {
	      "user": {
	        "login": "octocat",
	        "id": 1,
	        "avatar_url": "https://github.com/images/error/octocat_happy.gif",
	        "gravatar_id": "somehexcode",
	        "url": "https://api.github.com/users/octocat"
	      },
	      "url": "https://api.github.com/gists/f9383e4333d45b4b556b",
	      "created_at": "2011-04-14T16:00:49Z"
	    }
	  ],*/
	  
	  
	  private ArrayList<String> history; /* [
	    {
	      "url": "https://api.github.com/gists/0e6f01a065130a13e6dc",
	      "version": "57a7f021a713b1c5a6a199b54cc514735d2d462f",
	      "user": {
	        "login": "octocat",
	        "id": 1,
	        "avatar_url": "https://github.com/images/error/octocat_happy.gif",
	        "gravatar_id": "somehexcode",
	        "url": "https://api.github.com/users/octocat"
	      },
	      "change_status": {
	        "deletions": 0,
	        "additions": 180,
	        "total": 180
	      },
	      "committed_at": "2010-04-14T02:15:15Z"
	    }
	  ]*/

}
