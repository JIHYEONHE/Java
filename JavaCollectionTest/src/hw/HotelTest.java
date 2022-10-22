package hw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {
	private Scanner scan;
	private Map<String, Hotel> hotelMap;
	
	public HotelTest() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<String, Hotel>();
	}
	
	// 메뉴를 출력하는 메소드
	public void HotelMenu() {
		System.out.println();
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.print("메뉴 선택=>  ");
	}
	
	// 프로그램을 시작
	public void hotelStart() {
		System.out.println("*************************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("*************************************");
		
		while(true) {
			HotelMenu();
			
			int menuNum = scan.nextInt();
			scan.nextLine();
			
			switch(menuNum) {
			case 1 : checkIn();
			break;
			case 2 : checkOut();
			break;
			case 3 : roomcon();
			break;
			case 4 : 
				System.out.println("호텔문을 닫았습니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요");
			}
		}
	}
	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}
      //3번 객실상태
	private void roomcon() {
		
		Set<String> keySet = hotelMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String name = it.next();
				Hotel h = hotelMap.get(name);  //get으로 key 에 연결된value값 (Hotel)가져오기 
				System.out.println(" " + cnt + "\t방번호: " + h.getRoomNum() + "\t투숙객: " + h.getName());
			}
		}
		System.out.println();
	}
	//2번 체크아웃
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력=> ");
		String roomNum = scan.nextLine();
		
		if(hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}
		
	}

	//1번 체크인
	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNum = scan.nextLine();
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력=> ");
		String name = scan.nextLine();
		
		
		if(hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		} else {
			System.out.println("체크인 되었습니다.");
			
		}
		hotelMap.put(roomNum,new Hotel(name, roomNum));
	}
	
}




