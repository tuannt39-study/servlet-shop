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
          <div class="row">

            <div class="col-sm-10 col-sm-offset-1">
              <ul class="breadcrumb">
                <li><a href="#">Home</a>
                </li>
                <li>Shopping cart</li>
              </ul>
            </div>

            <div class="col-sm-10 col-sm-offset-1">
              <c:if test="${not empty error}">
                  <div class="col-sm-10 col-sm-offset-1 alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <i class="icon fa fa-exclamation-circle"></i> ${error}
                  </div>
                  <c:remove var="error" scope="session" />
              </c:if>
            </div>

            <div class="col-sm-10 col-sm-offset-1" id="basket">

              <div class="box">
                <form action="cart.html" method="POST">
                  <h3>Shopping cart</h3>
                  <p class="text-muted">You currently have ${cart.count} items in your cart.</p>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th colspan="2">Product</th>
                          <th>Quantity</th>
                          <th>Price</th>
                          <th colspan="2">Sub Total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="item" items="${cart.items}">
                            <tr>
                              <td>
                                <a href="product.html?id=item.product.id">
                                  <img src="static/upload/${item.product.image}" alt="Product" />
                                </a>
                              </td>
                              <td>
                                <a href="product.html?id=${item.product.id}">${item.product.name}</a>
                              </td>
                              <td>
                                <input type="number" name="${item.product.id}" value="${item.quantity}" 
                                       class="form-control" min="0" max="10" />
                              </td>
                              <td><fmt:formatNumber value="${item.product.price}" type="number" /> đ</td>
                              <td><fmt:formatNumber value="${item.subTotal}" type="number" /> đ</td>
                              <td>
                                <a href="cart.html?action=remove&id=${item.product.id}"><i class="fa fa-trash-o"></i></a>
                              </td>
                            </tr>
                        </c:forEach>
                      </tbody>
                      <tfoot>
                        <tr>
                          <th colspan="4">Total</th>
                          <th colspan="2"><fmt:formatNumber value="${cart.total}" type="number" /> đ</th>
                        </tr>
                      </tfoot>
                    </table>

                  </div>
                  <!-- /.table-responsive -->

                  <div class="box-footer">
                    <div class="pull-left">
                      <a href="index.html" class="btn btn-default"><i class="fa fa-chevron-left"></i> Continue shopping</a>
                    </div>
                    <div class="pull-right">
                      <button type="submit" class="btn btn-default"><i class="fa fa-refresh"></i> Update shopping cart</button>
                      <a href="checkout.html" class="btn btn-primary">Proceed to checkout <i class="fa fa-chevron-right"></i>
                      </a>
                    </div>
                  </div>
                </form>
              </div>
              <!-- /.box -->

            </div>
            <!-- /.col-sm-9 -->

          </div>
          <!-- /.row -->
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