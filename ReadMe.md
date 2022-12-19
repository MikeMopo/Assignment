<<---------------------------------- THE APOCALYPSE ---------------------------------------->>

1) Run the application

Run Application from command prompt with below command

mvn spring-boot:run


------------------------------------Db Connection------------------------------------------------

**Can be changed to suit user, used MySQL DB

Configured in applications.yml 

   Listening Port:8080
   Username: root
   Password: password

------------------------------------Endpoints------------------------------------------------

Documented Using Swagger

   Base URL: http://localhost:8081/swagger-ui/index.html

2) Adding survivors
   http://localhost:8081/api/newsurvivor
   JSON - {
   "firstname": "Joe",
   "surname": "Doe"
   "age": 25,
   "gender": "male",
   "location":[ "30.3333° S", "22.4444° E"],
   "resources": ["water","Ammunition"]}

3) Survivor Location
   http://localhost:8081/api/location/{id}
   Body- {"location": "10.3333° S", "11.4444° E"}

4) Listing all robot details
   http://localhost:8081/api/get/decepticons

5)	Percentage of infected and non-infected survivors.
      http://localhost:8081/api/ratio

6) Infected
   http://localhost:8081/api/infected

7) Not Infected
   http://localhost:8081/api/notinfected



