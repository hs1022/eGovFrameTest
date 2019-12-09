package egovframework.example.dao;

import java.util.List;

import egovframework.example.vo.UsrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("usrMapper")
public interface UsrMapper {
	List<UsrVO> selectUsrList();
	
	UsrVO selectData(String usr_id);

	
//	List<UsrVO> selectUsrList(Map param);
	int selectBoardCount();

	List<UsrVO> selectJqgridList(UsrVO usrVO);

	EgovMap selectJqgridListCnt(UsrVO usrVO);

	
}
