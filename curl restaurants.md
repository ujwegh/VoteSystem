curls samples. Use POSTMAN for test it.
#### getAll restaurants
curl -X POST \
  http://localhost:8080/votesystem/rest/admin/restaurants \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fe85c231-a2b8-477f-9ff0-101312749435' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'
  
#### get restaurant
curl -X GET \
  http://localhost:8080/votesystem/rest/admin/restaurants/10002 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 388845fe-77a1-4d37-bf35-dfede057e59a'
  
#### delete restaurant
curl -X DELETE \
  http://localhost:8080/votesystem/rest/admin/restaurants/10002 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: dfd918f5-034e-4568-a607-ae4c03a02bb1'
  
#### deleteNotFound restaurant
curl -X DELETE \
  http://localhost:8080/votesystem/rest/admin/restaurants/1000 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 947ae052-861b-46e1-951d-b1e6d30700e6'
  
#### getNotFound restaurant
curl -X GET \
  http://localhost:8080/votesystem/rest/admin/restaurants/100 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: cefef5a3-acdc-4b37-9c97-ca2ef7ce132b'
  
#### update restaurant
curl -X PUT \
  http://localhost:8080/votesystem/rest/admin/restaurants/10002 \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 95b4d5fb-95a6-454f-ac98-b3b3a4b29bb2' \
  -d '{
    "id": 10002,
    "name": "Шашлычная - чебуречная№2",
    "meals": null,
    "voteCount": 0
	}'
#### create restaurant
curl -X POST \
  http://localhost:8080/votesystem/rest/admin/restaurants \
  -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c91a6c8f-4e1b-418e-af12-860229886eb2' \
  -d '{
    "name": "Пиццерия"
	}'
	
#### voteFor restaurant
curl -X PUT \
  http://localhost:8080/votesystem/rest/restaurants/10003 \
  -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7e6d64fc-2450-43da-a3c5-6fc0ddf41c01'
  
