# Inventory
Spring Boot Inventory RESTful API
 You can visualize the endpoints documentation with Swagger on http://localhost:8080/swagger-ui/index.html.
 
![image](https://user-images.githubusercontent.com/44626021/165647109-25223908-9d21-4083-b94b-e97dbfcd22a5.png)


The project uses a H2 embedded database that use the files schema.sql and data.sql to create the initial tables of Products and Users.

![image](https://user-images.githubusercontent.com/44626021/165648132-ba4c6256-b33c-40e6-9bb1-38bb93885512.png)


The security use Spring Boot Security for authentication using the Users table, where the passwords are already hashed (the default password is “letmein”). When we sign in the project generate a Java Web Token (JWT) with the user and role to give authorization.

![image](https://user-images.githubusercontent.com/44626021/165648205-395b1d3d-807a-433a-a317-6a0752f5a9d5.png)
