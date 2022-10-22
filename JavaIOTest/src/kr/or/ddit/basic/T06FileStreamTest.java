package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 파일 출력 예제
 */  //char 2byte  int,float 4byte double 8byte
public class T06FileStreamTest {
	public static void main(String[] args) {
		//쓰기
		FileOutputStream fos = null;

		try { // FileOutputStream 해서 텍스트파일만듬
			fos = new FileOutputStream("d:/D_Other/out.txt");

			for (char ch = 'a'; ch <= 'z'; ch++) {
				fos.write(ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//읽기
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File("d:/D_Other/out.txt"));
			int data = 0;
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
