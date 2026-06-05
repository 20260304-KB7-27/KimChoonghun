package app;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {
    static MongoClient client; // mongodb 서버 연결
    static MongoDatabase database; // DB 까지 연결

    static {

        // PojoCodecProvider
        // BSON으로 변환해주는 Codec

        // BSON : Binary JSON [타입][필드명][값]
        // MongoDB에서 사용하는 데이터 저장 방법

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true) // PODO랑 BSON을 자동으로 매핑하겠다.
                .build();

        // CodecRegistry : mongodb 클라이언트에서 사용할 codec들 모음
        CodecRegistry pojoCodecRegistry =
                // fromRegistries : 여러 Registries를 묶어서 하나로 만들어주는 역할
                fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        client = MongoClients.create(connectionString);

        // MongoDatabase : Mongodb의 특정 데이터베이스에 접근하기 위한 객체
        // pojoCodecRegistry를 데이터베이스 현결에 적용 : 자바 객체를 직렬화, 역직렬화가 가능하도록
        database = client.getDatabase("todo_db").withCodecRegistry(pojoCodecRegistry);
    }

    public static void close() {
        client.close();
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    // 컬레션 객체 반환
    // PoJO 타입으로 전달할 것이므로 T 사용
    public static <T> MongoCollection<T> getCollection(String colName, Class<T> clazz) {
        MongoCollection<T> collection = database.getCollection(colName, clazz);
        return collection;
    }
}
