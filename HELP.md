### Deployment
```bash
mvn package
docker image build -t alxinsh/docker-java-hw35-notification:1.0.0 .
docker push alxinsh/docker-java-hw35-notification:1.0.0
```

### REST-queries
```bash
curl localhost:8084/notification | json_pp
```