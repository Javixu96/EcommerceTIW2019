package es.uc3m.ecommerce.controller;
/*
 * RequestHandler.java
 *
 * Created on 13 de diciembre de 2005, 14:42
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author telmoz
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public interface IHandler {
  /**
   * Interfaz que siguen todos los handlers. Devuelve la vista a la que redirigir, o nulo si hay error
   */

  String handleRequest(HttpServletRequest request, 
		  HttpServletResponse response)
		throws ServletException, IOException;
}
