<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>Reserva de Servidor</title>
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
        <h3 class="text-muted">Libros disponibles</h3>
        <nav>
          <ul class="nav nav-justified">
            <li><a href="homepage">Inicio</a></li>            
            <li><a href="admin?action=login">Administración</a></li>                        
          </ul>
        </nav>
      </div>
      <br>

      <h4><font color="red">${message}</font></h4>
      
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><b>Solicitar reserva de libro: ${book.name}</b></h3>
        </div>
        <div class="panel-body">

          <form action="requestForm"  method="post" >
             <div class="form-group">
              <label for="name">Nombre</label>
              <input type="text" class="form-control" name="name"   id="name">
            </div>                   
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" class="form-control" name="email"  id="email">
            </div>                   
            <div class="form-group">
              <label for="phone">Teléfono</label>
              <input type="text" class="form-control" name="phone"   id="phone">
            </div>                   
            <div class="form-group">
              <label for="address">Ubicación</label>
              <input type="text" class="form-control" name="address"   id="address">
            </div>                                          
            <%--div class="form-group">
              <label for="archivo">Documentos relacionados:</label>
              <input type="file" required id="archivo" name="archivo">
              <p class="help-block">Solo se permiten archivos con extensiones [ .pdf, .odt ]</p>
            </div>        --%>
            <input type="hidden" value="${book.id}" name="idBook">
            <button type="submit" class="btn btn-success" >Solicitar</button>
          </form>

        </div>
      </div>

      <!-- Site footer -->
      <footer class="footer">
        <p>&copy; 2017 JASR.</p>
      </footer>

    </div> <!-- /container -->

  </body>
</html>
