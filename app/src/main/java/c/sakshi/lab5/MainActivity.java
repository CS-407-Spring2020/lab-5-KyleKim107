package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static String usernameKey = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedpreferences = getSharedPreferences( "c.sakshi.lab5" , Context.MODE_PRIVATE);

        if(!sharedpreferences.getString(  usernameKey , "").equals("")){
            goToActivity2( sharedpreferences.getString( usernameKey, "" ));

        } else {

            setContentView(R.layout.activity_main);
        }



    }


    public void onButtonClick(View view) {

        sharedpreferences = getSharedPreferences( "c.sakshi.lab5" , Context.MODE_PRIVATE);
        EditText welcome = (EditText)  findViewById(R.id.editText2);
        String str = welcome.getText().toString();

        sharedpreferences.edit().putString("username", str ).apply();

        goToActivity2(str);

    }

    public void goToActivity2(String str){
        Intent intent = new Intent( this , SecondActivity.class);
        intent.putExtra("message" ,"" + str);
        startActivity(intent);
    }






}