package com.campione_tech.ws.password_generator_ws;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.campione_tech.ws.password_generator_ws.services.database.DbUtils;

import oracle.jdbc.OracleDriver;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * 
 * 
 * @author D. Campione
 *
 */
@WebServlet("/test")
public class StartupServlet extends HttpServlet implements Servlet {

    private static final long serialVersionUID = -96094727179754875L;

    private static StartupServlet INSTANCE;

    private static Logger logger = LogManager.getLogger(StartupServlet.class.getName());

    private OracleConnectionPoolDataSource ods;

    private Connection connection;

    private String testConnectionSQLQuery;

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info(Constants.PROJECT_NAME + " " + Constants.SERVICE_VERSION);
        logger.info("The init process of the Servlet is started.");
        INSTANCE = this;
        super.init(config);

        ClassLoader classLoader = null;
        //String app_init_file_name = getInitParameter("application-init-file");
        String app_init_file_name = "config.properties";
        //String log_init_file_name = getInitParameter("log4j-init-file");
        String log_init_file_name = "log4j.xml";
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
            logger.info("Loading the configuration file " + app_init_file_name);
            configureApp(app_init_file_name, classLoader);
            logger.info("The configuration file " + app_init_file_name + " has been loaded.");

            System.out.println("Loading the configuration file " + log_init_file_name);
            configureLog4J(log_init_file_name);
            logger.info("The configuration file " + log_init_file_name + " has been loaded.");
        } catch (Exception e) {
            logger.error(e.getMessage());
            destroy();
        }

        logger.info("The init process of the Servlet is ended.");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet called.");

        ResultSet resultSet = null;
        Statement statement = null;
        try { // Get Connection and Statement
            initConnection();

            statement = connection.createStatement();
            logger.info("query is " + testConnectionSQLQuery);
            logger.info("Executing query...");
            resultSet = statement.executeQuery(testConnectionSQLQuery);
            logger.info("The query has been executed successfully.");
            while (resultSet.next()) {
                logger.info(resultSet.getString(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            logger.info("Try to close all the open connections. Please wait...");
            DbUtils.close(resultSet, statement);
            DbUtils.close(connection);
            logger.info("All the connections are closed");
        }
    }

    public void initDriver(String oracleJDBCDriver) {
        logger.info("Searching for the JDBC driver (" + oracleJDBCDriver + ")...");
        try {
            Class.forName(oracleJDBCDriver);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            return;
        }
        new OracleDriver();
        logger.info("The JDBC driver has been loaded successfully.");
    }

    public void initOracleNetTNSAdmin(String tnsAdminPropertyValue) {
        System.setProperty("oracle.net.tns_admin", tnsAdminPropertyValue);
        logger.info("oracle.net.tns_admin updated.");
    }

    public void initOracleNetWalletLocation(String walletLocationPropertyValue) {
        System.setProperty("oracle.net.wallet_location", walletLocationPropertyValue);
        logger.info("oracle.net.wallet_location updated.");
    }

    public void initConnection() throws SQLException {
        connection = ods.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    private void configureLog4J(String logPropertiesFile) throws Exception {
        if (logPropertiesFile == null) {
            System.err.println("Configuration parameter log4j-init-file not found.");
        }

        //DOMConfigurator is used to configure logger from xml configuration file
        DOMConfigurator.configure(StartupServlet.class.getResource(logPropertiesFile));
        logger = Logger.getLogger(StartupServlet.class.getName());
        logger.info("Logger is started.");
    }

    private void configureApp(String propertiesFile, ClassLoader classLoader) throws Exception {
        if (propertiesFile == null) {
            logger.error("Properties file is null: check the configuration parameter \"application-init-file\".");
            return;
        }

        Properties properties = new Properties();
        properties.load(classLoader.getResourceAsStream(propertiesFile));
        ConfigManager cm = ConfigManager.getInstance();
        cm.setProperties(properties);

        // Get the property value
        String jdbcDriverOraclePropertyValue = cm.get("jdbc_driver.oracle");
        initDriver(jdbcDriverOraclePropertyValue);

        String plainTextAccessCredentialsPropertyValue = cm.get("plain_text_access_credentials");
        boolean useWallet = plainTextAccessCredentialsPropertyValue.trim().substring(0, 1).equalsIgnoreCase("N");

        if (useWallet) {
            logger.info("The connection will be established by using the Oracle Wallet technology.");

            String oracleNettnsAdminPropertyValue = cm.get("oracle.net.tns_admin");
            initOracleNetTNSAdmin(oracleNettnsAdminPropertyValue);

            String oracleNetwalletLocationPropertyValue = cm.get("oracle.net.wallet_location");
            initOracleNetWalletLocation(oracleNetwalletLocationPropertyValue);
        }

        logger.info("The connection data has been updated successfully.");

        try {
            // Get DataSource
            logger.info("Initializing the datasource...");
            Context initContext = new InitialContext();
            String envContextPropertyValue = cm.get("env_context");
            Context envContext = (Context) initContext.lookup(envContextPropertyValue);
            String oracleConnectionPoolDatasourcePropertyValue = cm.get("oracle_connection_pool_datasource");
            ods = (OracleConnectionPoolDataSource) envContext.lookup(oracleConnectionPoolDatasourcePropertyValue);
            logger.info("The datasource has been initialized successfully.");

            logger.info("Initializing the SQL query to execute to test the database connection...");
            testConnectionSQLQuery = cm.get("test_connection_sql_query");
            logger.info("The SQL query to execute to test the database connection has been initialized successfully.");
        } catch (NamingException e) {
            logger.error(e.getMessage());
            destroy();
        }
    }

    public static StartupServlet getInstance() {
        return INSTANCE;
    }
}