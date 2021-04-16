package com.example.testapril;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    private  ItemClickListener listener;

    public CityAdapter(Context mContext, List<CityBean> mDatas,ItemClickListener listener) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.listener = listener;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public CityAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final CityAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.tvCapter.setText(cityBean.getCap());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        TextView tvCapter;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvCapter = (TextView) itemView.findViewById(R.id.tvCapter);
            content = itemView.findViewById(R.id.content);
        }
    }

    interface  ItemClickListener{

        void onItemClick(int position);

    }
}