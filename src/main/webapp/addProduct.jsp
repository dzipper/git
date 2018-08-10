<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
	
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" >
	
</head>
<body>


    <h1>添加商品</h1>
    <form action="${pageContext.request.contextPath}/addLucene" method="post" enctype="multipart/form-data" >
		商品名称:<input type="text" name="name"><br/>
		商品价格:<input type="text" name="price"><br/>
		商品描述:<input type="text" name="content"><br/>
		商品图片:<input type="file" name="pic"><br/>
		商品状态:<input type="text" name="status"><br/>
		商品上产日期:<input type="text" name="createDate"><br/>
		商品产地:<input type="text" name="location"><br/>
		<input type="submit" value="提交"><br/>
    </form>


</body>
</html>
