<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>DETAIL</title>
    
	<!-- jqGrid detail-->
    <link rel="stylesheet" type="text/css" href="jqGrid-master/css/ui.jqgrid.css" />
    <link rel="stylesheet" type="text/css" href="jqGrid-master/css/addons/ui.multiselect.css" />
    
    <script type="text/javascript" src="jqGrid-master/jquery.js"></script>
    <script type="text/javascript" src="jqGrid-master/js/i18n/grid.locale-kr.js"></script>
    <script type="text/javascript" src="jqGrid-master/js/jquery.jqGrid.min.js"></script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<script>



</script>




<table class="table" border="1" style="width: 300px">
		<colgroup>
			<col width="120px">
			<col width="100px">
		</colgroup>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
			</tr>
		</thead>
			<tbody>
					<tr>
						<td><c:out value="${dto.usr_id}" /></td>
						<td><c:out value="${dto.usr_nm}"></c:out></td>
					</tr>
			</tbody>
		</table>
</body>
</html>
