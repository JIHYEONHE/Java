package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {
	public static void main(String[] args) {
		// ArrayList 는 기본적인 사용법이 Vector 와 같다.
		// DEFAULT_CAPACITY = 10;
		List list1 = new ArrayList();

		// add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111); // list1.add(new Integer(111)); 한것과 같다
		list1.add('k'); // character
		list1.add(true); // boolean
		list1.add(12.34);
		// 객체화 되었다 -> rapper class (Integer,character ...)

		// size() ==> 데이터의 갯수
		System.out.println("size  =>" + list1.size());
		System.out.println("list1 =>" + list1);

		// get 으로 데이터 꺼내오기
		System.out.println("1번째 자료 :" + list1.get(0));

		// add 데이터를 끼워 넣기도 같다.
		list1.add(0, "zzz"); // 인덱스로 위치 지정하여 넣음
		System.out.println("list1 => " + list1);

		// 데이터 변경하기(set메서드)
		String temp = (String) list1.set(0, "YYY"); // 바꿀것 인덱스, 바꿀 값
		System.out.println("temp => " + temp);
		System.out.println("list1 =>" + list1);

		// remove 삭제하기도 같다.
		list1.remove(0);
		System.out.println("삭제후:  " + list1);

		list1.remove("bbb");
		System.out.println("bbb 삭제 후 :" + list1);
		// list1.remove(111); 111번째 인덱스를 지우는 것이라고 인식함
		list1.remove(new Integer(111));
		System.out.println("111 삭제 후 " + list1);
		System.out.println("----------------------------------------------------");

		// 제너릭을 지정하여 선언할 수 있다. //String타입만 담겠다고 선언한 것
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + ":" + list2.get(i));
		}
		System.out.println("----------------------------------------------------");

		// 향상된 for문 (foreach 문)
		for (String s : list2) {
			System.out.println(s);
		}
		System.out.println("----------------------------------------------------");
		// contains(비교객체); =>리스트에 '비교객체'를 찾아 '비교객체'가 있으면 true, 없으면 false 리턴함
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		System.out.println("----------------------------------------------------");

		// indexOf(비교객체); => 리스트에서 '비교객체'를 찾아 '비교 객체'가 있는 index값을 반환한다
		// 없으면 -1을 반환
		System.out.println("DDD의 index값: " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값: " + list2.indexOf("ZZZ"));
		System.out.println("----------------------------------------------------");

		// toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		//			       기본적으로 object형 배열로 반환한다.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수: " + strArr.length);

		// 리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
		// 제너릭타입의 0개짜리 배열을 생성해서 배열변수로 넣어준다.
		// 형식)toArrray(new 제너릭타입[0])
		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 개수 : " + strArr2.length);

		// 리스트 삭제처리
		//list는 앞에것이 삭제되면 앞으로 계속 댕겨짐
		//따라서 다지우고 싶다면 뒤에서 부터 지워야함
		for (int i = 0; i < list2.size(); i++) {
			list2.remove(list2.get(i));
		}
		System.out.println(list2.size());
	}
}
