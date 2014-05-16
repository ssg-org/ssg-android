package org.sredisvojgrad.ulica.entityDao;

import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.SQLiteDao;
import com.turbomanage.storm.TableHelper;

import org.sredisvojgrad.ulica.entity.user;

/**
 * Created by Home on 8.5.2014..
 */
public class userDao extends SQLiteDao<user> {

    public DatabaseHelper getDbHelper(Context ctx) {

        return org.sredisvojgrad.ulica.Database.DbFactory.getDatabaseHelper(ctx);
    }

    @SuppressWarnings("rowtypes")
    public TableHelper getTableHelper() {
        return new org.sredisvojgrad.ulica.entityDao.userTable();
    }

    public userDao(Context ctx) {
        super(ctx);
    }


}
