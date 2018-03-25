package com.example.trihandayani.trihandayani_1202154321_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToDoList extends AppCompatActivity {

    EditText eTextName, eTextDesc, eTextPiority;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_list);

        eTextName = (EditText) findViewById(R.id.editText_activity_name);
        eTextDesc = (EditText) findViewById(R.id.editText_desc);
        eTextPiority = (EditText) findViewById(R.id.editText_priority);
        save = (Button) findViewById(R.id.button_add);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eTextName.getText().toString();
                String desc = eTextDesc.getText().toString();
                String priority = eTextPiority.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("activity_name", name);
                intent.putExtra("desc", desc);
                intent.putExtra("priority", priority);
                setResult(1, intent);
                finish();
            }
        });
    }


}
