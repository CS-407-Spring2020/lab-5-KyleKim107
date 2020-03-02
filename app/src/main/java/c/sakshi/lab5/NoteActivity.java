package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    int  noteid = -1;
    EditText editText;

    public static String usernameKey = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Get editText
         editText = (EditText) findViewById(R.id.editTextonThird);

        //init database instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("note", Context.MODE_PRIVATE,null);
        // My database have not used


        // get value of interger "noteid" from intent
        // Initialize the class variable
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);


        if(noteid != -1){
            Note note = SecondActivity.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);

        }

        //Button save = (Button) findViewById(R.id.button3);
        //save.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v) {
          //      saveMethod(v);
           // }
        //});
    }



    public void saveMethod(View view){
        SharedPreferences sharedpreferences = getSharedPreferences( "c.sakshi.lab5" , Context.MODE_PRIVATE);

        String username = sharedpreferences.getString(  "username" , "");
        //
        editText = (EditText) findViewById(R.id.editTextonThird);
        String content = editText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("note", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);


        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd?yyyy HH:mm:SS");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_" + (SecondActivity.notes.size() +1);
            dbHelper.saveNotes(username, title, content, date);


        }else{
            title = "NOTE" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);

        }
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("message", username);
        startActivity(intent);
    }
}
