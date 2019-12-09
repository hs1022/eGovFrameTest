package egovframework.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import egovframework.example.cmmn.JsonUtil;
import egovframework.example.service.UsrService;
import egovframework.example.vo.UsrList;
import egovframework.example.vo.UsrVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class UsrController {

	@Resource(name="usrService")
	private UsrService usrService;
	
	//jqGrid List Page
//	@RequestMapping(method=RequestMethod.GET, value="/test")
//	public String test(ModelMap model) {
//		System.out.println("test");
//		return "usr/jqGrid";
//	}
	
	
	//Usr List Page
	@RequestMapping(method=RequestMethod.GET, value="/usrList")
	public String selectUsrList(ModelMap model) {
		return "usr/usrList";
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="/usrListData", produces="application/json;charset=UTF-8")
//	public void usrListData(HttpServletRequest request, HttpServletResponse response,UsrVO usrVO) throws IOException {
//		PrintWriter out = null;
////		response.setCharacterEncoding("EUC-KR");
//		response.setCharacterEncoding("UTF-8");
//		
//
//		String json = new Gson().toJson(new UsrList(usrService.selectUsrList()));
//		
//		List<UsrVO> list = usrService.selectUsrList();
//		List<?> jqGridList = usrService.selectJqgridList(usrVO);
//		EgovMap jqGridListCnt = usrService.selectJqgridListCnt(usrVO);
//		
//		HashMap<String, Object> resMap = new HashMap<String, Object>();
//		
//		int selectBoardCount = usrService.selectBoardCount();
//		resMap.put("records", selectBoardCount);
//		resMap.put("rows", json);
//		resMap.put("page", request.getParameter("page"));
//		resMap.put("total", jqGridListCnt.get("totalpage"));
//
//		out = response.getWriter();
//		out.write(JsonUtil.HashMapToJson(resMap).toString());
//	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/usrListData", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String usrListData(ModelMap model) {
		String json = new Gson().toJson(new UsrList(usrService.selectUsrList()));
		
		System.out.println(json);
//		return json;
		
		//페이지처리 건수
		List resultList = service.
		
		//데이터 전체건수
		int total = usrService.selectBoardCount();


		paginationInfo.setTotalRecordCount(total);


		ModelAndView modelView = new ModelAndView();

		modelView .addObject("resultList",'조회리스트');

		modelView .addObject("paginationInfo",paginationInfo);
		modelView .setViewName("jsonView");

		return moelview;

	}
	
	
	
//	@RequestMapping("/list")
//	public String selectList(Model model){
//		List<UsrVO> list = usrService.selectUsrList();
//		model.addAttribute("list",list);
//		return "usr/list";
//	}
//	
	@RequestMapping(method=RequestMethod.GET,value="/detail", produces="application/json;charset=UTF-8")
	public String selectData(String usr_id,Model model) {
		String json = new Gson().toJson(usrService.selectData(usr_id));
		System.out.println("json"+json);
		return json;
	}

	@RequestMapping(value="/test")
    public void jqgridStartMain(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute UsrVO usrVO, ModelMap model) throws IOException {
		
		PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");

		List<UsrVO> list = usrService.selectUsrList();
		List<UsrVO> jqGridList = usrService.selectJqgridList(usrVO);
		EgovMap jqGridListCnt = usrService.selectJqgridListCnt(usrVO);
		System.out.println("list"+list);
		System.out.println("jqGridList"+jqGridList);
		System.out.println("jqGridListCnt"+jqGridListCnt);
		        
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		        
		resMap.put("records", jqGridListCnt.get("toatalTotCnt"));
		resMap.put("rows", list);
		resMap.put("page", request.getParameter("page"));
		System.out.println("page from request "+request.getParameter("page"));
		resMap.put("total", jqGridListCnt.get("totalpage"));
		        
		out = response.getWriter();
		        
		out.write(JsonUtil.HashMapToJson(resMap).toString());
        
    }


	
	
}
