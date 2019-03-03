package com.github.sdual.l4lbexpt.grpcclient;

public class Boot {

  public static void main(String[] args) {

    int port = 8080;
    String host = "localhost";

    //new GreeterClientSingleReq().callGreeter(host, port);
    new GreeterClientMultipleReq().callGreeter(host, port, 100);

  }

}
