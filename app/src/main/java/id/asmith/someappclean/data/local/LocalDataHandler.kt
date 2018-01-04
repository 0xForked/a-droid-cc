package id.asmith.someappclean.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import id.asmith.someappclean.data.model.UserModel
import id.asmith.someappclean.utils.AppConstants.DB_NAME
import id.asmith.someappclean.utils.AppConstants.DB_TABLE
import id.asmith.someappclean.utils.AppConstants.DB_VERSION
import id.asmith.someappclean.utils.AppConstants.KEY_CREATED
import id.asmith.someappclean.utils.AppConstants.KEY_ID
import id.asmith.someappclean.utils.AppConstants.KEY_UPDATED
import id.asmith.someappclean.utils.AppConstants.KEY_USER_EMAIL
import id.asmith.someappclean.utils.AppConstants.KEY_USER_ID
import id.asmith.someappclean.utils.AppConstants.KEY_USER_NAME
import id.asmith.someappclean.utils.AppConstants.KEY_USER_PHONE
import id.asmith.someappclean.utils.AppConstants.KEY_USER_TOKEN


/**
 * Created by Agus Adhi Sumitro on 23/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class LocalDataHandler (context: Context?) : SQLiteOpenHelper(context, DB_NAME,
        null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable: String = "Create table $DB_TABLE " +
                "("+
                    "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_USER_ID TEXT, " +
                    "$KEY_USER_NAME TEXT," +
                    "$KEY_USER_EMAIL TEXT," +
                    "$KEY_USER_PHONE TEXT, " +
                    "$KEY_USER_TOKEN TEXT," +
                    "$KEY_CREATED TEXT," +
                    "$KEY_UPDATED TEXT " +
                ")"

        // Here you create tables
        db.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop old
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE)
        // Create new
        onCreate(db)

    }

    fun getUserData(): HashMap<String, String> {
        val userData = HashMap<String, String>()
        val db: SQLiteDatabase = this.readableDatabase

        val  cursor: Cursor = db.rawQuery("SELECT * FROM $DB_TABLE",
                null )
        cursor.moveToFirst()
        if (cursor.moveToFirst()) {
            userData.put("uid", cursor.getString(1))
            userData.put("name", cursor.getString(2))
            userData.put("email", cursor.getString(3))
            userData.put("phone", cursor.getString(4))
            userData.put("token", cursor.getString(5))
            userData.put("logged", cursor.getString(6))
        }
        cursor.close()
        db.close()

        return userData

    }

    fun addUserData(user: UserModel) {
        deleteTableUser()
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USER_ID, user.uid)
        values.put(KEY_USER_NAME, user.name)
        values.put(KEY_USER_EMAIL, user.email)
        values.put(KEY_USER_PHONE, user.phone)
        values.put(KEY_USER_TOKEN, user.token)
        values.put(KEY_CREATED, user.created)
        values.put(KEY_UPDATED, user.updated)
        db.insert(DB_TABLE, null, values)
        db.close()

    }

    fun deleteTableUser() {
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(DB_TABLE, null, null)
        db.close()

    }

}
