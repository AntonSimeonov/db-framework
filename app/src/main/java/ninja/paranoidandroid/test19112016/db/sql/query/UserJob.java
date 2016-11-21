package ninja.paranoidandroid.test19112016.db.sql.query;

import android.database.Cursor;

import ninja.paranoidandroid.test19112016.db.sql.SQLiteQuery;

/**
 * Created by anton on 21.11.16.
 */

public class UserJob extends SQLiteQuery {

    private String[] mWhereArgs;

    public UserJob(String[] whereArgs) {
        mWhereArgs = whereArgs;
    }

    @Override
    public Cursor query() {
        Cursor cursor = mDBOperations.getUserJob(mWhereArgs);
        return cursor;
    }
}
