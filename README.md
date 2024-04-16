# taf-zarina-2024 

* The project is developed to cover the site with UI and API autotests
* The UI tests are designed according to the Page Object and PageFactory patterns 
* Tests can be run locally or using Jenkins

## Technologies used
* Java <a href="https://www.java.com/"><img src="/img/icons/java.svg" height="40"></a> 
* Maven <a href="https://maven.apache.org/"><img src="/img/icons/maven.svg" height="40"></a>
* Intellij IDEA <a href="https://www.jetbrains.com/idea/"><img src="/img/icons/intellij-idea.svg" height="40"></a>
* Selenium <a href="https://www.selenium.dev/"><img src="/img/icons/selenium.svg" height="40"></a>
* JUnit 5 <a href="https://junit.org/"><img src="/img/icons/junit5.svg" height="40"></a>
* Jenkins <a href="https://www.jenkins.io/"><img src="/img/icons/jenkins.svg" height="40"></a>
* Allure <a href="https://docs.qameta.io/allure/"><img src="/img/icons/allure.svg" height="40"></a>
* Rest Assured <a href="https://rest-assured.io/"><img src="/img/icons/rest-assured.png" height="40"></a>
* WebDriverManager <a href="https://github.com/bonigarcia/webdrivermanager"><img src="/img/icons/webdrivermanager.png" height="40"></a>
* Java Faker <a href="https://github.com/DiUS/java-faker"><img src="/img/icons/javafaker.png" height="25"></a>

## Local launch of autotests
To run the project locally, install [Maven](https://maven.apache.org/download.cgi) and [Java](https://www.java.com/en/download/) to the local machine, enter the following commands in the console:

```bash
mvn clean test
```

```bash
allure:install
```
```bash
mvn allure:report
``````
```bash
mvn allure:serve
``````

### Sample report
After completing the autotests, the following report will open in the browser:
![This is an image](/img/screenshots/jenkins1.png)
![This is an image](/img/screenshots/jenkins2.png)
![This is an image](/img/screenshots/jenkins3.png)

## CI/CD Jenkins:
Continuous integration and remote test execution is provided by Jenkins. Tests are performed every hour.

### Configuring Jenkins
Installation of the Allure Jenkins Plugin is required

To install the plugin:
1. On the main page, in the left menu, select Manage Jenkins
2. On the page that opens, in the System Configuration block, click Manage Plugins
3. On the Plugin Manager page, select Available, enter the name of the plugin in the search bar and install a checkbox next to it
4. After selecting all the necessary plugins, click on Install without restart


### Configuring the build
1. On the main page, in the left menu, click New Item
2. Enter the name of the project, select the Freestyle project and click OK
3. On the Configuration page, configure the project settings. The project uses the following configuration:
4. Source Code Management -> **Git** (setting up a repository with source code):
    - Repository URL = https://github.com/moksemn/taf-zarina-2024-itacademy.git
    - Credentials → jenkins/******
    - Branches to build = */master
5. Build Steps → **Invoke top-level Maven targets**:
    - Maven Version → Maven 3.9.5
    - Goals → clean tests
6. Post-build Actions → **Allure Report**:
    - Path = target/allure-results
7. Click **Save**

### To run autotests in Jenkins:
1. Open a project
2. Click **Build now**
3. The result of running the build can be viewed in the Allure report
   ![This is an image](/img/screenshots/jenkins4.png)
