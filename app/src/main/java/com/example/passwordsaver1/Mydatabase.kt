package com.example.passwordsaver1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class Mydatabase(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object
    {
        private const val DATABASE_NAME="mydata.db"
        private const val DATABASE_VERSION=1
    }
    private val TABLE_NAME="user"
    private val COLUMN_ID="id"
    private val COLUMN_EMAIL="email"
    private val COLUMN_PASSWORD="password"

    private val CUT="""CREATE TABLE $TABLE_NAME($COLUMN_ID integer primary key autoincrement,$COLUMN_EMAIL text,$COLUMN_PASSWORD text)"""

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(CUT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertUser(email:String,password:String)
    {
        val db=writableDatabase
        val values=ContentValues().apply{
            put(COLUMN_EMAIL,email)
            put(COLUMN_PASSWORD,password)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun getAllUsers():List<User>
    {
        val userList= mutableListOf<User>()
        val db=readableDatabase
        val cursor:Cursor=db.query(TABLE_NAME,null,null,null,null,null,null)
        while (cursor.moveToNext())
        {
            val id=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val email=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            val password=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            val user=User(id,email,password)
            userList.add(user)
        }
        cursor.close()
        db.close()
        return userList
    }
}