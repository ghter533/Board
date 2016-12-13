package bitek.board.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.FixedKeySet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bitek.board.file.UploadedFileVO;
import bitek.board.navi.NavigationVO;
import bitek.board.navi.PagingUtil;
import bitek.board.user.UserInfoVO;

@Service("boardService")
public class boardSVC {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	private int page;

	public boolean login(UserInfoVO vo, HttpSession session, HttpServletRequest request) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		System.out.println("sadf:" + vo.getId());
		
		vo = dao.login(vo);
		boolean check;
		if (vo == null) {
			check = false;
		} else {
			System.out.println(vo.getId());
			//request.getSession().setAttribute("UserId", vo.getId());
			session.setAttribute("UserId", vo.getId());
			
			
			// request.setAttribute("UserId", vo.getId());
			check = true;

		}
		session.setAttribute("loginOk", check);

		return check;
	}

	public void input(boardVO vo, UploadedFileVO uploadedvo) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);

		if (uploadedvo.getFile().getSize() > 0) {
			fileinput(vo, uploadedvo);
		} else
			dao.input(vo);
	}

	public void fileinput(boardVO vo, UploadedFileVO uploadedvo) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		HashMap<String, Object> map = new HashMap<String, Object>();

		InputStream inputstream = null;
		OutputStream outputstream = null;

		MultipartFile file = uploadedvo.getFile();

		String originfileName = file.getOriginalFilename();
		StringBuffer sChangeFilename;
		String changeFilename = "";

		sChangeFilename = new StringBuffer(originfileName);
		sChangeFilename.insert(sChangeFilename.lastIndexOf("."), "-" + new Date().getTime());
		changeFilename = sChangeFilename.toString();
		try {
			inputstream = file.getInputStream();
			File createdFile = new File("C:/Users/박성호/Desktop/JavaFiledownload/");

			if (!createdFile.exists()) {
				createdFile.createNewFile();
				changeFilename = originfileName;
			} else {
				sChangeFilename = new StringBuffer(originfileName);
				sChangeFilename.insert(sChangeFilename.lastIndexOf("."), "-" + new Date().getTime());
				changeFilename = sChangeFilename.toString();
				createdFile = new File("C:/Users/박성호/Desktop/JavaFiledownload/" + changeFilename);
			}

			outputstream = new FileOutputStream(createdFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputstream.read(bytes)) != -1) {
				outputstream.write(bytes, 0, read);
			}

			String title = vo.getTitle();
			String id = vo.getId();
			String contents = vo.getContents();
			int boadNum = vo.getBoardNum();
			// Date boardDate = vo.getBoarddate();
			uploadedvo.setOriginfilename(originfileName);
			uploadedvo.setChangefilename(changeFilename);

			map.put("title", title);
			map.put("id", id);
			map.put("boardnum", boadNum);
			map.put("contents", contents);
			map.put("originfilename", originfileName);
			map.put("changefilename", changeFilename);

			dao.insert(map);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<boardVO> getList(int page) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		List<boardVO> list = new ArrayList<boardVO>();
		this.page = page;
		if (page == 0) {
			page = 1;
		}
		list = dao.list(page);
		System.out.println("LIST : " + list);
		return list;
	}

	public NavigationVO navi() {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		int totalRows = dao.totalCount();

		PagingUtil util = new PagingUtil();
		return util.navi(page, totalRows);
	}

	public List<boardVO> boardInfo(int boardNum) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		boardVO vo = new boardVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardNum", boardNum);

		List<boardVO> infoList = null;
		vo = dao.boardInfo(map);

		infoList = (List<boardVO>) map.get("boardInfo");

		return infoList;
	}

	public boolean delete(int boardNum) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		int rows = dao.delete(boardNum);
		/*
		 * boolean deleteOk = false;
		 * 
		 * if(rows>0){ deleteOk = true; }
		 */
		return rows > 0 ? true : false;
	}

	public boardVO getUpdateInfo(int boardNum) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		boardVO vo = dao.getUpdateInfo(boardNum);
		System.out.println(vo.getTitle());

		return vo;
	}

	public boolean update(boardVO vo) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		System.out.println(vo.getBoardNum() + "||" + vo.getTitle() + "||" + vo.getContents());

		int rows = dao.update(vo);

		return rows > 0 ? true : false;
	}

	public UploadedFileVO getFileInfo(int fileNum) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		UploadedFileVO vo = new UploadedFileVO();
		vo = dao.getFileInfo(fileNum);

		return vo;
	}

	public byte[] download(UploadedFileVO vo, HttpServletResponse response) throws IOException {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		vo = dao.getFileInfo(vo.getFileNum());

		/*
		 * File createdFile = new File("C:/Users/박성호/Desktop/JavaFiledownload");
		 * 
		 * if(!createdFile.exists()){ createdFile.createNewFile(); }
		 */

		File file = new File("C:/Users/박성호/Desktop/JavaFiledownload/" + vo.getChangefilename());
		byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

		// 한글은 http 헤더에 사용할 수 없기때문에 파일명은 영문으로 인코딩하여 헤더에 적용한다445
		String fn = new String(file.getName().getBytes(), "iso_8859_1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
		response.setContentLength(bytes.length);
		response.setContentType("image/jpeg");

		return bytes;

	}

	public List<boardVO> searchList(String select, String searchVal, int page) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		this.page = page;
		if (page == 0) {
			page = 1;
		}

		System.out.println("찾으러서비스옴");
		List<boardVO> searchList = new ArrayList<boardVO>();

		if (select.equals("title")) {
			System.out.println("title로 찾을거야");
			searchList = dao.titleSearch(searchVal, page);
			// System.out.println("searchtitle"+searchList.get(0).getTitle());
		} else if (select.equals("id")) {
			searchList = dao.idsearch(searchVal, page);
		} else if (select.equals("contents")) {
			searchList = dao.contentsSearch(searchVal, page);
		}

		for (int i = 0; i < searchList.size(); i++) {

			System.out.println(searchList.get(i).getContents());
		}
		return searchList;

	}

	public NavigationVO searchNavi(String select, String searchVal) {
		boardDAO dao = sqlSessionTemplate.getMapper(boardDAO.class);
		int searchtotalRows = 0;

		if (select.equals("title")) {
			searchtotalRows = dao.titleRows(searchVal);
		} else if (select.equals("contents")) {
			searchtotalRows = dao.contentsRow(searchVal);
		} else if (select.equals("id")) {
			searchtotalRows = dao.idRows(searchVal);
		}

		PagingUtil util = new PagingUtil();

		return util.navi(page, searchtotalRows);
	}

}
