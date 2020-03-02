package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    TextView textView2;
    SharedPreferences sharedpreferences;
    public static ArrayList<Note> notes = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String str;
        String usernameKey = "username";
        sharedpreferences = getSharedPreferences( "c.sakshi.lab5" , Context.MODE_PRIVATE);


        textView2 = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        str = intent.getStringExtra("message");
        textView2.setText("Welcome " + str);

        // Getting SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("note", Context.MODE_PRIVATE,null);

        DBHelper as = new DBHelper(sqLiteDatabase);
        notes = as.readNotes( str );


        ArrayList<String> displayNotes = new ArrayList<>();

        for(Note note : notes){
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        //5. Use ListView view to display notes on screen.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        //6. Add onItemClicklister for listView item, anote in out case.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // When the user click the List view
                //Initialise intent to take user to third activity (Note activity in this case).
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("noteid" , position);
                startActivity(intent);
            }

        });

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
            sharedpreferences.edit().remove(MainActivity.usernameKey).apply();
            startActivity(intent);
            return true;

        }

        if(item.getItemId() == R.id.note){ // When the user click menu
            Intent intent = new Intent( this , NoteActivity.class);
            startActivity(intent);
            return true;
        }

        return false;


    }


}
