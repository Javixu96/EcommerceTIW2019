package es.uc3m.ecommerce.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public interface RequestHandler {
  
  String handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext appContext) throws ServletException, IOException;
  
}
