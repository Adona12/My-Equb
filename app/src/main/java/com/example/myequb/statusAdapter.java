package com.example.myequb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class statusAdapter extends RecyclerView.Adapter<statusAdapter.statusViewHolder>  {
    private Context mContext;
    private List<Person> mstatusDataList;
    private LayoutInflater mInflater;

    public statusAdapter(Context mContext, List<Person> mstatusDataList) {
        this.mContext = mContext;
        this.mstatusDataList = mstatusDataList;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public statusAdapter.statusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = mInflater.inflate(R.layout.single_member_status, viewGroup, false);
        return new statusViewHolder(this, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull statusAdapter.statusViewHolder statusViewHolder, int position) {

        statusViewHolder.memberName.setText(mstatusDataList.get(position).getName());
        if(mstatusDataList.get(position).getAmount()>0){
            statusViewHolder.wrong.setVisibility(View.INVISIBLE);
        }
        else{
            statusViewHolder.right.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mstatusDataList.size();
    }
    public class statusViewHolder extends RecyclerView.ViewHolder {
     ;
        private TextView memberName;
        private statusAdapter statusadapter;
        private ImageView right;
        private ImageView wrong;
        public statusViewHolder(statusAdapter statusadapt,@NonNull View itemView) {
            super(itemView);
            wrong=itemView.findViewById(R.id.wrong);
right=itemView.findViewById(R.id.check);

            memberName = itemView.findViewById(R.id.statusMemberName);
            this.statusadapter=statusadapt;
        }
    }
}


