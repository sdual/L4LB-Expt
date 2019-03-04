package com.github.sdual.l4lbexpt.grpcapp;

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Boot {

  private static final Logger LOGGER = LoggerFactory.getLogger(Boot.class);
//
//  public static void main(String[] args) {
//    LOGGER.info("start server.");
//    try {
//      startServer();
//    } catch (Exception e) {
//      LOGGER.error("failed to start server.", e);
//    }
//  }
//
//  private static void startServer() {
//    int port = 80;
//
//    ServerBuilder sb = new ServerBuilder();
//    sb.gracefulShutdownTimeout(1000L, 2000L);
//    sb.http(port);
//    // add gRPC service.
//    sb.service(new GrpcServiceBuilder().addService(new GreeterImpl())
//        .enableUnframedRequests(true)
//        .supportedSerializationFormats(GrpcSerializationFormats.PROTO,
//            GrpcSerializationFormats.JSON)
//        .build()
//    );
//    // add REST service.
//    sb.annotatedService(new HealthCheckService());
//
//    final Server server = sb.build();
//    server.start().join();
//
//    Runtime.getRuntime().addShutdownHook(
//        new Thread(() -> {
//          server.stop().join();
//          LOGGER.info("shutdown server.");
//        }));
//
//  }

  public static void main(String[] args) {
    try {
      io.grpc.Server server = NettyServerBuilder.forPort(80)
          .maxConnectionAge(100, TimeUnit.MILLISECONDS)
          .maxConnectionAgeGrace(200, TimeUnit.MILLISECONDS)
          .addService(new GreeterImpl())
          .build();

      server.start();
      server.awaitTermination();
    } catch (Exception e) {
      LOGGER.error("failed to start server.", e);
    }
  }


}
