# Maven-Selenium-Cucumber-Java

This project is used Maven, selenium, cucumber and java to automate the functional testing.

# Pre-requisites

- Java SDK 11
- Maven 3.8

# Running features

- Goto project directory.
- Use "mvn test" command to run all features.
- Use "mvn test -Dcucumber.filter.tags="Tag_Name" to run specific tag you want.
  For example:

> mvn test -Dcucumber.filter.tags="@Smoke"

# Running with browser

- Use "mvn test -Dbrowser=browser_name" to run features on specific browser.
- browser_name can be one of following but make sure that browser's driver file are present and specified in system
  variable.
  ```
  - Firefox
  - Chrome
  - Edge
  ```
  For example:

> mvn test -Dbrowser=Edge

# Customize Parameters
You can customize parameters in `dev.properties`, or if you want to customize it by command line,
your can use -D + parameter.
> -Dwait=5 -Denv=QC

# Reporting
After executing tests, a report folder "cucumber-html-report" will be generated at the directory.
You can open `overview-features.html` to view it.