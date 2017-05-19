package com.lxl.mineutil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxl.mineutil.bean.PoJieBean;
import com.lxl.mineutil.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 */

public class PoJieAdapter extends RecyclerView.Adapter<PoJieAdapter.PojieViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<PoJieBean> poJieBeenList;
    public PoJieAdapter(List<PoJieBean> poJieBeenList){
        this.poJieBeenList=poJieBeenList;
    }
    @Override
    public PojieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pojie,parent,false);
        return new PojieViewHolder(view);
    }



    @Override
    public void onBindViewHolder(PojieViewHolder holder, final int position) {
        holder.phone.setText(poJieBeenList.get(position).getPhone());
        holder.token.setText(poJieBeenList.get(position).getToken());
        holder.imei.setText(poJieBeenList.get(position).getEnterpriseId());
        holder.version.setText(poJieBeenList.get(position).getVersion());
        holder.random.setText(poJieBeenList.get(position).getRandom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position,poJieBeenList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return poJieBeenList.size();
    }

    public class PojieViewHolder extends RecyclerView.ViewHolder{
        private TextView phone;
        private TextView version;
        private TextView imei;
        private TextView token;
        private TextView random;

        public PojieViewHolder(View itemView) {
            super(itemView);
            token= (TextView) itemView.findViewById(R.id.token);
            version= (TextView) itemView.findViewById(R.id.version);
            imei= (TextView) itemView.findViewById(R.id.imei);
            phone= (TextView) itemView.findViewById(R.id.phone);
            random= (TextView) itemView.findViewById(R.id.random);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position, PoJieBean poJieBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void addPojie(PoJieBean poJieBean){
        poJieBeenList.add(poJieBean);
        notifyDataSetChanged();
    }
}
