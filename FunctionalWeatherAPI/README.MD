API Testing Scenario
API Test Framework Example in Java language using Java libraries; RestAssured (for API Service) and Cucumber (for BDD styled), along with Maven as a build tool.

Table of Contents
Overview
How to setup
How to run tests and generate reports
Where to find Reports
Examples of Test Execution and HTML report
Jenkins (CI/CD) Integration setup guideline

1. Overview
This API test framework uses Java as a main language with 'RestAssured' and 'Cucumber' libraries

Test will run according to feature files - which are stored under ./src/test/java/featureFiles folder.

Outputs (Json, XML and html files) will be produced under ./target/cucumber-reports folder.


2. How to setup
Pull (clone) source code from Git as below command:

git clone git@github.com:pichai-t/OpenWeatherMap-RestAssured.git
Then tests can be run as mentioned in the next step.

Note: Git client and Maven are required to setup and run

Git Installation
Apache Maven Installation

3. How to run tests and generate reports
Run CLI: "mvn test -Dcucumber.options="--tags @RegressionTest"" to build and execute tests

mvn test -Dcucumber.options="--tags @RegressionTest"
Note: or just simply run mvn test to use default 'tags' defined in RunAPITest.java.

This command will run only any scenario(in feature files) with tag as '@RegressionTest'.

Once finished, there will be reports in ./target/cucumber-reports/* folder.


4. Where to find reports
JSON file: ./target/cucumber-reports/Cucumber.json
XML file: ./target/cucumber-reports/Cucumber.xml
HTML file: ./target/cucumber-reports/html/index.html

5. Jenkins (CI/CD) Integration setup guideline
In your new Jenkins Job:

5.1) Under Git Integration: pull source code from github:

git clone git@github.com:pichai-t/OpenWeatherMap-RestAssured.git
5.2) Add step: to run maven (build and run goal as 'test')

Two options:

Run with the 'default' tags under Cucumber.options {...} in RunAPITest.java

mvn test 
Run with selective tags by passing parameters as below

mvn test -Dcucumber.options="--tags @RegressionTest"
5.3). If needed, JUnit and HTML reports can be used for further integrations (Jenkins build itself or other integrations; such as Jira, qTest.

JUnit file: ./target/cucumber-reports/Cucumber.xml
HTML file: ./target/cucumber-reports/html/index.html

6. Examples of Test Execution and HTML report



