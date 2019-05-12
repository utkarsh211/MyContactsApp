package com.example.mycontactsapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class ContactDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Bundle contactDetails = getIntent().getExtras();
        String contactName = contactDetails.getString("name");
        String contactNumber = contactDetails.getString("number");
        String userImage = contactDetails.getString("userImage");
        if(userImage!=null) {
            Uri uri = Uri.parse(userImage);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Drawable drawable = new BitmapDrawable(getResources(), bitmap);

            ImageView imageView = findViewById(R.id.contact_image);
            imageView.setImageDrawable(drawable);
        }
        EditText contactNameText = findViewById(R.id.contact_name_text);
        EditText contactNumberText = findViewById(R.id.contact_number_text);
        contactNameText.setText(contactName);
        contactNumberText.setText(contactNumber);

    }
}
