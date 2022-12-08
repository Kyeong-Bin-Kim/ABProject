package G4_ABProject;

import java.sql.SQLException;
import java.util.Scanner;

public class Board_Management extends Board_Open {
	static Scanner sc = new Scanner(System.in);
	private static String insertPW;
	private static String privatePW;
	private static String adminPW;
//	static int boardNum;
	static String title;
	static String boardContents;
	
	
	
	
	 void board_management_pass() {
			System.out.println("비밀번호를 입력해주세요.");
			insertPW = sc.next();
			try {
				privatePW = rs.getString(4);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board_closs();
}
	 void board_management_adminPW() {
			String sql4 = "select privatePW from AnonymousBoard where boardNum = 1";
				try {
			pstmt = conn.prepareStatement(sql4);
				rs = pstmt.executeQuery();
				
				rs.next(); 
				adminPW = rs.getString("privatePW");
				}catch(SQLException e){
					e.printStackTrace();
				}
				board_closs();
		 }
	
	 void board_management_update() {
		//boardNum값을 받아와야함
		board_management_pass();
		board_management_adminPW();
		if(privatePW.equals(insertPW)||insertPW.equals(adminPW)) {
			board_management_update_title();
			
			board_management_update_contents();
	    	String sql = "update AnonymousBoard set title = ? , boardContents =?"
					+ "where boardNum = ?";
					
					try {
						pstmt = conn.prepareStatement(sql);
						
						pstmt.setString(1, title);
						pstmt.setString(2, boardContents);
						pstmt.setInt(3, pNum);
						
						pstmt.executeUpdate();
						System.out.println("___________________");
						System.out.println();
						System.out.println("게시글 수정을 성공했습니다.");
						System.out.println("___________________");
						Board_List bl = new Board_List();
						bl.board_list_main();
					} catch (SQLException e1) {
						e1.printStackTrace();
						System.out.println("데이터 수정을 실패하였습니다.");//에러코드 보고다시수정
					}
		}else {
			
			System.out.println("___________________");
			System.out.println("   errorcode301");
			System.out.println("   비밀번호가 틀렸습니다.");
			System.out.println("___________________");
//				String sql2 = "select * AnonymousBoard where boardNum = ? ";//게시글열람으로 돌아가기위함
			//게시글 열람문추가해야됨
			Board_List bl = new Board_List();
			bl.board_list_main();
		}
		
	}
	 void board_management_delete() {
		board_management_pass();
		board_management_adminPW();
		if(privatePW.equals(insertPW)||insertPW.equals(adminPW)) {
					title = "삭제된 게시글입니다.";
			
					boardContents = "삭제된 게시글입니다.";
			
					privatePW = adminPW;
			
			String sql = "update AnonymousBoard set title = ? , boardContents =?, privatePW = ?"
			+ "where boardNum = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, title);
				pstmt.setString(2, boardContents);
				pstmt.setString(3, privatePW);
				pstmt.setInt(4, pNum);
				
				pstmt.executeUpdate();
				System.out.println("_______________________________");
				System.out.println();
				System.out.println(" 게시글이 삭제되었습니다. 목록으로돌아갑니다 ");
				System.out.println("_______________________________");
				
//					String sql2 = "select * AnonymousBoard where boardNum";//게시글열람으로 돌아가기위함
			//게시글열람문
				Board_List bl = new Board_List();
				bl.board_list_main();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("__________________________________");
				System.out.println();
				System.out.println("     Error code101 : DB접속불가");//에러코드 보고다시수정
				System.out.println("__________________________________");
			}
			
		}else {
			
			System.out.println("__________________________________");
			System.out.println();
			System.out.println("  Error code 301 비밀번호가 틀렸습니다.");
			System.out.println("__________________________________");
			//게시글 열람문
//				String sql2 = "select * AnonymousBoard where boardNum = ? ";//게시글열람으로 돌아가기위함
			Board_List bl = new Board_List();
			bl.board_list_main();
		}
		
	}
	 void board_management_update_title() {
		 System.out.println("______________________________________");
			System.out.println();
			System.out.println(" 제목 : 게시글의 제목을 입력해주세요.(40자 이내)");
			System.out.println("______________________________________");
			
			title = sc.next();
			if (title.length()>40) {
				board_management_update_title();
				System.out.println("error201 : 제목 글자수 초과");
			}
	 }
	 void board_management_update_contents() {	
			
		 System.out.println("______________________________________");
			System.out.println();
			System.out.println(" 내용 : 게시글의 내용을 입력해주세요.");
			System.out.println("______________________________________");
			Scanner sc2 = new Scanner(System.in);
			
			boardContents = sc2.nextLine();
			
	        if (boardContents.length()>500 || boardContents.isEmpty()) {
	        	System.out.println("errorcode202 : 내용이 없거나 글자수 초과");
	        	board_management_update_contents();
	        }else {
			    while (sc2.hasNextLine()) {
			    	String boardContent = sc2.nextLine();
		            if (boardContent.length()>500 || boardContent.isEmpty()) {
		            	break;
		            }
		
		            boardContents += "\r\n";
		            boardContents += boardContent;
		            
				}
	        }

		
 }
	
}
