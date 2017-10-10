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
              <li><a href="#">Home</a></li>
              <li><a href="#">Category</a></li>
              <li>${category.name}</li>
            </ul>

            <div class="row products">
              <c:forEach var="product" items="${products}">
                  <div class="col-sm-4 col-sm-6">
                    <div class="product">
                      <a href="detail?id=${product.id}">
                        <img src="<c:url value="static/upload/${product.image}" />"
                             alt="Product" class="img-responsive img-product" />
                      </a>
                      <div class="text">
                        <h3><a href="detail?id=${product.id}">${product.name}</a></h3>
                        <p class="price">
                          <fmt:formatNumber value="${product.price}" type="number"/> đ
                        </p>
                        <p class="buttons">
                          <a href="product.html?id=${product.id}" class="btn btn-default">
                            <i class="fa fa-eye"></i>View detail
                          </a>
                          <a href="cart.html?action=add&id=${product.id}" class="btn btn-primary">
                            <i class="fa fa-shopping-cart"></i>Add to cart
                          </a>
                        </p>
                      </div>
                      <!-- /.text -->
                    </div>
                    <!-- /.product -->
                  </div>
              </c:forEach>
            </div>
            <!-- /.products -->

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