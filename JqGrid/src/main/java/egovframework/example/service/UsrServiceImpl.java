package egovframework.example.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.dao.UsrMapper;
import egovframework.example.vo.UsrVO;

@Service("usrService")
public class UsrServiceImpl implements UsrService {
	
	@Resource(name="usrMapper")
	private UsrMapper dao;
	
	@Override
	public List<UsrVO> selectUsrList() {
		return dao.selectUsrList();
	}

	@Override
	public UsrVO selectData(String usr_id) {
		return dao.selectData(usr_id);
	}

}
