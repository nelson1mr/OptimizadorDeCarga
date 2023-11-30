<%@page import="java.util.List"%>
<%@page import="com.miempresa.entidades.Camion"%>
<%@page import="com.miempresa.entidades.Electrodomestico"%>
<%@page import="com.miempresa.entidades.ModeloReporteEnvio"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/StyleForm.css">
    </head>
    <body>

        <h1>
            Registrar un camion para optimizar envios
        </h1>
        <p><a href="ReporteControlador">Volver</a></p>

        <form action="ReporteControlador" method="POST">

            <table>
                <tr>
                    <th>Camiones</th>
                    <th></th>
                </tr>
                <p>Seleccione el camion que hará el envio</p>
                <tr>
                    
                    <td>
                        <select name="idCamion">
                            <option value="">--- Seleccione ---</option>
                            <c:forEach var="item" items="${camiones}">
                                <option value="${item.idCamion}" 
                                        <c:if test="${camion.idCamion == item.idCamion}">
                                            selected
                                        </c:if>
                                            > ${item.modelo} - - - [Kg]${item.capacidadKg}</option>

                            </c:forEach>
                        </select>
                        
                    </td>
                    
                    <td><input type="submit" value="Cargar y Enviar"></td> 
                    
                </tr>
                
                    
            </table>


            
        </form>



    </body>
</html>
