package kr.co.jimmy.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jimmy.DAO.MemberDAO;
import kr.co.jimmy.DAO.ZipcodeDAO;
import kr.co.jimmy.VO.MemberVO;
import kr.co.jimmy.VO.ZipCodeVO;

public class CommandController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println("¸ÊÇÎ");
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		
		String url = "./Main/home.jsp";

		if ("registView".equals(cmd)) {
			url = "./Main/regist_member.jsp";
		} else if ("regist".equals(cmd)) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String zip1 = request.getParameter("zip1");
			String zip2 = request.getParameter("zip2");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String tool = request.getParameter("tool");
			String project = request.getParameter("project");
			String[] langs = request.getParameterValues("lang");
	
			MemberVO vo = new MemberVO();

			vo.setId(id);
			vo.setPw(pw);
			name = new String(name.getBytes("iso-8859-1"), "UTF-8");
			vo.setName(name);
			vo.setEmail(email);
			vo.setZipcode(zip1 + "-" + zip2);
			vo.setAddr1(addr1);
			vo.setAddr2(addr2);
			vo.setTool(tool);
			vo.setProject(project);

			vo.setLangs(langs);

			MemberDAO dao = new MemberDAO();
			boolean flag = dao.register(vo);

			request.setAttribute("message", "success");

		} else if ("search".equals(cmd)) {
			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			int result = dao.searchMember(id);
			
			request.setAttribute("number", result);
			url = "./Main/regist_member.jsp";
		} else if ("searchAll".equals(cmd)) {
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			list = dao.searchAll();
			
			request.setAttribute("member", list);
			url = "./Main/searchAll.jsp";
		} else if ("insert".equals(cmd)) {
			String path = this.getServletContext().getRealPath("WEB-INF/file/zipcode.csv");
			ZipcodeDAO dao = new ZipcodeDAO();
			dao.InsertZipcode(path);
			url = "./Main/result.jsp";
		} else if ("searchpost".equals(cmd)) {
			String dong = request.getParameter("dong");
			ZipcodeDAO dao = new ZipcodeDAO();
			ArrayList<ZipCodeVO> list = new ArrayList<ZipCodeVO>();
			list = dao.search(dong);

			request.setAttribute("vo", list);
			url = "./postal.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
