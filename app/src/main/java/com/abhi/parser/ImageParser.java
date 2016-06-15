package com.abhi.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.abhi.database.ImageData;

import com.abhi.model.ImageModel;

/**
 * Created by Abhishek on 10-09-2015.
 */
public class ImageParser {

	private Context mContext;

	public static final String JSON_TAG_ALBUM_ID = "albumId";
	public static final String JSON_TAG_ID = "id";
	public static final String JSON_TAG_TITLE = "title";
	public static final String JSON_TAG_URL = "url";
	public static final String JSON_TAG_THUMBURL = "thumbnailUrl";
	public ArrayList<ImageModel> mImageList;
	private ImageData mImageData;

	public ImageParser(Context pContext) {
		mContext = pContext;
		mImageData = new ImageData(mContext);
	}

	public void doParse(String pJsonString) throws JSONException {

		mImageList = new ArrayList<ImageModel>();

		if (pJsonString.trim() != null) {

			JSONArray lJsonArray = new JSONArray(pJsonString);

			for (int i = 0; i < lJsonArray.length(); i++) {

				JSONObject lChildObj = lJsonArray.getJSONObject(i);
				ImageModel lModel = new ImageModel();
				
				lModel.album_id = lChildObj
						.optString(JSON_TAG_ALBUM_ID);
                lModel.id = lChildObj
                        .optString(JSON_TAG_ID);
                lModel.title = lChildObj
                        .optString(JSON_TAG_TITLE);
                lModel.url = lChildObj
                        .optString(JSON_TAG_URL);
                lModel.thumburl = lChildObj
                        .optString(JSON_TAG_THUMBURL);


				mImageList.add(lModel);
			}

		}
		insertImageDataIntoTable(mImageList);

	}

	private void insertImageDataIntoTable(ArrayList<ImageModel> pImageModelList) {

		for (int i = 0; i < pImageModelList.size(); i++) {

			ImageModel lImageModel = pImageModelList.get(i);
            mImageData.insertImageModel(lImageModel);

			

		}
	}

}
