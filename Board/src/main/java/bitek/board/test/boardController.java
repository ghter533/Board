package bitek.board.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bitek.board.file.UploadedFileVO;
import bitek.board.user.UserInfoVO;

@Controller
@RequestMapping(value = "/board/")
public class boardController {
	private String sSelect = "";
	private String sSearchVal = "";

	@Autowired
	boardSVC boardService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLoginForm() {
		return "login/loginForm";
		
	}
	
	

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public UserInfoVO login(UserInfoVO vo, HttpServletRequest request, HttpSession session) {
		System.out.println("id:" + vo.getId() + "pwd:" + vo.getPwd());

		vo.setLoginOk(boardService.login(vo, session, request));
		// boardService.login(vo, request);
		return vo;
	}

	// @RequestMapping(value = "list", method = RequestMethod.GET)
	@RequestMapping(value = "list")
	public String goList(HttpServletRequest request, boardVO vo, Model model, HttpSession session) {
		String path = "login/loginForm";
		
		// System.out.println("page!!!! -> " + vo.getPage());

		if (session.getAttribute("loginOk") != null && (Boolean) session.getAttribute("loginOk")) {
			if (sSelect.equals("") || sSearchVal.equals("")) {
				System.out.println("!!!!!!!!!!List!!!!!!!!!!");
				model.addAttribute("list", boardService.getList(vo.getPage()));
				model.addAttribute("nvo", boardService.navi());

				request.setAttribute("loginSession", request.getSession().getAttribute("loginOk"));

				path = "board/list";
			} else {
				System.out.println("!!!!!!!!!!!searchList!!!!!!!!!!");
				model.addAttribute("list", boardService.searchList(sSelect, sSearchVal, vo.getPage()));
				model.addAttribute("nvo", boardService.searchNavi(sSelect, sSearchVal));

				path = "board/list";
			}

		}
		// System.out.println(path);
		return path;
	}

	@RequestMapping(value = "boardInput", method = RequestMethod.GET)
	public String goBoardInput() {
		return "board/inputForm";
	}

	@RequestMapping(value = "input", method = RequestMethod.POST)
	public String input(boardVO vo, UploadedFileVO uploadedvo) {
		boardService.input(vo, uploadedvo);

		return "redirect:list";
	}

	@RequestMapping(value = "boardInfo", method = RequestMethod.GET)
	public String voardInfo(boardVO vo, Model model) {
		System.out.println("boardInfo fnum:" + vo.getFileNum());
		System.out.println("boardNum:" + vo.getBoardNum());
		model.addAttribute("boardInfo", boardService.boardInfo(vo.getBoardNum()));
		model.addAttribute("fileInfo", boardService.getFileInfo(vo.getFileNum()));

		return "board/boardInfo";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public boardVO delete(boardVO vo, Model model) {
		System.out.println("delete");
		vo.setDeleteOk(boardService.delete(vo.getBoardNum()));
		
		
		List<String> list = new ArrayList<String>();
		
		
		
		Map<String, List<String> > map = new HashMap<String, List<String>>();
		
		
		return vo;
	}

	@RequestMapping(value = "updateForm", method = RequestMethod.GET)
	public ModelAndView updateForm(@Param("boardNum") int boardNum, ModelAndView mv) {
		System.out.println("boardNum:" + boardNum);
		mv = new ModelAndView("board/updateForm", "updateInfo", boardService.getUpdateInfo(boardNum));

		return mv;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boardVO update(boardVO vo) {
		System.out.println(vo.getContents());
		vo.setUpdateOk(boardService.update(vo));

		return vo;
	}

	@RequestMapping(value = "download", method = RequestMethod.GET)
	@ResponseBody
	public byte[] download(UploadedFileVO vo, HttpServletResponse response) throws IOException {
		
		return boardService.download(vo, response);
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(boardVO vo, @RequestParam("select") String select, @RequestParam("searchVal") String searchVal,
			Model model) {
		System.out.println("search!!!");
		System.out.println("select:" + select);
		System.out.println("searchVal:" + searchVal);
		sSelect = select;
		sSearchVal = searchVal;
		
		model.addAttribute("list", boardService.searchList(select, searchVal, 1));
		model.addAttribute("nvo", boardService.searchNavi(select, searchVal));

		return "board/list";
	}
}
