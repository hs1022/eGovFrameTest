package egovframework.example.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import egovframework.example.service.UsrService;
import egovframework.example.vo.UsrList;


@Controller
public class UsrController {

	@Resource(name="usrService")
	private UsrService usrService;
	
	//jqGrid List Page
	@RequestMapping(method=RequestMethod.GET, value="/test")
	public String test(ModelMap model) {
		System.out.println("test");
		return "usr/jqGrid";
	}
	
	
	//Usr List Page
	@RequestMapping(method=RequestMethod.GET, value="/usrList")
	public String selectUsrList(ModelMap model) {
		System.out.println("usrList.do");
		return "usr/usrList";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/usrListData", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String usrListData(ModelMap model) {
		System.out.println("usrListData.do");
		String json = new Gson().toJson(new UsrList(usrService.selectUsrList()));
		System.out.println(json);
		return json;
	}
	
	
	
//	@RequestMapping("/list")
//	public String selectList(Model model){
//		List<UsrVO> list = usrService.selectList();
//		model.addAttribute("list",list);
//		return "usr/list";
//	}
//	
//	@RequestMapping("/detail")
//	public String detailList(String usr_id,Model model) {
//		UsrVO dto = usrService.detailList(usr_id);
//		model.addAttribute("dto",dto);
//		System.out.println(dto);
//		return "usr/detail";
//	}
//	


	
	
}
