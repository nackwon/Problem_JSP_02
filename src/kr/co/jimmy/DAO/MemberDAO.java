package kr.co.jimmy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.jimmy.VO.MemberVO;
import kr.co.jimmy.connection.ConnectionManager;

public class MemberDAO {

	
	//등록 인원 출력
	public ArrayList<MemberVO> searchAll() {

		ArrayList<MemberVO> list = null;

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM member_tbl";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			MemberVO vo = null;
			if (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPw(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setZipcode(rs.getString(5));
				vo.setAddr1(rs.getString(6));
				vo.setAddr2(rs.getString(7));
				vo.setTool(rs.getString(8));
				String temp= rs.getString(9);
				
				String[] vals = temp.split("-");
				String[] langs = {"","","",""};
				for (String index : vals) {
					int idx = Integer.parseInt(index);
					vals[idx] = index;
				}
				vo.setLangs(langs);
				// MemberVO 데이터 클래스를 만들어서 인스턴스를 하나 생성
				vo.setProject(rs.getString(10));

				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, stmt, rs);
		}
		return list;
	}

	public String searchMember(String userId){
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		
		String sql = "SELECT * FROM member_tbl WHERE user_id LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+userId+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("user_id");
				return name;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
		
		return name; 
	}
	
	//회원 가입
	public boolean register(MemberVO vo) {
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO member_tbl VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getZipcode());
			pstmt.setString(6, vo.getAddr1());
			pstmt.setString(7, vo.getAddr2());
			pstmt.setString(8, vo.getTool());
			String[] temp = vo.getLangs();
			StringBuffer sb = new StringBuffer(temp[0]);
			for(int i=1;i<temp.length;i++) {
				sb.append("-"+temp[i]);
			}
			pstmt.setString(9, sb.toString());
			pstmt.setString(10, vo.getProject());
			
			int affectedCount = pstmt.executeUpdate();

			if (affectedCount > 0) {
				flag = true;
				System.out.println("삽입 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, null);
		}
		return flag;
	}
}
