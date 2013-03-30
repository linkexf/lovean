package com.xuan.lovean.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.xuan.lovean.utils.Validators;

/**
 * DBHelper����һ�����г��������������Զ��������������ݿ�
 * 
 * @author xuan
 */
public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = "lovean.DBHelper";

	// ���ڳ�ʼ�����������ݿ���ļ�����ÿ��һ��db.version��+1
	private static final String DB_INIT_OR_UPGRADE_FILENAME = "db_${db.version}.sql";

	// ���ݿ�汾�ţ������ʼ��ʱ�����ʼ����ֵ
	private static int DATABASE_VERSION = -1;

	// ���ݿ����������ʼ��ʱ�����ʼ����ֵ
	public static String DATABASE_NAME = "lovean";

	private final Context context;

	/**
	 * �������ݿ�汾�ţ������ʼ��(��Ӧ��������ʱ��Ϳ��Գ�ʼ����)
	 * 
	 * @param DATABASE_VERSION
	 *            the DATABASE_VERSION to set
	 */
	public static void init(String DATABASE_NAME, int DATABASE_VERSION) {
		DBHelper.DATABASE_VERSION = DATABASE_VERSION;
		DBHelper.DATABASE_NAME = DATABASE_NAME;
	}

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	/**
	 * ���ݿ��һ�α�����ʱ�����ã����ڹ��캯���з��������ڵ���getWritableDatabase��getReadableDatabaseʱ������ʱ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (DATABASE_VERSION <= 0) {
			throw new RuntimeException(
					"DBHelper.DATABASE_VERSION�����ʼ���������init����");
		}

		if (Validators.isEmpty(DATABASE_NAME)) {
			throw new RuntimeException("DBHelper.DATABASE_NAME����Ϊ��");
		}

		Log.i(TAG, "initing dababase");

		// read and execute assets/db_1.sql
		executeSqlFromFile(db,
				DB_INIT_OR_UPGRADE_FILENAME.replace("${db.version}", "1"));

		// �������onCreateʱ�汾�ű�1��������(�����ڶ���������û���һ�ΰ�װ�������û�ж�غ����°�װ)
		if (DATABASE_VERSION > 1) {
			onUpgrade(db, 1, DATABASE_VERSION);
		}
	}

	/**
	 * ���ݿ�İ汾�ű���Ҫ����ʱ������
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (DATABASE_VERSION <= 0) {
			throw new RuntimeException(
					"DBHelper.DATABASE_VERSION�����ʼ���������init����");
		}

		if (newVersion <= oldVersion) {
			return;
		}

		Log.i(TAG, "updating dababase from version " + oldVersion
				+ "to version " + newVersion);
		for (int i = oldVersion + 1; i <= newVersion; i++) {
			executeSqlFromFile(
					db,
					DB_INIT_OR_UPGRADE_FILENAME.replace("${db.version}",
							String.valueOf(i)));
		}
	}

	/**
	 * ���ļ���ȡsql��ִ��
	 * 
	 * @param db
	 * @param fileName
	 */
	private void executeSqlFromFile(SQLiteDatabase db, String fileName) {
		Log.i(TAG, "begin to execute sql in assets/" + fileName);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					context.getAssets().open(fileName)));

			String line = null;
			StringBuilder sql = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("--")) {// ע����
					continue;
				}
				if (line.trim().equalsIgnoreCase("go")) {// ��ʾһ��sql�Ľ���
					if (!Validators.isEmpty(sql.toString())) {
						// ִ��sql
						db.execSQL(sql.toString());
					}
					sql = new StringBuilder();
					continue;
				}
				sql.append(line);
			}
			if (!Validators.isEmpty(sql.toString())) {
				// ִ��sql
				db.execSQL(sql.toString());
			}

		} catch (Exception e) {
			Log.e(TAG, "", e);
			throw new RuntimeException(e);
		}

		Log.i(TAG, "succeed to execute sql in assets/" + fileName);
	}

}
