package com.abhi.uiscreens;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.abhi.R;
import com.abhi.adapter.SectionHeaderAdapter;
import com.abhi.adapter.SimpleAdapter;
import com.abhi.database.ImageData;
import com.abhi.model.ImageModel;
import com.abhi.model.SectionHeaderModel;
import com.abhi.parser.ImageParser;
import com.abhi.utils.ServerRequest;
import com.abhi.utils.UrlConstant;
import com.abhi.utils.UtilClass;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 10-09-2015.
 */
public class ImageListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProgressDialog mProgressDialog;
    private ArrayList<ImageModel> mImageList;
    private ArrayList<Integer> SectionPostion;
    private ImageData mImageData;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mImageData = new ImageData(ImageListActivity.this);
        initview();
    }

    private void initview() {

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        mRecyclerView = (RecyclerView) findViewById(R.id.image_list_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(ImageListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        setSupportActionBar(toolbar);
        setActionBarTitle("GalleryApp");

        if (UtilClass.isNetworkAvailable(ImageListActivity.this)) {

            if (mImageData.isdataAvailable()) {

                initRecyclerView();


            } else {

                new FetchAllSong().execute();
            }


        } else {
            displayAlert();


        }


    }

    public void setActionBarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle(title);
    }

    private void initRecyclerView() {


        mImageList = mImageData.getImageList();
        SectionPostion = mImageData.getAlbumIdPostion();

        //Your RecyclerView.Adapter
        mAdapter = new SimpleAdapter(this, mImageList);


        //This is the code to provide a sectioned list
        List<SectionHeaderModel> sections =
                new ArrayList<SectionHeaderModel>();

        //Sections
        for (int i = 0; i < SectionPostion.size(); i++) {

            int j = i + 1;

            sections.add(new SectionHeaderModel(SectionPostion.get(i), "Album Id: " + j));

        }


        //Add your adapter to the sectionAdapter
        SectionHeaderModel[] dummy = new SectionHeaderModel[sections.size()];
        SectionHeaderAdapter mSectionedAdapter = new
                SectionHeaderAdapter(this, R.layout.listrow_section, R.id.section_text, mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);


    }

    public class FetchAllSong extends
            AsyncTask<String, Void, ArrayList<ImageModel>> {

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(ImageListActivity.this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.show();

        }

        @Override
        protected ArrayList<ImageModel> doInBackground(String... params) {

            ServerRequest lServerRequest = new ServerRequest();
            ImageParser lImageParser = new ImageParser(ImageListActivity.this);

            try {
             String response = lServerRequest
                        .getServerResponse(UrlConstant.IMAGELIST_URL);
          //  String response = lServerRequest.loadJSONFromAsset(ImageListActivity.this,"images.txt");

                System.out.println("lResponse = " + response);
                lImageParser.doParse(response);

                mImageList = mImageData.getImageList();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return mImageList;
        }

        @Override
        protected void onPostExecute(ArrayList<ImageModel> result) {

            if (mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }

            initRecyclerView();


        }

    }


    public void displayAlert() {
        new AlertDialog.Builder(this)
                .setMessage(
                        "Please Check Your Internet Connection and Try Again")
                .setTitle("Network Error")
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                finish();
                            }
                        }).show();
    }

}
