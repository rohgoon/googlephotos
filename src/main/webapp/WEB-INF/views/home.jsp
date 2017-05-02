<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
<meta charset="utf-8">
<title>사진 관리 시스템</title>
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/gallery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.guestMod{
		height: 600px;
		background: url("${pageContext.request.contextPath}/resources/images/guestbg2.png") no-repeat center;
		background-size: contain;
	}
</style>
</head>
<div class="navbar navbar-fixed-top header">
 	<div class="col-md-12">
        <div class="navbar-header">
          
          <a href="#" class="navbar-brand">My Photos</a>
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse1">
          <i class="glyphicon glyphicon-search"></i>
          </button>
      
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse1">
          <form class="navbar-form pull-left">
              <div class="input-group" style="max-width:470px;">
                <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
                <div class="input-group-btn">
                  <button class="btn btn-default btn-primary" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
              </div>
          </form> <!-- 검색바 -->
          <ul class="nav navbar-nav navbar-right"> 
          	
            <!-- 갤러리버튼 -->
             <li><a href="#" id="btnToggle"><i class="glyphicon glyphicon-th-large"></i></a></li>
            <!-- 업로드 버튼 -->
            <li><a href="#uploadModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-cloud-upload"></i></a></li>
            <!-- 로그인 버튼 -->
            <li>
           		<c:if test="${empty login }">
	            	<a href="#loginModal" role="button" data-toggle="modal">	            			
	            		<span class="loginName">GUEST</span><i class="glyphicon glyphicon-user"></i> 
	            	</a>
            	</c:if>
            	<!-- 로그아웃 -->
            	<c:if test="${!empty login  }">   
            		<a href="#" role="button" data-toggle="modal" id="logout">         	
	            		<span class="loginName">${login.uname }</span><i class="glyphicon glyphicon-user"></i>
	            	</a> 
	            </c:if>
           	</li>            
           </ul>
        </div>	
     </div>	
</div>

<!--main-->
<div class="container" id="main">
	<div class="row">
	<c:if test="${empty login }">		
		<div class="guestMod"></div>
	</c:if>
	<c:if test="${!empty login}">
		<c:if test="${empty fileList || fileList.length == 0}">			
		 <!-- no image default-->	
			<div class="col-sm-4 noimg" style="margin: 0 auto; float: inherit !important;">      
	        <div class="panel panel-default">
	          <div class="panel-thumbnail">
	          	<img src="${pageContext.request.contextPath}/resources/images/noimg.png" class="img-responsive">
	          </div>
	          <div class="panel-body">
	            <p class="lead">저장된 이미지가 없습니다.</p>
	            <p>업로드 아이콘을 클릭하여 이미지를 넣어 주세요.</p>
	          </div>
	        </div>
	      </div>
	     </c:if>  
	     <c:if test="${!empty fileList }"> <!-- uid+"/s_"+filename, s_일시_시간_원본파일명-->
	     <c:forEach var="filename" items="${fileList }"></c:forEach>
	     	<div class="col-sm-4">      
		        <div class="panel panel-default">
		          <div class="panel-thumbnail">
		          <c:set var="thumbFile" value="${login.uid}/s_${filename }"></c:set>
		          	<img src="displayFile?filename=${thumbFile }" class="img-responsive">
		          </div>
		          <%
		          	String fStr = "${filename}";
		          	String[] fsArr = fStr.split("_");
		          %>
		          <div class="panel-body">
		            <p class="lead"><%=fsArr[3] %></p>
		            <p><%=fsArr[1] %>, <%=fsArr[2] %></p>            
		          </div>
		        </div>        
     		 </div>
	     </c:if>	
	</c:if>	  
     <!-- <div class="col-sm-4">
        <div class="panel panel-default">
          <div class="panel-thumbnail">
          	<img src="/assets/example/bg_4.jpg" class="img-responsive">
          </div>
          <div class="panel-body">
            <p class="lead">Social Good</p>
            <p>1,200 Followers, 83 Posts</p>            
          </div>
        </div>
      </div>/col -->    
    <br>
    <!-- footer -->
    <div class="clearfix"></div>
      
    <hr>
    <div class="col-md-12 text-center">
    	<p>
    		<a href="#">DGIT Photo Management System</a><br>
    		<a href="#">Roh Chang gyun</a>
    	</p>
    </div>
    <hr>
    
  </div><!--/main row-->
