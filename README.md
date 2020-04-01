## API Demo Challenge

#### Project Setup
1. Clone this project.
2. Setup the project in your IDE.
3. From command line run mvn clean install -U -DskipTests
5. Make sure you can run the DemoTest

#### Expectations
We will be evaluating
1. Quality of test cases
2. Variety  of testing types (examples: boundary, happy path, negative, etc)
3. Code structure and organization
4. Naming conventions
5. Code readability
6. Code modularity


#### Exercise
1. Review the spec in the root directory, PizzaAPIReferenceDoc.  API endpoints for this exercise can be found here
   https://my-json-server.typicode.com/sa2225/demo/
2. In the Read me file, write up all of the test cases you think are necessary to test the endpoints defined in the provided spec.
3. Code up a few examples of 
  - order get call including response validation
  - order post call
4. When complete please check your code into a public git repo

#### Test Cases

 Orders:
 1. Assert that Status Code 200 for retrieving orders with correct path
 2. Assert that Status code 400 for retrieving orders with incorrect path
 3. Get List of All Orders by "id" Assert that status code is 200
 4. Verify that giving an invalid number of toppings returns Status Code 406 
 5. Verify that Unspecified Pizza (no information) returns status code 408
 6. Verify  that providing same toppings on pizza returns status code 407
 
 Pizzas:
 1. Create New Pizza with Valid entry Medium 8 Slices - 2 toppings and Verify status code 201
 2. Create New Pizza with Valid entry Large 10 Slices - No Toppings and verify Status code 201
 3. Create New Pizza with Invalid entry Medium 10 Slices - 2 Toppings and Verify Status Code 407
 4. Create  New Pizza with Invalid entry No Size - 2 Toppings and Assert that Status Code 408
 5. Create New Pizza Unspecified Entry (no size, no toppings) and Assert that Status Code 408
 
 Toppings:
 1. Get all Toppings and verify status code 200
 2. Add new Topping verify status code 204
 3. Duplicate New Topping verify status code 405
 4. Get list of Toppings by "id", verify "id" and "name" are valid and assert that status code 200
 5. Delete Toppings and Assert that status 200
 
 EndPoints:
 Cover and verify status codes for all end points
 
 
 
 
 

