package com.example.med;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class   DBHelper extends SQLiteOpenHelper {

    private Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "main.db";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_CALLS = "calls";
    public static final String TABLE_CHATS = "chats";
    public static final String TABLE_MESSAGES = "messages";
    public static final String COLUMN_PASSPORT_ID = "passport_id";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_IS_DOCTOR = "is_doctor";
    public static final String COLUMN_UNIX_EPOCH_CREATE = "unix_epoch_create";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CHAT_ID = "chat_id";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_SYMTOMPS = "symtomps";
    public static final String COLUMN_DIAGNOSIS = "diagnosis";
    public static final String COLUMN_TREATMENT = "treatment";
    public static final String COLUMN_APPOINTMENT_TIME = "appointment_time";
    public static final String COLUMN_UNIX_EPOCH_END = "unix_epoch_end";
    public static final String COLUMN_ACTUAL = "actual";
    public static final String COLUMN_ID_CALL = "id_call";
    public static final String COLUMN_PATIENT_ID = "patient_id";
    public static final String COLUMN_ADMIN_ID = "admin_id";
    public static final String COLUMN_UNIX_CREATE = "unix_create";
    public static final String COLUMN_UNIX_END = "unix_end";
    public static final String COLUMN_CHAT_PROGRESS = "chat_progress";
    public static final String COLUMN_MESSAGE_ID = "message_id";
    public static final String COLUMN_UNIX_CREATE_MESSAGE = "unix_create_message";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                COLUMN_PASSPORT_ID + " INTEGER," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_FULL_NAME + " TEXT," +
                COLUMN_AREA + " INTEGER," +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_IS_DOCTOR + " INTEGER" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSPORT_ID, 1111);
        values.put(COLUMN_ADDRESS, "Рогачевская 5-22");
        values.put(COLUMN_FULL_NAME, "Одинец Прохор Игоревич");
        values.put(COLUMN_AREA, 13);
        values.put(COLUMN_IS_DOCTOR, 1);
        values.put(COLUMN_ID, 0);
        db.insert(TABLE_USERS, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_PASSPORT_ID, 3333);
        values2.put(COLUMN_ADDRESS, "Сергея Есенина 58-35");
        values2.put(COLUMN_FULL_NAME, "Дашкевич Анна Николаевна");
        values2.put(COLUMN_AREA, 13);
        values2.put(COLUMN_IS_DOCTOR, 0);
        values2.put(COLUMN_ID, 1);
        db.insert(TABLE_USERS, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(COLUMN_PASSPORT_ID, 9999);
        values3.put(COLUMN_ADDRESS, "Рогачевская 5-34");
        values3.put(COLUMN_FULL_NAME, "Справка");
        values3.put(COLUMN_AREA, 13);
        values3.put(COLUMN_IS_DOCTOR, 2);
        values3.put(COLUMN_ID, 2);
        db.insert(TABLE_USERS, null, values3);

        ContentValues values4 = new ContentValues();
        values4.put(COLUMN_PASSPORT_ID, 4444);
        values4.put(COLUMN_ADDRESS, "Чекушкино 3-23");
        values4.put(COLUMN_FULL_NAME, "Леус Жанна Владимировна");
        values4.put(COLUMN_AREA, 13);
        values4.put(COLUMN_IS_DOCTOR, 0);
        values4.put(COLUMN_ID, 3);
        db.insert(TABLE_USERS, null, values4);

        ContentValues values5 = new ContentValues();
        values5.put(COLUMN_PASSPORT_ID, 5555);
        values5.put(COLUMN_ADDRESS, "Почтовая 3-22");
        values5.put(COLUMN_FULL_NAME, "Кулецкая Юлия Николаевна");
        values5.put(COLUMN_AREA, 14);
        values5.put(COLUMN_IS_DOCTOR, 1);
        values5.put(COLUMN_ID, 4);
        db.insert(TABLE_USERS, null, values5);

        String CREATE_CALLS_TABLE = "CREATE TABLE " + TABLE_CALLS +
                "(" +
                COLUMN_UNIX_EPOCH_CREATE + " INTEGER," +
                COLUMN_ID + " INTEGER," +
                COLUMN_PHONE_NUMBER + " INTEGER," +
                COLUMN_SYMTOMPS + " TEXT," +
                COLUMN_DIAGNOSIS + " TEXT," +
                COLUMN_TREATMENT + " TEXT," +
                COLUMN_APPOINTMENT_TIME + " INTEGER," +
                COLUMN_UNIX_EPOCH_END + " INTEGER," +
                COLUMN_AREA + " INTEGER," +
                COLUMN_ACTUAL + " INTEGER," +
                COLUMN_ID_CALL + " INTEGER" +
                ")";
        db.execSQL(CREATE_CALLS_TABLE);

        ContentValues callValues = new ContentValues();
        callValues.put(COLUMN_UNIX_EPOCH_CREATE, "1711443701");
        callValues.put(COLUMN_ID, "1");
        callValues.put(COLUMN_PHONE_NUMBER, "8033978350");
        callValues.put(COLUMN_SYMTOMPS, "Рука болит");
        callValues.put(COLUMN_DIAGNOSIS, "Перелом");
        callValues.put(COLUMN_TREATMENT, "Лежать");
        callValues.put(COLUMN_APPOINTMENT_TIME, "");
        callValues.put(COLUMN_UNIX_EPOCH_END, "");
        callValues.put(COLUMN_AREA, "13");
        callValues.put(COLUMN_ACTUAL, "1");
        callValues.put(COLUMN_ID_CALL, "1");
        db.insert(TABLE_CALLS, null, callValues);

        ContentValues callValues2 = new ContentValues();
        callValues2.put(COLUMN_UNIX_EPOCH_CREATE, "1711443768");
        callValues2.put(COLUMN_ID, "1");
        callValues2.put(COLUMN_PHONE_NUMBER, "80296347348");
        callValues2.put(COLUMN_SYMTOMPS, "Нога болит");
        callValues2.put(COLUMN_DIAGNOSIS, "Подскользнулся");
        callValues2.put(COLUMN_TREATMENT, "Постельный режим");
        callValues2.put(COLUMN_APPOINTMENT_TIME, "");
        callValues2.put(COLUMN_UNIX_EPOCH_END, "");
        callValues2.put(COLUMN_AREA, "14");
        callValues2.put(COLUMN_ACTUAL, "0");
        callValues2.put(COLUMN_ID_CALL, "2");
        db.insert(TABLE_CALLS, null, callValues2);

        ContentValues callValues3 = new ContentValues();
        callValues3.put(COLUMN_UNIX_EPOCH_CREATE, "1711443952");
        callValues3.put(COLUMN_ID, "3");
        callValues3.put(COLUMN_PHONE_NUMBER, "8012940112");
        callValues3.put(COLUMN_SYMTOMPS, "Горло болит");
        callValues3.put(COLUMN_DIAGNOSIS, "ОРВИ");
        callValues3.put(COLUMN_TREATMENT, "Пить");
        callValues3.put(COLUMN_APPOINTMENT_TIME, "");
        callValues3.put(COLUMN_UNIX_EPOCH_END, "");
        callValues3.put(COLUMN_AREA, "13");
        callValues3.put(COLUMN_ACTUAL, "1");
        callValues3.put(COLUMN_ID_CALL, "3");
        db.insert(TABLE_CALLS, null, callValues3);

        ContentValues callValues4 = new ContentValues();
        callValues4.put(COLUMN_UNIX_EPOCH_CREATE, "1711443993");
        callValues4.put(COLUMN_ID, "3");
        callValues4.put(COLUMN_PHONE_NUMBER, "8029403320");
        callValues4.put(COLUMN_SYMTOMPS, "Печень");
        callValues4.put(COLUMN_DIAGNOSIS, "Лихорадка");
        callValues4.put(COLUMN_TREATMENT, "Таблетки от поноса");
        callValues4.put(COLUMN_APPOINTMENT_TIME, "");
        callValues4.put(COLUMN_UNIX_EPOCH_END, "");
        callValues4.put(COLUMN_AREA, "14");
        callValues4.put(COLUMN_ACTUAL, "1");
        callValues4.put(COLUMN_ID_CALL, "4");
        db.insert(TABLE_CALLS, null, callValues4);

        String CREATE_CHATS_TABLE = "CREATE TABLE " + TABLE_CHATS +
                "(" +
                COLUMN_CHAT_ID + " INTEGER," +
                COLUMN_PATIENT_ID + " INTEGER," +
                COLUMN_ADMIN_ID + " INTEGER," +
                COLUMN_UNIX_CREATE + " INTEGER," +
                COLUMN_UNIX_END + " INTEGER," +
                COLUMN_CHAT_PROGRESS + " TEXT" +
                ")";
//                CHAT_PROGRESS (COMPLETE, IN PROGRESS, NOT STARTED)
        db.execSQL(CREATE_CHATS_TABLE);


        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES +
                "(" +
                COLUMN_MESSAGE_ID + " INTEGER," +
                COLUMN_CHAT_ID + " INTEGER," +
                COLUMN_UNIX_CREATE_MESSAGE + " INTEGER," +
                COLUMN_PATIENT_ID + " INTEGER," +
                COLUMN_MESSAGE + " TEXT" +
                ")";
        db.execSQL(CREATE_MESSAGES_TABLE);
    }

    public Cursor getUserByFullName(String fullName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_FULL_NAME + " = ?";
        String[] selectionArgs = {fullName};
        return db.rawQuery(query, selectionArgs);
    }

    public Cursor getCalls() {
        SQLiteDatabase db = this.getReadableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String area = sharedPreferences.getString("area", "");

        String selection = "actual = ? AND area = ?";
        String[] selectionArgs = {"1", area};

        return db.query(
                "calls",
                new String[]{"unix_epoch_create", "phone_number", "symtomps", "id", "id_call"},
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }

    public Cursor getCallById(String id_call) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "id_call = ?";
        String[] selectionArgs = {id_call};
        return db.query("calls", null, selection, selectionArgs, null, null, null);
    }

    public boolean updateCall(String id, String symtomps, String diagnosis, String treatment, String appointment_time, long unixEpochEndTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("symtomps", symtomps);
        contentValues.put("diagnosis", diagnosis);
        contentValues.put("treatment", treatment);
        contentValues.put("appointment_time", appointment_time);
        contentValues.put("unix_epoch_end", unixEpochEndTime);
        contentValues.put("actual", 0);

        int result = db.update("calls", contentValues, "id_call = ?", new String[]{id});
        return result > 0;
    }

    public Cursor getUser(String passportId, String fullName) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DBHelper.COLUMN_PASSPORT_ID,
                DBHelper.COLUMN_FULL_NAME,
                DBHelper.COLUMN_IS_DOCTOR
        };
        String selection = DBHelper.COLUMN_PASSPORT_ID + " = ? AND " + DBHelper.COLUMN_FULL_NAME + " = ?";
        String[] selectionArgs = {passportId, fullName};
        return db.query(
                DBHelper.TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }

    public Cursor getUsersByIds(List<String> ids) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_FULL_NAME, DBHelper.COLUMN_ADDRESS};
        String selection = DBHelper.COLUMN_ID + " IN (" + TextUtils.join(",", Collections.nCopies(ids.size(), "?")) + ")";
        String[] selectionArgs = ids.toArray(new String[0]);
        Cursor cursor = db.query(DBHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALLS);
        onCreate(db);
    }

    public void insertChat(int chatId, int patientId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("chat_id", chatId);
        values.put("patient_id", patientId);
        values.put("unix_create", System.currentTimeMillis() / 1000);
        values.put("chat_progress", "NOT STARTED");
        db.insert("chats", null, values);
        db.close();
    }

    public void insertMessage(int chatId, String message, int patientId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("chat_id", chatId);
        values.put("message_id", new Random().nextInt(100000));
        values.put("unix_create_message", System.currentTimeMillis() / 1000);
        values.put("patient_id", patientId);
        values.put("message", message);
        db.insert("messages", null, values);
        db.close();
    }

    public int getLastChatId(int patientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT chat_id FROM chats WHERE patient_id = ? ORDER BY unix_create DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(patientId)});
        int chatId = -1;
        if (cursor.moveToFirst()) {
            chatId = cursor.getInt(cursor.getColumnIndexOrThrow("chat_id"));
        }
        cursor.close();
        db.close();
        return chatId;
    }

    public boolean isChatComplete(int chatId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT chat_progress FROM chats WHERE chat_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(chatId)});
        boolean isComplete = false;
        if (cursor.moveToFirst()) {
            String chatProgress = cursor.getString(cursor.getColumnIndexOrThrow("chat_progress"));
            isComplete = "COMPLETE".equals(chatProgress);
        }
        cursor.close();
        db.close();
        return isComplete;
    }

    public List<String> getMessages(int patientId) {
        List<String> messages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor chatCursor = db.query("messages",
                new String[]{"chat_id"},
                "patient_id = ?",
                new String[]{String.valueOf(patientId)},
                null, null, null);

        if (chatCursor.moveToFirst()) {
            int chatId = chatCursor.getInt(chatCursor.getColumnIndexOrThrow("chat_id"));

            Cursor messageCursor = db.query("messages",
                    new String[]{"message"},
                    "chat_id = ?",
                    new String[]{String.valueOf(chatId)},
                    null, null,
                    "unix_create_message DESC");

            while (messageCursor.moveToNext()) {
                String message = messageCursor.getString(messageCursor.getColumnIndexOrThrow("message"));
                messages.add(message);
            }

            messageCursor.close();
            chatCursor.close();
        }

        return messages;
    }

    public Cursor getCallsUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");

        return db.query(
                "calls",
                new String[]{"unix_epoch_create", "id", "phone_number", "symtomps", "diagnosis", "treatment", "actual", "id_call"},
                "id = ?",
                new String[]{userId},
                null,
                null,
                null
        );
    }

    public boolean insertCall(long unixEpochCreate, int id, String phoneNumber, String symptoms, String area, int actual, int idCall) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("unix_epoch_create", unixEpochCreate);
        contentValues.put("id", id);
        contentValues.put("phone_number", phoneNumber);
        contentValues.put("symtomps", symptoms);
        contentValues.put("area", area);
        contentValues.put("actual", actual);
        contentValues.put("id_call", idCall);
        long result = db.insert("calls", null, contentValues);
        return result != -1; // Return true if insertion was successful, false otherwise
    }

    public Cursor searchDoctor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM calls WHERE id = ?", new String[]{String.valueOf(id)});
    }
}
