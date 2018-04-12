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
	
	//나중에 한 insert
	public boolean InsertZipcodeV2(String path) {
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<ZipCodeVO> list = new ArrayList<ZipCodeVO>();
		
		// zipcode_seq.NEXTVAL는 이미 우리가 SQL로 만들었다 
		// 이것으로 순서를 지정해주면 NEXTVAL가 순서를 입력해준다
		String sql = "INSERT INTO zipcode2_tbl VALUES (zipcode_seq2.NEXTVAL,?,?,?,?,?,?,?)";
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
			//기본적으로 오토 커밋이 들어있어서 우리가 커밋을 하도록 하기 위해 그 기능을 끔
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			for (ZipCodeVO vo : list) {
				pstmt.setString(1, vo.getZipcode());
				pstmt.setString(2, vo.getSido());
				pstmt.setString(3, vo.getGugun());
				pstmt.setString(4, vo.getDong());
				pstmt.setString(5, vo.getRi());
				pstmt.setString(6, vo.getBldg());
				pstmt.setString(7, vo.getBunji());
				
				//쿼리를 보내지 않고 모았다가 한꺼번에 보냄 모으는 기능
				pstmt.addBatch();
				count ++;
				if(count % 100 ==0) {
					//100개가 모이면 보내겠다.
					pstmt.executeBatch();
				}
			}
			//나머지 남은 쿼리들을 보낸다
			pstmt.executeBatch();
			System.out.println("삽입 완료");
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = true;
			try {
				// 데이터를 보내는 도중에 에러가 나면 DB에 모두 저장이되지 않는다.
				// 이전에 그냥 excuteupdate를 하게 되면 일부 저장되고 오류난 뒤 부터는 저장이 안되는데
				// 이렇게 하면 100개를 일단 보내고 오류가 발생한 부분 부터는 안들어가게 된다.
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			//다시 반납 전에 commit을 켜고 반납해야한다.
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mgr.ConnectionClose(con, pstmt, null);
		}
		
		// 이곳에서 false가 나와야 값이 들어갔다는 의미가 된다.
		// 값을 전달 받는 쪽은 true가 되어야 값이 들어갔다라고 로직을 짯기 떄문에 여기서 이렇게 보내면됩니다.
		// 똑똑하네
		return !flag;
	}
}
