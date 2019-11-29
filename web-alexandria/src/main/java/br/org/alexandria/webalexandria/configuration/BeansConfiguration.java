package br.org.alexandria.webalexandria.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.alexandria.commons.helper.ExceptionHelper;

@Configuration
public class BeansConfiguration {

  @Bean
  public ExceptionHelper getExceptionHelper () {
    return new ExceptionHelper ();
  }
}