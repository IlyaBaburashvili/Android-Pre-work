package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.FileUtils;
import android.view.View;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   List <String> items;
   Button btnAdd;
   EditText etItem;
   RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd= findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);
        ItemsAdapter itemsAdapter;

        loadItems();

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){
          @Override
           public void onItemLongClicked(int position) {
              items.remove[position];
              itemsAdapter.notifyItemRemoved(position);
              Toast.makeText(getApplicationContext(), text: "Item was removed", Toast.LENGTH_SHORT).show();
              saveItems();
          }
        };

        final ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(context: this));

        btnAdd.setOnClickListener(new View.OnClickListener());

        public void onClick(View v){
            String todoItem = etItem.getText().toString();
            items.add(todoItem);
            itemsAdapter.notifyItemInserted(position: items.size()-1);
            etItem.setText("");
            Toast.makeText(getApplicationContext(), text: "Item was added", Toast.LENGTH_SHORT).show();
            saveItems();
        }

    });
}

private File getDataFile(){
    return new File(getFilesDir(), child: "data.txt");
}

private void loadItems() {
    try {
        items= new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));

    }
    catch (IDException e){
        Log.e(tag: "MainActivity", msg: "Error", e);
        items = new ArrayList<>;
    }
}

private void saveItems() {
    try {
        FileUtils.writeLines(getDataFile(), items);
    } catch (IDException e) {
        Log.e(tag:"MainActivity", msg:"Error", e);
    }
}
