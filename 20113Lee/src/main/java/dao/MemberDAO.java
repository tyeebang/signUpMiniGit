package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.JdbcUtil;

public class MemberDAO {
	// member테이블 조회(getMemberList)
	public ArrayList<String> getMemberList() {
		ArrayList<String> list = new ArrayList<>();
		
		//DB 연동
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select userid from member";
		
		conn = JdbcUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("userid"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	// member테이블 추가(insertMember)
	public int insertMember(String userId, String userPwd) {
		int n = 0;
		
		// DB연동
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?, ?)";
		
		conn = JdbcUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
		return n;
	}
	
	// member테이블 수정(updateMember)
	public int updateMember(String userId, String userPwd) {
		int n = 0;
		
		//DB 연동
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member set userpwd = ? where user = ?";
		
		conn = JdbcUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
		
		return n;
	}
	
	// member테이블 삭제(deleteMember)
	
	
}
