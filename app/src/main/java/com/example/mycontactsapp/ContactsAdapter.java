package com.example.mycontactsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    String user_image_thumbnail;
    private Context context;
    private List<ContactsModel> contactsList;

    public ContactsAdapter(Context context, List<ContactsModel> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.contact_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder viewHolder, int i) {
       final String name = contactsList.get(i).getName();
       final String number = contactsList.get(i).getNumber();
        viewHolder.contactName.setText(name);
        final String email = contactsList.get(i).getEmail();
        final String user_image = contactsList.get(i).getUser_image();
        viewHolder.contactsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ContactDetails.class);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                if(user_image!=null)
                {
                    intent.putExtra("userImage",user_image);
                }

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView contactsCard;
        TextView contactName;
        ImageView contactImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contact_name);
            contactImage = itemView.findViewById(R.id.contact_image);
            contactsCard = itemView.findViewById(R.id.contact_card);


        }
    }
    public void filterList(ArrayList<ContactsModel> filteredList){
        contactsList = filteredList;
        notifyDataSetChanged();
    }
}
