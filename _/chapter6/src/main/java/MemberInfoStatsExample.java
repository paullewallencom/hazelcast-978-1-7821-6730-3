import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiTask;
import com.hazelcast.monitor.DistributedMemberInfoCallable;
import com.hazelcast.monitor.DistributedMemberInfoCallable.MemberInfo;

import java.util.concurrent.ExecutorService;

public class MemberInfoStatsExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    ExecutorService exec = hz.getExecutorService();
    MultiTask<MemberInfo> mapStatsTask = new MultiTask<MemberInfo>(
      new DistributedMemberInfoCallable(), hz.getCluster().getMembers());

    exec.execute(mapStatsTask);

    for(MemberInfo memberInfo : mapStatsTask.get()) {
      System.err.println("partitions: " +  memberInfo.getPartitions().size());
    }
  }
}
