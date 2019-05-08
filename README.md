# REST API for accessing devices data


1. New Spring Rest HATEOAS have some unpredictable behaviour (NPE in some basic cases), so its not used in app on UI side (consider not fully supported)
1. Swagger documentation not attached since time is very limited
1. Device list query is very slow. First of all fonoApi seems not supporting limit/position (which mentioned in documentation). So it sends huge amount of data every time. For me one single request to the fonoAPI using Postman takes from 550ms to 2500ms depending on response. Plus everything would be faster if WebClient used (AsyncRestTemplate is deprecated in Spring 5).
1. Tests was removed because of limited time - they required to spend more time on each change. Yes, it's bad. 