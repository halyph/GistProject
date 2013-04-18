<p>For build, inside gist-cli folder type:"Ant", it create runnable jar file.</p>

<p> Commands:
<br> >  java –jar gist-cli.jar –l login –p password  :authorization and lazy fetching gist list from github with saving it inside working directory (/localRepository)
<br> >  java –jar gist-cli.jar –d  :the same behavior as previously command if exist inside working directory user property file (“config.properties”)
<br> >  java –jar gist-cli.jar –h  :show command list
<br> >  java –jar gist-cli.jar – show gistId  :show gist info by ID
<br> >  java –jar gist-cli.jar – show all  :show all user gists info
<br> >  java –jar gist-cli.jar – download gistId   :download gist files by gistId into work directory (/localRepository/’gistId’/)
<br> >  java –jar gist-cli.jar – download all   :download all gists files into work directory  (/localRepository/’gistId’/)</p>
