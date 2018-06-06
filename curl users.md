Curl samples. Use POSTMAN for test it.
#### get All Users
  curl -X GET \
  http://localhost:8080/votesystem/rest/admin/users \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fc542d45-a6e2-45b0-97b0-7b162058dd3d'
  
#### get User 10001
  curl -X GET \
  http://localhost:8080/votesystem/rest/admin/users/10001 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: ee74c5d1-1375-4993-bcd5-1d414755a266'
  
 #### delete User 10001
  curl -X DELETE \
  http://localhost:8080/votesystem/rest/admin/users/10001 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6cb8a3d7-332b-497b-9dbc-6a01115ba268'
  
  #### update User 10001
  curl -X PUT \
  http://localhost:8080/votesystem/rest/admin/users/10001 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a23a333c-1970-4c94-b6e7-8fb6ab27c2c9' \
  -d '{
    "id": 10001,
    "name": "Adminnnn",
    "email": "admin@gmail.com",
    "password": "admin",
    "enabled": true,
    "registered": "2018-06-05T07:08:00.792+0000",
    "roles": [
        "ROLE_ADMIN",
        "ROLE_USER"
    ]
	}'
	
#### getByEmail User
	curl -X GET \
  http://localhost:8080/votesystem/rest/admin/users/by?email=user@yandex.ru \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 20d572af-f9bb-43a0-821e-bd29254df5c9' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'
  
#### enable(disable) User 
  curl -X POST \
  http://localhost:8080/votesystem/rest/admin/users/10000?enabled=false \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 3b6e1edc-7a47-4210-918b-9ad8dee2e2ea' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'
  
