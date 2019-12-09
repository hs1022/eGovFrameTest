<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>usrList</title>
    <!-- jqGrid usrList-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/jquery-ui-custom/jquery-ui.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/jqgrid/css/ui.jqgrid.css" type="text/css"/>
		
	<script type="text/javascript" src="<c:url value='/lib/jquery-2.1.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/lib/jqgrid/js/jquery.jqGrid.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/lib/jquery.form.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/lib/jqgrid/js/i18n/grid.locale-en.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/lib/jquery-ui-custom/jquery-ui.js'/>"></script>
    

</head>

<body>
<!-- jqGrid -->
<table id="jqGrid"></table>
<div id="jqGridPager"></div>

<script type="text/javascript">
$(document).ready(function () {
    $("#jqGrid").jqGrid({
    	url: "usrListData.do",
        colModel: [
            {
                label: '아이디',
                name: 'usr_id',
                width: 100,
            },
            {
                label: '이름',
                name: 'usr_nm',
                width: 100,
            }
        ],
        viewrecords: true, // show the current page, data rang and total records on the toolbar
        width: 780,
        height: 200,
        rowNum: 10, //한 페이지에 보여줄 갯수
        rowList:[10,20,30],
	   	pager: '#jqGridPager',
//         datatype: 'local',
        datatype: 'json',
   		jsonReader : {
        root: "rows",
//         page: "page",
//         total: "total",
//         records: "records",
        "page":"paginationInfo.currentPageNo",
        "total":"paginationInfo.totalPageCount",
        "records":"paginationInfo.totalRecordCount",
        repeatitems: false
   		},
        caption: "jqGrid",
        
      	//event handling
      	//click event
        onSelectRow: function(id){
            var usr_id = $(this).jqGrid('getCell', id, 'usr_id');
            var usr_nm = $(this).jqGrid('getCell', id, 'usr_nm');
//             alert(usr_id+usr_nm);
//             location.href = "/detail.do/"+ usr_id;
			$.ajax({
			    type : "get",
			    url : "detail.do?usr_id="+usr_id,
			    dataType : "text",
			    error : function(){
			        alert('통신실패!!');
			    },
			    success : function(data){
// 			        alert("통신데이터 값 : " + data) ;
			        $("#dataArea").html(data);
// 			        $("#usr_id").val(data.usr_id);
// 			        $("#usr_nm").val(data.usr_nm);
			    }
			     
			});
        }
   		
    });
    fetchGridData();
    function fetchGridData() {
        var gridArrayData = [];
        // show loading message
        //$("#jqGrid")[0].grid.beginReq();
        $.ajax({
        	type: 'GET',
            url: "usrListData.do",
            //contentType: 'application/json',
            dataType: 'json',
            //////////
//             url: "usrListData.do",
            success: function (result) {
//                 alert("result: " + result)
                /*
                for (var i = 0; i < result.rows.length; i++) {
                    var item = result.rows[i];
                    if(i == 0){
                        alert(item.title);
                    }
                    gridArrayData.push({
                        usr_id: item.usr_id,
                        usr_nm: item.usr_nm,
                    });
                }
                // set the new data
                $("#jqGrid").jqGrid('setGridParam', { data: gridArrayData});
                */
                $("#jqGrid").jqGrid('setGridParam', { data: result.rows});
                $("#jqGrid").jqGrid('setGridParam', { data: result.records});
                $("#jqGrid").jqGrid('setGridParam', { data: result.page});
                $("#jqGrid").jqGrid('setGridParam', { data: result.total});
                // hide the show message
                // $("#jqGrid")[0].grid.endReq();
                // refresh the grid
                $("#jqGrid").trigger('reloadGrid');
            }
        });
    }
//     function formatTitle(cellValue, options, rowObject) {
//         return cellValue.substring(0, 50) + "...";
//     };
//     function formatLink(cellValue, options, rowObject) {
//         return "<a href='" + cellValue + "'>" + cellValue.substring(0, 25) + "..." + "</a>";
//     };

});

</script>
<!-- <div id="dataArea"> -->
<table id="dataArea" border="1">

				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="usr_id" id="usr_id" value="${dto.usr_id}" maxlength="20">
					</td>
					<td>이름</td>
					<td>
						<input type="text" id="usr_nm" name="usr_nm" value="${dto.usr_nm}" maxlength="160">
					</td>
				</tr>

</table>


</table>
<!-- </div> -->
</body>
</html>
