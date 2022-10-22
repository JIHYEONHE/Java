package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09FileEncodingTest {
/*
 * 한글 인코딩 방식에 대하여...
 * 
 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나누어 볼 수 있다.
 * 원래 한글 윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서
 * EUC-KR 방식에서 확장하였기 때문에 MS949라고도 부른다.
 * 한글 Window의 메모장에서 말하는 ANSI 인코딩이란 CP949(Code page 949)를 말한다
 * -MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI 계열)
 * -UTF-8 => !!유니코드!! UTF-8 인코딩 방식
 *          (영문자 및 숫자: 1byte 한글: 3byte) => 가변적
 *          
 * -US-ASCII => 영문전용 인코딩 방식
 * 
 * ANSI는 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없다가 
 * 나중에 여기에 EUC-KR, CP949라는 식으로 한글이 포함이 생겨났다.
 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		InputStreamReader isr = null; //보조스트림 (inputstream (바이트기반을 문자기반으로)reader로)
		
		try {
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			//파일 인코딩을 이용하여 읽어오기
			//InputStreamReader 객체는 파일 인코딩 방식을 지정할 수 있다.
			//형식) new InputStreamReader(바이트기반스트림객체, 인코딩방식);
			isr = new InputStreamReader(fis,"UTF-8"); //MS949
			
			int data = 0;
			while((data = isr.read())!=-1) {
				System.out.print((char)data);
			}
			System.out.println();
			System.out.println("출력 끝...");
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				isr.close(); //보조 스트림만 닫아도 된다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
