package edu.utep.cs.cs4330.booky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    ListView listView;
    List<Book> bookList;

    DatabaseReference bookDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        listView = findViewById(R.id.listView);
        bookList = new ArrayList<>();

        bookDbRef = FirebaseDatabase.getInstance().getReference("book");

        bookDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookList.clear();

                for(DataSnapshot bookDatasnap : dataSnapshot.getChildren()){
                    Book book = bookDatasnap.getValue(Book.class);
                    bookList.add(book);
                }

                BookAdapter adapter = new BookAdapter(BookActivity.this,bookList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Set itemLong Listener on listView item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                showUpdateDialog(book.getID(), book.getTitle());
                return false;
            }
        });
    }

    private void showUpdateDialog(String id, String title){
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog,null);

        mDialog.setView(mDialogView);

        //create views references
        final EditText editTextUpdateTitle = mDialogView.findViewById(R.id.editTextUpdateBookTitle);
        final EditText editTextUpdateAuthor = mDialogView.findViewById(R.id.editTextUpdateBookAuthor);
        final EditText editTextUpdateGenre = mDialogView.findViewById(R.id.editTextUpdateBookGenre);
        final EditText editTextUpdateISBN = mDialogView.findViewById(R.id.editTextUpdateBookISBN);
        Button buttonUpdate = mDialogView.findViewById(R.id.buttonUpdate);

        mDialog.setTitle("Updating " + title );
        final AlertDialog alertDialog = mDialog.create();
        mDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Updating data in database
                //Get values from view

                String newTitle = editTextUpdateTitle.getText().toString();
                String newAuthor = editTextUpdateAuthor.getText().toString();
                String newGenre = editTextUpdateGenre.getText().toString();
                String newISBN = editTextUpdateISBN.getText().toString();

                updateData(id, newTitle, newAuthor, newGenre, newISBN);

                Toast.makeText(BookActivity.this, "Record updated!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    private void updateData(String id, String title, String author, String genre, String isbn){
        //creating database reference
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("book").child(id);
        Book book = new Book(id, title, author, genre, isbn);
        DbRef.setValue(book);

    }
}