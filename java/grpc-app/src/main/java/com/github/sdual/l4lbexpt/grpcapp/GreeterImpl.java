package com.github.sdual.l4lbexpt.grpcapp;

import com.github.sdual.helloworld.GreeterGrpc;
import com.github.sdual.helloworld.HelloReply;
import com.github.sdual.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GreeterImpl extends GreeterGrpc.GreeterImplBase {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreeterImpl.class);

  public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
    LOGGER.info("receive request. the name is " + req.getName());
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}

