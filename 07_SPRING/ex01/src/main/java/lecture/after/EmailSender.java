package lecture.after;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    public void send(String message) {
        System.out.println("이메일 발송 : " + message);
    }
}
