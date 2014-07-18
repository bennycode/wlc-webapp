package com.welovecoding.tutorial.data.control.database;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class DatabaseControlService {

  @Resource(name = "java:app/jdbc/welovecoding")
  private DataSource database;

  /**
   * Dumps the content of the whole database as a String. This method is only
   * optimized for the relational database MySQL.
   *
   * @return dump string
   */
  public String dumpDatabase() {
    try {
      IDatabaseConnection connection = new DatabaseConnection(database.getConnection());
      connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

      // full database export
      IDataSet fullDataSet = connection.createDataSet();

      StringBuilder sql = new StringBuilder();

      for (String tableName : fullDataSet.getTableNames()) {

        // ignore STATISTIC and SEQUENCE table
        if (tableName.equalsIgnoreCase("STATISTIC") || tableName.equalsIgnoreCase("SEQUENCE")) {
          continue;
        }

        ITable table = fullDataSet.getTable(tableName);
        sql.append("INSERT INTO `").append(tableName).append("` (");
        for (Column column : table.getTableMetaData().getColumns()) {
          sql.append("`").append(column.getColumnName()).append("`,");
        }
        if (sql.charAt(sql.length() - 1) == ',') {
          sql.deleteCharAt(sql.length() - 1);
        }
        sql.append(") VALUES \n");

        for (int i = 0; i < table.getRowCount(); i++) {
          sql.append("(");
          for (Column column : table.getTableMetaData().getColumns()) {
            sql.append(getStringBySqlType(table.getValue(i, column.getColumnName()), column.getSqlTypeName(), column.getDataType())).append(",");
          }
          if (sql.charAt(sql.length() - 1) == ',') {
            sql.deleteCharAt(sql.length() - 1);
          }
          sql.append("),\n");
        }
        if (sql.charAt(sql.length() - 2) == ',') {
          sql.deleteCharAt(sql.length() - 1);
          sql.deleteCharAt(sql.length() - 1);
        }
        sql.append(";\n");
      }

      return sql.toString();
    } catch (DatabaseUnitException | SQLException ex) {
      Logger.getLogger(DatabaseControlService.class.getName()).log(Level.SEVERE, null, ex);
      throw new IllegalStateException();
    }
  }

  private String getStringBySqlType(Object value, String sqlType, DataType dataType) {

    if (value == null) {
      return "null";
    }
    /**
     * TODO possible values CHARACTER, VARCHAR, CHARACTER VARYING, BINARY,
     * BOOLEAN, VARBINARY BINARY, VARYING INTEGER, SMALLINT, BIGINT, DECIMAL,
     * NUMERIC, FLOAT, REAL, FLOAT, DOUBLE PRECISION, DATE, TIME, TIMESTAMP,
     * INTERVAL, ARRAY, MULTISET, XML
     */
    switch (sqlType) {
      case "BIGINT":
      case "INT":
      case "BIT":
      case "DECIMAL":
        return value.toString();
      case "VARCHAR":
      case "DATETIME":
        return "'" + value.toString().replaceAll("'", "''") + "'";
      default:
        System.out.println("SQLType: " + sqlType + " DataType: " + dataType + " Value: " + value);
        throw new IllegalStateException("Unknown Datatype!");
    }
  }
}
