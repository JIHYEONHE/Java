package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//service 이용한 테스트
public class AnnotationTest {
	public static void main(String[] args) {
		
		// Reflection API를 이용하여 선언된 메서드 목록 가져오기
		Method[] declaredMethods = Service.class.getDeclaredMethods(); //.class 서비스 클래스정보
	
		for(Method m : declaredMethods) {
			System.out.println(m.getName());
			
			// Annotation 정보 가져오기
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for(Annotation anno : annos) {
				if(anno.annotationType()
						.getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for(int i=0; i<printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
					
				}
			}
			System.out.println();
		}
	
	
	
	
	}
}
