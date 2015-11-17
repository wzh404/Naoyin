package com.xeehoo.health.common.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xeehoo.health.R;
import com.xeehoo.health.common.bean.Article;
import com.xeehoo.health.databinding.LvArticleBinding;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleListAdapter extends BaseAdapter {
	private List<Article> list;
	private LayoutInflater inflater;

	public ArticleListAdapter(Context context, List<Article> list) {
		super();
		this.inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        LvArticleBinding binding = null;
		if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_article, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
		} else {
            binding = (LvArticleBinding) convertView.getTag();
		}
		
		Article article = list.get(position);
        binding.setArticle(article);

		return convertView;
	}
}
