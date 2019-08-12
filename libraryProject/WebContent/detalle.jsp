<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>Detalle del libro- ${book.name}</title>
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
        <h3 class="text-muted">Libros</h3>
        <nav>
          <ul class="nav nav-justified">
            <li><a href="homepage">Inicio</a></li>            
            <li><a href="admin?action=login">Administracion</a></li>
          </ul>
        </nav>
      </div>
      <form method ="post" action="buscar" class="navbar-form navbar-right">
        <div class="form-group">
          <input type="text" name="query" required placeholder="Buscar libros..." class="form-control">
        </div>        
        <button type="submit" class="btn btn-success">Buscar</button>
      </form>
      <br><br><br>

      <div class="panel panel-primary">
        <div class="panel-heading">
          <h3 class="panel-title">Nombre del libro: ${book.name}</h3>
        </div>
        <div class="panel-body">
          <h5><b>Libro</b>: ${book.name}</h5>
          <h5><b>Autor</b>: ${book.author}</h5>
          <h5><b>Fecha de publicacion</b>: ${book.datePublished}</h5>
          <b>Descripcion:</b><br>
          <p class="text-justify">${book.description}</p>
          
          <!--
          Mostramos un boton para permitir a un usuario enviar documentos adjuntos a su solicitud.
          -->          
          <p><a class="btn btn-default btn-success" title="Reservar" href="libro?action=enviar&id=${book.name}" role="button">Reservar</a></p>
        </div>
        
      </div>      
      <!-- Site footer -->
      <footer class="footer">
        <p>&copy; 2017 JASR.</p>
      </footer>

    </div> <!-- /container -->

  </body>
</html>
