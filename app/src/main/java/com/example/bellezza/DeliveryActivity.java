package com.example.bellezza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeliveryActivity extends AppCompatActivity {



    EditText name2, phone2, addr2, city2;
    Button addbtn;
    Delivery deliver;
    DatabaseReference DeliveryRef;
    String Cname , PhoneNo , Address , City;
    ProgressDialog loadingBar;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        //edit text
        name2 = findViewById(R.id.name1);
        phone2 = findViewById(R.id.phone);
        addr2 = findViewById(R.id.address);
        city2 = findViewById(R.id.city);
        loadingBar = new ProgressDialog(this);

        //button
        addbtn= findViewById(R.id.button2);


        //new deliver object
        deliver = new Delivery();


        //action bar
        setTitle("Delivery Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //insert button
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Check();


            }

        });
    }



    //check the entered details
    private void Check(){

        Cname = name2.getText().toString();
        PhoneNo = phone2.getText().toString();
        Address= addr2.getText().toString();
        City=city2.getText().toString();

        if (Cname.length()==0)
        {
            name2.requestFocus();
            name2.setError("Name cannot be empty!");
        }

        else if(!Cname.matches("[a-zA-Z ]+")  )
        {
            /*Toast.makeText(this,"Please provide your full name.. ",Toast.LENGTH_SHORT);*/
            name2.requestFocus();
            name2.setError("Enter only alphabetical characters!");
        }
        else if (PhoneNo.length()==0)
        {
            phone2.requestFocus();
            phone2.setError("Phone number cannot be empty!");
        }
        else if(PhoneNo.length()<10 || PhoneNo.length()>10)
        {
            phone2.requestFocus();
            phone2.setError("Enter valid Phone number!");
        }
        else if(Address.length()==0)
        {
            addr2.requestFocus();
            addr2.setError("Address cannot be empty!");
        }
        else if(City.length()==0)
        {
            city2.requestFocus();
            city2.setError("City cannot be empty!");
        }
        else
        {
            confirmOrder();
        }
    }



    private void confirmOrder(){

       DeliveryRef = FirebaseDatabase.getInstance().getReference().child("Delivery");

        final String saveCurrentdate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentdate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());


        Cname = name2.getText().toString();
        PhoneNo = phone2.getText().toString();
        Address= addr2.getText().toString();
        City=city2.getText().toString();
        deliver.setDate(saveCurrentdate);
        deliver.setTime(saveCurrentTime);

        Delivery deliver = new Delivery(Cname , PhoneNo , Address , City , "","" , "");
        //DeliveryRef.child("996450325V").setValue(deliver);
        key = DeliveryRef.push().getKey();
        DeliveryRef.child(key).setValue(deliver);
        Toast.makeText(DeliveryActivity.this,"Your order has placed successful..",Toast.LENGTH_SHORT).show();


        OpenUi();

    }


    //open next page
    private void OpenUi() {


        Intent intent = new Intent(this, ConfirmDetailsActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);

        StoreDeliveryInformation();


    }


    //loading bar
    private void StoreDeliveryInformation(){

        //loadingBar.setIcon(R.drawable.plus);
        loadingBar.setTitle("Bellezza");
        loadingBar.setMessage("Please Wait......");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        clearControls();

    }



    private void clearControls(){


        name2.setText("");
        phone2.setText("");
        addr2.setText("");
        city2.setText("");

    }
}


