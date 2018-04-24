<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="Css/Report.css" rel="stylesheet"/>
<div class="gallery">

	<%	
		List<String> imageUrlList = new ArrayList();  
		File imageDir = new File("C://Vatsal//EliteCoreGITProject//WebContent//file");  
		for(File imageFile : imageDir.listFiles()){  
				String imageFileName = imageFile.getName();  
	  	imageUrlList.add(imageFileName);  

		}  
		request.setAttribute("imageUrlList", imageUrlList);
	 %>
		<div class="content">
			<c:forEach var="img" items="${imageUrlList}">  
				<div class="media all people">
					<a href="file/${img}"><img src="file/${img}" alt="" title="This right here is a caption." /> </a>
				</div> 
			</c:forEach>
		</div>
</div>
		
<script src="Js/jquery.poptrox.min.js"></script>
<script src="Js/jquery.scrolly.min.js"></script>
<script src="Js/skel.min.js"></script>
<script src="Js/util.js"></script>
<script src="Js/main.js"></script>

</body>
</html>