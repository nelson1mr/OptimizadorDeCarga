
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
        <h1>Usuario</h1>
        <br>
        <a href="UsuarioControlador?action=add">Nuevo</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Correo</th>
                <th>Nombre</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${usuarios}">
                <tr>
                    <td>${item.idUsuario}</td>
                    <td>${item.correo}</td>
                    <td>${item.nombre}</td>
                    
                    <td><a href="UsuarioControlador?action=edit&id=${item.idUsuario}&objeto=usuario">Editar</a></td>
                    <td><a href="UsuarioControlador?action=delete&id=${item.idUsuario}" onclick="return(confirm('Estas seguro de eliminar???????'))">Eliminar</a></td>
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
