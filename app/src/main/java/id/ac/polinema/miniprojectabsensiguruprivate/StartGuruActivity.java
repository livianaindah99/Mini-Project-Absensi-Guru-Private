package id.ac.polinema.miniprojectabsensiguruprivate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemDataSiswa;
import id.ac.polinema.miniprojectabsensiguruprivate.helper.Session;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiClient;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartGuruActivity extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_guru);

        session = new Session(getApplicationContext());

        final RecyclerView datasiswaView = findViewById(R.id.rv_datasiswa);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        final List datasiswa = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ItemDataSiswa>> call = apiInterface.getDataSiswa();

        call.enqueue(new Callback<List<ItemDataSiswa>>() {
            @Override
            public void onResponse(Call<List<ItemDataSiswa>> call, Response<List<ItemDataSiswa>> response) {
                List<ItemDataSiswa> datasiswaItems = response.body();
                if (response.isSuccessful()) {
                    for (ItemDataSiswa item : datasiswaItems) {
                        datasiswa.add(new ItemDataSiswa(item.getNim(), item.getNama(), item.getAlamat(),
                                item.getJenis_kelamin(), item.getTanggal_lahir(), item.getKelas()));
                    }

                    itemAdapter.add(datasiswa);
                    datasiswaView.setAdapter(fastAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    datasiswaView.setLayoutManager(layoutManager);
                } else {
                    Toast.makeText(getApplicationContext(), "Data gagal ditampilkan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemDataSiswa>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }
}
