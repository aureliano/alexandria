package br.org.alexandria.websecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.alexandria.websecurity.helper.WebHelper;
import br.org.alexandria.websecurity.service.FunctionalityService;

@Controller
public class FunctionalityController {

  @Autowired
  private FunctionalityService functionalityService;

  @Autowired
  private WebHelper webHelper;

}