package com.example.mashuda11.sqldatabase;



import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayContact extends AppCompatActivity {
int from_where_I_Am_Coming = 0;
    private DBHelper mydb;


TextView name;
TextView phone;
TextView email;
TextView street;
TextView country;
int id_To_Update = 0;
    private Cursor re;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        
        name = (TextView) findViewById(R.id.editTextName);
        phone = (TextView) findViewById(R.id.editTextPhone);
        email= (TextView) findViewById(R.id.editTextEmail);
        street = (TextView) findViewById(R.id.editTextStreet);
        country = (TextView) findViewById(R.id.editTextCity);


        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            int Value= extras.getInt("id");
        if(Value>0){
        //means this is the view part not the add contact part.
            Cursor rs = mydb.getData(Value);
            id_To_Update=Value;
            rs.moveToFirst();


            String nam = rs.getString(re.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
            String phon = rs.getString(re.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
            String emai = rs.getString(re.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL);
            String str= rs.getString(re.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
            String city = rs.getString(re.getColumnIndex(DBHelper.CONTACTS_COLUMN_CITY));

            if (!rs.isClosed)) {
                rs.close();
            }

            Button b =(Button)findViewById(R.id.saveButton);
            b.setVisibility(View.INVISIBLE);

name.setText((CharSequence)name);
name.setFocusable(false);
name.setClickable(false);


phone.setText((CharSequence)phone);
phone.setFocusable(false);
phone.setClickable(false);


email.setText((CharSequence)email);
email.setFocusable(false);
email.setClickable(false);


street.setText((CharSequence)street);
street.setFocusable(false);
street.setClickable(false);


country.setText((CharSequence)country);
country.setFocusable(false);
country.setClickable(false);


        }
        }
    }

    @Override

    public boolean onCreaeOptionMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value =  extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_contact, menu);

            }

            else{
                getMenuInflater().inflate(R.menu.menu_main menu);
            }
        }
        return true;
    }


public boolean onOptionItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
        case R.id.Edit_Contact:
            Button b = (Button)findViewById(R.id.saveButton);
            b.setVisibility(View.VISIBLE);

            name.setEnabled(true);
            name.setFocusableInTouchMode(true);
            name.setClickable(true);

            phone.setEnabled(true);
            phone.setFocusableInTouchMode(true);
            phone.setClickable(true);

            email.setEnabled(true);
            email.setFocusableInTouchMode(true);
            email.setClickable(true);

            street.setEnabled(true);
            street.setFocusableInTouchMode(true);
            street.setClickable(true);

            country.setEnabled(true);
            country.setFocusableInTouchMode(true);
            country.setClickable(true);
            return true;

        case R.id.Delete_Contact:

            AlertDialog.Builder builder =new AlertDialog.Builder(this);

            builder.setMessage(R.string.deleteContact)

                    .setPositiveButton(R.string.yes,new DialogInterface.onClickListener()){
                public void onClick(DialogInterface dialog, int id){
                mydb.deleteContact(id_To_Update);
                Toast.makeText(getApplicationContext(),"Deleted Successfully",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity1.class);
                startActivity(intent);
            }
        });


            .builder.setNegativeButton();



AlertDialog d = builder.create();
d.setTitle("Are you sure");
d.show();
return true;
            default:
return super.onOptionsItemSelected(item);
    }

}

public void run(View view){
        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateContact(id_To_Update,name.getText().toString(),
                        phone.getText().toString(),email.getText().toString(),
                        street.getText().toString(),country.getText().toString()))
            }


Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();

Intent intent =new intent(getApplicationContext(),MainActivity1.class);
startActivity(intent); }

else{

      Toast.makeText(getApplicationContext(), "not Update", Toast.LENGTH_SHORT).show();

        }

        else{


            if(mydb.insertContact(name.getText().toString(),

                    phone.getText().toString(),email.getText().toString(),
                    street.getText().toString(),
                    country.getText().toString())){
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

            }

            else{

    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();


            }

            Intent intent= new intent(getApplicationContext(), MainActivity1.class);
            startActivity(intent);

        }
}
}


}