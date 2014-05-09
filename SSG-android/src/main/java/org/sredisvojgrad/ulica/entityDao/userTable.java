package org.sredisvojgrad.ulica.entityDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;

import com.turbomanage.storm.TableHelper;
import com.turbomanage.storm.query.FilterBuilder;
import com.turbomanage.storm.types.LongConverter;
import com.turbomanage.storm.types.StringConverter;

import org.sredisvojgrad.ulica.entity.user;

/**
 * Created by Home on 8.5.2014..
 */
 public class userTable extends TableHelper<user> {

    public enum Columns implements Column {
        _ID,
       NAME,
       LASTNAME,
       EMAIL,
       PASSWORD,
       USERCITY
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
  public Column[] getColumns() {
        return Columns.values();
    }

    @Override
    public long getId(user user) {
        return 0;
    }

    public void setId(user obj, long id){
           //do nothing
       }


    @Override
    public Column getIdCol() {
        return Columns._ID;
    }

    @Override
    public String createSql()
    {
        return  "CREATE TABLE IF NOT EXISTS user("+"_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"NAME VARCHAR(20),"+"LASTNAME VARCHAR(20),"+"EMAIL VARCHAR(20),"+"PASSWORD VARCHAR(20),"+"USERCITY VARCHAR(20)"+")";

    }
    @Override
    public String dropSql()
    {
        return "DROP TABLE IF EXISTS user";
    }

    @Override
    public String upgradeSql(int oldVersion,int newVersion)
    {
        return null;
    }
    @Override
    public String[] getRowValues(Cursor c)
    {
        String[] values=new String[c.getColumnCount()];
        values[0]= LongConverter.GET.toString(getLongOrNull(c,0));
        values[1]= StringConverter.GET.toString(getStringOrNull(c,1));
        values[2]= StringConverter.GET.toString(getStringOrNull(c,2));
        values[3]= StringConverter.GET.toString(getStringOrNull(c,3));
        values[4]= StringConverter.GET.toString(getStringOrNull(c,4));
        values[5]= StringConverter.GET.toString(getStringOrNull(c,5));
        return values;
    }
    @Override
    public void bindRowValues(DatabaseUtils.InsertHelper insHelper,String[] rowValues)
    {
        if(rowValues[0]==null) insHelper.bindNull(0); else insHelper.bind(0, LongConverter.GET.fromString(rowValues[0]));
        if(rowValues[1]==null) insHelper.bindNull(1); else insHelper.bind(1,StringConverter.GET.fromString(rowValues[1]));
        if(rowValues[2]==null) insHelper.bindNull(2); else insHelper.bind(2,StringConverter.GET.fromString(rowValues[2]));
        if(rowValues[3]==null) insHelper.bindNull(3); else insHelper.bind(3,StringConverter.GET.fromString(rowValues[3]));
        if(rowValues[4]==null) insHelper.bindNull(4); else insHelper.bind(4,StringConverter.GET.fromString(rowValues[4]));
        if(rowValues[5]==null) insHelper.bindNull(5); else insHelper.bind(5,StringConverter.GET.fromString(rowValues[5]));
    }
    @Override
    public  String[] getDefaultValues()
    {
        String[] values=new String[getColumns().length];
        user defaultObj=new user();
        values[0]=LongConverter.GET.toString(LongConverter.GET.toSql(defaultObj.getUserId()));
        values[1]=StringConverter.GET.toString(StringConverter.GET.toSql(defaultObj.getName()));
        values[2]=StringConverter.GET.toString(StringConverter.GET.toSql(defaultObj.getLastname()));
        values[3]=StringConverter.GET.toString(StringConverter.GET.toSql(defaultObj.getEmail()));
        values[4]=StringConverter.GET.toString(StringConverter.GET.toSql(defaultObj.getPassword()));
        values[5]=StringConverter.GET.toString(StringConverter.GET.toSql(defaultObj.getUserCity()));
        return  values;
    }
    @Override
    public user newInstance(Cursor c)
    {
        user obj=new user();
        obj.setUserId(LongConverter.GET.fromSql(getLongOrNull(c,0)));
        obj.setName(StringConverter.GET.fromSql(getStringOrNull(c,1)));
        obj.setLastname(StringConverter.GET.fromSql(getStringOrNull(c, 2)));
        obj.setEmail(StringConverter.GET.fromSql(getStringOrNull(c, 3)));
        obj.setPassword(StringConverter.GET.fromSql(getStringOrNull(c, 4)));
        obj.setUserCity(StringConverter.GET.fromSql(getStringOrNull(c, 5)));
        return obj;
    }
    @Override
    public ContentValues getEditableValues(user obj)
    {
        ContentValues cv=new ContentValues();
       // cv.put("userID",LongConverter.GET.toSql(obj.getUserId()));
        cv.put("NAME",StringConverter.GET.toSql(obj.getName()));
        cv.put("LASTNAME",StringConverter.GET.toSql(obj.getLastname()));
        cv.put("EMAIL",StringConverter.GET.toSql(obj.getEmail()));
        cv.put("PASSWORD",StringConverter.GET.toSql(obj.getPassword()));
        cv.put("USERCITY",StringConverter.GET.toSql(obj.getUserCity()));
        return cv;
    }
    @Override
    public FilterBuilder buildFilter(FilterBuilder filter,user obj)
    {
    user defaultObj= new user();
      if(obj.getUserId()!=defaultObj.getUserId())
        filter = filter.eq(Columns._ID,LongConverter.GET.toSql(obj.getUserId()));
        return filter;
    }

}
