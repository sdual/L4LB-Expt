# L4LB-Expt

This repository is for the experiment whether the L4 load balancer distributes requests to the gRPC application servers uniformly.

https://github.com/grpc/proposal/blob/master/A9-server-side-conn-mgt.md

## Usage

- Build docker images of grpc-app and grpc-client. You can send requests by using grpc-client as it is, and the image of grpc-client is not used in this experiment. 

```
$ mvn clean package docker:build
```

- Run HAProxy and two grpc-app containars.
```
$ docker-compose up
```

- Check grpc-app log.

```
$ docker exec -it l4lb-expt_grpc-app1_1 /bin/sh
$ cd logs
$ tail -f app.log
```

### gRPC Gateway

```
$ cd golang
$ go run main.go
```

gRPC Gateway provides RESTful API. You can send a request as following.

```
$ curl "http://localhost:9090/v1/greet?name=sdual"
```