</div><!--/main-->



<!--login modal-->
<div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <!-- <h2 class="text-center"><img src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" class="img-circle"><br>Login</h2> -->
          <h2 class="text-center"><img src="${pageContext.request.contextPath}/resources/images/photo.jpg" style="width: 100px;" class="img-circle"><br>Login</h2>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block loginForm" action="login" method="post">
            <div class="form-group">
              <input class="form-control input-lg" placeholder="ID" type="text" name="uid">              
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Password" type="password" name="upassword">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block loginBtn">Sign In</button>
              <span class="pull-right"><a href="#registerModal"  data-toggle="modal">Register</a></span>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
		  </div>	
      </div>
  </div>
  </div>
</div>

<!--user register modal-->
<div id="registerModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h2 class="text-center">Register</h2>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block signUpForm" action="register" method="post">
            <div class="input-group" style="margin-bottom: 15px;">
              <input class="form-control input-lg" placeholder="ID" type="text" name="uid" id="idInput">
              <span class = "bnt input-group-addon searchId">중복 검색</span>
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Name" type="text" name="uname">
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Password" type="password" name="upassword" id="upw">
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Confirm Password" type="password" id="upwConfirm">
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Email" type="email" name="uemail" id="suEmail">
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Phone" type="tel" name="uphone">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block signUp">Sign Up</button>              
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
		  </div>	
      </div>
  </div>
  </div>
</div>
<!-- uploadModal -->
<div id="uploadModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h2 class="text-center">Upload Your Images</h2>
      </div>
      <div class="modal-body">
          <div class="col-md-12 text-center">
            <form class="form col-md-12 center-block signUpForm" action="register" method="post">                     
            <div class="form-group">
              <input class="form-control input-lg" type="file" name="files" multiple="multiple">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block">Upload</button>              
            </div>
          </form>
         </div>
      </div>
      <div class="modal-footer">
          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
      </div>
  </div>
  </div>
</div>

<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type='text/javascript' src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<!-- gallery.js -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/gallery.js" type="text/javascript"></script>
<script type="text/javascript">
$('#logout').click(function() {
	var logout = confirm("로그아웃 하시겠습니까?");
	if(logout){
		location.href="${pageContext.request.contextPath}/logout";
		return true;
	}else{
		return false;
	}	
});
$('.signUp').click(function() {//signUpForm 예외처리
	var $upw = $('#upw').val();
	var $upwConfirm = $('#upwConfirm').val();
	var $suEmail = $('#suEmail').val();
	var sueArr = $suEmail.split('@');
	if ($upw != $upwConfirm) {
		alert("비밀번호가 일치하지 않습니다.");
		$('#upw').val("");
		$('#upwConfirm').val("");
		$('#upw').focusin;
		return false;
	}else if(sueArr.length != 2 || sueArr[1].split(".").length != 2){
		alert("이메일 형식이 맞지 않습니다.");
		$('#suEmail').val("");
		$('#suEmail').focusin;
		return false;
	}
});
$('.searchId').click(function() {
	var uid = $('#idInput').val();
	if(uid == ""){
		alert("아이디를 먼저 입력해 주세요.");
		$('#idInput').focusin;
		return false;
	}
	$.ajax({
		url: "${pageContext.request.contextPath}/searchId",
		type: "get",
		data:{"uid":uid},
		dataType: "json",
		contentType: "application/json",
		success:function(res){
			if(res.checkId == "0"){
				alert("사용 가능한 아이디 입니다.");
			}else{
				$('#idInput').val("");
				$('#idInput').focusin;
				alert("이미 존재하는 아이디 입니다.");
			}
			return true;
		}
		
	});
	
});
</script>
</html>
