Testing a [fake API](https://jsonplaceholder.typicode.com/), by making requests with the standard Java http package. 
Libraries used in this project: (aquality) Selenium, TestNG, gson. 

#### test case
|  step | expected result  |
| ------------ | ------------ |
| Send GET Request to get all posts | Response status code is 200;<br> Response body is a valid json string;<br> Posts in response body are in ascending order; |
| Send GET request to get post with an id=99 | Response status code is 200;<br> Received post has correct values: userId=10, id=10, body and title are not empty; |
| Send GET request to get post with an id=150 | Response status code is 404; <br> Response body is empty; |
| Send POST request to create post with a userId=1, with random body and title | Response status code is 201;<br> Post information is correct: title, body, userId match the sent data;<br> Id is present in response;|
|Send GET request to get users | Response status code is 200; <br> Response body is a valid json string;<br>User with id=5 matches provided data;|
| Send GET request to get user with an id=5 | Response status code is 200; <br> User with matches data from the previous step;
