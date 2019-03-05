package com.github.sdual.l4lbexpt.grpcclient;

import com.github.sdual.helloworld.GreeterGrpc;
import com.github.sdual.helloworld.HelloReply;
import com.github.sdual.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GreeterClientMultipleReq {

  public void callGreeter(String host, int port, int num) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
        .enableRetry()
        //.maxRetryAttempts(3)
        .usePlaintext()
        .build();

    GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

    for (int i = 0; i < num; i++) {
      HelloRequest request = HelloRequest.newBuilder()
          .setName("sdual")
          .build();
      HelloReply reply = stub.sayHello(request);
      System.out.println(i + ": Reply: " + reply);

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
    }
  }

}
