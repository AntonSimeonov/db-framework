package ninja.paranoidandroid.test19112016.db.sql.update;

import ninja.paranoidandroid.test19112016.db.sql.SQLiteUpdate;
import ninja.paranoidandroid.test19112016.model.User;

/**
 * Created by anton on 20.11.16.
 */

public class UpdateUser extends SQLiteUpdate {

    private User mUser;
    private String mWhereClause;
    String[] mWhereArgs;

    public UpdateUser(User user, String where, String[] args){

        mUser = user;
        mWhereClause = where;
        mWhereArgs = args;

    }

    @Override
    public int update() {

        int numberUpdatedRows = mDBOperations.updateUser(mUser, mWhereClause, mWhereArgs);
        return numberUpdatedRows;
    }
}
