package com.lxl.mineutil.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxl.mineutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxl on 2017/5/19.
 */

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>{
    private List<String> phones=new ArrayList<>();
    private LongClickItemListener longClickItemListener;
    private ItemClickListener itemListener;

    public PhoneAdapter(List<String> phones){
        this.phones=phones;
    }
    @Override
    public PhoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_phone,parent,false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhoneViewHolder holder, final int position) {
        holder.phoneNumber.setText(phones.get(position));
        if (longClickItemListener!=null){
            holder.phoneNumber.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickItemListener.longClickListener(phones.get(position));
                    return false;
                }
            });
        }
        if (itemListener!=null){
            holder.phoneNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.clickListener(phones.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder{
        private TextView phoneNumber;
        public PhoneViewHolder(View itemView) {
            super(itemView);
            phoneNumber= (TextView) itemView.findViewById(R.id.phone_number);
        }
    }
    public interface  LongClickItemListener{
        void longClickListener(String phone);
    }

    public void setLongClickItemListener(LongClickItemListener longClickItemListener){
        this.longClickItemListener=longClickItemListener;
    }

    public interface ItemClickListener{
        void clickListener(String phone);
    }

    public void setItemClickListener(ItemClickListener itemListener){
        this.itemListener=itemListener;
    }

    public void refreshView(List<String> phones){
        this.phones=new ArrayList<>();
        this.phones.addAll(phones);
        notifyDataSetChanged();
    }
}
