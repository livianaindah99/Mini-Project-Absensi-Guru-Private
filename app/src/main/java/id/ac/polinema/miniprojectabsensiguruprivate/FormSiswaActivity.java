package id.ac.polinema.miniprojectabsensiguruprivate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiClient;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiInterface;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSiswaActivity extends AppCompatActivity {
    private Button btnAdd;
    private EditText inputnama, inputalamat, inputTglLahir, inputkelas;
    private RadioGroup radioGroup;
    private RadioButton selectedbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_siswa);

        btnAdd = findViewById(R.id.btn_tambah_data_siswa);
        inputnama = findViewById(R.id.edt_namasiswa);
        inputalamat = findViewById(R.id.edt_alamatsiswa);
        radioGroup = findViewById(R.id.group_jksiswa);
        inputTglLahir = findViewById(R.id.edt_tgllahir);
        inputkelas = findViewById(R.id.edt_kelas);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahDataSiswa();
            }
        });
    }

    private void tambahDataSiswa(){
        String nama = inputnama.getText().toString();
        String alamat = inputalamat.getText().toString();
        selectedbtn = findViewById(radioGroup.getCheckedRadioButtonId());
        String jenis_kelamin = "";
        if (selectedbtn != null) {
            jenis_kelamin = selectedbtn.getText().toString();
        }
        String tgllahir = inputTglLahir.getText().toString();
        String kelas = inputkelas.getText().toString();

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("nama", createPartFromString(nama));
        map.put("alamat", createPartFromString(alamat));
        map.put("jenis_kelamin", createPartFromString(jenis_kelamin));
        map.put("tanggal_lahir", createPartFromString(tgllahir));
        map.put("kelas", createPartFromString(kelas));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.tambahSiswa(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Data berhasil ditambah!", Toast.LENGTH_SHORT).show();
                    Intent mintent = new Intent(getApplicationContext(), SiswaActivity.class);
                    startActivity(mintent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private RequestBody createPartFromString(String description) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, description);
    }
}
