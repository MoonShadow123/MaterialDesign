package com.example.materialdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.materialdesign.R;


/**
 * The type Fruit activity.
 *
 * @author PengLiang
 * Time: 2019/8/27
 */
public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    private ImageView fruitImageView;
    private TextView fruitContentText;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        initViews();
        setViews();
    }

    private void setViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        collapsingToolbarLayout.setTitle(fruitName);
        fruitImageView.setImageResource(fruitImageId);

        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);
    }

    private void initViews() {
        fruitImageView = findViewById(R.id.fruit_image_view);
        fruitContentText = findViewById(R.id.fruit_content_text);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
