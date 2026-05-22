package basic.ch18.sec02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("resource/fileoutput.db");

            /*
                FileInputStream
                - 바이트 단위로 파일을 읽어오는 클래스
                - 이미지, 동영상, 문자 모든 바이너리 데이터 읽어올 수 있음.
             */
            while (true) {
                int data = is.read(); //1byte씩 읽기
                if (data == -1) break; // 파일을 다 읽으면 -1 반환 -> 반복문 종료
                System.out.println(data);
            }

            is.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
