package ninja.paranoidandroid.test19112016.db.sql.query;

import android.database.Cursor;

import ninja.paranoidandroid.test19112016.db.sql.SQLiteQuery;

/**
 * Created by anton on 21.11.16.
 */

public class AllJobsGetter extends SQLiteQuery {

    public AllJobsGetter() {
    }

    @Override
    public Cursor query() {
        Cursor cursor = mDBOperations.getAllJobs();
        return cursor;
    }
}
