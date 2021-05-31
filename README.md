# iHub Challenge

### Table of Contents
- [Description](#description)
- [Technical Specification](#technical-specification)
- [Development Tools](#development-tools)
- [Getting Started](#getting-started)
- [API](#api)
    - [User API](#user-api)    
    - [Product API](#product-api)    
    - [Cart API](#cart-api)    
    - [Order API](#order-api)    
    - [Code Documentation](#code-documentation)
    - [Docker Image](#docker-image)

## Description

The goal of this exercise is to build a Purchasing API. 
To use you must follow the following steps:

- Create one or more Product(s) in the Product API.
- Add one or more existing Product(s) to the users Cart in the Cart API. 
  (We automatically add the item to the user's cart that is logged in.)
- Place an order in the order API.
  (We automatically create the Order based on the logged-in user's cart and clear the cart so that user can purchase new items)

## Technical Specification

- Language: Java, version 8
- Framework: Spring Boot, version 2.1.0

## Development Tools

- Eclipse IDE 2018-12, version 4.9.0
- Apache Maven, version 3.6.0
- Apache Maven Javadoc Plugin, version 3.0.1
- Docker Maven Plugin, version 0.20.1

## Getting Started

### API

This application exposes 4 main APIs.

#### User API

    /register or /authenticate
    
The API must accept `POST` requests to the `/register` endpoint
with a body that complies with the following example:    
```
{
    "username":"john",
    "password":"mypass1234",
    "email": "john@gmail.com"
}
```

After that, you can use the `/authenticate` endpoint to login and obtain the session token. 
If you want, you can use a user who is already registered by deafult:

```
{
    "username":"ihub",
    "password":"password"
}
```


#### Product API

    
The API must be used to Add, Update, Find or Delete Product(s) to the System,  that will be used for purchases.

#### Cart API

The API must be used to Add, Update, Find or Delete Product(s) to the users Cart.

#### Order API

The API must be used to Create a order based on the users Cart, or list the orders already made.

### Code Documentation

To acess the code documentation is just run the server, then the documentation will be accessible at:

[![N|Solid](https://i.ibb.co/3WFTxWC/aggregate-sw-hub.png)](https://i.ibb.co/3WFTxWC/aggregate-sw-hub.png)

    http://localhost:8080/swagger-ui.html


### Docker Image

To run:

    docker-compose up web



