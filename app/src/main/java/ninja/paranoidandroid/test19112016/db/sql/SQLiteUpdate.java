package ninja.paranoidandroid.test19112016.db.sql;

import android.database.sqlite.SQLiteDatabase;
import ninja.paranoidandroid.test19112016.db.DBOperations;

/**
 * Created by anton on 20.11.16.
 */

public abstract class SQLiteUpdate {

    protected DBOperations mDBOperations;
    protected String sqliteOperationDescription;

    public abstract int update();

    public void createDBOperation(SQLiteDatabase sqLiteDatabase){
        mDBOperations = new DBOperations(sqLiteDatabase);
    }

}
