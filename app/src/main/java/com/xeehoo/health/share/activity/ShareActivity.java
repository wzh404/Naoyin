package com.xeehoo.health.share.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xeehoo.health.R;
import com.xeehoo.health.share.bean.Bimp;
import com.xeehoo.health.util.FileUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ShareActivity extends Activity {

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private TextView activity_selectimg_send;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimg);
		Init();
	}

	public void Init() {
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update1();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == Bimp.bmp.size()) {
					// new PopupWindows(PublishedActivity.this,
					// noScrollgridview);
					Intent intent = new Intent(ShareActivity.this,
							AlbumActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(ShareActivity.this,
							PhotoActivity.class);
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});
		activity_selectimg_send = (TextView) findViewById(R.id.activity_selectimg_send);
		activity_selectimg_send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < Bimp.drr.size(); i++) {
					String Str = Bimp.drr.get(i).substring(
							Bimp.drr.get(i).lastIndexOf("/") + 1,
							Bimp.drr.get(i).lastIndexOf("."));
					list.add(FileUtils.SDPATH + Str + ".JPEG");
				}
				// 高清的压缩图片全部就在 list 路径里面了
				// 高清的压缩过的 bmp 对象 都在 Bimp.bmp里面
				// 完成上传服务器后 .........
				FileUtils.deleteDir();
				finish();
			}
		});
		
		ImageView photo = (ImageView)findViewById(R.id.select_camera);
		photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				photo();				
			}
		});
	}

	@SuppressLint("HandlerLeak")
	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater; // 视图容器
		private int selectedPosition = -1;// 选中的位置
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update1() {
			loading1();
		}

		public int getCount() {
			return (Bimp.bmp.size() + 1);
		}

		public Object getItem(int arg0) {

			return null;
		}

		public long getItemId(int arg0) {

			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}
		
		/**
		 * ListView Item设置
		 */
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// final int coord = position;
			ViewHolder holder = null;

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				holder.unImage = (ImageView) convertView
						.findViewById(R.id.unselected);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.image.setVisibility(View.VISIBLE);
			if (position == Bimp.bmp.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.icon_addpic_unfocused));
				holder.unImage.setVisibility(View.GONE);
			} else {
				holder.image.setImageBitmap(Bimp.bmp.get(position));
				holder.unImage.setVisibility(View.VISIBLE);
			}

			holder.unImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
//					Log.e("unImage", "View = " + position);
					if (Bimp.bmp.size() == 1) {
						Bimp.bmp.clear();
						Bimp.drr.clear();
						Bimp.max = 0;
					} else {
						Bimp.bmp.remove(position);
						Bimp.drr.remove(position);
						Bimp.max -= 1;
					}					
					adapter.notifyDataSetChanged();
				}
			});

			if (position == 9) {
				holder.image.setVisibility(View.GONE);
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
			public ImageView unImage;
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading1() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (Bimp.max == Bimp.drr.size()) {
							Log.e("bimap max", Bimp.max + "");
							
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							try {
								String path = Bimp.drr.get(Bimp.max);
								Log.e("bimap path", Bimp.drr.size() + " - " + Bimp.max);
								Bitmap bm = Bimp.revitionImageSize(path);
								Bimp.bmp.add(bm);
								String newStr = path.substring(
										path.lastIndexOf("/") + 1,
										path.lastIndexOf("."));
								FileUtils.saveBitmap(bm, "" + newStr);
							} catch (IOException e) {
								e.printStackTrace();
							}finally{
								Bimp.max += 1;
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							}
						}
					}
				}
			}).start();
		}
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	protected void onRestart() {
		adapter.update1();
		super.onRestart();
	}

//	public class PopupWindows extends PopupWindow {
//
//		public PopupWindows(Context mContext, View parent) {
//			super(mContext);
//
//			View view = View
//					.inflate(mContext, R.layout.item_popupwindows, null);
//			view.startAnimation(AnimationUtils.loadAnimation(mContext,
//					R.anim.fade_ins));
//			LinearLayout ll_popup = (LinearLayout) view
//					.findViewById(R.id.ll_popup);
//			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
//					R.anim.push_bottom_in_2));
//
//			setWidth(LayoutParams.FILL_PARENT);
//			setHeight(LayoutParams.FILL_PARENT);
//			setBackgroundDrawable(new BitmapDrawable());
//			setFocusable(true);
//			setOutsideTouchable(true);
//			setContentView(view);
//			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
//			update();
//
//			Button bt1 = (Button) view
//					.findViewById(R.id.item_popupwindows_camera);
//			Button bt2 = (Button) view
//					.findViewById(R.id.item_popupwindows_Photo);
//			Button bt3 = (Button) view
//					.findViewById(R.id.item_popupwindows_cancel);
//			bt1.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					photo();
//					dismiss();
//				}
//			});
//			bt2.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					Intent intent = new Intent(PublishedActivity.this,
//							TestPicActivity.class);
//					startActivity(intent);
//					dismiss();
//				}
//			});
//			bt3.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					dismiss();
//				}
//			});
//
//		}
//	}

	private static final int TAKE_PICTURE = 0x000000;
	private String path = "";

	public void onConfigurationChanged(Configuration config) {
		super.onConfigurationChanged(config);
	}

	public void photo() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			File dir = new File(Environment.getExternalStorageDirectory()
					+ "/myimage/");
			if (!dir.exists())
				dir.mkdirs();
			
			File file = new File(dir,
					String.valueOf(System.currentTimeMillis()) + ".jpg");
			path = file.getPath();
			
			Uri imageUri = Uri.fromFile(file);
			Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			openCameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
			startActivityForResult(openCameraIntent, TAKE_PICTURE);
		} else {
			String myimage = getApplicationContext().getFilesDir().getAbsolutePath() + "/myimage/";
			File dir = new File(myimage);
			if (!dir.exists())
				dir.mkdirs();
			File file = new File(dir,
					String.valueOf(System.currentTimeMillis()) + ".jpg");
			path = file.getPath();
			
			Uri imageUri = Uri.fromFile(file);
			Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			openCameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
			startActivityForResult(openCameraIntent, TAKE_PICTURE);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PICTURE:
			if (Bimp.drr.size() < 9 && resultCode == -1) {
				Bimp.drr.add(path);
			}
			break;
		}
	}

}
