package com.abhi.uiscreens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.abhi.R;
import com.abhi.utils.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
/**
 * Created by Abhishek on 10-09-2015.
 */
public class ImageDetailActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener;
	private ImageView mImageView;
    private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_details);

        url = getIntent().getExtras().getString("url");
        System.out.println("url = " + url);

        initImageLoader();
		initview();




	}

	private void initImageLoader() {

		options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
				.showImageForEmptyUri(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.displayer(new RoundedBitmapDisplayer(0)).build();
		animateFirstListener = new AnimateFirstDisplayListener();

		if (!ImageLoader.getInstance().isInited()) {
			ImageLoader.getInstance().init(
					ImageLoaderConfiguration
							.createDefault(ImageDetailActivity.this));
		}

	}



	private void initview() {

		mImageView = (ImageView) findViewById(R.id.image_id);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
        setActionBarTitle("Details");
		initOpenCloseWithIcon();

        ImageLoader.getInstance().displayImage(url,
                mImageView, options,animateFirstListener);

	}

	public void setActionBarTitle(String title) {
		toolbar.setTitle(title);
		toolbar.setTitleTextColor(getResources().getColor(R.color.white));
		getSupportActionBar().setTitle(title);
	}

	private void initOpenCloseWithIcon() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		

	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
