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

import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemRekapAbsen;
import id.ac.polinema.miniprojectabsensiguruprivate.helper.Session;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiClient;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekapAbsenActivity extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_absen);

        session = new Session(getApplicationContext());

        final RecyclerView rekapView = findViewById(R.id.rv_rekapabsen);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        final List rekap = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ItemRekapAbsen>> call = apiInterface.listRekap();

        call.enqueue(new Callback<List<ItemRekapAbsen>>() {
            @Override
            public void onResponse(Call<List<ItemRekapAbsen>> call, Response<List<ItemRekapAbsen>> response) {
                List<ItemRekapAbsen> rekapItems = response.body();
                if (response.isSuccessful()) {
                    for (ItemRekapAbsen item : rekapItems) {
                        rekap.add(new ItemRekapAbsen(item.getUsername(), item.getPassword(), item.getRekap()));
                    }

                    itemAdapter.add(rekap);
                    rekapView.setAdapter(fastAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    rekapView.setLayoutManager(layoutManager);
                } else {
                    Toast.makeText(getApplicationContext(), "Data gagal ditampilkan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemRekapAbsen>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }

    public void OnClickBackRekap(View view) {
        Intent brintent = new Intent(getApplicationContext(), StartAdminActivity.class);
        startActivity(brintent);
    }
}
