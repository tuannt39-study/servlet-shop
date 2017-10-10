<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<c:import url="/fragments/head.jsp" />

<body>

  <c:import url="/fragments/header.jsp" />

  <div id="all">

    <div id="content">
      <div class="container">

        <div class="col-sm-12">

          <div class="row" id="error-page">
            <div class="col-sm-6 col-sm-offset-3">
              <div class="box">

                <p class="text-center">
                  <img src="img/logo.png" alt="Obaju template">
                </p>

                <h3>We are sorry - this page is not here anymore</h3>
                <h4 class="text-muted">Error 404 - Page not found</h4>

                <p class="text-center">To continue please use the <strong>Search form</strong> or <strong>Menu</strong> above.</p>

                <p class="buttons"><a href="index.html" class="btn btn-primary"><i class="fa fa-home"></i> Go to Homepage</a>
                </p>
              </div>
            </div>
          </div>

        </div>
        <!-- /.col-sm-12 -->

      </div>
      <!-- /.container -->
    </div>
    <!-- /#content -->

    <c:import url="/fragments/footer.jsp" /> 

  </div>
  <!-- /#all -->

  <c:import url="/fragments/scripts.jsp" /> 

</body>

</html>