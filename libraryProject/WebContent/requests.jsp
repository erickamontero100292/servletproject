<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>Lista de todas las Solicitudes</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <!-- The justified navigation menu is meant for single line per list item.
         Multiple lines will require custom code not provided by Bootstrap. -->
    <div class="masthead">
        <h3 class="text-muted">Administraci�n</h3>
        <nav>
            <ul class="nav nav-justified">
                <li><a href="admin?action=crear">Crear Libro</a></li>
                <li><a href="libro?action=lista">Libros</a></li>
                <li><a href="requestForm?action=solicitudes">Solicitudes</a></li>
                <li><a href="admin?action=logout">Salir</a></li>
            </ul>
        </nav>
    </div>
    <br>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><b>Lista de solicitudes recibidas</b></h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="left">Fecha</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Tel�fono</th>
                    <th>Ubicaci�n</th>
                    <th>Libro solicitado</th>
                    <th>Adjuntos</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requests}" var="request" varStatus="status">
                    <tr>
                        <td class="left">${request.dateRequest}</td>
                        <td>${request.name}</td>
                        <td>${request.email}</td>
                        <td>${request.phone}</td>
                        <td>${request.address}</td>
                        <td>${request.book.name}</td>
                        <td>
                            <left>
                                <!-- Mostramos un link para el archivo subido por el usuario. El nombre del archivo lo tenemos
                                guadado en el campo archivo de la tabla solicitud y estan almacenados en la carpeta uploads en nuestro
                                directorio raiz de nuestra aplicacion.
                                -->
                                <a href="uploads/${request.file}" target="_blank">
                                    <img src="images/download.png" title="Descargar adjunto">
                                </a>
                            </left>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Site footer -->
    <footer class="footer">
        <p>&copy; 2019 EAMH.</p>
    </footer>

</div> <!-- /container -->

</body>
</html>
