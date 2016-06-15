package com.abhi.model;


import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Abhishek on 10-09-2015.
 */
public class ImageModel implements Parcelable{
	
	public String album_id;
	public String id;
	public String title;
	public String url;
	public String thumburl;

	
	
		
	public ImageModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ImageModel(Parcel in){
		album_id = in.readString();
		id = in.readString();
		title = in.readString();
		url = in.readString();
		thumburl = in.readString();


		
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {

          dest.writeString(album_id);
          dest.writeString(id);
          dest.writeString(title);
          dest.writeString(url);
          dest.writeString(thumburl);

		
	}
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };
    

}
