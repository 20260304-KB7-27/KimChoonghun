package basic.ch18.sec02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {
    /*
        FileOutputStream
        - 기본 파일 입출력 스트림
        - 바이트 단위로 파일에 데이터를 저장하는 스트림
     */
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("resource/fileoutput.db")) {

            byte a = 10;
            byte b = 20;
            byte c = 30;

            os.write(a);
            os.write(b);
            os.write(c);

            os.flush();
            os.close(); // close 할때 flush 먼저 수행해준다.

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
