package app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

/*
 * POJO (Plan Old Jav Object)
 * -> 순수한 Java로 만들어진 파일
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private ObjectId id;
    private String title;
    private String desc;
    private boolean done;
}
