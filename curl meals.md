curl samples. Use POSTMAN for test it.
#### getAll meals from restaurant
curl -X GET \
  http://localhost:8080/votesystem/rest/profile/restaurants/10003/meals \
  -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 154eb83e-10e6-45a6-a22e-8b7694b80200'
  
#### get meal from restaurant
curl -X GET \
  http://localhost:8080/votesystem/rest/profile/restaurants/10003/meals/10006 \
  -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 14b45a3b-c349-484b-8ad3-c57f1e6364df'

#### delete meal from restaurant
curl -X DELETE \
  http://localhost:8080/votesystem/rest/admin/restaurants/10003/meals/10006 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6c980d6a-2ccd-414f-b34d-0848b9769a08'
  
#### create meal
curl -X POST \
  http://localhost:8080/votesystem/rest/admin/restaurants/10003/meals/ \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0198ed1c-690e-4819-885d-5d79df5d084d' \
  -d '{
    "name": "Шаурма царская",
    "price": 3000,
    "date": "2018-06-06"
	}'
	
#### getAllByDate meals	from restaurant
curl -X GET \
  'http://localhost:8080/votesystem/rest/admin/restaurants/10003/meals/filter?chosenDate=2018-05-30' \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 2b5fd681-8914-4666-8b95-faee4e72359c'
