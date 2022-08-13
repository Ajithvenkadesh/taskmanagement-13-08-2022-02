package com.system.assignee.dao;

import com.connection.DBConnector;
import com.system.assignee.model.Assignee;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Creates, deletes, reads, updates assignee records.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class AssigneeDao {
    public static final Logger LOGGER = Logger.getLogger(AssigneeDao.class.getName());

    /**
     * Inserts new assignee record into the database.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure message.
     */
    public int create(final Assignee assignee) {
        final String insertSql = "INSERT INTO assignee (assignee_name, role) VALUES (?,?)";
        final DBConnector connector = DBConnector.getInstance();
        final String selectSql = "SELECT assignee_id FROM assignee ORDER BY assignee_id DESC LIMIT 1";
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                if ((assignee.getAssigneeName() == null) || (assignee.getRole() == null)) {
                    LOGGER.warning("assginee name or assignee role is null");
                    return 0;
                } else {
                    insertStatement.setString(1, assignee.getAssigneeName());
                    insertStatement.setString(2, assignee.getRole());

                    if (insertStatement.executeUpdate() > 0) {
                        try (Statement selectStatement = connection.createStatement()) {
                            final ResultSet result = selectStatement.executeQuery(selectSql);

                            if (result.next()) {
                                return result.getInt(1);
                            }
                        }
                    } else {
                        return 0;
                    }
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return 0;
    }

    /**
     * Updates a particular assignee record.
     *
     * @param id   Id of the assignee.
     * @param name Name of the assignee.
     * @return Success or failure message.
     */
    public boolean update(final int id, final String name, final String role) {
        final String updateSql = "UPDATE assignee SET assignee_name = COALESCE(?,assignee_name)," +
                "role = COALESCE(?,role) WHERE assignee_id = ?";
        final DBConnector connector = DBConnector.getInstance();
        final String selectSql = "select * from assignee where assignee_id =" + id;
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement testStatement = connection.createStatement()) {
                final ResultSet result = testStatement.executeQuery(selectSql);

                if (result != null) {
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setString(1, name);
                        updateStatement.setString(2, role);
                        updateStatement.setInt(3, id);

                        if (updateStatement.executeUpdate() > 0) {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return false;
    }

    /**
     * Deletes a particular record from assignee table.
     *
     * @param id Id of the assignee.
     * @return Success or failure message.
     */
    public boolean delete(final int id) {
        final String updateSql = "DELETE FROM assignee WHERE assignee_id = ?";
        final String testSql = "select * from assignee where assignee_id =" + id;
        final DBConnector connector = DBConnector.getInstance();
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement testStatement = connection.createStatement()) {
                final ResultSet result = testStatement.executeQuery(testSql);

                if (result != null) {
                    try (PreparedStatement deleteStatement = connection.prepareStatement(updateSql)) {
                        deleteStatement.setInt(1, id);

                        if (deleteStatement.executeUpdate() > 0) {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return false;
    }

    /**
     * Searches a particular assignee record using assignee id.
     *
     * @param id Id of the assignee.
     * @return Object of assignee class.
     */
    public Assignee get(final int id) {
        final String sql = "SELECT * FROM assignee WHERE assignee_id = " + id;
        final DBConnector connector = DBConnector.getInstance();
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement statement = connection.createStatement()) {
                final ResultSet result = statement.executeQuery(sql);

                if (result.next()) {
                    return new Assignee(result.getInt(1), result.getString(2),
                            result.getString(3));
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return null;
    }

    /**
     * Displays all the assignees.
     *
     * @return List of assignees.
     */
    public ArrayList<Assignee> list() {
        final DBConnector connector = DBConnector.getInstance();
        final ArrayList<Assignee> assigneeList = new ArrayList<Assignee>();
        final String sql = "SELECT * FROM assignee";
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement statement = connection.createStatement()) {
                final ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    final Assignee assignee = new Assignee(result.getInt(1),
                            result.getString(2), result.getString(3));
                    assigneeList.add(assignee);
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return assigneeList;
    }
}
