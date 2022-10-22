package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation
	public void method1() { 
		System.out.println("메서드1 에서 출력되었습니다.");
	}
	@PrintAnnotation( "%")  //value만 사용할경우  value생략하고 "%" 만해도 가능 에러안남
	public void method2() {
		System.out.println("메서드2 에서 출력되었습니다.");
	}
	@PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3 에서 출력되었습니다.");
	}
	
	

}
