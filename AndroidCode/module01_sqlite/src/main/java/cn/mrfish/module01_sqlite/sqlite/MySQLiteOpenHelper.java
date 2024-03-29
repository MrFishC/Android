package cn.mrfish.module01_sqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Jack
 * @time 19-9-20 上午11:47
 * @describe
 *
 * copy from : https://www.jianshu.com/p/8e3f294e2828
 *
 * https://github.com/Carson-Ho/DataBase_Demo/blob/master/app/src/main/java/scut/carson_ho/database_demo/MySQLiteOpenHelper.java
 *
 * 创建数据库子类，继承自SQLiteOpenHelper类,需 复写 onCreat（）、onUpgrade（）
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // 数据库版本号
    private static Integer Version = 1;

    /**
     * 构造函数
     * 在SQLiteOpenHelper的子类中，必须有该构造函数
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
        // 参数说明
        // context：上下文对象
        // name：数据库名称
        // param：一个可选的游标工厂（通常是 Null）                                游标结果工厂,如果需要使用,则需要自定义结果集工厂,null值表示使用默认的结果工厂集
        // version：当前数据库的版本，值必须是整数并且是递增的状态

        // 必须通过super调用父类的构造函数
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context,String name,int version) {
        this(context,name,null,version);
    }

    public MySQLiteOpenHelper(Context context,String name) {
        this(context, name, Version);
    }


    /**
     * 复写onCreate（）
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     * SQLite数据库创建支持的数据类型： 整型数据、字符串类型、日期类型、二进制
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // 创建数据库1张表
        // 通过execSQL（）执行SQL语句（此处创建了1个名为person的表）
        String sql = "create table person(id integer primary key autoincrement,name varchar(64),address varchar(64))";
        db.execSQL(sql);

        // 注：数据库实际上是没被创建 / 打开的（因该方法还没调用）
        // 直到getWritableDatabase() / getReadableDatabase() 第一次被调用时才会进行创建 / 打开

    }

    /**
     * 复写onUpgrade（）
     * 调用时刻：当数据库升级时则自动调用（即 数据库版本 发生变化时）
     * 作用：更新数据库表结构
     * 注：创建SQLiteOpenHelper子类对象时,必须传入一个version参数，该参数 = 当前数据库版本, 若该版本高于之前版本, 就调用onUpgrade()
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 参数说明：
        // db ： 数据库
        // oldVersion ： 旧版本数据库
        // newVersion ： 新版本数据库

        // 使用 SQL的ALTER语句
        String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);

    }

}
