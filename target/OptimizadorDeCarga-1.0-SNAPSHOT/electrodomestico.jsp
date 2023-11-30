
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
                    Productos
                </li>
                <li>
                    
                    <a href="CamionControlador">Camiones</a>
                </li>
            </ul>
        </nav>
        <br>
        <p> <h2>PRODUCTOS</h2> </p>
        <br>
        <a href="ElectrodomesticoControlador?action=add">Nuevo</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Peso</th>
                <th>Beneficio</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${electrodomesticos}">
                <tr>
                    <td>${item.idElectrodomestico}</td>
                    <td>${item.nombre}</td>
                    <td>${item.pesoKg}</td>
                    <td>${item.beneficio}</td>
                    
                    <td><a href="ElectrodomesticoControlador?action=edit&id=${item.idElectrodomestico}&objeto=electrodomestico">Editar</a></td>
                    <td><a href="ElectrodomesticoControlador?action=delete&id=${item.idElectrodomestico}" onclick="return(confirm('Estas seguro de eliminar???????'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
