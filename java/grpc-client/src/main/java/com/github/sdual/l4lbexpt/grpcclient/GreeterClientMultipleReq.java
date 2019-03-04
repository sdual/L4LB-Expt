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
        .usePlaintext()
        .build();

    GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

    for (int i = 0; i < num; i++) {
      try {
        HelloRequest request = HelloRequest.newBuilder()
            .setName("sdual")
            .build();
        HelloReply reply = stub.sayHello(request);
        System.out.println(i + ": Reply: " + reply);
      } catch (StatusRuntimeException e) {
        e.printStackTrace();
        System.out.println(i + "番目");
      }

      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
      }
    }
  }

}
