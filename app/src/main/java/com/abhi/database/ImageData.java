package com.abhi.database;

import java.util.ArrayList;
import com.abhi.model.ImageModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Abhishek on 10-09-2015.
 */
public class ImageData {

	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mSqLiteDatabase;
	private Context mContext;

	public ImageData(Context pContext) {
		mContext = pContext;
		mDatabaseHelper = DatabaseHelper.getDBinstance(mContext);
		mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
	}

	public ContentValues createImageData(ImageModel pImageModel) {

		ContentValues lContentValues = new ContentValues();

		lContentValues.put(DatabaseHelper.COLUMN_ALBUM_ID,
				pImageModel.album_id);
		lContentValues.put(DatabaseHelper.COLUMN_ID,
				pImageModel.id);
		lContentValues.put(DatabaseHelper.COLUMN_TITLE,
				pImageModel.title);
		lContentValues.put(DatabaseHelper.COLUMN_URL,
				pImageModel.url);
		lContentValues.put(DatabaseHelper.COLUMN_THUMBURL,
				pImageModel.thumburl);


		return lContentValues;
	}

	public void insertImageModel(ImageModel pImageModel) {

		if (mSqLiteDatabase != null) {
			mSqLiteDatabase.insert(DatabaseHelper.TABLE_IMAGE, null,
                    createImageData(pImageModel));
		}
	}



	public boolean isdataAvailable() {

		String sql = "SELECT * FROM " + DatabaseHelper.TABLE_IMAGE;

		Cursor lCursor = mSqLiteDatabase.rawQuery(sql, null);
		if (lCursor != null) {
			if (lCursor.getCount() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}



	public ArrayList<ImageModel> getImageList() {
		String sql = "SELECT * FROM " + DatabaseHelper.TABLE_IMAGE + ";";
		Cursor lCursor = mSqLiteDatabase.rawQuery(sql, null);
		lCursor.moveToFirst();

		ArrayList<ImageModel> mImageData = new ArrayList<ImageModel>();
		while (!lCursor.isAfterLast()) {

			ImageModel lImageModel = new ImageModel();

            lImageModel.album_id = lCursor.getString(lCursor
					.getColumnIndex(DatabaseHelper.COLUMN_ALBUM_ID));
            lImageModel.id = lCursor.getString(lCursor
                    .getColumnIndex(DatabaseHelper.COLUMN_ID));
            lImageModel.title = lCursor.getString(lCursor
                    .getColumnIndex(DatabaseHelper.COLUMN_TITLE));
            lImageModel.url = lCursor.getString(lCursor
                    .getColumnIndex(DatabaseHelper.COLUMN_URL));
            lImageModel.thumburl = lCursor.getString(lCursor
                    .getColumnIndex(DatabaseHelper.COLUMN_THUMBURL));

            mImageData.add(lImageModel);
			lCursor.moveToNext();

		}
		return mImageData;

	}

		public ArrayList<Integer> getAlbumIdPostion() {
		String sql = "SELECT * FROM " + DatabaseHelper.TABLE_IMAGE + ";";
		Cursor lCursor = mSqLiteDatabase.rawQuery(sql, null);
		lCursor.moveToFirst();
         int i = 0;
			int albumid = 0;
		ArrayList<Integer> positions = new ArrayList<Integer>();
		while (!lCursor.isAfterLast()) {
			i++;
			int lAlbumId = Integer.parseInt(lCursor.getString(lCursor
					.getColumnIndex(DatabaseHelper.COLUMN_ALBUM_ID)));
			if(albumid != lAlbumId){
				albumid = lAlbumId;
				positions.add(i-1);
			}
			lCursor.moveToNext();

		}
		return positions;

	}


}
