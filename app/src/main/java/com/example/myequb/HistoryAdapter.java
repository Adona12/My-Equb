package com.example.myequb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context mContext;
    private List<history_holder> mHistoryDataList;
    private LayoutInflater mInflater;

    public HistoryAdapter(Context mContext, List<history_holder> mHistoryDataList) {
        this.mContext = mContext;
        this.mHistoryDataList = mHistoryDataList;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = mInflater.inflate(R.layout.single_history, viewGroup, false);
        return new HistoryViewHolder(this, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  HistoryAdapter.HistoryViewHolder HistoryViewHolder, int position) {
        HistoryViewHolder.Date.setText(mHistoryDataList.get(position).getDate());
        HistoryViewHolder.Winner.setText(mHistoryDataList.get(position).getWinner());
    }

    @Override
    public int getItemCount() {
        return mHistoryDataList.size();
    }
    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView Date;
        private TextView Winner;
        private HistoryAdapter historyadapter;
        public HistoryViewHolder(HistoryAdapter Historyadapt,@NonNull View itemView) {
            super(itemView);
           Date = itemView.findViewById(R.id.historyDate);
            Winner = itemView.findViewById(R.id.historyWinner);
            this.historyadapter= Historyadapt;
        }
    }
}
