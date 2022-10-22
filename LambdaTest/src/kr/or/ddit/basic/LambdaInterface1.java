package kr.or.ddit.basic;
//함수적 인터페이스(Functional Interface) (추상메스드가 1개)
@FunctionalInterface
public interface LambdaInterface1 {
	//반환값이 없고 매개변수도 없는 추상메서드 선언
	public void test();
}

@FunctionalInterface
interface LambdaInterface2 {
	//반환값이 없고 매개변수가 있는 추상메서드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaInterface3 {
	//반환값이 있고 매개변수도 있는 추상메서드 선언
	public int test(int a, int b);
}
