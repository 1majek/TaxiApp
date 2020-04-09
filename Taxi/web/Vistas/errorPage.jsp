<%-- 
    Document   : errorPage
    Created on : 28-jun-2019, 4:33:57
    Author     : Steven Majek
--%>

<%@page contentType="text/html" isErrorPage="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="bootStrap.html"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Error Page</title>
        </head>
        <body>
        <%
            String error = (String) request.getAttribute("error");
            String str = (String) session.getAttribute("error");
        %>
        <div class="alert alert-danger">
            <strong>Error!</strong> Algo ha fallado, asegurate de haber introducido todos los datos. También asegurate de que no lleve ningún apóstrofe (')<a href='' data-toggle="modal" data-target="#error" class="alert-link"> Más info</a>
        </div>

        <div class="modal fade" id="error" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header alert-danger">
                        <h5 class="modal-title">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><%=error%></p>
                    </div>
                    <div class="modal-footer">
                        <a class="nav-link alert-danger"  href="Controlador?accion=formulario" data-dismiss="modal">Cerrar</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
