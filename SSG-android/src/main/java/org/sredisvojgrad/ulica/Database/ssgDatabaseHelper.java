package org.sredisvojgrad.ulica.Database;

/**
 * Created by Home on 7.5.2014..
 */
import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.api.Database;
import com.turbomanage.storm.api.DatabaseFactory;

@Database(name = "ssgDB", version = 1)
public class ssgDatabaseHelper extends DatabaseHelper{

    public ssgDatabaseHelper(Context ctx, DatabaseFactory dbFactory)
    {
        super(ctx,dbFactory);
    }
    public static final String DB_NAME = "ssgDB";
    public static final int DB_VERSION = 1;
    @Override
    public UpgradeStrategy getUpgradeStrategy()
    {
        return UpgradeStrategy.DROP_CREATE  ;
    }

}