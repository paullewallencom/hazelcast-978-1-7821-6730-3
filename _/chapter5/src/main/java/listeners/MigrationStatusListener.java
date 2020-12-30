package listeners;

import util.MigrationStatus;

import com.hazelcast.partition.MigrationEvent;
import com.hazelcast.partition.MigrationListener;

public class MigrationStatusListener implements MigrationListener {

  @Override
  public void migrationStarted(MigrationEvent migrationEvent) {
    MigrationStatus.migrationEvent(migrationEvent.getPartitionId(), true);
  }

  @Override
  public void migrationCompleted(MigrationEvent migrationEvent) {
    MigrationStatus.migrationEvent(migrationEvent.getPartitionId(), false);
  }

  @Override
  public void migrationFailed(MigrationEvent migrationEvent) {
    System.err.println("Failed: " + migrationEvent);
    MigrationStatus.migrationEvent(migrationEvent.getPartitionId(), false);
  }
}
