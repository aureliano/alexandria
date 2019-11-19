package br.org.alexandria.webgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class WebGatewayApplication {

  public static void main (String[] args) {
    SpringApplication.run (WebGatewayApplication.class, args);
  }
}