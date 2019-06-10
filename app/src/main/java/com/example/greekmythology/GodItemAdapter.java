package com.example.greekmythology;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.greekmythology.model.GodItem;
import java.util.List;
import java.util.jar.Attributes;

public class GodItemAdapter extends RecyclerView.Adapter<GodItemAdapter.ViewHolder>{

    public static final String ITEM_NAME = "item_name";
    private List<GodItem> mItems;
    private Context mContext;

    public GodItemAdapter(Context context, List<GodItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public GodItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GodItemAdapter.ViewHolder holder, int position) {
        final GodItem item = mItems.get(position);

        holder.godName.setText(item.getName());
        holder.characteristics.setText(item.getCharacteristics());
        holder.description.setText(item.getDetailedDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = item.getName();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(ITEM_NAME, itemId);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder {

        TextView godName;
        TextView characteristics;
        TextView description;
        View mView;

        ViewHolder(View itemView) {
            super(itemView);

            godName = itemView.findViewById(R.id.godName);
            characteristics = itemView.findViewById(R.id.characteristics);
            description = itemView.findViewById(R.id.description);
            mView = itemView;
        }
    }
}
