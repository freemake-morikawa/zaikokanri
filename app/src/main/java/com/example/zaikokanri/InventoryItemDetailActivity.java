package com.example.zaikokanri;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class InventoryItemDetailActivity extends AppCompatActivity {

    private static final int INTENT_INT_EXTRA_DEFAULT_VALUE = 0;
    private static final int REQUEST_GALLERY = 0;
    private static final String INTENT_TYPE_IMAGE = "image/*";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
    }

    private void initView() {
        final Intent intent = getIntent();

        // 詳細情報の表示
        final String time = intent.getStringExtra(Constants.INTENT_KEY_TIME_STRING);
        final TextView timeTextView = findViewById(R.id.detail_activity_time_text_view);
        timeTextView.setText(time);

        final int inventoryCount = intent.getIntExtra(Constants.INTENT_KEY_INVENTORY_COUNT, INTENT_INT_EXTRA_DEFAULT_VALUE);
        final TextView inventoryCountTextView = findViewById(R.id.detail_activity_inventory_count_text_view);
        inventoryCountTextView.setText(String.valueOf(inventoryCount));

        final String comment = intent.getStringExtra(Constants.INTENT_KEY_COMMENT);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(comment);

        imageView = findViewById(R.id.detail_activity_selected_image_view);
        final int position = intent.getIntExtra(Constants.INTENT_KEY_POSITION, INTENT_INT_EXTRA_DEFAULT_VALUE);
        if (MyApplication.getInstance().hasImage(position)) {
            final Bitmap bitmap = MyApplication.getInstance().getImage(position);
            imageView.setImageBitmap(bitmap);
        }

        final ImageButton photoLibraryImageButton = findViewById(
                R.id.detail_activity_photo_library_image_button
        );
        photoLibraryImageButton.setOnClickListener(view -> {
            final Intent intent1 = new Intent();
            intent1.setType(INTENT_TYPE_IMAGE);
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent1, REQUEST_GALLERY);
        });
    }

    // 選択された画像の画面表示
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                final InputStream inputStream = getContentResolver().openInputStream(data.getData());
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

                final int position = getIntent().getIntExtra(Constants.INTENT_KEY_POSITION, INTENT_INT_EXTRA_DEFAULT_VALUE);
                MyApplication.getInstance().setImage(position, bitmap);
            } catch (final FileNotFoundException e) {
                Log.d(Constants.EXCEPTION, e.toString());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
