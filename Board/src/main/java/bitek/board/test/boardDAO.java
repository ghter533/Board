package bitek.board.test;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import bitek.board.file.UploadedFileVO;
import bitek.board.user.UserInfoVO;

@Transactional
public interface boardDAO {

	public UserInfoVO login(UserInfoVO vo);

	public void input(boardVO vo);

	public List<boardVO> list(int page);

	public int totalCount();

	public boardVO boardInfo(HashMap<String, Object> map);

	public int delete(int boardNum);

	public boardVO getUpdateInfo(int boardNum);

	public int update(boardVO vo);

	public void insert(HashMap<String, Object> map);

	public UploadedFileVO getFileInfo(int fileNum);

	public List<boardVO> titleSearch(@Param("searchVal")String searchVal, @Param("page")int page);

	public List<boardVO> idsearch(@Param("searchVal")String searchVal, @Param("page")int page);

	public List<boardVO> contentsSearch(@Param("searchVal")String searchVal, @Param("page")int page);

	public int titleRows(String searchVal);

	public int contentsRow(String searchVal);

	public int idRows(String searchVal);

	

}
