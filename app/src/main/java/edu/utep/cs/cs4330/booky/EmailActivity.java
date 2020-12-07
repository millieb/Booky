package edu.utep.cs.cs4330.booky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EmailActivity extends AppCompatActivity {

    EditText email, subject, message;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        email = findViewById(R.id.et_to);
        subject = findViewById(R.id.et_subject);
        message = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.bt_send);

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() &&
                !subject.getText().toString().isEmpty() && !message.getText().toString().isEmpty()){

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());

                    intent.setType("message/rfc822");
                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    } else {
                        Toast.makeText(EmailActivity.this, "There is " +
                                "no application that supports this action.", Toast.LENGTH_SHORT).show();
                    }

                } else{
                    Toast.makeText(EmailActivity.this, "Please" +
                            "fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}