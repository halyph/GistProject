Info:
=========
Project created for simplification access to Github gist.


Build project:
=========
For build, inside gist-cli folder type:`ant` for build project by Ant or type:`mvn install` for build by maven, it create runnable jar file. As needed you can automatically install ivy, for it type: `ant init-ivy`


Сommands:
=========
* `java –jar gist-cli.jar –u username -p password` download and save gists from github by user username and password
* `java –jar gist-cli.jar –r` the same behavior as previously command if exist inside working directory user property file (“config.properties”)
* `java –jar gist-cli.jar –l` use local saved data as work data
* `java –jar gist-cli.jar –h` show command list
* `java –jar gist-cli.jar –show gistId` show gist info by ID
* `java –jar gist-cli.jar –show all` show all user gists info
* `java –jar gist-cli.jar –d gistId` download gist files by gistId into work directory (/localRepository/’gistId’/)
* `java –jar gist-cli.jar –d all` download all gists files into work directory  (/localRepository/’gistId’/)


Documentation:
=========
* Ant `ant javadoc` create documentation to `gist-cli\build\runnable\javadoc\`
* Maven  `mvn javadoc:javadoc` create doucmentation to `gist-cli\target\site\`


Tests:
=========
* Maven  `mvn test` run tests and create report to `gist-cli\target\surefire-reports\index.html`


Code Coverage:
=========
* `mvn cobertura:cobertura` generate code coverage documentation by maven
