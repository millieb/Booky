package edu.utep.cs.cs4330.booky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBook extends AppCompatActivity {
    EditText edit_book_title;
    EditText edit_book_author;
    EditText edit_book_genre;
    EditText edit_book_isbn;
    Button save,cancel;

    List<Book> bookList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        edit_book_title = findViewById(R.id.edit_book_title);
        edit_book_author = findViewById(R.id.edit_book_author);
        edit_book_genre = findViewById(R.id.edit_book_genre);
        edit_book_isbn = findViewById(R.id.edit_book_isbn);

        save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = edit_book_title.getText().toString();
                String author = edit_book_author.getText().toString();
                String genre = edit_book_genre.getText().toString();
                String isbn = edit_book_isbn.getText().toString();

                Book book = new Book(title, author, genre, isbn);
                databaseReference.child("book").push().setValue(book);
                finish();
            }
        });

        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}