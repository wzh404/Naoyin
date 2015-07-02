package com.xeehoo.health.share.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xeehoo.health.R;
import com.xeehoo.health.share.adapter.ImageGridAdapter;
import com.xeehoo.health.share.adapter.ImageGridAdapter.TextCallback;
import com.xeehoo.health.share.bean.Bimp;
import com.xeehoo.health.share.bean.ImageBucket;
import com.xeehoo.health.share.bean.ImageItem;
import com.xeehoo.health.share.util.AlbumHelper;
import com.xeehoo.health.share.view.PopupAlbumPhoto;

//import com.example.testpic.PublishedActivity.PopupWindows;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivity extends Activity
{
	// ArrayList<Entity> dataList;//用来装载数据源的列表
	List<ImageBucket> dataList;
//	List<ImageItem> itemList;
	GridView gridView;
	TextView bt;
	ImageGridAdapter adapter;// 自定义的适配器
	AlbumHelper helper;
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	public static Bitmap bitmap;
	public Map<String, String> map = new HashMap<String, String>();
	public int selectedTotal = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_bucket);

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		initData();
		initView();
	}
	
	Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 0:
				Toast.makeText(AlbumActivity.this, "最多选择9张图片", 400).show();
				break;
			case 9:
				int pos = (Integer)msg.getData().get("position");
				Toast.makeText(AlbumActivity.this, "Select " + pos, 400).show();
//				itemList = dataList.get(pos).imageList;
//				adapter.notifyDataSetChanged();
//				adapter = new ImageGridAdapter(TestPicActivity.this, itemList, mHandler);
				adapter.setDataList(dataList.get(pos).imageList);
//				adapter.setBucket(pos);
//				currentBucket = pos;
				gridView.setAdapter(adapter);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 初始化数据
	 */
	private void initData()
	{
		// /**
		// * 这里，我们假设已经从网络或者本地解析好了数据，所以直接在这里模拟了10个实体类，直接装进列表中
		// */
		// dataList = new ArrayList<Entity>();
		// for(int i=-0;i<10;i++){
		// Entity entity = new Entity(R.drawable.picture, false);
		// dataList.add(entity);
		// }
		dataList = helper.getImagesBucketList(false);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.unshare_pic);
	}

	/**
	 * 初始化view视图
	 */
	private void initView()
	{
		TextView tv = (TextView)findViewById(R.id.activity_select_photo);
		tv.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
//				Log.e("Photo", "onclick photo");
//				Toast.makeText(TestPicActivity.this, "onclick photo", Toast.LENGTH_LONG).show();
				new PopupAlbumPhoto(AlbumActivity.this, gridView, dataList, mHandler);
				
			}
			
		});
		
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		List<ImageItem> itemList = new ArrayList<ImageItem>();
		//itemList = dataList.get(0).imageList;
		for (ImageBucket bucket: dataList){
			itemList.addAll(bucket.imageList);
		}
		
		selectedTotal = Bimp.drr.size();
		adapter = new ImageGridAdapter(AlbumActivity.this, itemList, mHandler);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new TextCallback()
		{
			public void onListen(int count)
			{
				bt.setText("完成" + "(" + count + "/9)");
			}
		});
		
		bt = (TextView) findViewById(R.id.bt);
		if (this.selectedTotal > 0){
			bt.setText("完成" + "(" + this.selectedTotal + "/9)");
		}
		
		bt.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				ArrayList<String> list = new ArrayList<String>();
				Collection<String> c = map.values();
				Iterator<String> it = c.iterator();
				for (; it.hasNext();)
				{
					list.add(it.next());
				}
				Log.e("TestPicActivity", "size is" + list.size());
//				if (Bimp.act_bool)
//				{
//					Intent intent = new Intent(TestPicActivity.this,
//							PublishedActivity.class);
//					startActivity(intent);
//					Bimp.act_bool = false;
//				}
				for (int i = 0; i < list.size(); i++)
				{
					if (Bimp.drr.size() < 9)
					{
//						Log.e("selected", list.get(i));
						Bimp.drr.add(list.get(i));
//						Bimp.bucket.add(currentBucket);
					}
				}
				finish();
			}

		});
//	}
		
//		adapter = new ImageBucketAdapter(TestPicActivity.this, dataList);
//		gridView.setAdapter(adapter);

//		gridView.setOnItemClickListener(new OnItemClickListener()
//		{
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id)
//			{
//				/**
//				 * 根据position参数，可以获得跟GridView的子View相绑定的实体类，然后根据它的isSelected状态，
//				 * 来判断是否显示选中效果。 至于选中效果的规则，下面适配器的代码中会有说明
//				 */
//				// if(dataList.get(position).isSelected()){
//				// dataList.get(position).setSelected(false);
//				// }else{
//				// dataList.get(position).setSelected(true);
//				// }
//				/**
//				 * 通知适配器，绑定的数据发生了改变，应当刷新视图
//				 */
//				// adapter.notifyDataSetChanged();
//				Intent intent = new Intent(TestPicActivity.this,
//						ImageGridActivity.class);
//				intent.putExtra(TestPicActivity.EXTRA_IMAGE_LIST,
//						(Serializable) dataList.get(position).imageList);
//				startActivity(intent);
//				finish();
//			}
//
//		});
	}
	
	
}
