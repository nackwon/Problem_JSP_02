package kr.co.jimmy.Controller;

import java.io.IOException;
import java.io.Writer;
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
		// System.out.println("맵핑");
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
			boolean flag = dao.id_check(id);
			String message = "";
			if (flag) {
				message = "false"; // 아이디 사용 X
			} else {
				message = "true"; // 아이디 사용 가능
			}
			request.setAttribute("result", message);
			url = "./id_check.jsp";
		}  else if ("insertzipcode".equals(cmd)) {
			String path = this.getServletContext().getRealPath("WEB-INF/file/zipcode.csv");
			ZipcodeDAO dao = new ZipcodeDAO();
			dao.InsertZipcodeV2(path);
			url = "./Main/result.jsp";
		} else if ("searchpost".equals(cmd)) {
			String dong = request.getParameter("dong");
			ZipcodeDAO dao = new ZipcodeDAO();
			ArrayList<ZipCodeVO> list = new ArrayList<ZipCodeVO>();
			list = dao.search(dong);

			request.setAttribute("vo", list);
			url = "./postal.jsp";
		} else if ("searchMember".equals(cmd)) {
			String id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			MemberVO vo = new MemberVO();
			vo = dao.searchMember(id);
			url = "./Main/regist_member.jsp";
		} else if ("viewIdService".equals(cmd)) {
			url = "./Main/id_service.jsp";
		} else if ("make".equals(cmd)) {
			url = "./Main/zipcode.jsp";
		} else if("update".equals(cmd)) {
			url = "./command?cmd=searchAll";
		} else if("updateMember".equals(cmd)) {
			MemberDAO dao = new MemberDAO();
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

			boolean flag = dao.register(vo);
			dao.updateMember(vo);
			
			url = "./Main/home.jsp";
		} else if("updateReady".equals(cmd)) {
			String id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			MemberVO vo = new MemberVO();
			vo = dao.searchMember(id);
			request.setAttribute("vo", vo);
			url ="./Main/update_member.jsp";
		} else if("deleteMember".equals(cmd)){
			String id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			dao.deleteMember(id);
			url = "./Main/home.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
