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

<table id="jqGrid"></table>
<div id="jqGridPager"></div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#jqGrid").jqGrid({
            colModel: [
                {
                    label: 'Title',
                    name: 'Title',
                    width: 150,
                    formatter: formatTitle
                },
                {
                    label: 'Link',
                    name: 'Link',
                    width: 80,
                    formatter: formatLink
                },
                {
                    label: 'View Count',
                    name: 'ViewCount',
                    width: 35,
                    sorttype:'integer',
                    formatter: 'number',
                    align: 'right'
                },
                {
                    label: 'Answer Count',
                    name: 'AnswerCount',
                    width: 25
                }
            ],
            viewrecords: true, // show the current page, data rang and total records on the toolbar
            width: 780,
            height: 200,
            rowNum: 15,
            datatype: 'local',
            pager: "#jqGridPager",
            caption: "Load live data from stackoverflow"
        });
        fetchGridData();
        function fetchGridData() {
            var gridArrayData = [];
            // show loading message
            //$("#jqGrid")[0].grid.beginReq();
            $.ajax({
                url: "http://api.stackexchange.com/2.2/questions?order=desc&sort=activity&tagged=jqgrid&site=stackoverflow",
                success: function (result) {
                    alert("result: " + result)
                    for (var i = 0; i < result.items.length; i++) {
                        var item = result.items[i];
                        if(i == 0){
                            alert(item.title);
                        }
                        gridArrayData.push({
                            Title: item.title,
                            Link: item.link,
                            CreationDate: item.creation_date,
                            ViewCount: item.view_count,
                            AnswerCount: item.answer_count
                        });
                    }
                    // set the new data
                    $("#jqGrid").jqGrid('setGridParam', { data: gridArrayData});
                    // hide the show message
                    // $("#jqGrid")[0].grid.endReq();
                    // refresh the grid
                    $("#jqGrid").trigger('reloadGrid');
                }
            });
        }
        function formatTitle(cellValue, options, rowObject) {
            return cellValue.substring(0, 50) + "...";
        };
        function formatLink(cellValue, options, rowObject) {
            return "<a href='" + cellValue + "'>" + cellValue.substring(0, 25) + "..." + "</a>";
        };
    });
</script>
</body>
</html>
