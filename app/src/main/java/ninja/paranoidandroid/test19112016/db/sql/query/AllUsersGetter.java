package ninja.paranoidandroid.test19112016.db.sql.query;

import android.database.Cursor;

import ninja.paranoidandroid.test19112016.db.sql.SQLiteQuery;

/**
 * Created by anton on 20.11.16.
 */

public class AllUsersGetter extends SQLiteQuery {


    @Override
    public Cursor query() {

        Cursor cursor = mDBOperations.getAllUsersCursor();
        return cursor;
    }

}
