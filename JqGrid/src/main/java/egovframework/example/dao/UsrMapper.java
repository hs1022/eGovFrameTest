package egovframework.example.dao;

import java.util.List;

import egovframework.example.vo.UsrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("usrMapper")
public interface UsrMapper {
	List<UsrVO> selectUsrList();
	
	UsrVO selectData(String usr_id);
}
