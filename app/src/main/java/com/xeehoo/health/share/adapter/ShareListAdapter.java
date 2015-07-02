package com.xeehoo.health.share.adapter;

import java.util.ArrayList;
import java.util.List;

import com.xeehoo.health.R;
import com.xeehoo.health.R.drawable;
import com.xeehoo.health.R.id;
import com.xeehoo.health.R.layout;
import com.xeehoo.health.share.bean.ShareContent;
import com.xeehoo.health.util.ResourceUtils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ShareListAdapter extends BaseAdapter {

//	private NearByPeopleProfile mProfile;
//	private NearByPeople mPeople;
//	private OtherFeedListPopupWindow mPopupWindow;
//	private int mWidthAndHeight;
//	private int mPosition;
//	private SimpleListDialog mDialog;

//	public OtherFeedListAdapter(Context context,
//			List<? extends Entity> datas) {
//		super(application, context, datas);
//		mProfile = profile;
//		mPeople = people;
//		mWidthAndHeight = (int) TypedValue.applyDimension(
//				TypedValue.COMPLEX_UNIT_DIP, 120, context.getResources()
//						.getDisplayMetrics());
//		mPopupWindow = new OtherFeedListPopupWindow(context, mWidthAndHeight,
//				mWidthAndHeight);
//		mPopupWindow.setOnOtherFeedListPopupItemClickListner(this);
//	}
	
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
			holder.root = (RelativeLayout) convertView
					.findViewById(R.id.feed_item_layout_root);
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
			holder.more = (ImageView) convertView
					.findViewById(R.id.feed_item_ib_more);
			holder.comment = (LinearLayout) convertView
					.findViewById(R.id.feed_item_layout_comment);
			holder.commentCount = (TextView) convertView
					.findViewById(R.id.feed_item_htv_commentcount);
			holder.site = (TextView) convertView
					.findViewById(R.id.feed_item_htv_site);
			
//			List images = new ArrayList();
//			images.add("1");
//			images.add("1");
//			images.add("1");
//			images.add("1");
////			images.add("1");
////			images.add("1");
			
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
		} else {
			holder.contentImage.setVisibility(View.VISIBLE);
//			holder.contentImage.setImageResource(R.drawable.b16a);
			if (content.getImages().length == 1){
				holder.contentImage.setNumColumns(1);
			}
			else if (content.getImages().length == 4){
				holder.contentImage.setNumColumns(2);
				ViewGroup.LayoutParams layoutParams =
						holder.contentImage.getLayoutParams();
				layoutParams.width = ResourceUtils.dip2px(context, 120) * 2;
//				layoutParams.height = ResourceUtils.dip2px(context, 100);
				holder.contentImage.setLayoutParams(layoutParams);
			}
			else{
				holder.contentImage.setNumColumns(3);
			}
			holder.contentImage.setAdapter(new GridviewImageAdapter(context, content.getImages()));
		}
		holder.site.setText(content.getLikes() + "");
		holder.commentCount.setText("评论");
		
		return convertView;
	}

	class ViewHolder {
		RelativeLayout root;
		ImageView avatar;
		TextView time;
		TextView name;
		TextView content;
		GridView contentImage;
		ImageView more;
		LinearLayout comment;
		TextView commentCount;
		TextView site;
	}

//	@Override
//	public void onItemClick(int position) {
//		final FlippingLoadingDialog dialog = new FlippingLoadingDialog(
//				mContext, "正在提交,请稍后...");
//		dialog.show();
//		new Handler().postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				dialog.dismiss();
//				showCustomToast("举报的信息已提交");
//			}
//		}, 1500);
//	}

//	@Override
//	public void onCopy(View v) {
//		Feed feed = (Feed) getItem(mPosition);
//		String text = feed.getContent();
//		ClipboardManager m = (ClipboardManager) mContext
//				.getSystemService(Context.CLIPBOARD_SERVICE);
//		m.setText(text);
//		showCustomToast("已成功复制文本");
//	}
//
//	@Override
//	public void onReport(View v) {
//		String[] codes = mContext.getResources().getStringArray(
//				R.array.reportfeed_items);
//		mDialog = new SimpleListDialog(mContext);
//		mDialog.setTitle("举报留言");
//		mDialog.setTitleLineVisibility(View.GONE);
//		mDialog.setAdapter(new SimpleListDialogAdapter(mContext, codes));
//		mDialog.setOnSimpleListItemClickListener(this);
//		mDialog.show();
//	}

//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Object getItem(int arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long getItemId(int arg0) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
