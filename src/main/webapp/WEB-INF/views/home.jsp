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
            	<a href="#loginModal" role="button" data-toggle="modal">
            	<span class="loginName">GUEST</span><i class="glyphicon glyphicon-user"></i> 
            	</a>
           	</li>            
           </ul>
        </div>	
     </div>	
</div>

<!--main-->
<div class="container" id="main">
	<div class="row">
	
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
    		<a href="#" target="_ext">DGIT 사진관리 시스템</a><br>
    		<a href="#" target="_ext">노창균</a>
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
          <h2 class="text-center"><img src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" class="img-circle"><br>Login</h2>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block">
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Email" type="text">
            </div>
            <div class="form-group">
              <input class="form-control input-lg" placeholder="Password" type="password">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block">Sign In</button>
              <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span>
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


<!--about modal은 업로드 modal로 수정예정-->
<div id="uploadModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h2 class="text-center">About</h2>
      </div>
      <div class="modal-body">
          <div class="col-md-12 text-center">
            <a href="http://bootply.com/90113">This Bootstrap Template</a><br>was made with <i class="glyphicon glyphicon-heart"></i> by <a href="http://bootply.com/templates">Bootply</a>
            <br><br>
            <a href="https://github.com/iatek/bootstrap-google-plus">GitHub Fork</a>
          </div>
      </div>
      <div class="modal-footer">
          <button class="btn" data-dismiss="modal" aria-hidden="true">OK</button>
      </div>
  </div>
  </div>
</div>

<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type='text/javascript' src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<!-- gallery.js -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/gallery.js" type="text/javascript"></script>
</html>
