package example.mcroservice.users.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Links_response {
  private String next;
  private String last;
  private String prev;
  private String first;


  //CONSTRUCTOR----------------------------------------------------------------
  public Links_response( HttpServletRequest request, long total_pages) {
    /*La creacion de los links es en 2 metodos:
      format_query -> donde modificamos el query y obtenemos la posicion de page
      create_links -> donde modificamos el valor de page dependendiendo del caso
    */
    format_query(
      request.getRequestURI(), 
      request.getQueryString(),
      total_pages
    );
  }

  //METODOS--------------------------------------------------------------------
  private Void format_query(
  String path, 
  String query,
  Long total_pages){
    int flag_index = -1, //para si lo encontro o no
      page_number = -1; //el numero de pagina que tiene page
    //Dividimos la cadena para separar a los querys
    ArrayList<String> query_list =new ArrayList<String>(
      Arrays.asList(query.split("&"))
    ); //convertimos a arrayList para mas opciones
    //Si hay querys . . . . . . . . . . . . . . . . . . . . . .
    if (query_list.size() != 0){
      Pattern regexp = Pattern.compile("page=\\d{1,}");
      //buscamos a page
      for (int i = 0; i < query_list.size(); i++) {
        //evaluemos con la regexp
        if(regexp.matcher(query_list.get(i)).matches()){
          page_number = Integer.parseInt(
            query_list.get(i).split("=")[1] //obtengo el valor de page
          ); 
          flag_index = i;
          break;  
        }
      }
      //si despues de la busqueda no lo encontro lo agrega
      if(flag_index < 0){
        flag_index = query_list.size();//si hay "5" la ultima posicion es "4", entonces un 6to tendria posicion "5" (el ultimo)
        query_list.add("page=1"); 
        page_number = 1;
      }
    } 
    else{ //si no hay querys
      page_number = 1;
      query_list.add("page=1"); //se sobreentiende que si no habia page entonces estaba en la pag1
      flag_index = 0; //osea el primer elem 
    }

    //Convertimos de list a array a string y unimos con el path
    String complete_url = path+"?"+String.join("&", 
      query_list.stream().toArray(String[]::new));
    
    create_links(page_number, total_pages, complete_url);
    return null;
  }


  private Void create_links (
  int page_number,
  Long total_pages,
  String complete_url) {
    String next = "",
      last = "",
      prev = "",
      first = "";

    if (page_number > 0 && page_number < total_pages) {
      next = complete_url.replaceAll("page=\\d{1,}","page="+(page_number+1));
      last = complete_url.replaceAll("page=\\d{1,}","page="+total_pages);
    }
    if (page_number > 1) {
      prev = complete_url.replaceAll("page=\\d{1,}","page="+(page_number-1));
      first = complete_url.replaceAll("page=\\d{1,}","page=1");
    }
    
    //Asignamos los valores 
    setNext(next);
    setLast(last);
    setFirst(first);
    setPrev(prev);

    return null;
  }


  //GETTERS AND SETTERS--------------------------------------------------------
  public String getNext() {
    return next;
  }
  public void setNext(String next) {
    this.next = next;
  }
  public String getLast() {
    return last;
  }
  public void setLast(String last) {
    this.last = last;
  }


  public String getPrev() {
    return prev;
  }


  public void setPrev(String prev) {
    this.prev = prev;
  }


  public String getFirst() {
    return first;
  }


  public void setFirst(String first) {
    this.first = first;
  }

  

}
