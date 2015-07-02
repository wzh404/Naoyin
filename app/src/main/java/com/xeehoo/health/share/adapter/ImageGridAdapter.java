package com.xeehoo.health.share.adapter;

import java.util.List;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeehoo.health.R;
import com.xeehoo.health.share.activity.AlbumActivity;
import com.xeehoo.health.share.bean.Bimp;
import com.xeehoo.health.share.bean.BitmapCache;
import com.xeehoo.health.share.bean.BitmapCache.ImageCallback;
import com.xeehoo.health.share.bean.ImageItem;

public class ImageGridAdapter extends BaseAdapter
{
	private TextCallback textcallback = null;
	final String TAG = getClass().getSimpleName();
	AlbumActivity act;
	List<ImageItem> dataList;
//	Map<String, String> map = new HashMap<String, String>();
	BitmapCache cache;
	private Handler mHandler;
//	private int selectTotal = 0;
//	private int bucket = 0;
	
	ImageCallback callback = new ImageCallback()
	{
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap,
				Object... params)
		{
			if (imageView != null && bitmap != null)
			{
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag()))
				{
					((ImageView) imageView).setImageBitmap(bitmap);
				} else
				{
					Log.e(TAG, "callback, bmp not match");
				}
			} else
			{
				Log.e(TAG, "callback, bmp null");
			}
		}
	};

	public static interface TextCallback
	{
		public void onListen(int count);
	}

	public void setTextCallback(TextCallback listener)
	{
		textcallback = listener;
	}
	
	public ImageGridAdapter(AlbumActivity act, List<ImageItem> list, Handler mHandler)
	{
		this.act = act;
//		dataList = list;
		cache = new BitmapCache();
		this.mHandler = mHandler;
//		selectTotal = act.selectedTotal;
		
		setDataList(list);
	}
	
	public void setDataList(List<ImageItem> dataList){
		this.dataList = dataList;
//		selectTotal = Bimp.drr.size();
		if (textcallback != null)
			textcallback.onListen(act.selectedTotal);
		
		for (ImageItem item: dataList){
			boolean selected = false;
			for (String f : Bimp.drr){
				if (item.imagePath.equals(f)){
					selected = true;
					break;
				}
			}
			
			item.isSelected = selected;
		}
	}

	@Override
	public int getCount()
	{
		int count = 0;
		if (dataList != null)
		{
			count = dataList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	class Holder
	{
		private ImageView iv;
		private ImageView selected;
		private TextView text;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		final Holder holder;

		if (convertView == null)
		{
			holder = new Holder();
			convertView = View.inflate(act, R.layout.item_image_grid, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.image);
			holder.selected = (ImageView) convertView
					.findViewById(R.id.isselected);
			holder.text = (TextView) convertView
					.findViewById(R.id.item_image_grid_text);
			convertView.setTag(holder);
		} else
		{
			holder = (Holder) convertView.getTag();
		}
		final ImageItem item = dataList.get(position);

		holder.iv.setTag(item.imagePath);
		cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath,
				callback);
		if (item.isSelected)
		{
			holder.selected.setImageResource(R.drawable.icon_data_select);
			holder.text.setBackgroundResource(R.drawable.bgd_relatly_line);
		} else
		{
			holder.selected.setImageResource(-1);
			holder.text.setBackgroundColor(0x00000000);
		}
		holder.iv.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String path = dataList.get(position).imagePath;
				
				
				if (act.selectedTotal < 9)
				{
					item.isSelected = !item.isSelected;
					if (item.isSelected)
					{
						holder.selected
								.setImageResource(R.drawable.icon_data_select);
						holder.text
								.setBackgroundResource(R.drawable.bgd_relatly_line);
						act.selectedTotal++;
						if (textcallback != null)
							textcallback.onListen(act.selectedTotal);
						act.map.put(path, path);
						Log.e("PUT", "path is " + path);
					} else if (!item.isSelected)
					{
						holder.selected.setImageResource(-1);
						holder.text.setBackgroundColor(0x00000000);
						act.selectedTotal--;
						if (textcallback != null)
							textcallback.onListen(act.selectedTotal);
						Log.e("Remove", "path is " + path);
						act.map.remove(path);
					}
				} 
				else if (act.selectedTotal >= 9)
				{
					if (item.isSelected == true)
					{
						item.isSelected = !item.isSelected;
						holder.selected.setImageResource(-1);
						act.selectedTotal--;
						act.map.remove(path);
					} else
					{
						Message message = Message.obtain(mHandler, 0);
						message.sendToTarget();
					}
				}
			}

		});

		return convertView;
	}
}
