package com.lxl.mineutil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lxl.mineutil.R;
import com.lxl.mineutil.bean.UserBean;

import java.util.List;

/**
 * Created by lxl on 2017/5/16.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MViewHolder> {
    private List<UserBean.EnterpriseUserListBean> userListBean;
    private PoJieListener poJieListener;
    public UserAdapter(List<UserBean.EnterpriseUserListBean> userListBean){
        this.userListBean=userListBean;
    }
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_show_user,parent,false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, final int position) {
        holder.phone.setText(userListBean.get(position).getPhone());
        String userPassword=userListBean.get(position).getPassword();
        holder.password.setText(userPassword);
        holder.goPojie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (poJieListener!=null) poJieListener.startPoJie(userListBean.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListBean.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder{
        private TextView phone;
        private TextView password;
        private Button goPojie;
        public MViewHolder(View itemView) {
            super(itemView);
            phone= (TextView) itemView.findViewById(R.id.user_phone);
            password= (TextView) itemView.findViewById(R.id.user_password);
            goPojie= (Button) itemView.findViewById(R.id.go_pojie);
        }
    }

    public void addData(List<UserBean.EnterpriseUserListBean> userListBean){
        this.userListBean.addAll(userListBean);
        notifyDataSetChanged();
    }

    public void addData(UserBean.EnterpriseUserListBean userListBean){
        this.userListBean.add(userListBean);
        notifyDataSetChanged();
    }

    public interface PoJieListener{
        void startPoJie(UserBean.EnterpriseUserListBean userListBean);
    }

    public void setPoJieListener(PoJieListener poJieListener){
        this.poJieListener=poJieListener;
    }
}
