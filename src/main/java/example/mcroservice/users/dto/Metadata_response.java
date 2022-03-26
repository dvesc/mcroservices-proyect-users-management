package example.mcroservice.users.dto;

import javax.servlet.http.HttpServletRequest;

public class Metadata_response {
  private int current_page;
  private int page_size;
  private Long total_elements;
  private int total_pages;
  private Links_response links;

  //CONTRUCTOR-----------------------------------------------------------------
  public Metadata_response( HttpServletRequest request,
  int current_page,
  int page_size, 
  long total_elements,
  int total_pages
  ) {
    this.current_page = current_page;
    this.page_size = page_size;
    this.total_elements = total_elements;
    this.total_pages = total_pages;
    this.links = new Links_response(request, total_pages);
  }

  //GETTERS AND SETTERS--------------------------------------------------------
  public int getCurrent_page() {
    return current_page;
  }


  public void setCurrent_page(int current_page) {
    this.current_page = current_page;
  }


  public int getPage_size() {
    return page_size;
  }


  public void setPage_size(int page_size) {
    this.page_size = page_size;
  }


  public long getTotal_elements() {
    return total_elements;
  }


  public void setTotal_elements(long total_elements) {
    this.total_elements = total_elements;
  }


  public int getTotal_pages() {
    return total_pages;
  }


  public void setTotal_pages(int total_pages) {
    this.total_pages = total_pages;
  }


  public Links_response getLinks() {
    return links;
  }


  public void setLinks(Links_response links) {
    this.links = links;
  }


  

}
