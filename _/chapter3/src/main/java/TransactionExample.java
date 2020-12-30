import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Transaction;

import java.util.Map;

public class TransactionExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    Map<String, String> testMap = hz.getMap("test");

    Transaction tx = hz.getTransaction();
    tx.begin();

    try {
      System.err.println(testMap.get("foo"));
      testMap.put("foo", "bar");

      Thread.sleep(20000);

      System.err.println(testMap.get("foo"));

      tx.commit();
    }
    catch (Exception e) {
      tx.rollback();
    }
  }
}
