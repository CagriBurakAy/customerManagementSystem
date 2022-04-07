# CustomerManagementSystem

# Requirements

For building and running the application you need:

- [JDK 1.8]

# Security

Integration with Spring Security and add other filter for jwt token process.

To access Bearer Token's token 


{
    "username": "admin",
    "password": "password"
}

should post at

http://localhost:8080/authenticate

# Database

It uses MySQL and PostgreSQL, can be changed easily in the `application.properties` for any other database. However, database connections are required for it to work.

# How to Test

Postman can be used.All project is under the this url

http://localhost:8080/user

To Add Customer POST

http://localhost:8080/user/addCustomer

{
    "name": "Arda",
    "surname": "Berk"
}

To Get All Customer GET

http://localhost:8080/user/getAllCustomer

To Get Customer By Id GET

http://localhost:8080/user/customer/1

To Update Customer By Id PUT

http://localhost:8080/user/updateCustomer/1

{
    "id":1,
    "name": "Burak",
    "surname": "Berk"
}

To Delete Customer By Id DELETE
http://localhost:8080/user/deleteCustomer/1

To Add File POST

http://localhost:8080/user/uploadFile

form-data KEY="file"

You can add VALUE, paying attention to the file size indicated in `application.properties`

To Get All File GET

http://localhost:8080/user/getAllFiles

To Get File By Id GET

http://localhost:8080/user/downloadFile/1

To Update File By Id

http://localhost:8080/user/files/1

form-data KEY="file"

You can add VALUE, paying attention to the file size indicated in `application.properties`

To Add File multiple file POST

http://localhost:8080/user/uploadMultipleFiles

form-data KEY="file"

You can add VALUE, paying attention to the file size and request size indicated in `application.properties`

To Delete File By Id DELETE

http://localhost:8080/user/deleteFile/1

To Add CustomerFiles POST

http://localhost:8080/user/addCustomerFile

{
    "fileId": "1",
    "customerId": "1"
}

To Get All CustomerFiles GET

http://localhost:8080/user/getAllCustomerFiles

To Get CustomerFiles By Customer Id GET

http://localhost:8080/user/getCustomerFile/1

To Get CustomerFiles By Id GET

http://localhost:8080/user/getCustomerFileById/1

To Update CustomerFiles By Id PUT

http://localhost:8080/user/updateCustomerFile/1

{
    "id":1,
    "fileId": "2",
    "customerId": "2"
}

To Delete CustomerFiles By Id DELETE

http://localhost:8080/user/deleteCustomerFile/1

# ER Diagram

![NISH_ERDiagram](https://user-images.githubusercontent.com/26979943/162211856-6fe08cc0-8a0a-4d3c-ac92-3b008ac99159.jpeg)
