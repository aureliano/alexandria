package br.org.alexandria.websecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.alexandria.commons.helper.ExceptionHelper;
import br.org.alexandria.commons.helper.WebHelper;

@Configuration
public class BeansConfiguration {

  @Bean
  public WebHelper getWebHelper () {
    return new WebHelper ();
  }

  @Bean
  public ExceptionHelper getExceptionHelper () {
    return new ExceptionHelper ();
  }
}