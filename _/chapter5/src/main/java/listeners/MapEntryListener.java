package listeners;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;

public class MapEntryListener implements EntryListener<String, String> {

  @Override
  public void entryAdded(EntryEvent<String, String> event) {
    System.err.println("Added: " + event);
  }

  @Override
  public void entryRemoved(EntryEvent<String, String> event) {
    System.err.println("Removed: " + event);
  }

  @Override
  public void entryUpdated(EntryEvent<String, String> event) {
    System.err.println("Updated: " + event);
  }

  @Override
  public void entryEvicted(EntryEvent<String, String> event) {
    System.err.println("Evicted: " + event);
  }
}
