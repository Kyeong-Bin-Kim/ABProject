package G4_ABProject;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board_Open extends Board_Access implements UserChoice {

	static int pNum;
	String pTitle;
	String pContents;
	
	void board_open_contents() {
		try {
			pNum = rs.getInt(1); 
		    pTitle = rs.getString(2);
		    pContents = rs.getString(3);
		    
			System.out.println("___________________________________________________");
			System.out.println("글번호 : "+pNum+"\t제목 : "+pTitle);
			System.out.println("___________________________________________________");
			System.out.println(pContents);
			System.out.println();
			System.out.println("___________________________________________________");
			
		} catch (SQLException e) {
			System.out.println("ErrorCode 101 : DB접속오류");
		} finally {
			if ("삭제된 게시글입니다.".equals(pTitle)) {
				System.out.println("목록으로 돌아가려면 아무키나 누르세요.");
				Scanner sc = new Scanner(System.in);
				String dumpInput = sc.next();
				if (dumpInput.equals("managePW")) {
					board_open_choice();
				} else {
					board_closs();
					Board_List bl = new Board_List();
					bl.board_list_main();
				}
			} else
				board_open_choice();
			
		}
	}
	
	void board_open_choice() {
		int choice = -1;
		//do {
			Scanner sc = new Scanner(System.in);
			System.out.println("원하시는 작업을 선택해주세요.\n(수정 : '1' / 삭제 : '2' / 목록돌아가기 : '0' / 프로그램 종료 : '-1')");
			try {
				choice = sc.nextInt();
				choiceMenu(choice);
				
			} catch(InputMismatchException e) {
				System.out.println("잘못된 입력. 숫자만으로 다시 입력해주십시오.");
				board_open_choice();
			}
		
	}

	@Override
	public void choiceMenu(int choice) {
		if (choice == -1) {
			System.out.println("프로그램을 종료합니다.");
		} else if (choice == 0) {
			System.out.println();
			Board_List bl = new Board_List();
			bl.board_list_main();
		} else if (choice == 1 ) {
			System.out.println();
			Board_Management bm = new Board_Management();
			bm.board_management_update();
//			System.out.println("수정 UI입니다.");
		} else if (choice == 2) {
			System.out.println();
			Board_Management bm = new Board_Management();
			bm.board_management_delete();
//			System.out.println("삭제 UI입니다.");
		} else {
			System.out.println("ErrorCode 103 : 선택 오류");
			board_open_choice();
		}
		
		
	}
}
