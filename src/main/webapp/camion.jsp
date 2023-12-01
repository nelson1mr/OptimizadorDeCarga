
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <link rel="icon" type="image/x-icon" href="Recursos/logo.png">
    </head>
    <body>
        <h1>
            <img src="Recursos/logo.png" class="logo">SISTEMA DE OPTIMIZACIÓN DE TRANSPORTE DE CARGA
        </h1>
        <nav>
            <h3>Opciones</h3>
            <ul>
                <li>
                    <a href="ReporteControlador">Generar reporte</a>
                </li>
                <li>
                    <a href="ElectrodomesticoControlador">Productos</a>
                </li>
                <li>
                    Caminones
                </li>
            </ul>
        </nav>      
        <br> 
        <h2>CAMIONES</h2>
        <br>
        <a href="CamionControlador?action=add">Nuevo</a>
        <table>

            <tr>
                <th>Id</th>
                <th>Modelo</th>
                <th>Capacidad</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${camiones}">
                <tr>
                    <td>${item.idCamion}</td>
                    <td>${item.modelo}</td>
                    <td>${item.capacidadKg}</td>

                    <td><a href="CamionControlador?action=edit&id=${item.idCamion}&objeto=camion">Editar</a></td>
                    <td><a href="CamionControlador?action=delete&id=${item.idCamion}" onclick="return(confirm('Estas seguro de eliminar???????'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
        <footer>
            <br>
            <div class="container">
                <p>
                <table border = 0>
                    <tr>
                        <td>Ingeniería de Sistemas</td>
                        <td>Desarrolado por:</td>
                        <td><ul>
                                <li>Nelson Mamani Ramos</li>
                                <li>Nicol Carla Cochi Muñoz</li>
                                <li>Noelia Argana Callisaya</li>
                            </ul>
                        </td>
                    </tr>
                </table>
                </p>
            </div>
        </footer>
    </body>
</html>
