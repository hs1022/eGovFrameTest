package egovframework.example.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.dao.UsrMapper;
import egovframework.example.vo.UsrVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("usrService")
public class UsrServiceImpl implements UsrService {

	@Resource(name = "usrMapper")
	private UsrMapper dao;

	@Override
	public List<UsrVO> selectUsrList() {
		return dao.selectUsrList();
	}

	@Override
	public UsrVO selectData(String usr_id) {
		return dao.selectData(usr_id);
	}

	// @Override
	// public List<UsrVO> selectUsrList(Map param) throws Exception {
	// // TODO Auto-generated method stub
	// return dao.selectUsrList(param);
	// }

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount();
	}

	
	//////
	@Override
	public List<UsrVO> selectJqgridList(UsrVO usrVO) {
		// TODO Auto-generated method stub
		return dao.selectJqgridList(usrVO);
	}

	@Override
	public EgovMap selectJqgridListCnt(UsrVO usrVO) {
		// TODO Auto-generated method stub
		return dao.selectJqgridListCnt(usrVO);
	}

}
