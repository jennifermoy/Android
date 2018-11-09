package edu.chapman.jennifer.filmdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ATTEMPT 2

public class DatabaseManager {

    private Context ctx = null;
    private SQLiteDatabaseHelper dbHelper = null;
    private SQLiteDatabase database = null;

    private String dbName = "";
    private int dbVersion = 0;

    private List<String> tableNameList = null;
    private List<String> createTableSqlList = null;

    public DatabaseManager(Context ctx, String dbName, int dbVersion, List<String> tableNameList, List<String> createTableSqlList) {
        this.ctx = ctx;
        this.dbName = dbName;
        this.dbVersion = dbVersion;
        this.tableNameList = tableNameList;
        this.createTableSqlList = createTableSqlList;
    }

    //open database connection
    public DatabaseManager openDB() {
        dbHelper = new SQLiteDatabaseHelper(ctx, this.dbName, null, this.dbVersion);
        dbHelper.setTableNameList(this.tableNameList);
        dbHelper.setCreateTableSqlList(this.createTableSqlList);

        this.database = dbHelper.getWritableDatabase();
        return this;
    }

    //close database connection
    public void closeDB() {
        this.database.close();
        this.dbHelper.close();
        this.database = null;
        this.dbHelper = null;
    }

    public void insert(String tableName, List<TableColumn> columnList) {
        if(!TextUtils.isEmpty(tableName) && columnList!=null)
        {
            int size = columnList.size();

            if(size > 0)
            {
                // Create a content values object.
                ContentValues contentValues = new ContentValues();

                // Loop in the table column list
                for(int i=0;i<size;i++)
                {
                    TableColumn tableColumn = columnList.get(i);

                    // Put column name and value in content values.
                    if(!TextUtils.isEmpty(tableColumn.getColumnName())) {
                        contentValues.put(tableColumn.getColumnName(), tableColumn.getColumnValue());
                    }
                }

                // Insert data.
                this.database.insert(tableName, null, contentValues);
            }
        }
    }

    public int update(String tableName,  List<TableColumn> columnList, String whereClause)
    {
        int ret = 0;
        if(!TextUtils.isEmpty(tableName) && columnList!=null)
        {
            int size = columnList.size();

            if(size > 0)
            {
                // Create a content values object.
                ContentValues contentValues = new ContentValues();

                // Loop in the table column list
                for(int i=0;i<size;i++)
                {
                    TableColumn tableColumn = columnList.get(i);

                    // Put column name and value in content values.
                    if(!TextUtils.isEmpty(tableColumn.getColumnName())) {
                        contentValues.put(tableColumn.getColumnName(), tableColumn.getColumnValue());
                    }
                }

                // Update data.Return update row count.
                ret = this.database.update(tableName, contentValues, whereClause, null);
            }
        }
        return ret;
    }

    public void delete(String tableName, String whereClause)
    {
        if(!TextUtils.isEmpty(tableName)) {
            this.database.delete(tableName, whereClause, null);
        }
    }

    public List<Map<String, String>> queryAllReturnListMap(String tableName)
    {
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();

        // Query all rows in table.
        Cursor cursor = this.database.query(tableName, null, null, null, null, null, null);
        if(cursor!=null)
        {
            // Get all column name in an array.
            String columnNamesArr[] = cursor.getColumnNames();

            // Move to first cursor.
            cursor.moveToFirst();
            do {
                // Create a map object represent a table row data.
                Map<String, String> rowMap = new HashMap<String, String>();

                // Get column count.
                int columnCount = columnNamesArr.length;
                for(int i=0;i<columnCount;i++)
                {
                    // Get each column name.
                    String columnName = columnNamesArr[i];

                    // This is the column value.
                    String columnValue = "";

                    // Get column index value.
                    int columnIndex = cursor.getColumnIndex(columnName);

                    // Get current column data type.
                    int columnType = cursor.getType(columnIndex);

                    if(Cursor.FIELD_TYPE_STRING == columnType)
                    {
                        columnValue = cursor.getString(columnIndex);
                    }else if(Cursor.FIELD_TYPE_INTEGER == columnType)
                    {
                        columnValue = String.valueOf(cursor.getInt(columnIndex));
                    }else if(Cursor.FIELD_TYPE_FLOAT == columnType)
                    {
                        columnValue = String.valueOf(cursor.getFloat(columnIndex));
                    }else if(Cursor.FIELD_TYPE_BLOB == columnType)
                    {
                        columnValue = String.valueOf(cursor.getBlob(columnIndex));
                    }else if(Cursor.FIELD_TYPE_NULL == columnType)
                    {
                        columnValue = "null";
                    }

                    rowMap.put(columnName, columnValue);

                    ret.add(rowMap);
                }
            }while(cursor.moveToNext());

            cursor.close();
        }
        return ret;
    }

    /*
     *  Query all rows in sqlite database table.
     *  tableName : The table name.
     *  Return a Cursor object.
     * */
    public Cursor queryAllReturnCursor(String tableName)
    {
        // Query all rows in table.
        Cursor cursor = this.database.query(tableName, null, null, null, null, null, null);
        if(cursor!=null)
        {
            // Move to first cursor.
            cursor.moveToFirst();
        }
        return cursor;
    }
}
