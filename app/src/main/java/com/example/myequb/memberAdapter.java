package com.example.myequb;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import java.util.List;

public class memberAdapter extends RecyclerView.Adapter<memberAdapter.memberViewHolder>{

    private Context mContext;
    private List<Person> mMemberDataList;
    private LayoutInflater mInflater;

    public memberAdapter(Context mContext, List<Person> mMemberDataList) {
        this.mContext = mContext;
        this.mMemberDataList = mMemberDataList;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public memberAdapter.memberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = mInflater.inflate(R.layout.single_member, viewGroup, false);
        return new memberViewHolder(this, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final memberAdapter.memberViewHolder memberViewHolder, final int position) {
        memberViewHolder.memberImage.setImageResource(mMemberDataList.get(position).getPersonImage());
        memberViewHolder.memberName.setText(mMemberDataList.get(position).getName());


        if(mMemberDataList.get(position).getAmount()>0){
            memberViewHolder.mswitch.setChecked(true);
            memberViewHolder.mswitch.setEnabled(false);


        }
        memberViewHolder.mswitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                // get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                View promptView = layoutInflater.inflate(R.layout.dialog, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setView(promptView);

                final EditText editText = (EditText) promptView.findViewById(R.id.editText);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                String Name = editText.getText().toString();
                                int x=0;
                                if(Name.equals("")){
                                    memberViewHolder.mswitch.setChecked(false);
                                    alertDialogBuilder.setCancelable(true);
                                }
                                else {
                                    try{


                                    x = Integer.parseInt(Name);
                                    }
                                    catch (Exception e){
                                        editText.setHint(mContext.getString(R.string.tryagain));
                                    }
                                    insert(x, mMemberDataList.get(position).getPhone_num());
                                    memberViewHolder.mswitch.setEnabled(false);
                                }
                            }
                        });
                alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialogBuilder.setCancelable(true);
                        memberViewHolder.mswitch.setChecked(false);
                        alertDialogBuilder.setCancelable(true);
                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMemberDataList.size();
    }


    public class memberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         private com.suke.widget.SwitchButton  mswitch;

        private ImageView memberImage;
        private TextView memberName;
private memberAdapter memberadapter;
        public memberViewHolder(memberAdapter memberadapt,@NonNull View itemView) {
            super(itemView);
            memberImage = itemView.findViewById(R.id.member_image);
           memberName = itemView.findViewById(R.id.member_name);
           mswitch=itemView.findViewById(R.id.switch_money);

           this.memberadapter=memberadapt;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
//            Toast.makeText(mContext,"entered", Toast.LENGTH_SHORT).show();
            int position = getLayoutPosition();
            Person currentData = mMemberDataList.get(position);
            Intent intent1 =new Intent(mContext,WorkActivity.class);
            intent1.putExtra("name",currentData.getName());
            intent1.putExtra("phone",currentData.getPhone_num());
            intent1.putExtra("email",currentData.getEmail());
            mContext.startActivity(intent1);

        }
    }
    protected void showInputDialog(final String phone) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.editText);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String Name = editText.getText().toString();
                        if(Name.equals("")){

                        }
                        int x=Integer.parseInt(Name);
                        insert(x,phone);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogBuilder.setCancelable(true);
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    public void insert(int amount,String phone){
        DatabaseHelper db=new DatabaseHelper(mContext);
        db.updateAmount(amount,phone);
    }
}
