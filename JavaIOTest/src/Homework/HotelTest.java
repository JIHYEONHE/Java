package Homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {
	private static Scanner scan;
	private static Map<Integer, Hotel> hotelMap;

	public HotelTest() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<Integer, Hotel>();
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
		newFile(); // bin 파일이 해당 경로에 위치하고 있는지 확인 하는 메서드 -> 있으면 진행 없으면 생성
		receive();// 해당 경로에 bin 파일에 저장된 정보를 갖고와 map에 담는 메서드 -> bin파일 쓴이유 : 객체에 정보를 저장하기 위해
		while (true) {
			HotelMenu();

			int menuNum = scan.nextInt();
			scan.nextLine();

			switch (menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomcon();
				break;
			case 4:
				System.out.println("호텔문을 닫았습니다.");
				close(); // 프로그램 수행시 처리한 정보를 종료시 bin 파일에 저장해주는 기능
				System.exit(0);// 프로그램 종료
				//return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요");
			}
		}
	}

	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}

	// 3번 객실상태
	private void roomcon() {
		//hotelMap에 담긴 정보를 모두 출력 하여야 해서 keySet키의  iterator()메서드로 모든 키를 받아온다.
		Set<Integer> keySet = hotelMap.keySet();
		System.out.println("=====================================================");
		if(keySet.size()==0) {	//keySet의 size()메서드로 저장된 값이 있는지 확인  'keySet.size()==0'이면 저장된 정보가 없음.			
			System.out.println("체크인 한 사람이 아무도 없습니다.");
		}else {
			//Set의 데이터를 가져오기 위해 Iterator객체를 얻어와야한다.
			// => Set의 iterator() 메서드를 호출하면 된다.
			Iterator<Integer> it = keySet.iterator();
			System.out.println("▼ 현재 객실 상태 ▼");
			//데이터 개수만큼 반복하기
			while (it.hasNext()) {//hasNext()메서드 => 포인터 다음 위치에 데이터가 있으면 true, 없으면 false를 반환한다
				int rNum =it.next();	//next()메서드 => 포인터 다음 자료위치로 이동하고, 이동한 위치의 자료를 반환한다.
				Hotel h = hotelMap.get(rNum);	//it.next로 반환 받은 rNum(방번호)를 키로 hotelMap에서 get으로 갖고와 h변수에 저장
				System.out.println(h.getName()+"님이  "+h.getRoomNum()+"호에 체크인 되었습니다.");
			}
		}
		System.out.println("=====================================================\n\n");
		
	}
//		Set<Integer> keySet = hotelMap.keySet();
//
//		if (keySet.size() == 0) {
//			System.out.println("등록된 정보가 없습니다.");
//		} else {
//			// Set의 데이터를 가져오기 위해 Iterator객체를 얻어와야한다.
//			// => Set의 iterator() 메서드를 호출하면 된다.
//			Iterator<Integer> it = keySet.iterator();
//			int cnt = 0;
//			while (it.hasNext()) {
//				cnt++;
//				String name = it.next();
//				Hotel h = hotelMap.get(name); // get으로 key 에 연결된value값 (Hotel)가져오기
//				System.out.println(" " + cnt + "\t방번호: " + h.getRoomNum() + "\t투숙객: " + h.getName());
//			}
//		}
//		System.out.println("=====================================================\n\n");
//	}

	// 2번 체크아웃
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력=> ");
		int roomNum = scan.nextInt();

		if (hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}

	}

	// 1번 체크인
	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		Integer roomNum = scan.nextInt();
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력=> ");
		String name = scan.nextLine();

		if (hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		} else {
			System.out.println("체크인 되었습니다.");

		}
		hotelMap.put(roomNum, new Hotel(name, roomNum));
	}
	//방번호 입력 메소드
	public static int roomNum() {
		
		System.out.print("방번호 입력 => ");
		int rNum = scan.nextInt();
		return rNum;
	}
	
	//이름을 입력받는 메소드
	public static String name() {
		System.out.print("이름 입력 => ");
		String rName = scan.next();
		return rName;
	}

	/**
	 * 최초로 파일이 없을 경우 파일 생성, 파일이 있을경우 넘어가는 메서드
	 */
	private static void newFile() {
		File hotel = new File("d:/D_Other/hotel/roomState.bin");

		if (hotel.exists()) { // roomState 있으면 그냥 리턴
								// 파일명.exists() 파일 존재 확인 하는 것 있으면 true
			return;
		} else { // roomState 파일이 없으면 파일을 생성해줘야한다.

			try {
				ObjectOutputStream room = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream("d:/D_Other/hotel/roomState.bin")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 호텔 문이 열릴시 기존 정보를 받아오는 메서드
	 */
	@SuppressWarnings("unchecked")
	private static void receive() {
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/hotel/roomState.bin")));

			Object obj;

			while ((obj = ois.readObject()) != null) {
				hotelMap = (Map<Integer, Hotel>) obj; // 케스팅 후에 저장
			}
			ois.close();

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 업무 종료시 파일에 정보를 저장 하는 메서드
	 */
	private static void close() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("d:/D_Other/hotel/roomState.bin")));
			oos.writeObject(hotelMap);
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class Hotel {
	private String name;
	private Integer roomNum;

	public Hotel(String name, Integer roomNum) {
		super();
		this.name = name;
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", roomNum=" + roomNum + "]";
	}

}
