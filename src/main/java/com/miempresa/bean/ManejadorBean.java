package com.miempresa.bean;

import com.miempresa.algoritmo.AlgoritmoGenetico;
import com.miempresa.entidades.Camion;
import com.miempresa.entidades.Electrodomestico;
import com.miempresa.entidades.Envio;
import com.miempresa.entidades.EnvioElectrodomestico;
import com.miempresa.entidades.ModeloReporteEnvio;
import com.miempresa.entidades.Usuario;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManejadorBean {

    private String atributoName = "";
    private String jspPath = "";
    private Object objetoEnvio = new Object();

    private CamionBean camionBean;
    private ReporteBean reporteBean;
    private ElectrodomesticoBean electrodomesticoBean;
    private UsuarioBean usuarioBean;

    public ManejadorBean(CamionBean injeccion) {
        camionBean = injeccion;
    }

    public ManejadorBean(ReporteBean injeccion) {
        reporteBean = injeccion;
    }

    public ManejadorBean(ElectrodomesticoBean injeccion) {
        electrodomesticoBean = injeccion;
    }

    public ManejadorBean(UsuarioBean injeccion) {
        usuarioBean = injeccion;
    }
   

    public void evaluarParametro(HttpServletRequest request, HttpServletResponse response) {

        if (request.getRequestURI().equals("/GenDelivery/CamionControlador")) {

            if (request.getParameter("action") == null) {
                atributoName = "camiones";
                jspPath = "camion.jsp";
                objetoEnvio = camionBean.obtenerCamiones();
            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "camion";
                    jspPath = "frmcamion.jsp";
                    objetoEnvio = new Camion();
                    
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "camion";
                    jspPath = "frmcamion.jsp";
                    objetoEnvio = camionBean.traerCamion(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "camiones";
                    jspPath = "camion.jsp";
                    camionBean.eliminarCamion(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = camionBean.obtenerCamiones();
                }
            }

        } else if (request.getRequestURI().equals("/GenDelivery/ReporteControlador")) {
            if (request.getParameter("action") == null) {
                atributoName = "reportes";
                jspPath = "reporte.jsp";
                objetoEnvio = reporteBean.obtenerReporte();
                
            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "reporte";
                    jspPath = "frmreporte.jsp";
                    
                    objetoEnvio = new ModeloReporteEnvio();
                    
                    CamionBean camiones = new CamionBean();
                    ElectrodomesticoBean electro = new ElectrodomesticoBean();
                    
                    request.setAttribute("camiones", camiones.obtenerCamiones());
                    
                } 
                
            }

        } else if (request.getRequestURI().equals("/GenDelivery/ElectrodomesticoControlador")) {
            if (request.getParameter("action") == null) {
                atributoName = "electrodomesticos";
                jspPath = "electrodomestico.jsp";
                objetoEnvio = electrodomesticoBean.obtenerElectrodomesticos();

            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "electrodomestico";
                    jspPath = "frmelectrodomestico.jsp";
                    objetoEnvio = new Electrodomestico();
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "electrodomestico";
                    jspPath = "frmelectrodomestico.jsp";
                    objetoEnvio = electrodomesticoBean.traerElectrodomestico(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "electrodomesticos";
                    jspPath = "electrodomestico.jsp";
                    electrodomesticoBean.eliminarElectrodomestico(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = electrodomesticoBean.obtenerElectrodomesticos();
                }
            }

        } else if (request.getRequestURI().equals("/GenDelivery/UsuarioControlador")) {
            if (request.getParameter("action") == null) {
                atributoName = "usuarios";
                jspPath = "usuario.jsp";
                objetoEnvio = usuarioBean.obtenerUsuarios();

            } else {
                if (request.getParameter("action").equals("add")) {
                    atributoName = "usuario";
                    jspPath = "frmusuario.jsp"; 
                    objetoEnvio = new Usuario();
                } else if (request.getParameter("action").equals("edit")) {
                    atributoName = "usuario";
                    jspPath = "frmusuario.jsp";
                    objetoEnvio = usuarioBean.traerUsuario(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = usuarioBean.traerUsuario(Integer.parseInt(request.getParameter("id")));
                } else if (request.getParameter("action").equals("delete")) {
                    atributoName = "usuarios";
                    jspPath = "usuario.jsp";
                    usuarioBean.eliminarUsuario(Integer.parseInt(request.getParameter("id")));
                    objetoEnvio = usuarioBean.obtenerUsuarios();
                }
            }

        }

    }

    
    public void evaluarAccion(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        if (request.getRequestURI().equals("/GenDelivery/CamionControlador")) {
            
            String sdf = request.getParameter("id");
            
            if (request.getParameter("id").equals("")) {
                
                Camion camion = new Camion();
                camion.setModelo(request.getParameter("modelo"));
                camion.setCapacidadKg(new BigDecimal(request.getParameter("capacidad")));

                CamionBean bean = new CamionBean();
                bean.addCamion(camion);
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/CamionControlador");
                
            } else {
                
                String sddd = request.getParameter("id");
                
                Camion camion = new Camion();
                camion.setIdCamion(Integer.valueOf(request.getParameter("id")));
                camion.setModelo(request.getParameter("modelo"));
                camion.setCapacidadKg((new BigDecimal(request.getParameter("capacidad"))));

                CamionBean bean = new CamionBean();
                bean.editarCamion(camion);
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/CamionControlador");
            }
            
            
        } else if (request.getRequestURI().equals("/GenDelivery/ReporteControlador")) {
            
            String a = request.getParameter("action");
            
            if (request.getParameter("action") == null) {
                String parametro = request.getParameter("idCamion");
                
                Camion camion = camionBean.obtenerCamion(Integer.parseInt(parametro));
                int capacidadCamion = camion.getCapacidadKg().intValue();
                
                
                /*Aqui va mi algoritmo*/
                AlgoritmoGenetico poblacion = new AlgoritmoGenetico();
                List<Electrodomestico> elestrodesticos = poblacion.algoritmoGenetico(camion.getCapacidadKg().intValue());
                /*Aqui va mi algoritmo*/
                
                
                /*llenamos la tabla envio*/
                Envio envio = new Envio();
                envio.setIdCamion(camion);
                Date fecha = new Date();
                envio.setFechaEnvio(fecha);
                
                EnvioBean envioBean = new EnvioBean();
                envioBean.addEnvio(envio);
                
                
                
                /*llenamos la tabla Envio_Electrodomestico*/
                for (int i = 0; i < elestrodesticos.size(); i++) {
                    EnvioElectrodomestico ee = new EnvioElectrodomestico();
                    ee.setIdEnvio(envioBean.obtenerEnvioUltimo());
                    ee.setIdElectrodomestico(elestrodesticos.get(i));
                    
                    EnvioElectroBean eeBean = new EnvioElectroBean();
                    eeBean.addEe(ee);
                }
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/ReporteControlador");
                
            } else {
                
            }
        }
        else if (request.getRequestURI().equals("/GenDelivery/ElectrodomesticoControlador")) {
            
            String a = request.getParameter("id");
            
            if (request.getParameter("id").equals("")) {
                Electrodomestico electro = new Electrodomestico();
                electro.setNombre(request.getParameter("nombre"));
                electro.setPesoKg(new BigDecimal(request.getParameter("peso")));
                electro.setBeneficio(Integer.valueOf(request.getParameter("beneficio")));

                ElectrodomesticoBean bean = new ElectrodomesticoBean();
                bean.addElectrodomestico(electro);
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/ElectrodomesticoControlador");
            } else {
                
                String av = request.getParameter("id");
                        
                Electrodomestico electro = new Electrodomestico();
                electro.setIdElectrodomestico(Integer.valueOf(request.getParameter("id")));
                electro.setNombre(request.getParameter("nombre"));
                electro.setPesoKg(new BigDecimal(request.getParameter("peso")));
                electro.setBeneficio(Integer.valueOf(request.getParameter("beneficio")));

                ElectrodomesticoBean bean = new ElectrodomesticoBean();
                bean.editarElectrodomestico(electro);
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/ElectrodomesticoControlador");
            }
        } 
        else if (request.getRequestURI().equals("/GenDelivery/UsuarioControlador")) {
            if (request.getParameter("id").equals("")) {
                Usuario usuario = new Usuario();
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setContrasena(request.getParameter("contrasena"));
                usuario.setCorreo(request.getParameter("correo"));

                UsuarioBean bean = new UsuarioBean();
                bean.addUsuario(usuario);
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/UsuarioControlador");
                
            } else {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.valueOf(request.getParameter("id")));
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setContrasena(request.getParameter("contrasena"));
                usuario.setCorreo(request.getParameter("correo"));

                UsuarioBean bean = new UsuarioBean();
                bean.editarUsuario(usuario);
                
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/UsuarioControlador");
            }
        }
    }

    
    
    public String getAtributoName() {
        return atributoName;
    }

    public void setAtributoName(String atributoName) {
        this.atributoName = atributoName;
    }

    public String getJspPath() {
        return jspPath;
    }

    public void setJspPath(String jspPath) {
        this.jspPath = jspPath;
    }

    public Object getObjetoEnvio() {
        return objetoEnvio;
    }

    public void setObjetoEnvio(Object objetoEnvio) {
        this.objetoEnvio = objetoEnvio;
    }

    

}
