<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="xyz.zelamkin.MFAN.pojo.Product"%>
<%@ page import="xyz.zelamkin.MFAN.pojo.User"%>
<%
User user = (User)request.getAttribute("User");
%>
<%@ page import="java.util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
		
	<head>
		<base href="<%=basePath%>" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Corasun - Responsive Bootstrap 4 Admin Dashboard</title>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
		<!-- google font -->
		<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet" type="text/css" />
		<link href="http://cdn.bootstrapmb.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="assets/css/ionicons.css" rel="stylesheet" type="text/css">
		<link href="assets/css/simple-line-icons.css" rel="stylesheet" type="text/css">
		<link href="assets/css/themify-icons.css" rel="stylesheet" type="text/css">
		<link href="assets/css/jquery.mCustomScrollbar.css" rel="stylesheet">
		<link href="assets/css/weather-icons.min.css" rel="stylesheet">
<link href="assets/css/jquery.easy-pie-chart.css" rel="stylesheet">

		<link href="assets/css/style.css" rel="stylesheet">
		<link href="assets/css/header.css" rel="stylesheet">
		<link href="assets/css/menu.css" rel="stylesheet">
		<link href="assets/css/index.css" rel="stylesheet">
		<link href="assets/css/responsive.css" rel="stylesheet">

<% 
	String message = (String)request.getAttribute("message");
	if(message!=null)
	{
%>
		<script type="text/javascript">
      		alert("<%=message%>");
		</script>
<%
	}
