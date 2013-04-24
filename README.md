Build project
=========
For build, inside gist-cli folder type:"Ant", it create runnable jar file.

Сommands:
=========
* `java –jar gist-cli.jar –d` the same behavior as previously command if exist inside working directory user property file (“config.properties”)
* `java –jar gist-cli.jar –h` show command list
* `java –jar gist-cli.jar –show gistId` show gist info by ID
* `java –jar gist-cli.jar –show all` show all user gists info
* `java –jar gist-cli.jar –download gistId` download gist files by gistId into work directory (/localRepository/’gistId’/)
* `java –jar gist-cli.jar –download all` download all gists files into work directory  (/localRepository/’gistId’/)


Documentation:
=========
Ant `?`
Maven  `mvn javadoc:javadoc` create doucmentation to `gist-cli\target\site\`



Tests:
=========
Ant `?`
Maven  `mvn test` run tests and create report to `gist-cli\target\surefire-reports\index.html`