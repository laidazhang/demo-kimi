import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import core.framework.mongo.MongoMigration;
import org.bson.Document;

/**
 * @author kimi
 */
public class Main {
    public static void main(String[] args) {
        var migration = new MongoMigration("sys.properties", "sys.mongo.adminURI");
        migration.migrate(mongo -> {
            mongo.runCommand(new Document().append("setParameter", 1).append("notablescan", 1));
        });

        migration = new MongoMigration("sys.properties");
        migration.migrate(mongo -> {
            mongo.createIndex("products", Indexes.ascending("title"));
            mongo.createIndex("products", Indexes.ascending("status"));
            mongo.createIndex("products", Indexes.ascending("description"));
            mongo.createIndex("products", Indexes.ascending("status", "title", "description"));
            mongo.createIndex("products", Indexes.compoundIndex(Indexes.ascending("title"), Indexes.ascending("status")), new IndexOptions().name("title_status_asc"));
        });
    }
}
