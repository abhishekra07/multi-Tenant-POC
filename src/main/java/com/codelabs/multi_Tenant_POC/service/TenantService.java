package com.codelabs.multi_Tenant_POC.service;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class TenantService {

    @Autowired
    private DataSource baseDataSource; // This points to your root database (any schema)

    public void createTenantSchema(String schemaName) {
        try (Connection conn = baseDataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS `" + schemaName + "`");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating schema: " + schemaName, e);
        }

        // Run liquibase for this schema
        runLiquibaseForSchema(schemaName);
    }

    private void runLiquibaseForSchema(String schemaName) {
        try {
            HikariDataSource schemaDataSource = new HikariDataSource();
            schemaDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + schemaName); // Customize as needed
            schemaDataSource.setUsername("root");
            schemaDataSource.setPassword("root");
            schemaDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = schemaDataSource.getConnection()) {
                JdbcConnection jdbcConnection = new JdbcConnection(connection);
                Database database = DatabaseFactory.getInstance()
                        .findCorrectDatabaseImplementation(jdbcConnection);

                Liquibase liquibase = new Liquibase(
                        "db/changelog/db.changelog-master.yaml",
                        new ClassLoaderResourceAccessor(),
                        database
                );
                liquibase.update(new Contexts(), new LabelExpression());
            } finally {
                schemaDataSource.close(); // Close to avoid connection leak
            }
        } catch (Exception e) {
            throw new RuntimeException("Liquibase migration failed for schema: " + schemaName, e);
        }
    }
}