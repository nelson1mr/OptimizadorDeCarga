
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
                    Generar reporte
                </li>
                <li>
                    
                    <a href="ElectrodomesticoControlador">Productos</a>
                </li>
                <li>
                    <a href="CamionControlador">Camiones</a>
                </li>
                
            </ul>
        </nav>
        <br>
        <p> <h2>REPORTE</h2> </p>
        <br>
        <a href="ReporteControlador?action=add">Nuevo / Usar Algoritmo Genetico</a>
        <table>
            <tr>
                <th>Id Envio</th>
                <th>Camion</th>
                <th>Capacidad Camion</th>
                <th>Fecha Envio</th>
                <th>Producto</th>
                <th>Peso Producto</th>
            </tr>
            <c:forEach var="item" items="${reportes}">
                <tr>
                    <td>${item.idEnvio}</td>
                    <td>${item.modelo}</td>
                    <td>${item.capacidadKg}</td>
                    <td>${item.fechaEnvio}</td>
                    <td>${item.nombre}</td>
                    <td>${item.pesoKg}</td>
                    
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
