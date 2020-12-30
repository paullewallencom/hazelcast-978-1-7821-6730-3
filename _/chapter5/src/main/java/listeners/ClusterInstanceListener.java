package listeners;

import com.hazelcast.core.InstanceEvent;
import com.hazelcast.core.InstanceListener;

public class ClusterInstanceListener implements InstanceListener {

  @Override
  public void instanceCreated(InstanceEvent event) {
    System.err.println("Instance Created: " + event);
  }

  @Override
  public void instanceDestroyed(InstanceEvent event) {
    System.err.println("Instance Destroyed: " + event);
  }
}
