package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class SecondActivity extends AppCompatActivity {

    TextView textView2;
    SharedPreferences sharedpreferences;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String usernameKey = "username";
        sharedpreferences = getSharedPreferences( "c.sakshi.lab5" , Context.MODE_PRIVATE);

        textView2 = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();

        if(!sharedpreferences.getString(  usernameKey , "").equals("")){
            String str = sharedpreferences.getString( usernameKey, "" );
            textView2.setText( "Welcome "+ str);


        }else {
            String str = intent.getStringExtra("message");
            textView2.setText("Welcome " + str);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator  = getMenuInflater();
        inflator.inflate(R.menu.menu_after_login ,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(this, MainActivity.class);
            //SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
            sharedpreferences.edit().remove(MainActivity.usernameKey).apply();
            startActivity(intent);
            return true;
        }

        return false;


    }


}
