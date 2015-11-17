package com.xeehoo.health.share.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xeehoo.health.R;
import com.xeehoo.health.share.bean.ShareContent;
import com.xeehoo.health.util.ResourceUtils;


import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShareListAdapter extends BaseAdapter {
	private Context context;
    private List<ShareContent> items;
    
    public ShareListAdapter(Context context, List<ShareContent> items){
        this.context = context;
        this.items = items;
    }
    
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.listitem_share, null);
			holder = new ViewHolder();
//			holder.root = (RelativeLayout) convertView
//					.findViewById(R.id.feed_item_layout_root);
			holder.avatar = (ImageView) convertView
					.findViewById(R.id.feed_item_iv_avatar);
			holder.time = (TextView) convertView
					.findViewById(R.id.feed_item_htv_time);
			holder.name = (TextView) convertView
					.findViewById(R.id.feed_item_htv_name);
			holder.content = (TextView) convertView
					.findViewById(R.id.feed_item_etv_content);
			holder.contentImage = (GridView) convertView
					.findViewById(R.id.share_image_grid_view);
			holder.image = (ImageView) convertView.findViewById(R.id.share_image_view);

//			holder.more = (ImageView) convertView
//					.findViewById(R.id.feed_item_ib_more);
//			holder.comment = (LinearLayout) convertView
//					.findViewById(R.id.feed_item_layout_comment);
			holder.commentCount = (TextView) convertView
					.findViewById(R.id.feed_item_htv_comment_count);
			holder.site = (TextView) convertView
					.findViewById(R.id.feed_item_htv_site);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ShareContent content = (ShareContent) getItem(position);
		
		holder.avatar.setImageResource(R.drawable.user_icon);
		holder.name.setText(content.getName());
		holder.time.setText(content.getTime());
		holder.content.setText(content.getContent());

		if (content.getImages() == null) {
			holder.contentImage.setVisibility(View.GONE);
			holder.image.setVisibility(View.GONE);
		} else {
			if (content.getImages().length == 1){
				holder.contentImage.setVisibility(View.GONE);
				holder.image.setVisibility(View.VISIBLE);

                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                        .build();
                holder.image.setScaleType(ImageView.ScaleType.FIT_XY);

                ImageLoader.getInstance().displayImage(content.getImages()[0], holder.image, options,
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri,
                                  View view, Bitmap loadedImage) {
                                int h = loadedImage.getHeight();
                                int w = loadedImage.getWidth();

                                WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
                                DisplayMetrics dm = new DisplayMetrics();
                                wm.getDefaultDisplay().getMetrics(dm);
                                int mScreenWidth = dm.widthPixels;
                                int mScreenHeight = dm.heightPixels;

                                if (w > mScreenWidth * 2 / 3 || h > mScreenHeight * 2 / 3){
                                    if (h > w && (h * 1.0 / w > 1.5)) {
                                        h = h / 3;
                                        w = w / 3;
                                    }else  {
                                        w = w * 2 / 3;
                                        h = h * 2 / 3;
                                    }
                                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
                                    params.height = h;
                                    params.width = w;

                                    view.setLayoutParams(params);
                                }
                            }
                        });
			}
			else if (content.getImages().length == 4){
				holder.contentImage.setVisibility(View.VISIBLE);
				holder.image.setVisibility(View.GONE);

				holder.contentImage.setNumColumns(2);
				ViewGroup.LayoutParams layoutParams =
						holder.contentImage.getLayoutParams();
				layoutParams.width = ResourceUtils.dip2px(context, 120) * 2;
				holder.contentImage.setLayoutParams(layoutParams);
				holder.contentImage.setAdapter(new ShareImageGridAdapter(context, content.getImages()));
			}
			else{
                Log.e("grid image", "------[" + content.getShareID() + "]-[" + content.getImages().length + "]");
				holder.contentImage.setVisibility(View.VISIBLE);
				holder.image.setVisibility(View.GONE);
				holder.contentImage.setNumColumns(3);
				holder.contentImage.setAdapter(new ShareImageGridAdapter(context, content.getImages()));
			}

		}
		holder.site.setText(content.getLikes() + "");
		holder.commentCount.setText(content.getComments() + "");
		
		return convertView;
	}

	class ViewHolder {
//		RelativeLayout root;
		ImageView avatar;
		TextView time;
		TextView name;
		TextView content;
		GridView contentImage;
		ImageView image;
//		LinearLayout comment;
		TextView commentCount;
		TextView site;
	}
}
