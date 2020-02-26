package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    private ImageView avatarPicture;
    private Bitmap bitmap;

    // VARIABEL KEY
    public static final String FULLNAME_KEY = "fullname";
    public static final String EMAIL_KEY = "email";
    public static final String PSWD_KEY = "password";
    public static final String CONFPASWD_KEY = "confPassword";
    public static final String HOMEPAGE_KEY = "homePage";
    public static final String ABOUT_KEY = "about";

    //VARIABEL
    public TextInputEditText fullnameInput;
    public TextInputEditText emailInput;
    public TextInputEditText passwordInput;
    public TextInputEditText confirmPasswordInput;
    public TextInputEditText homepageInput;
    public TextInputEditText aboutInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = findViewById(R.id.text_fullname);
        emailInput = findViewById(R.id.text_email);
        passwordInput = findViewById(R.id.text_password);
        confirmPasswordInput = findViewById(R.id.text_confirm_password);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);
        avatarPicture = findViewById(R.id.image_profile);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarPicture.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleProfile(View view) {
        String fullnameText = fullnameInput.getText().toString();
        String emailText = emailInput.getText().toString();
        String passwordText = passwordInput.getText().toString();
        String confirmText = confirmPasswordInput.getText().toString();
        String homepageText = homepageInput.getText().toString();
        String aboutText = aboutInput.getText().toString();


        if(!(fullnameText).equals("") && !(emailText).equals("") && !(passwordText).equals("") && !(confirmText).equals("") && !(homepageText).equals("") && !(aboutText).equals("") && bitmap!= null){
            if((passwordText).equals(confirmText)){
                Intent intent = new Intent(this,ProfileActivity.class);
                intent.putExtra("FULLNAME_KEY", fullnameText);
                intent.putExtra("EMAIL_KEY", emailText);
                intent.putExtra("PASSWORD_KEY", passwordText);
                intent.putExtra("CONFIRM_KEY", confirmText);
                intent.putExtra("HOMEPAGE_KEY", homepageText);
                intent.putExtra("ABOUT_KEY", aboutText);
                startActivity(intent);
            }else{
                Toast.makeText(this, "PASSWORD dan CONFIRM PASSWORD HARUS SAMA !",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "DATA KURANG LENGKAP \n ISI SEMUA DATA !",Toast.LENGTH_SHORT).show();
        }
}

    public void handleChangePicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    }