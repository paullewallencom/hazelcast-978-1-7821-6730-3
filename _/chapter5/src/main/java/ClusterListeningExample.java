import listeners.ClusterInstanceListener;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class ClusterListeningExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    conf.addListenerConfig(new ListenerConfig(new ClusterInstanceListener()));

    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);
  }
}
