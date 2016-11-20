package ninja.paranoidandroid.test19112016.db.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ninja.paranoidandroid.test19112016.db.DBOperations;
/**
 * Created by anton on 13.05.16.
 */
public abstract class SQLiteQuery {

    protected DBOperations mDBOperations;
    protected String sqliteOperationDescription;

    public abstract Cursor query();

    public void createDBOperation(SQLiteDatabase sqLiteDatabase){

        mDBOperations = new DBOperations(sqLiteDatabase);

    }
}
