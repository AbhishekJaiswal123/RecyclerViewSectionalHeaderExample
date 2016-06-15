package com.abhi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.R;
import com.abhi.model.ImageModel;
import com.abhi.uiscreens.ImageDetailActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Abhishek on 10-09-2015.
 */


    public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

        private final Context mContext;
        private ArrayList<ImageModel> mData;
          private DisplayImageOptions options;

        public void add(ImageModel s,int position) {
            position = position == -1 ? getItemCount()  : position;
            mData.add(position,s);
            notifyItemInserted(position);
        }

        public void remove(int position){
            if (position < getItemCount()  ) {
                mData.remove(position);
                notifyItemRemoved(position);
            }
        }

        public static class SimpleViewHolder extends RecyclerView.ViewHolder {
            public final TextView title;
            public final ImageView thumbnail;
            public final CardView root;

            public SimpleViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                root = (CardView) view.findViewById(R.id.card_view);
            }
        }

        public SimpleAdapter(Context context, ArrayList<ImageModel> data) {
            mContext = context;
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .showImageForEmptyUri(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true)
                    .cacheOnDisk(true).considerExifParams(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .displayer(new RoundedBitmapDisplayer(0)).build();

            if (data != null)
                mData = data;
            else
                mData = new ArrayList<ImageModel>();
        }

        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.listrow_image, parent, false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, final int position) {

            holder.title.setText(mData.get(position).title);
            if (!ImageLoader.getInstance().isInited()) {
                ImageLoader.getInstance().init(
                        ImageLoaderConfiguration.createDefault(mContext));
            }
            ImageLoader.getInstance().displayImage(
                    mData.get(position).thumburl,
                    holder.thumbnail, options);
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ImageDetailActivity.class);
                    intent.putExtra("url",mData.get(position).url);
                    mContext.startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }



