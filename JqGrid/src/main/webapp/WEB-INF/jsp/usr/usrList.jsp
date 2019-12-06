<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>article</title>
    <!-- jqGrid -->
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
        datatype: 'local',
//         datatype: 'json',
//    		jsonReader : {
//         root: "rows",
//         page: "page",
//         total: "total",
//         records: "records",
//         repeatitems: false
//    		},
        pager: "#jqGridPager",
        caption: "jqGrid",
        
      	//event handling
        onSelectRow: function(id){
            var usr_id = $(this).jqGrid ('getCell', id, 'usr_id');
//             location.href = "/articles/"+ usr_id;
			$.ajax({
			    type : "GET",
			    url : "detail.do?id="+usr_id,
			    dataType : "text",
			    error : function(){
			        alert('통신실패!!');
			    },
			    success : function(data){
// 			        alert("통신데이터 값 : " + data) ;
			        $("#dataArea").html(data) ;
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
<div id="dataArea"></div>
</body>
</html>
