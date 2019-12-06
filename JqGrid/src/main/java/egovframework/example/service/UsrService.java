package egovframework.example.service;

import java.util.List;

import egovframework.example.vo.UsrVO;

public interface UsrService {
	List<UsrVO> selectUsrList();
	
	UsrVO selectData(String usr_id);
}
