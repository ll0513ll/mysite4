<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css"rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript"src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<c:import url = "/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url = "/WEB-INF/views/includes/navigation.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="button" id = "btnAdd" VALUE=" 확인 " /></td>
							</tr>
						</table>
						
						
					
					<ul id = "guestbook-list"><!-- 방명록 리스트-->
						
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url = "/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> <input type="password" name="modalPassword"
						id="modalPassword"><br> <input type="hidden"
						name="modalPassword" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>

<script type="text/javascript">
$(document).ready(function(){
	fetchlist();
});	
	

	$("#guestbook-list").on("click", "[name=listdelete]", function() {
		console.log("모달");
		$("#del-pop").modal();
		var no = $(this).attr("id");
		console.log(no);

		$("#modalNo").val(no);

	});
	

	$("#btn_del").on("click",function() {
	
		
		var password = $("#modalPassword").val();
		var no = $("#modalNo").val();

		$.ajax({

			url : "${pageContext.request.contextPath}/api/gb/delete",
			type : "get",
			//contentType : "application/json",
			data : {
				no : no,
				password : password
			},

			dataType : "json",
			success : function(result) {

				if (result != 0) {
					$("[id=" + result + "]").remove();
				}
				$("#modalPassword").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
		$("#del-pop").modal("hide");
	});

	$("#btnAdd").on("click", function() {
		event.preventDefault();
		console.log("btnAdd");

		/* var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name='content']").val();
		console.log(name);
		console.log(password);
		console.log(content); */

		guestbookVo = {
			name : $("input[name='name']").val(),
			password : $("input[name='password']").val(),
			content : $("textarea[name='content']").val()

		};

		$.ajax({

			url : "${pageContext.request.contextPath}/api/gb/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo),

			dataType : "json",
			success : function(guestVo) {
				render(guestVo, "up");
				$("[name=name]").val("");
				$("[name=password]").val("");
				$("[name=content]").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	});
		


	function fetchlist() {
		//리스트요청 ajax방식
		$.ajax({

			url : "${pageContext.request.contextPath}/api/gb/list",
			type : "get",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(list) {
				/*성공시 처리해야될 코드 작성*/
				console.log(list);
				//var userVo = ${requestScope.userList}

				for (var i = 0; i < list.length; i++) {

					render(list[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	};

	function render(GuestVo, updown) {
		var str = "";
		str += "<li id = '"+GuestVo.no+"'>";
		str += "   <table>";
		str += "       <tr>";
		str += "         <td>[" + GuestVo.no + "]</td>";
		str += "         <td>" + GuestVo.name + "</td>";
		str += "         <td>" + GuestVo.regDate + "</td>";
		str += "         <td><input type='button' name='listdelete' id='"+GuestVo.no+"' value = '삭제'></td>";
		str += "       </tr>";
		str += "       <tr>";
		str += "         <td colspan=4>";
		str += GuestVo.content;
		str += "         </td>";
		str += "       </tr>";
		str += "   </table>";
		str += "  <br>";
		str += "</li>";

		if (updown == "up") {
			$("#guestbook-list").prepend(str);

		} else if (updown == "down") {
			$("#guestbook-list").append(str);

		} else {
			console.log("오류")
		}
	};
</script>
</html>