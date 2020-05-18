package id.ac.polinema.miniprojectabsensiguruprivate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import id.ac.polinema.miniprojectabsensiguruprivate.helper.Session;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiClient;
import id.ac.polinema.miniprojectabsensiguruprivate.restapi.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        session = new Session(getApplicationContext());

        final RecyclerView guruView = findViewById(R.id.rv_guru);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);

        final List guru = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ItemGuru>> call = apiInterface.getGuru();

        call.enqueue(new Callback<List<ItemGuru>>() {
            @Override
            public void onResponse(Call<List<ItemGuru>> call, Response<List<ItemGuru>> response) {
                List<ItemGuru> guruItems = response.body();
                if (response.isSuccessful()) {
                    for (ItemGuru item : guruItems) {
                        guru.add(new ItemGuru(item.getId_guru(), item.getNama(), item.getAlamat(), item.getJenis_kelamin(),
                                item.getNo_telp(), item.getFoto(), item.getUsername(), item.getPassword()));
                    }

                    itemAdapter.add(guru);
                    guruView.setAdapter(fastAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    guruView.setLayoutManager(layoutManager);
                } else {
                    Toast.makeText(getApplicationContext(), "Data gagal ditampilkan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemGuru>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }

    public void OnClickBackGuru(View view) {
        Intent bintent = new Intent(getApplicationContext(), StartAdminActivity.class);
        startActivity(bintent);
    }
}
