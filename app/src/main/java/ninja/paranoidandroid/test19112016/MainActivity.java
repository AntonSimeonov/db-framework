package ninja.paranoidandroid.test19112016;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import ninja.paranoidandroid.test19112016.db.DBContract;
import ninja.paranoidandroid.test19112016.db.DBHelper;
import ninja.paranoidandroid.test19112016.db.fragments.SQLiteAllQueries;
import ninja.paranoidandroid.test19112016.db.sql.insert.InsertUser;
import ninja.paranoidandroid.test19112016.db.sql.query.AllUsersGetter;
import ninja.paranoidandroid.test19112016.db.sql.SQLiteInsert;
import ninja.paranoidandroid.test19112016.db.sql.SQLiteQuery;

public class MainActivity extends Activity implements SQLiteAllQueries.CallBack{

    //SQLite fragment TAG
    public final static String SQLITE_FRAGMENT_TAG = "sqlite_fragment_tag";

    //Log
    public final static String TAG = "MainActivity";

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragmentManager();
        setSQLiteFragment();
        //createNewUser();
        getAllUsers();

        //new AsyncDBInit().execute();

    }

    //
    private void setFragmentManager(){
        mFragmentManager = getFragmentManager();
    }

    //
    private void setSQLiteFragment(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(new SQLiteAllQueries(), SQLITE_FRAGMENT_TAG);
        ft.commit();
        //Why this worked, have to see this method later!!!
        mFragmentManager.executePendingTransactions();
    }

    private SQLiteAllQueries getSQLiteAllQueries(){

        SQLiteAllQueries sqLiteAllQueries = (SQLiteAllQueries) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT_TAG);

        return sqLiteAllQueries;
    }

    private void executeQuery(SQLiteQuery sqLiteQuery){
        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.executeQuery(sqLiteQuery);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }
    }

    private void insertNewUser(SQLiteInsert sqLiteInsert){
        SQLiteAllQueries sqLiteAllQueries = getSQLiteAllQueries();

        if(sqLiteAllQueries != null) {
            sqLiteAllQueries.insertNewUser(sqLiteInsert);
            Log.i(TAG, "YES WE GOT THE FRAGMENT.");
        }else{
            Log.i(TAG, "NO, STILL NO FARGMENT.");
        }
    }

    //Remove executing sqlite fragment from activity.
    private void removeSQLiteFragment(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        SQLiteAllQueries sqLiteFragment = (SQLiteAllQueries) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT_TAG);
        ft.remove(sqLiteFragment);
        ft.commit();

    }

    @Override
    public void onCursorReturned(Cursor cursor) {

        logAllusers(cursor);

    }

    @Override
    public void onIsertIndexReturned(Long newInsertindex) {


    }

    @Override
    public void onNumberUpdatedReturned(Integer numberUpdated) {

    }

    @Override
    public void onNumberDeletedReturned(Integer numberDeleted) {

    }

    private void createNewUser(){

        SQLiteInsert newUser = new InsertUser("anton", "pass", "abvmail", "my adddres", 44);
        insertNewUser(newUser);
    }

    private void getAllUsers(){
        SQLiteQuery sqLiteQuery = new AllUsersGetter();
        executeQuery(sqLiteQuery);
    }

    private void logAllusers(Cursor cursor){
        String name = null;
        String password = null;
        String mail = null;
        String address = null;
        int jobId = 0;

        if(cursor != null){
            if(cursor.moveToFirst()){

                do{

                    name = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_NAME));
                    password = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_PASSWORD));
                    mail = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_MAIL));
                    address = cursor.getString(cursor.getColumnIndex(DBContract.User.COLUMN_USER_ADDRESS));
                    jobId = cursor.getInt(cursor.getColumnIndex(DBContract.User.COLUMN_USER_JOB_ID));
                    Log.i(TAG, "User properties are: " + name + " " + password + " " + mail + " ect.");
                }while(cursor.moveToNext());

            }
        }
    }


    private class AsyncDBInit extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {


            DBHelper dbHelper = new DBHelper(MainActivity.this);
            dbHelper.getWritableDatabase();
            dbHelper.close();


            return null;
        }
    }
}
