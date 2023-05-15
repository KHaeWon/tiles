<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<!DOCTYPE>
<html>
<head>
<title>인사관리시스템</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">

	/* $(document).ready(function(){ //자바스크립트가 실행되면
		data = "${board.category}"//서버에서 받아온 board객체의 getCategory()메소드를 호출해서 
									값을 받은 후에 자바스크립트 data변수에 저장해줌
		$("#category").val(data).attr("selected","selected");
	}); */
	
	
	//자바스크립트에서 자바 데이터(DB에서 조회한 값, 컨트롤러에서 넘어온 값, 자바 변수...)를 가져오기
	$(document).ready(function(){
		//변수에 서버에서 받아온 board 객체의 getCategory() 메소드를 호출한 결과를 저장
		//							 -----------------------------
		//								EL로 간단하게 세팅
		data = "${board.category}";
		//alert(data); //검증하는 작업 하고 지나가자!
		
		//select 태그에 값 반영하기
		$("#category").val(data).attr("selected","selected") //val이 data인 것을 찾아 선택,  attr(속성명,속성값)
	});


</script>
</head>
<body>

	<form class="form-horizontal" 
		action="/erp/board/read.do?state=UPDATE&board_no=${board.board_no }" 
		method="post" >

		
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="id" class="control-label">번호</label>
			</div>
			<div class="col-md-8">${board.board_no}</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="id" class="control-label">게시글종류</label>
			</div>
			<div class="col-md-3">
				<select name="category" class="form-control" 
							id="category">
					<option value="경조사" >경조사</option>
					<option value="사내소식">사내소식</option>
					<option value="게시판" >게시판(익명)</option>
					<!-- 
					<option value="게시판" selected="selected">게시판(익명)</option>
					이렇게 하면 항상 게시판(익명)이 선택됨, attr(속성명,속성값) 
					-->
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="id" class="control-label">작성자</label>
			</div>
			<div class="col-md-8">${board.id}</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="title" class="control-label">제목</label>
			</div>
			<div class="col-md-8">${board.title }</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="title" class="control-label">작성날짜</label>
			</div>
			<div class="col-md-8">${board.write_date }</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="text" class="control-label">내용</label>
			</div>
			<div class="col-md-8"
				style="width: 500px; height: 400px; border: solid 1px;">
				${board.content }
				</div>
		</div>

		<div class="form-group">
			<div class="col-md-10 text-center">
				<input type="submit" class="btn btn-lg btn-primary" 
				value="수정">

				<button type="button" class="btn btn-danger btn-lg"
					onclick="location.href='/erp/board/list.do'">
					<i class="fa fa-fw fa-close"></i> 목록
				</button>
				<button type="button" class="btn btn-danger btn-lg"
					id="deletebtn">
					<i class="fa fa-fw fa-close"></i> 삭제
				</button>
			</div>
		</div>
	</form>

</body>
</html>