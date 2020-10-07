<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更换头像</title>
</head>
<body>
	<form method="post"enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/ChangeHeadSubmit">
											<div class="form-group row">
												<label class="col-sm-4 control-label" for="image">Image</label>
												<div class="col-sm-5">
													<input type="file" class="form-control" id="image" name="image" placeholder="image"/>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-8 ml-auto">
													<button type="submit" class="btn btn-primary" name="signup" value="Sign up" style="width: 55px;">
														OK
													</button>
													<a href="${pageContext.request.contextPath}/page/ToIndex" style="margin-left:20px;">
													<button class="btn btn-primary">
													返回
													</button>
													</a>
												</div>
											</div>
										</form>
</body>
</html>