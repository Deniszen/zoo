[![Build Status](https://travis-ci.org/Deniszen/zoo.svg?branch=master)](https://travis-ci.org/Deniszen/zoo)
[![codecov](https://codecov.io/gh/Deniszen/zoo/branch/master/graph/badge.svg)](https://codecov.io/gh/Deniszen/zoo)
# Zoo CRUD Service

Zoo this a docker image built to be used for API testing practice.
Zoo is full CRUD service based on Spring Boot, you can send and check POST, GET, PUT and DELETE requests.

Requirements
------------
Docker is installed in your system.

Quick Start
------------
1. Pull Docker image.
    ```bash 
    docker pull zentreid/zoo:v1.01
    ```

2. Run Docker image.
    ```bash
    docker run -d -p 8080:8080 --name crud zentreid/zoo:v1.01
    ```

3. Send request to http://localhost:8080/info for get more information about CRUD service.

4. Practice testing the API.

5. Enjoy! :blue_heart:
