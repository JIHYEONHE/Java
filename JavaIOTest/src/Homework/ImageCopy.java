package Homework;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImageCopy {
public static void main(String[] args) throws Exception {
	File fre = null;
		fre = new File("e:/Tulip.jpg");
		byte[] bytes = new byte[(int) fre.length()]; //파일크기
		//읽기
		DataInputStream dis = new DataInputStream(new FileInputStream(fre));
		dis.readFully(bytes);//파일크기 넣어서 한방에읽음
		dis.close(); 
		
		FileOutputStream fos = new FileOutputStream("e:/복사본_Tulips.jpg");
		fos.write(bytes);
		fos.close();
	}
}

