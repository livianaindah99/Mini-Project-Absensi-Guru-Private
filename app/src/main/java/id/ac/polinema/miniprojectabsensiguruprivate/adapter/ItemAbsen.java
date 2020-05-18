package id.ac.polinema.miniprojectabsensiguruprivate.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.miniprojectabsensiguruprivate.MapsLocActivity;
import id.ac.polinema.miniprojectabsensiguruprivate.R;

public class ItemAbsen extends AbstractItem<ItemAbsen, ItemAbsen.ViewHolder> {
    private String username;
    private String password;
    private String jam_login;
    private String jam_logout;
    private String tanggal;
    private double lokasi_latitude;
    private double lokasi_longitude;
    private String nim_siswa;
    private String nama_siswa;
    private String kelas_siswa;

    public ItemAbsen(String username, String password, String jam_login, String jam_logout, String tanggal, double lokasi_latitude, double lokasi_longitude, String nim_siswa, String nama_siswa, String kelas_siswa) {
        this.username = username;
        this.password = password;
        this.jam_login = jam_login;
        this.jam_logout = jam_logout;
        this.tanggal = tanggal;
        this.lokasi_latitude = lokasi_latitude;
        this.lokasi_longitude = lokasi_longitude;
        this.nim_siswa = nim_siswa;
        this.nama_siswa = nama_siswa;
        this.kelas_siswa = kelas_siswa;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJam_login() {
        return jam_login;
    }

    public String getJam_logout() {
        return jam_logout;
    }

    public String getTanggal() {
        return tanggal;
    }

    public double getLokasi_latitude() {
        return lokasi_latitude;
    }

    public double getLokasi_longitude() {

        return lokasi_longitude;
    }

    public String getNim_siswa() {
        return nim_siswa;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public String getKelas_siswa() {
        return kelas_siswa;
    }

    @NonNull
    @Override
    public ItemAbsen.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_absen;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_absen;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<ItemAbsen> {
        private TextView jam_login, jam_logout, tanggal, latitude, longitude, nim_siswa, nama_siswa, kelas_siswa;

        public ViewHolder(View itemView) {
            super(itemView);
            jam_login = itemView.findViewById(R.id.txt_jam_login);
            jam_logout = itemView.findViewById(R.id.txt_jam_logout);
            tanggal = itemView.findViewById(R.id.txt_tanggal);
            latitude = itemView.findViewById(R.id.txt_lokasi_latitude);
            longitude = itemView.findViewById(R.id.txt_lokasi_longitude);
            nim_siswa = itemView.findViewById(R.id.txt_nimssw);
            nama_siswa = itemView.findViewById(R.id.txt_namassw);
            kelas_siswa = itemView.findViewById(R.id.txt_kelasssw);
        }

        @Override
        public void bindView(final ItemAbsen item, List<Object> payloads) {
            jam_login.setText(item.jam_login);
            jam_logout.setText(item.jam_logout);
            tanggal.setText(item.tanggal);
            latitude.setText(String.valueOf(item.lokasi_latitude));
            longitude.setText(String.valueOf(item.lokasi_longitude));
            nim_siswa.setText(item.nim_siswa);
            nama_siswa.setText(item.nama_siswa);
            kelas_siswa.setText(item.kelas_siswa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, MapsLocActivity.class);
                    intent.putExtra("latitude", item.lokasi_latitude);
                    intent.putExtra("longitude", item.lokasi_longitude);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void unbindView(ItemAbsen item) {
            jam_login.setText(null);
            jam_logout.setText(null);
            tanggal.setText(null);
            latitude.setText(null);
            longitude.setText(null);
            nim_siswa.setText(null);
            nama_siswa.setText(null);
            kelas_siswa.setText(null);
        }
    }
}
