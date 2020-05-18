package id.ac.polinema.miniprojectabsensiguruprivate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemGuru;
import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemSiswa;
import id.ac.polinema.miniprojectabsensiguruprivate.helper.Session;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiClient;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiswaActivity extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        session = new Session(getApplicationContext());

        final RecyclerView siswaView = findViewById(R.id.rv_siswa);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        final List siswa = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ItemSiswa>> call = apiInterface.getSiswa();

        call.enqueue(new Callback<List<ItemSiswa>>() {
            @Override
            public void onResponse(Call<List<ItemSiswa>> call, Response<List<ItemSiswa>> response) {
                List<ItemSiswa> siswaItems = response.body();
                if (response.isSuccessful()) {
                    for (ItemSiswa item : siswaItems) {
                        siswa.add(new ItemSiswa(item.getNim(), item.getNama(), item.getAlamat(), item.getJenis_kelamin(),
                                item.getTanggal_lahir(), item.getKelas()));
                    }

                    itemAdapter.add(siswa);
                    siswaView.setAdapter(fastAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    siswaView.setLayoutManager(layoutManager);
                } else {
                    Toast.makeText(getApplicationContext(), "Data gagal ditampilkan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemSiswa>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }

    public void OnClickBackSiswa(View view) {
        Intent bsintent = new Intent(getApplicationContext(), StartAdminActivity.class);
        startActivity(bsintent);
    }
}
