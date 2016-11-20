package ninja.paranoidandroid.test19112016.db.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ninja.paranoidandroid.test19112016.MainActivity;
import ninja.paranoidandroid.test19112016.db.DBHelper;
import ninja.paranoidandroid.test19112016.db.sql.SQLiteDelete;
import ninja.paranoidandroid.test19112016.db.sql.SQLiteInsert;
import ninja.paranoidandroid.test19112016.db.sql.SQLiteQuery;
import ninja.paranoidandroid.test19112016.db.sql.SQLiteUpdate;

/**
 * Created by anton on 19.11.16.
 */

public class SQLiteAllQueries extends Fragment {

    public final static String LOG_TAG = "sqlite All queries";

    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private AsyncQueryExecutor mAsyncQueryExecutor;
    private AsyncInsertExecutor mAsyncInsertExecutor;

    private Activity mContainerActivity;
    private CallBack mCallBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        createDBHelper();
        setSQLiteDatabase();
        Log.i(LOG_TAG, " in onCreate method()");
    }

    //Makes fragment headless with null returned value.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //Attaches fragment instance to hosting activity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (CallBack) activity;
        mContainerActivity = (MainActivity) activity;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        mContainerActivity = (MainActivity) context;
//        mCallBack = (CallBack) mContainerActivity;
//    }

    public void executeQuery(SQLiteQuery sqLiteQuery){

        mAsyncQueryExecutor = new AsyncQueryExecutor();
//      mAsyncQueryExecutor.setSQLitedatabase(mSQLiteDatabase);
        mAsyncQueryExecutor.execute(sqLiteQuery);

    }

    //Get called form activity, and gets a instance of SQLiteInsertAbstract class
    public void insertNewUser(SQLiteInsert sqLiteInsert){
        mAsyncInsertExecutor = new AsyncInsertExecutor();
        mAsyncInsertExecutor.execute(sqLiteInsert);
    }

    //Sets SQliteOpenHelper for the fragment instance
    public void createDBHelper(){
        mDBHelper = new DBHelper(mContainerActivity);
    }

    //Sets SQLiteDataBase for the fragment instance
    public void setSQLiteDatabase(){
        mSQLiteDatabase = mDBHelper.getWritableDatabase();
    }

    //Interface for returning result in activity
    public interface CallBack{

        void onCursorReturned(Cursor cursor);
        void onIsertIndexReturned(Long newInsertindex);
        void onNumberUpdatedReturned(Integer numberUpdated);
        void onNumberDeletedReturned(Integer numberDeleted);

    }

    //Runs query interface in Async class
    public class AsyncQueryExecutor extends AsyncTask<SQLiteQuery, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Cursor doInBackground(SQLiteQuery... queries) {

            SQLiteQuery sqLiteQuery = queries[0];
            sqLiteQuery.createDBOperation(mSQLiteDatabase);
            Cursor cursor = sqLiteQuery.query();

            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            //Activity callback interface goes here.
            mCallBack.onCursorReturned(cursor);

        }
    }

    //Runs insert interface in async class
    public class AsyncInsertExecutor extends AsyncTask<SQLiteInsert, Void, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(SQLiteInsert... queries) {

            SQLiteInsert sqLiteInsert = queries[0];
            sqLiteInsert.createDBOperation(mSQLiteDatabase);
            Long insertIndex = sqLiteInsert.insert();

            Log.i(LOG_TAG, "new user may be inserted!!!");

            return insertIndex;
        }

        @Override
        protected void onPostExecute(Long newInsertIndex) {
            super.onPostExecute(newInsertIndex);
            //Activity callback interface goes here.
            mCallBack.onIsertIndexReturned(newInsertIndex);
        }
    }

    //Runs delete interface in async class.
    public class AsyncDeleteExecutor extends AsyncTask<SQLiteDelete, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(SQLiteDelete... queries) {

            SQLiteDelete sqLiteDelete = queries[0];
            sqLiteDelete.createDBOperation(mSQLiteDatabase);
            Integer deleteNumberReturned = sqLiteDelete.delete();


            Log.i(LOG_TAG, "new user may be deleted!!!");

            return deleteNumberReturned;
        }

        @Override
        protected void onPostExecute(Integer deleteNumberReturned) {
            super.onPostExecute(deleteNumberReturned);
            //Activity callback interface goes here.
            mCallBack.onNumberDeletedReturned(deleteNumberReturned);
        }
    }


    //Runs delete interface in async class.
    public class AsyncUpdateExecutor extends AsyncTask<SQLiteUpdate, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(SQLiteUpdate... queries) {

            SQLiteUpdate sqLiteUpdate = queries[0];
            sqLiteUpdate.createDBOperation(mSQLiteDatabase);
            Integer updateNumberReturned = sqLiteUpdate.update();

            Log.i(LOG_TAG, "new user may be updatetd!!!");

            return updateNumberReturned;
        }

        @Override
        protected void onPostExecute(Integer updateNumberReturned) {
            super.onPostExecute(updateNumberReturned);
            //Activity callback interface goes here.
            mCallBack.onNumberUpdatedReturned(updateNumberReturned);
        }
    }
}
