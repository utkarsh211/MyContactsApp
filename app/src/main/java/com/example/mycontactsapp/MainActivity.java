package com.example.mycontactsapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText searchText;
    private int READ_CONTACTS_CODE = 1;
    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<ContactsModel> contactsList;
    String name,number,email,user_image_thumbnail,user_image;

    @Override
    protected void onStart() {
        super.onStart();
        fetchContacts();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_text);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        askPermission();
        contactsList = new ArrayList<>();
        fetchContacts();
        contactsAdapter = new ContactsAdapter(this,contactsList);
        recyclerView = findViewById(R.id.contacts_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactsAdapter);
    }
    private void fetchContacts(){

        ContentResolver contentResolver = getContentResolver();
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Email.DATA, ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,ContactsContract.CommonDataKinds.Phone.PHOTO_URI};
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projection,null,null,null);
        while(cursor.moveToNext()){

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            user_image_thumbnail = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));
            user_image = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            ContactsModel contactsModel = new ContactsModel(name, number, email, user_image_thumbnail, user_image);
            contactsList.add(contactsModel);
        }

    }
    private void askPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACTS_CODE);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_NUMBERS},READ_CONTACTS_CODE);
        }
    }

    public void filter(String text)
    {
        ArrayList<ContactsModel> filteredList = new ArrayList<>();
        for(ContactsModel model : contactsList)
        {
            if(model.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(model);
            }
        }
        contactsAdapter.filterList(filteredList);
    }


}
