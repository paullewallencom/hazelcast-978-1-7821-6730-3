import data.City;
import tasks.AverageCityPopulationCallable;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AverageCityPopulationCallableExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    MapConfig citiesConf = conf.getMapConfig("cities");
    citiesConf.addMapIndexConfig(new MapIndexConfig("country", false));

    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    IMap<String, City> cities = hz.getMap("cities");

    if (cities.isEmpty()) {
      cities.put("London-GB",
        new City("London", "GB", 8174100));
      cities.put("Southampton-GB",
        new City("Southampton", "GB", 304400));
      cities.put("Plymouth-GB",
        new City("Plymouth", "GB", 258700));
      cities.put("York-GB",
        new City("York", "GB", 197800));

      cities.put("Paris-FR",
        new City("Paris", "FR", 2268265));
    }

    ExecutorService exec = hz.getExecutorService();

    Future<Integer> avgTask = exec.submit(new AverageCityPopulationCallable("GB"));

    Integer avgPop = avgTask.get();
    System.err.println("Average GB city population: " + avgPop);
  }
}
