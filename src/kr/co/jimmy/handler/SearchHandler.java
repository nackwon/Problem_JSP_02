package kr.co.jimmy.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.co.jimmy.DAO.MemberDAO;
import kr.co.jimmy.VO.MemberVO;

public class SearchHandler implements Command {

	@Override
	public String process(HttpServletRequest request) {
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list = dao.searchAll();

		request.setAttribute("member", list);
		String url = "./Main/list.jsp";
		return url;
	}

}
