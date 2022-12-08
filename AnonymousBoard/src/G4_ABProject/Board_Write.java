package G4_ABProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Board_Write extends Board_Access {

	static Scanner sc = new Scanner(System.in);
	
	static String title;
	static String boardContents;
	static String privatePW;
	
	public static void board_write_title() {
		
		System.out.println("______________________________________");
		System.out.println();
		System.out.println(" 제목 : 게시글의 제목을 입력해주세요.(40자 이내)");
		System.out.println("______________________________________");
		
		title = sc.nextLine();
		if (title.length()>40) {
			
			System.out.println("error201 : 제목 글자수 초과");
			
			board_write_title();
			
		}
		
	}

	
	public static void board_write_main() {	
		
		System.out.println("______________________________________");
		System.out.println();
		System.out.println(" 내용 : 게시글의 내용을 입력해주세요.");
		System.out.println("______________________________________");
		
		boardContents = sc.nextLine();
        if (boardContents.length()>500 || boardContents.isEmpty()) {
        	System.out.println("errorcode202 : 내용이 없거나 글자수 초과");
        	board_write_main();
        }else {
		    while (sc.hasNextLine()) {
		    	String boardContent = sc.nextLine();
	            if (boardContent.length()>500 || boardContent.isEmpty()) {
	            	break;
	            }
	
	            boardContents += "\r\n";
	            boardContents += boardContent;
	            
			}
        }

	}
	
	public static void board_write_pw() {
		
		System.out.println("______________________________________");
		System.out.println();
		System.out.println(" PassWord : 게시글의 비밀번호 입력해주세요.(공백 제외 8자 이내)");
		System.out.println("______________________________________");
		
		privatePW = sc.next();
		if (privatePW.startsWith(" ") || privatePW.endsWith(" ") || privatePW.length()>8 || privatePW.isEmpty()) {
			
			System.out.println("errorcode203 : 비밀번호 입력 오류");
			
			board_write_pw();
			
		}
		
	}
		
//	public static void main(String[] args) {
//		board_accessProcess();	
	
	void board_write_total() {

		board_write_title();
		
		board_write_main();
		
		board_write_pw();
		
		String sql = "insert into AnonymousBoard(boardNum, title, boardContents, privatePW)"
				+ "values(boardNum_seq.NEXTVAL, ?, ?, ?)";
		
		try {
	
			pstmt = conn.prepareStatement(sql);
			//DB에 저장하는 루틴
			pstmt.setString(1, title); 
			pstmt.setString(2, boardContents); 
			pstmt.setString(3, privatePW);
			
			pstmt.executeUpdate();
			System.out.println("______________________________________");
			System.out.println();
			System.out.println(" 게시글 저장에 성공하였습니다.");
			System.out.println("______________________________________");
			board_closs();
			Board_List.board_list_main();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("______________________________________");
			System.out.println();
			System.out.println(" 게시글 저장에 실패하였습니다.");
			System.out.println("______________________________________");
			board_closs();
			board_write_total();
//			main(args);
		}

	}		
				
}
	
