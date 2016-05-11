package com.xeehoo.health.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xeehoo.health.BR;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.TransferActivity;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.model.Transfer;
import com.xeehoo.health.view.CircleProgressBar;
import com.xeehoo.health.view.ProductEmptyView;
import com.xeehoo.health.view.ProductItemView;
import com.xeehoo.health.view.TransferItemView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class TransferRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private final static int ITEM_VIEW_TYPE_HEADER = 0;
    private final static int ITEM_VIEW_TYPE_DATA = 1;

    private List<Transfer> items;
    private Context context;

    public TransferRecyclerAdapter(Context context, List<Transfer> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == TransferRecyclerAdapter.ITEM_VIEW_TYPE_HEADER){
            v = new ProductEmptyView(context, parent).getView();
        }
        else {
            v = new TransferItemView(context, parent).getView();
        }

        RecyclerViewHolder holder = new RecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (holder.getItemViewType() == TransferRecyclerAdapter.ITEM_VIEW_TYPE_HEADER)
            return;

        final Transfer transfer =  items.get(position);
        holder.getBinding().setVariable(BR.transfer, transfer);
        holder.getBinding().executePendingBindings();

        CircleProgressBar circleProgressBar = (CircleProgressBar)holder.itemView.findViewById(R.id.circleProgressbar);
        try {
            int progress = transfer.getDiscount()
                    .multiply(new BigDecimal(100))
                    .intValue();
            circleProgressBar.setProgress(progress);
        }catch (Exception e){
            e.printStackTrace();
        }

//        String amount = transfer.getDiscount()
//                .multiply(new BigDecimal(100))
//                .setScale(2,BigDecimal.ROUND_DOWN)
//                .toString();

        circleProgressBar.setUnit("折");

        final TransferActivity activity = (TransferActivity)holder.itemView.getContext();
        LinearLayout linearLayout = (LinearLayout)holder.itemView.findViewById(R.id.item_transfer_id);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, transfer.getTransferId() + "", Toast.LENGTH_SHORT).show();
                activity.startTransferWebview(transfer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Transfer transfer =  items.get(position);
        if (transfer.getTransferId().intValue() == 0){
            return TransferRecyclerAdapter.ITEM_VIEW_TYPE_HEADER;
        }
        else{
            return TransferRecyclerAdapter.ITEM_VIEW_TYPE_DATA;
        }
    }
}
