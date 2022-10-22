package kr.or.ddit.reflection;

import java.lang.reflect.Modifier;

public class T02ClassMetadataTest {
	public static void main(String[] args) {
		//클래스 오브젝트 생성하기
		Class<?> clazz = SampleVO.class;
		
		System.out.println("심플클래스명:" + clazz.getSimpleName());
		System.out.println("클래스명: "+ clazz.getName());
		System.out.println("상위클래스명: " + clazz.getSuperclass().getName());
		
		//패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println("패키지정보: " + pkg.getName());
		
		//해당 클래스에서 구현하고 있는 인터페이스 목록 가져오기
		Class<?>[] interfaces = clazz.getInterfaces(); //클래스 객체 여러개를 가지고 있는 배열 
		
		System.out.println("인터페이스 목록: "); //Implement 한 인터페이스 , 배열을 이용한 향상된 for문 
		for(Class<?> inf : interfaces) {
			System.out.println(inf.getName() + " | ");
		}
		System.out.println();
		
		//클래스의 접근제어자 가져오기 (flag bit값 반환됨 . => 접근제어자 설정유무 정보)
		int modFlag = clazz.getModifiers();
		
		System.out.println("접근제어자: " + Modifier.toString(modFlag));
		
	}
}
