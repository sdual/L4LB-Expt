package com.github.sdual.l4lbexpt.grpcapp;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.server.annotation.Get;

public class HealthCheckService {

  @Get("/health")
  public HttpResponse checkHealth() {
    return HttpResponse.of("OK", HttpStatus.OK);
  }

}
