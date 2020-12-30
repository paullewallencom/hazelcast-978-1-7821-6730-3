import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.IMap;

public class ClientExample {
  public static void main(String[] args) {
    ClientConfig conf = new ClientConfig();
    conf.addAddress("127.0.0.1:5701");
    conf.addAddress("127.0.0.1:5702", "127.0.0.1:5703");
    conf.setShuffle(true);

    HazelcastClient hzc = HazelcastClient.newHazelcastClient(conf);

    IMap<String, String> capitals = hzc.getMap("capitals");

    if (capitals.isEmpty()) {
      System.err.println("Empty capitals map, adding entries");

      capitals.put("GB", "London");
      capitals.put("FR", "Paris");
      capitals.put("US", "Washington DC");
      capitals.put("AU", "Canberra");
    }

    System.err.println(
      "Known capital cities: " + capitals.size());

    System.err.println(
      "Capital city of GB: " + capitals.get("GB"));

    hzc.shutdown();
  }
}
