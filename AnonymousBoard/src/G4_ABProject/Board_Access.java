package G4_ABProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Board_Access {

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	/*
	public static void main(String[] args) {
	
		board_accessProcess();
		
	}
	*/
	
	static void board_accessProcess() {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hyun"; 
		String pw = "1234";
	
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			//System.out.println("드라이버 연결에 성공하였습니다.");
			//System.out.println("사용자 인증에 성공하였습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("ErrorCode101 : DB접속불가");
			//System.out.println("드라이버 연결에 실패하였습니다.");
		} catch (SQLException e) {
			System.out.println("ErrorCode101 : DB접속불가");
			//System.out.println("사용자 인증에 실패하였습니다.");
		}
		
	}
	
	static void board_closs() {
		
		try {
			if(rs != null) { rs.close(); }
			if(pstmt != null) { pstmt.close(); }
			//System.out.println("데이터베이스 객체를 닫는데 성공하였습니다.");
			
		}catch(Exception e) {
			System.out.println("데이터베이스 객체를 닫는데 실패하였습니다.");
		}
	}
	
	static void board_disconnection() {
		try {
			if(conn != null) { conn.close(); }
			System.out.println("연결 종료.");
		} catch(Exception e) {
			System.out.println("데이터베이스 연결을 끊는데 실패하였습니다.");
		}
	}
	
	
}
