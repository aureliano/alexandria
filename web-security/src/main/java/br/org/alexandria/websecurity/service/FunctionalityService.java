package br.org.alexandria.websecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.alexandria.websecurity.repository.FunctionalityRepository;

@Service
public class FunctionalityService {

  @Autowired
  private FunctionalityRepository functionalityRepository;
}