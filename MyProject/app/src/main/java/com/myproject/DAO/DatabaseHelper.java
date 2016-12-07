package com.myproject.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.myproject.Bean.Vocabulary;

import java.sql.SQLException;

/**
 * Created by D1ngZenL1ng on 2016/11/25.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME="translate.db";
    private Dao<Vocabulary,Integer> vocabularyDao;
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Vocabulary.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,Vocabulary.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;
//    单例获取该helper，防止出现多个对象同时访问，导致数据不一致
    public static synchronized DatabaseHelper getHelper(Context context)
    {
        if (instance==null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance==null)
                {
                    instance=new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

//    获得Dao
    public Dao<Vocabulary,Integer> getVocabularyDao() throws SQLException {
        if (vocabularyDao==null)
        {
            vocabularyDao=getDao(Vocabulary.class);
        }
        return  vocabularyDao;
    }
    //释放资源

    @Override
    public void close() {
        super.close();
        vocabularyDao=null;
    }
}
