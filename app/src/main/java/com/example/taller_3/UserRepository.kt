package com.example.taller_3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class UserRepository(context: Context) {
    private val dbHelper = UserDatabaseHelper(context)

    fun insertUser(name: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
        }
        db.insert("users", null, values)
    }

    fun getAllUsers(): List<User> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("users", null, null, null, null, null, null)
        val users = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                users.add(User(id, name))
            }
        }
        cursor.close()
        return users
    }
}