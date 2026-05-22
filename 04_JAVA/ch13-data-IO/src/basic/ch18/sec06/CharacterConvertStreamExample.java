package basic.ch18.sec06;

import java.io.*;

public class CharacterConvertStreamExample {
    public static void main(String[] args) throws Exception {
        String str = "문자 변환 스트림을 사용합니다.";

        write(str);

        String data = read();
        System.out.println(data);
    }

    private static void write(String str) throws Exception {

        // 바이트 스트림
        OutputStream os = new FileOutputStream("resource/test.txt");

        // 바이트 스트림 -> 문자 스트림으로 변환
        Writer writer = new OutputStreamWriter(os, "UTF-8");

        writer.write(str);

        writer.flush();

        writer.close();
    }

    private static String read() throws Exception {
        InputStream is = new FileInputStream("resource/test.txt");

        // 바이트 스트림 -> 문자열 스트림 변환
        Reader reader = new InputStreamReader(is, "UTF-8");

        // 데이터 읽어올 공간
        char[] data = new char[100];

        int num = reader.read(data);

        String str = new String(data, 0, num);

        return str;
    }
}
