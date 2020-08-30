# ui
This repository was cloned from https://github.com/metflix/ui with following extensions:
- Divided components for easily understand each responsibility
- Added unit tests by using Mocks

<br>


## Overview
![Overview image](./uml/overview.png)

ui, [recommendations](https://github.com/hageyahhoo/recommendations), and [membership](https://github.com/hageyahhoo/membership) are Microservices.

<br>


## How to run this service

### 1) CUI
1. Run `./mvnw clean package` and `target/ui-0.0.1-SNAPSHOT.jar` will be created
2. Run `java -jar target/ui-0.0.1-SNAPSHOT.jar`

<br>

### 2) IDE (e.g. IntelliJ IDEA)
Run [src/main/java/com/metflix/UiApplication.java](https://github.com/hageyahhoo/ui/blob/master/src/main/java/com/metflix/UiApplication.java)

<br>


## How to run tests
Run `./mvnw clean build`
