package com.github.sdual.l4lbexpt.grpcclient;

import com.github.sdual.helloworld.GreeterGrpc;
import com.github.sdual.helloworld.HelloReply;
import com.github.sdual.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreeterClientMultipleReq {

  public void callGreeter(String host, int port, int num) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .build();

    GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

    for (int i = 0; i < num; i++) {
      HelloRequest request = HelloRequest.newBuilder()
          .setName("sdual")
          .build();
      HelloReply reply = stub.sayHello(request);
      System.out.println("Reply: " + reply);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }

}
