package com.shym.front_end;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shym.front_end.databinding.ActivityAddCarAgencyBinding;
import com.theartofdev.edmodo.cropper.CropImage;


public class AddCarAgencyActivity extends AppCompatActivity {
    private ActivityAddCarAgencyBinding binding;

    private Uri imageUri;
    private String imageUrl;

    private ImageView close;
    private ImageView imageAdded;
    private TextView post;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddCarAgencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        //close = findViewById(R.id.close);
        //imageAdded = findViewById(R.id.image_added);
        //post = findViewById(R.id.post);
        //description = findViewById(R.id.description);
        imageAdded=binding.imageadded;
        description=binding.description;

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCarAgencyActivity.this , MainActivity.class));
                finish();
            }
        });

        binding.completeaddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        binding.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().start(AddCarAgencyActivity.this);
            }
        });

        Spinner category = binding.category;
        String[] items = new String[]{"Category...", "sports", "minivan", "pickup"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        category.setAdapter(adapter);

        Spinner model = binding.model;
        items = new String[]{"Model...", "voloxwagen", "mercides", "range"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        model.setAdapter(adapter);
    }

    private void upload() {




        if (imageUri != null) {
        }

    }

    private String getFileExtension(Uri uri) {

        return MimeTypeMap.getSingleton().getExtensionFromMimeType(this.getContentResolver().getType(uri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            imageAdded.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}