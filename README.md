# Identity E2E Test

A test automation framework developed using Selenium, Java, Cucumber to generate HTML report

## Getting Started
To set up this project to the local machine. See Installation section.
### Note: 
```
For future tasks multiple input files can be passed in below steps i.e. "file1.txt,file2,txt,file3.txt"
```


### Prerequisites

* Maven 3.3 or higher
* JDK 1.8 or higher

Check Maven Version by Below Command
```
mvn --version
```

Check Java Version by Below Command
```
java -version
```

### Installing

Download Github Repository to local machine

1. Open IntelliJ IDEA
2. From the Welcome screen, click **New Project From Existing Source**.
The Select File or Directory to Import dialog will open.
3. Navigate to downloaded Maven project and select folder.
4. Click **Open**.
5. The **Import Project** dialog will opens:
6. In the dialog For the **Import project from external model** value, select **Maven** and click **Next**.
7. Click **Finish**.
8. **Project** should be imported, In left Panel **pom.xml** should be shown
9. Select **pom.xml** (right click ) select **Maven** and then click **Reimport**
10. All **External Libraries** will be downloaded


## Running the tests

Test can be executed by running a feature file or using command line with below command.
```
mvn clean install
```
To generate HTML Report
```
mvn test
```
HTML Report can be viewed in the Target folder i.e.
```
target/Report/cucumber-reports/cucumber-html-reports/Features-CarRegistrationCheck-feature.html
```

## Links to Download Pre-requisite

* [JAVA JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)
* [Maven](https://maven.apache.org/) - Dependency Management
* [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac) - Code Editor

## Authors

* **Asjad Tariq**
