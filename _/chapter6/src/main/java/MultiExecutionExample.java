import tasks.TimeInstanceAwareCallable;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.core.MultiTask;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class MultiExecutionExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    ExecutorService exec = hz.getExecutorService("exec");

    Callable<String> timeCallable = new TimeInstanceAwareCallable();

    while(true) {
      Set<Member> clusterMembers = hz.getCluster().getMembers();

      MultiTask<String> timeTask =
        new MultiTask<String>(timeCallable, clusterMembers);

      exec.execute(timeTask);
      Collection<String> manyTimes = timeTask.get();

      for (String theTime : manyTimes) {
        System.err.println("The time is: " + theTime);
      }

      Thread.sleep(1000);
    }
  }
}

