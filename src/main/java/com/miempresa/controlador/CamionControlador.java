package com.miempresa.controlador;

import com.miempresa.bean.CamionBean;
import com.miempresa.bean.ManejadorBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CamionControlador", urlPatterns = {"/CamionControlador"})
public class CamionControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ManejadorBean manejadorBean = new ManejadorBean(new CamionBean());
        manejadorBean.evaluarParametro(request, response);
        
        String atributoName = manejadorBean.getAtributoName();
        String jspPath = manejadorBean.getJspPath();
        Object objetoEnvio = manejadorBean.getObjetoEnvio();
        
        request.setAttribute(atributoName, objetoEnvio);
        request.getRequestDispatcher(jspPath).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ManejadorBean manejadorBean = new ManejadorBean(new CamionBean());
            manejadorBean.evaluarAccion(request, response);
        } catch (Exception ex) {
           System.out.println("ERROR: "  + ex.getMessage());
        }
    }
}
