package id.ac.polinema.miniprojectabsensiguruprivate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.ac.polinema.miniprojectabsensiguruprivate.helper.Session;

public class StartAdminActivity extends AppCompatActivity {
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_admin);

        session = new Session(getApplicationContext());
    }

    public void showGuruOnClick(View view) {
        Intent guruintent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(guruintent);
    }

    public void showSiswaOnClick(View view) {
        Intent siswaintent = new Intent(getApplicationContext(), SiswaActivity.class);
        startActivity(siswaintent);
    }

    public void HandleShowRekapAbsen(View view){
        Intent rekapintent = new Intent(getApplicationContext(), RekapAbsenActivity.class);
        startActivity(rekapintent);
    }

    public void myOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FormGuruActivity.class);
        startActivity(intent);
    }

    public void siswaOnClick(View view){
        Intent siswa = new Intent(getApplicationContext(), FormSiswaActivity.class);
        startActivity(siswa);
    }

    public void myOnClickLogout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartAdminActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Yakin Anda ingin logout?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                session.logout();
                Toast.makeText(getApplicationContext(), "Logout Berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
