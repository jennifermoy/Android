package edu.chapman.jennifer.filmdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//ATTEMPT 2

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private List<String> tableNameList = null;
    private List<String> createTableSqlList = null;
    public static final String LOG_TAG_SQLITE_DB = "LOG_TAG_SQLITE_DB";
    private Context ctx = null;

    //constructor with all input parameter
    public SQLiteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if(createTableSqlList != null){
            int size = createTableSqlList.size();
            for(int i = 0; i < size; i++) {
                String createTableSql = createTableSqlList.get(i);
                sqLiteDatabase.execSQL(createTableSql);

                Toast.makeText(ctx, "Run sql successfully, " + createTableSql, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(tableNameList != null) {
            int size = tableNameList.size();
            for(int i = 0; i< size; i++) {
                String tableName = tableNameList.get(i);

                if(!TextUtils.isEmpty(tableName)) {
                    sqLiteDatabase.execSQL("drop table if exists " + tableName);
                }
            }
        }

        onCreate(sqLiteDatabase);
    }

    public List<String> getTableNameList() {
        if(tableNameList == null) {
            tableNameList = new ArrayList<String>();
        }

        return tableNameList;
    }

    public void setTableNameList(List<String> tableNameList) {
        this.tableNameList= tableNameList;
    }

    public List<String> getCreateTableSqlList() {
        if(createTableSqlList == null) {
            createTableSqlList = new ArrayList<String>();
        }

        return createTableSqlList;
    }

    public void setCreateTableSqlList(List<String> createTableSqlList) {
        this.createTableSqlList = createTableSqlList;
    }
}
