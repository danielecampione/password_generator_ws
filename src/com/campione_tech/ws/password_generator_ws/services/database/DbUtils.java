package com.campione_tech.ws.password_generator_ws.services.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 *
 *
 * @author D. Campione
 *
 */
public class DbUtils {

    private static Logger logger = Logger.getLogger(DbUtils.class);

    /** Closes a JDBC ResultSet. */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            logger.error("Error occured while the ResultSet is closing: " + sqle.getMessage());
        }
    }

    /** Closes a JDBC Statemtent. */
    public static void close(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            logger.error("Error occured while the Statement is closing: " + sqle.getMessage());
        }
    }

    /** Closes a ResultSet and a JDBC Statemtent */
    public static void close(ResultSet rs, Statement stmt) {
        close(rs);
        close(stmt);
    }

    /** Closes a Connection. */
    public static void close(Connection con) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException sqle) {
            logger.error("Error occured while the Connection is closing: " + sqle.getMessage());
        }
    }
}