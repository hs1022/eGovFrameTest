package egovframework.example.service;

import java.util.List;
import java.util.Map;

import egovframework.example.vo.UsrVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface UsrService {
	List<UsrVO> selectUsrList();
	
	UsrVO selectData(String usr_id);

	
//	public List<UsrVO> selectUsrList(Map param) throws Exception;
//	public List<UsrVO> selectBoardCount(Map param) throws Exception;
	int selectBoardCount();

	/////
	List<UsrVO> selectJqgridList(UsrVO usrVO);

	EgovMap selectJqgridListCnt(UsrVO usrVO);
}
