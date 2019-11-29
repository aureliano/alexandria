package br.org.alexandria.webalexandria.dto;

import java.util.List;

public class PageDTO<T> {

  private List<T> data;
  private Integer pageNumber;
  private Integer pageSize;
  private Long totalElements;
  private Integer totalPages;

  public List<T> getData () {
    return data;
  }

  public void setData (List<T> data) {
    this.data = data;
  }

  public Integer getPageNumber () {
    return pageNumber;
  }

  public void setPageNumber (Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize () {
    return pageSize;
  }

  public void setPageSize (Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Long getTotalElements () {
    return totalElements;
  }

  public void setTotalElements (Long totalElements) {
    this.totalElements = totalElements;
  }

  public Integer getTotalPages () {
    return totalPages;
  }

  public void setTotalPages (Integer totalPages) {
    this.totalPages = totalPages;
  }
}