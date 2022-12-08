package G4_ABProject;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board_List extends Board_Access implements UserChoice {


	public static void main(String[] args) {
		
		board_accessProcess();
		board_list_main();
		board_closs();
		board_disconnection();
		
	} 
	static void board_list_main() {
		Board_List bl = new Board_List();
		String sql = "select * from AnonymousBoard where title not like '삭제된 게시글입니다.'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("<게시판 목록>");
		} catch (SQLException e) {
			System.out.println("ErrorCode101 : DB접속불가");
		}
		
		System.out.println("___________________________________________________________");
		System.out.println("번호 \t 제목");
		System.out.println("___________________________________________________________");
		
		try {
			while(rs.next()) {
				int pNum = rs.getInt(1); 
			    String pTitle = rs.getString(2);
			    
			    System.out.println(pNum+"\t"+pTitle);
			}
			
			System.out.println("___________________________________________________________");
			board_closs();
			bl.board_list_choice();
		} catch (SQLException e) {
			System.out.println("알 수 없는 이유로 게시판을 불러오지 못하였습니다.\n다시 확인해주세요.");
		}
		
	}
	
	void board_list_choice() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		System.out.println("열람하고자 하는 게시물 번호를 입력해주세요.\n(새글쓰기 : '0'입력 / 프로그램 종료 : '-1'입력)");
		
		try {
			choice = sc.nextInt();
			try {
				String sql = "select * from AnonymousBoard where boardNum = ";
				pstmt = conn.prepareStatement(sql+choice);
				rs = pstmt.executeQuery();
				choiceMenu(choice);
				
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류.");
//				e.printStackTrace();
			}
		} catch(InputMismatchException e) {
			System.out.println("잘못된 입력. 숫자만으로 다시 입력해주십시오.");
			board_list_choice();
		}
		
	}

	@Override
	public void choiceMenu(int choice) {
		if(choice < -1) {
			System.out.println("ErrorCode 102 : 게시글 번호 없음");
			board_list_choice();
		} else if (choice == -1) {
			System.out.println("프로그램을 종료합니다.");
		} else if (choice == 0) {
			System.out.println();
			board_closs();
			Board_Write bw = new Board_Write();
			bw.board_write_total();
//			System.out.println("글쓰기 UI입니다.");
		} else
			try {
				if (!rs.next()) {
					System.out.println("ErrorCode 102 : 게시글 번호 없음");
					board_closs();
					board_list_choice();
				} else {
//					System.out.println(rs.getString(2));
					System.out.println();
					Board_Open bo = new Board_Open();
					bo.board_open_contents();
				}
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류.");
//				e.printStackTrace();
			}
		
	}
}
