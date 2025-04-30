package org.example;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.time.Instant;

public class DatabaseChecker {

    private final CassandraConnector cassandraConnector;
    private Long latestId = null;
    private Instant latestUpdatedAt = null;

    public DatabaseChecker(CassandraConnector cassandraConnector) {
        this.cassandraConnector = cassandraConnector;
    }

    public boolean checkForModifications() {
        try {
            String query = "SELECT id, title, updated_at FROM movies ORDER BY updated_at DESC LIMIT 1";
            ResultSet resultSet = cassandraConnector.fetchData(query);
            Row latestMovie = resultSet.one();

            if (latestMovie != null) {
                Long currentId = latestMovie.getLong("id");
                String currentTitle = latestMovie.getString("title");
                Instant currentUpdatedAt = latestMovie.getInstant("updated_at");

                if (latestId == null || !currentId.equals(latestId) ||
                        (currentUpdatedAt != null && (latestUpdatedAt == null || currentUpdatedAt.isAfter(latestUpdatedAt)))) {

                    latestId = currentId;
                    latestUpdatedAt = currentUpdatedAt;
                    System.out.println("New or updated movie detected: " + currentTitle);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error checking database modification: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
