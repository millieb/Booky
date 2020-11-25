package edu.utep.cs.cs4330.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class AddBook extends AppCompatActivity {
    EditText edit_book_title;
    EditText key;
    Button save;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Firebase.setAndroidContext(this);
        //firebase = new Firebase("https://booky-a68b3.firebaseio.com/");
        firebase = new Firebase("https://booky-a68b3.firebaseio.com/Book");

        edit_book_title = findViewById(R.id.edit_book_title);
        save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edit_book_title.getText().toString();
                firebase.push().setValue(title);
            }
        });
    }
}