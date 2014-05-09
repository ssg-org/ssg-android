package org.sredisvojgrad.ulica.Database;

import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.TableHelper;
import com.turbomanage.storm.api.DatabaseFactory;

import org.sredisvojgrad.ulica.entityDao.userTable;

/**
 * Created by Home on 8.5.2014..
 */
public class DbFactory implements DatabaseFactory {
    private static final String DB_NAME= "ssgDB";
    private static  final int DB_VERSION=1;
    private static final TableHelper[] TABLE_HELPERS= new TableHelper[]
            {
                   new userTable()
            };
    private static DatabaseHelper mIstance;
    public static DatabaseHelper getDatabaseHelper(Context ctx)
    {
        if(mIstance==null)
            newInstance(ctx);
        return mIstance;
    }
    private static void newInstance(Context ctx)
    {
        mIstance=new ssgDatabaseHelper(ctx, new DbFactory());
    }
    public  String getName() {
        return DB_NAME;
    }

    public  int getVersion() {
        return DB_VERSION;
    }

    public TableHelper[] getTableHelpers() {
        return TABLE_HELPERS;
    }



}