%>

	</head>

	<body>
		<div class="wrapper">

			<!-- header -->
			<div class="header-bg">
				<header class="main-header">
					<div class="container_header phone_view border_top_bott">

						<div class="row">
							<div class="col-md-12">
								<div class="logo d-flex align-items-center">
									<a href="#"> <strong class="logo_icon"> <img src="assets/images/small-logo.png" alt=""> </strong> <span class="logo-default"> <img src="assets/images/logo.png" alt=""> </span> </a>
									<div class="icon_menu">
										<a href="#" class="menu-toggler sidebar-toggler"></a>
									</div>
								</div>

								<div class="right_detail d-flex justify-content-end">
									<div class="row full_width">
										<div class="col-xl-12 col-12 d-flex justify-content-end">
											<div class="right_bar_top d-flex align-items-center justify-content-md-between justify-content-end">

												
													<div class="d-lg-block d-xs-none">
														<form role="search" class="search-form form_show" action="#">
															<div class="input-group">
																<input type="text" class="form-control" placeholder="Search...">
																<button type="button" class="btn" data-target="#" data-toggle="" aria-label="Close">
																	<i class="ion-android-search"></i>
																</button>
															</div>
														</form>
													</div>

												</div>

												<div class="align_end">
												<!-- notification_Start -->
												<div class="dropdown dropdown-notification">
													<ul class="dropdown-menu scroll_auto height_fixed" >
														<li class="bigger">
															<h3><span class="bold">Notifications</span></h3>
															<span class="notification-label">New 6</span>
														</li>
														<li>
															<ul class="dropdown-menu-list">
																<li>
																	<a href="javascript:;"> <span class="time">just now</span> <span class="details"> <span class="notification-icon deepPink-bgcolor"> <i class="fa fa-check"></i> </span> Congratulations!. </span> </a>
																</li>
																<li>
																	<a href="javascript:;"> <span class="time">3 mins</span> <span class="details"> <span class="notification-icon purple-bgcolor"> <i class="fa fa-user o"></i> </span> <b>John Micle </b>is now following you. </span> </a>
																</li>
																<li>
																	<a href="javascript:;"> <span class="time">7 mins</span> <span class="details"> <span class="notification-icon blue-bgcolor"> <i class="fa fa-comments-o"></i> </span> <b>Sneha Jogi </b>sent you a message. </span> </a>
																</li>
																<li>
																	<a href="javascript:;"> <span class="time">12 mins</span> <span class="details"> <span class="notification-icon pink"> <i class="fa fa-heart"></i> </span> <b>Ravi Patel </b>like your photo. </span> </a>
																</li>
																<li>
																	<a href="javascript:;"> <span class="time">15 mins</span> <span class="details"> <span class="notification-icon yellow"> <i class="fa fa-warning"></i> </span> Warning! </span> </a>
																</li>
																<li>
																	<a href="javascript:;"> <span class="time">10 hrs</span> <span class="details"> <span class="notification-icon red"> <i class="fa fa-times"></i> </span> Application error. </span> </a>
																</li>
															</ul>
														</li>
													</ul>
												</div>
												<!-- notification_End -->

												<!-- Dropdown_User -->
												<div class="dropdown dropdown-user">
													<ul class="dropdown-menu dropdown-menu-default">
														<li>
															<div class="user-panel">
																<div class="user_image">
																	<img src="assets/images/user3.png" class="img-circle mCS_img_loaded" alt="User Image">
																</div>
																<div class="info">
																
																	<a href="#"> pabola@gmail.com</a>
																</div>
															</div>
														</li>
														<li>
															<a href="#"> <i class="icon-user"></i> Profile </a>
														</li>
														<li>
															<a href="#"> <i class="icon-settings"></i> Settings </a>
														</li>
														<li>
															<a href="#"> <i class="icon-directions"></i> Help </a>
														</li>
														<li class="divider"></li>
														<li>
															<a href=""> <i class="icon-lock"></i> Lock </a>
														</li>
														<li>
															<a href="#"> <i class="icon-logout"></i> Log Out </a>
														</li>
													</ul>
												</div>

											</div>

											</div>
										</div>

									</div>
								</div>

							</div>
						</div>
					</div>

				</header>

			</div>
			<!-- header_End -->

			<!-- Content_right -->
			<div class="container_full">

				<div class="side_bar scroll_auto">
					<div class="user-panel">
						<div class="user_image">
						
						<% 
						if(user!=null)
						{
							%>
							
								<img src="GetHeadImage" class="img-circle mCS_img_loaded" alt="User Image">
						</div>
						<div class="info">
						<a href="${pageContext.request.contextPath}/user/UserInformation">
							<p>
								<%=user.getUsername() %>
							</p>
							<a href="${pageContext.request.contextPath}/user/Logout"> <i class="fa fa-circle text-success"></i>点击登出</a>
							<a href="${pageContext.request.contextPath}/user/ChangeHead">更换头像</a>
							<%
						}else
						{
							%>
						</div>
						<div class="info">
						<a href="${pageContext.request.contextPath}/user/UserInformation">
							<p>
								未登录
							</p>
							<a href="${pageContext.request.contextPath}/user/ToLogin"> <i class="fa fa-circle text-success"></i>点击登录</a>
							<%
						}
						%>
						</a>
						</div>
					</div>

					<ul id="dc_accordion" class="sidebar-menu tree">

					<li>
						<a href="page/ToIndex"> <i class="ti-home"></i> <span>主页</span></a>
					</li>

					<li class="menu_sub">
						<a href="#"> <i class="fa fa-handshake-o"></i> <span>客户</span> <span class="icon-arrow-down styleicon"></span> </a>
						<ul class="down_menu">
							<li>
								<a href="${pageContext.request.contextPath}/customer/AllCustomers">客户查询</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/customer/MyCustomers">我的客户</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/customer/AddCustomer">添加客户</a>
							</li>
						</ul>
					</li>

					<li class="menu_sub">
						<a href="#"> <i class="ti-pencil-alt"></i> <span>部门</span> <span class="icon-arrow-down styleicon"></span> </a>
						<ul class="down_menu">
							<li>
								<a href="${pageContext.request.contextPath}/department/AllDepartments">部门查询</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/department/AddDepartment">添加部门</a>
							</li>

						</ul>
					</li>

					<li class="menu_sub">
						<a href="#"> <i class="fa fa-table"></i> <span>订单</span> <span class="icon-arrow-down styleicon"></span> </a>
						<ul class="down_menu">
							<li>
								<a href="${pageContext.request.contextPath}/order/AllOrders">订单查询</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/order/MyOrders">我的订单</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/order/AddOrder">添加订单</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/order/AddOrderTemporary">添加临时订单</a>
							</li>
						</ul>
					</li>

					<li class="menu_sub">
						<a href="#"> <i class="fa fa-wheelchair-alt"></i> <span>产品</span> <span class="icon-arrow-down styleicon"></span> </a>
						<ul class="down_menu">
							<li>
								<a href="${pageContext.request.contextPath}/product/AllProducts">产品查询</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/product/AddProduct">添加产品</a>
							</li>

						</ul>
					</li>

					<li class="menu_sub">
						<a href="#"> <i class="fa fa-users"></i> <span>用户</span> <span class="icon-arrow-down styleicon"></span> </a>
						<ul class="down_menu">
							<li>
								<a href="${pageContext.request.contextPath}/user/AllUsers">用户查询</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/AddUser">添加用户</a>
							</li>

						</ul>
					</li>
					<li class="menu_sub">
						<a href="#"> <i class="fa fa-database"></i> <span>库存</span> <span class="icon-arrow-down styleicon"></span> </a>
						<ul class="down_menu">
							<li>
								<a href="${pageContext.request.contextPath}/storage/AllStorages">库存查询</a>
							</li>
						</ul>
					</li>

					<li class="menu_sub">

				</ul>

			</div>

			<!--main contents start-->
			<main class="content_wrapper">
				<!--page title start-->
				<div class="page-heading">
					<div class="container-fluid">
						<div class="row d-flex align-items-center">
							<div class="col-md-6">
								<div class="page-breadcrumb">
									<h1>部门信息</h1>
								</div>
							</div>
							<div class="col-md-6 justify-content-md-end d-md-flex">
								<div class="breadcrumb_nav">
									<ol class="breadcrumb">
										<li>
											<i class="fa fa-home"></i>
											<a class="parent-item" href="${pageContext.request.contextPath}/page/ToIndex">主页</a>
											<i class="fa fa-angle-right"></i>
										</li>
										<li class="active">
											部门信息
										</li>
									</ol>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--page title end-->
				<div class="container-fluid">
					<!-- state start-->
					<div class="row">
						<div class=" col-md-12">
							<div class="card card-shadow mb-4">
								<div class="card-header">
									<div class="card-title">
										部门信息
									</div>
								</div>
								<%
		Product product = (Product)request.getAttribute("Product");
	%>
								<div class="card-body">
									<form id="signupForm" method="post" class=" right-text-label-form" action="${pageContext.request.contextPath}/product/ProductFormUpdataSubmit">
										<div class="form-group row">
											<label class="col-sm-4 control-label" for="title">Product Name</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" id="title" name="title" placeholder="title" value="<%=product.getTitle()%>" />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-sm-4 control-label" for="price">Price</label>
											<div class="col-sm-5">
												<input type="price" class="form-control" id="price" name="price" placeholder="price" value="<%=product.getPrice()%>" />
											</div>
										</div>
										<div class="form-group row">
											<div class="col-sm-8 ml-auto">
												<button type="submit" class="btn btn-primary" name="" value="">
														OK
													</button>
												<a onclick="javascript:history.back(-1);" style="margin-left:20px;">
													<button class="btn btn-primary">
													返回
													</button>
												</a>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>

				</div>
			</main>
			<!--main contents end-->
		</div>
		<!-- Content_right_End -->
		<!-- Footer -->
		<footer class="footer ptb-20">
			<div class="row">
				<div class="col-md-12 text-center">
					<div class="copy_right">
						<p>
							2019 © Dashboard Theme By
							<a href="http://www.bootstrapmb.com/">Corasun.</a>
						</p>
					</div>
					<a id="back-to-top" href="#"> <i class="ion-android-arrow-up"></i> </a>
				</div>
			</div>
		</footer>
		<!-- Footer_End -->
		</div>
		<script type="text/javascript" src="assets/js/jquery.min.js"></script>
		<script type="text/javascript" src="assets/js/popper.min.js"></script>
		<script type="text/javascript" src="http://cdn.bootstrapmb.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
		<script type="text/javascript" src="assets/js/jquery.validate.js"></script>
		<script src="assets/js/jquery-validate-init.js"></script>
		<script type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
		<script src="assets/js/custom.js" type="text/javascript"></script>
	</body>

</html>