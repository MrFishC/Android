package cn.jack.module01_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.jack.module01_sqlite.sqlite.MySQLiteOpenHelper;

/**
 * copy from : https://www.jianshu.com/p/8e3f294e2828
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button instablish;
    private Button insert;
    private Button upgrade;
    private Button modify;
    private Button delete;
    private Button query;
    private Button delete_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定按钮
        instablish = (Button) findViewById(R.id.instablish);
        insert = (Button) findViewById(R.id.insert);
        upgrade = (Button) findViewById(R.id.upgrade);
        modify = (Button) findViewById(R.id.modify);
        delete = (Button) findViewById(R.id.delete);
        query = (Button) findViewById(R.id.query);
        delete_database = (Button) findViewById(R.id.delete_database);

        //设置监听器
        instablish.setOnClickListener(this);
        insert.setOnClickListener(this);
        upgrade.setOnClickListener(this);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
        query.setOnClickListener(this);
        delete_database.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            //点击创建数据库库
            case R.id.instablish:

                // 创建SQLiteOpenHelper子类对象
                MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this,"test_carson");
                //数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
                SQLiteDatabase  sqliteDatabase = dbHelper.getWritableDatabase();
                // SQLiteDatabase  sqliteDatabase = dbHelper.getReadbleDatabase();

                break;

            //点击更新数据
            case R.id.upgrade:

                // 创建SQLiteOpenHelper子类对象
                MySQLiteOpenHelper dbHelper_upgrade = new MySQLiteOpenHelper(this,"test_carson",2);
                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase  sqliteDatabase_upgrade = dbHelper_upgrade.getWritableDatabase();
                // SQLiteDatabase  sqliteDatabase = dbHelper.getReadbleDatabase();

                break;

            //点击插入数据到数据库
            case R.id.insert:

                System.out.println("插入数据");

                // 创建SQLiteOpenHelper子类对象
                MySQLiteOpenHelper dbHelper1 = new MySQLiteOpenHelper(this,"test_carson",2);
                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase  sqliteDatabase1 = dbHelper1.getWritableDatabase();

                // 创建ContentValues对象
                ContentValues values1 = new ContentValues();

                // 向该对象中插入键值对
                values1.put("id", 1);
                values1.put("name", "carson");

                // 调用insert()方法将数据插入到数据库当中
                sqliteDatabase1.insert("user", null, values1);

                //insert方法,返回值 long id,插入的主键id
                //参数1:表名
                //参数2:nullColumnHack,如果写null，就无法插入一条空数据（2.3会出异常，4.0之后可以写null）
                //字符串类型，指明如果某一字段没有值，那么会将该字段的值设为NULL
                //一般给该参数传递null就行,如果没有特殊要求
                //如果想插入空数据，第二个参数必须写一个列名（任意列）
                //参数3:键值对,键对应列名，值对应插入的值.这个列名是用来拼接sql语句的，如果contentValue为空，则后台不知道表的列名，无法构建sql语句


                // sqliteDatabase.execSQL("insert into user (id,name) values (1,'carson')");

                //关闭数据库
                sqliteDatabase1.close();

                break;

            //点击查询数据库
            case R.id.query:

                System.out.println("查询数据");

                // 创建DatabaseHelper对象
                MySQLiteOpenHelper dbHelper4 = new MySQLiteOpenHelper(MainActivity.this,"test_carson",2);

                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase sqliteDatabase4 = dbHelper4.getReadableDatabase();

                // 调用SQLiteDatabase对象的query方法进行查询
                // 返回一个Cursor对象：由数据库查询返回的结果集对象
                Cursor cursor = sqliteDatabase4.query("user", new String[] { "id",
                        "name" }, "id=?", new String[] { "1" }, null, null, null);

                /*
                 * 第一个参数 table，查询的表名
                 * 第二个参数 columns，要查询的字段
                 * 第三个参数selection 过滤字段
                 * 第四个参数selectionArgs 过滤字段的值
                 * 第五个参数groupBy 分组字段，null代表不分组
                 * 第六个参数having
                 * A filter declare which row groups to include in the cursor,
                 * if row grouping is being used, formatted as an SQL HAVING clause
                 * (excluding the HAVING itself). Passing null will cause all row groups
                 * to be included, and is required when row grouping is not being used.
                 * 第七个参数orderBy 排序字段，asc正序，desc倒序，null代表自然顺序
                 */

                /*
                 * Cursor是结果集游标，用于对结果集进行随机访问。Cursor与JDBC中的ResultSet作用很相似。
                 *                 Cursor中维护一个行索引一个列索引，游标中本身没有数据，它只是指向数据库的索引，模拟一个行、列的表结构。其起始位置是在-1的位置上的。
                 *                 常用方法：
                 *                 moveToNext():将游标从当前行移动到下一行，如果已经移过了结果集的最后一行，返回结果为false，否则为true。
                 *                 moveToPrevious():用于将游标从当前行移动到上一行，如果已经移过了结果集的第一行，返回值为false，否则为true。
                 *                 moveToFirst():用于将游标移动到结果集的第一行，如果结果集为空，返回值为false，否则为true。
                 *                 moveToLast():用于将游标移动到结果集的最后一行，如果结果集为空，返回值为false，否则为true。
                 */

                String id = null;
                String name = null;

                //将光标移动到下一行，从而判断该结果集是否还有下一条数据
                //如果有则返回true，没有则返回false
                while (cursor.moveToNext()) {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    name = cursor.getString(cursor.getColumnIndex("name"));
                    //输出查询结果
                    System.out.println("查询到的数据是:"+"id: "+id+"  "+"name: "+name);

                }
                //关闭数据库
                sqliteDatabase4.close();

                break;


            //点击修改数据
            case R.id.modify:
                System.out.println("修改数据");

                // 创建一个DatabaseHelper对象
                // 将数据库的版本升级为2
                // 传入版本号为2，大于旧版本（1），所以会调用onUpgrade()升级数据库
                MySQLiteOpenHelper dbHelper2 = new MySQLiteOpenHelper(MainActivity.this,"test_carson", 2);


                // 调用getWritableDatabase()得到一个可写的SQLiteDatabase对象
                SQLiteDatabase sqliteDatabase2 = dbHelper2.getWritableDatabase();

                // 创建一个ContentValues对象
                ContentValues values2 = new ContentValues();
                values2.put("name", "zhangsan");

                // 调用update方法修改数据库
                sqliteDatabase2.update("user", values2, "id=?", new String[]{"1"});
                //参数1:表名
                //参数2:设置要修改的字段的新值，没有涉及到的字段则默认不修改
                //参数3:whereArgs  ,如果有条件选项，对应的条件选项的具体参数，没有写null
                //关闭数据库
                sqliteDatabase2.close();
                break;

            //点击删除数据
            case R.id.delete:

                System.out.println("删除数据");

                // 创建DatabaseHelper对象
                MySQLiteOpenHelper dbHelper3 = new MySQLiteOpenHelper(MainActivity.this,"test_carson",2);

                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase sqliteDatabase3 = dbHelper3.getWritableDatabase();

                //删除数据
                sqliteDatabase3.delete("user", "id=?", new String[]{"1"});
                //参数1:表名
                //参数2:选择的条件选项，如果为null则删除表中的所有数据
                //参数3:whereArgs  ,如果有条件选项，对应的条件选项的具体参数，没有写null

                //关闭数据库
                sqliteDatabase3.close();
                break;




            //点击删除数据库
            case R.id.delete_database:

                System.out.println("删除数据库");

                MySQLiteOpenHelper dbHelper5 = new MySQLiteOpenHelper(MainActivity.this,
                        "test_carson",2);

                // 调用getReadableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase sqliteDatabase5 = dbHelper5.getReadableDatabase();

                //删除名为test.db数据库
                deleteDatabase("test_carson");
                break;

            default:
                break;

        }
    }
}
