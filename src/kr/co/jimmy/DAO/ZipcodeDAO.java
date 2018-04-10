package kr.co.jimmy.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.jimmy.VO.ZipCodeVO;
import kr.co.jimmy.connection.ConnectionManager;

public class ZipcodeDAO {

	private ZipCodeVO getInstance(String line) {
		ZipCodeVO vo = new ZipCodeVO();

		vo.setZipcode(line.split(",")[0]);
		vo.setSido(line.split(",")[1].equals("") ? " " : line.split(",")[1]);
		vo.setGugun(line.split(",")[2].equals("") ? " " : line.split(",")[2]);
		vo.setDong(line.split(",")[3].equals("") ? " " : line.split(",")[3]);
		vo.setRi(line.split(",")[4].equals("") ? " " : line.split(",")[4]);
		vo.setBldg(line.split(",")[5].equals("") ? " " : line.split(",")[5]);
		vo.setBunji(line.split(",")[6].equals("") ? " " : line.split(",")[6]);
		vo.setSeq(line.split(",")[7].equals("") ? " " : line.split(",")[7]);

		return vo;
	}

	// 주소 넣기
	public boolean InsertZipcode(String path) {
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<ZipCodeVO> list = new ArrayList<ZipCodeVO>();
		String sql = "INSERT INTO zipcode_tbl VALUES (?,?,?,?,?,?,?,?)";
		String line = null;
		int count = 0;

		try {
			File file = new File(path);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				list.add(this.getInstance(line));
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pstmt = con.prepareStatement(sql);

			for (ZipCodeVO vo : list) {
				pstmt.setString(1, vo.getSeq());
				pstmt.setString(2, vo.getZipcode());
				pstmt.setString(3, vo.getSido());
				pstmt.setString(4, vo.getGugun());
				pstmt.setString(5, vo.getDong());
				pstmt.setString(6, vo.getRi());
				pstmt.setString(7, vo.getBldg());
				pstmt.setString(8, vo.getBunji());
				pstmt.executeUpdate();

				count++;
				System.out.println(count + "개 삽입 중");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mgr.ConnectionClose(con, pstmt, null);
		return flag;
	}

	// 동 찾기
	public ArrayList<ZipCodeVO> search(String dong) {
		ZipCodeVO vo = null;
		ArrayList<ZipCodeVO> list = null;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		list = new ArrayList<ZipCodeVO>();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM zipcode_tbl WHERE dong LIKE ?";

		try {
			stmt = con.createStatement();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+dong+"%");
			// stmt = con.createStatement();
			// rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				vo = new ZipCodeVO();
				vo.setSeq(rs.getString(1));
				vo.setZipcode(rs.getString(2));
				vo.setSido(rs.getString(3));
				vo.setGugun(rs.getString(4));
				vo.setDong(rs.getString(5));
				vo.setRi(rs.getString(6));
				vo.setBldg(rs.getString(7));
				vo.setBunji(rs.getString(8));

				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
		return list;
	}
}
