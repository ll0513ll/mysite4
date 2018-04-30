<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url = "/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url = "/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board/searchwhere" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:forEach items="${requestScope.allList}" var = "list">			
					<tr>
						<td>${list.no}</td>
						<td><a href="${pageContext.request.contextPath}/board/view?no=${list.no}">${list.title}</a></td>
						<td>${list.name}</td>
						<td>${list.hit}</td>
						<td>${list.reg_date}</td>
						<c:if test="${sessionScope.authUser.no eq list.no}">
						<td><a href="${pageContext.request.contextPath}/board/delete?no=${list.no}" class="del">삭제</a></td>
						</c:if>
						<c:if test="${sessionScope.authUser.no ne list.no}">
						<td></td>
						</c:if>
					</tr>
					</c:forEach>
				</table>
				
				<div class="pager">
					<ul>
						<li><a href="${pageContext.request.contextPath}/board/frontPage">◀</a></li>
						<li><a href="${pageContext.request.contextPath}/board/fristPage">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>				
				<div class="bottom">
				<c:if test="${!empty sessionScope.authUser}">
					<a href="${pageContext.request.contextPath}/board/write" id="new-book">글쓰기</a>
				</c:if>
				</div>				
			</div>
		</div>
		
		<c:import url = "/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>