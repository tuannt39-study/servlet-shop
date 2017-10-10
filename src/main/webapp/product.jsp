<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

  <c:import url="/fragments/head.jsp" />

  <body>

    <c:import url="/fragments/header.jsp" />

    <div id="all">

      <div id="content">
        <div class="container">

          <div class="col-sm-3">
            <!-- *** MENUS *** -->
            <div class="panel panel-default sidebar-menu">

              <div class="panel-heading">
                <h3 class="panel-title">Categories</h3>
              </div>

              <div class="panel-body">
                <ul class="nav nav-pills nav-stacked category-menu">
                  <c:forEach var="category" items="${categories}">
                      <li>
                        <a href="category.html?id=${category.id}">${category.name}</a>
                      </li>
                  </c:forEach>
                </ul>
              </div>
            </div>
            <!-- *** MENUS END *** -->
          </div>

          <div class="col-sm-9">
            <ul class="breadcrumb">
              <li><a href="index.html">Home</a></li>
              <li>Product</li>
              <li>${product.name}</li>
            </ul>

            <div class="row box" id="productMain">
              <div class="col-sm-12">
                <h4 class="mainHeading">Product Details</h4>
              </div>

              <div class="col-sm-3">
                <div id="mainImage">
                  <img src="<c:url value="static/upload/${product.image}" />" 
                       alt="Product" class="img-responsive" />
                </div>
                <p class="buttons">
                  <a href="cart.html?action=add&id=${product.id}" class="btn btn-primary"> 
                    <i class="fa fa-shopping-cart"></i>Add to cart
                  </a>
                </p>
              </div>

              <div class="col-sm-9">
                <table class="table table-bordered">
                  <tr>
                    <td>Name</td>
                    <td>${product.name}</td>
                  </tr>
                  <tr>
                    <td>Price</td>
                    <td><fmt:formatNumber value="${product.price}" type="number"/> đ</td>
                  </tr>
                  <tr>
                    <td>Description</td>
                    <td>${product.description}</td>
                  </tr>
                </table>
              </div>
            </div>

            <div class="box" id="details">
              <div class="social">
                <h4>Show it to your friends</h4>
                <p>
                  <a href="#" class="external facebook" data-animate-hover="pulse"><i class="fa fa-facebook"></i></a>
                  <a href="#" class="external gplus" data-animate-hover="pulse"><i class="fa fa-google-plus"></i></a>
                  <a href="#" class="external twitter" data-animate-hover="pulse"><i class="fa fa-twitter"></i></a>
                  <a href="#" class="email" data-animate-hover="pulse"><i class="fa fa-envelope"></i></a>
                </p>
              </div>
            </div>

          </div>
          <!-- /.col-sm-9 -->
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