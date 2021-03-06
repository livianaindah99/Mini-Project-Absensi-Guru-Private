package id.ac.polinema.miniprojectabsensiguruprivate.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.miniprojectabsensiguruprivate.GuruActivity;
import id.ac.polinema.miniprojectabsensiguruprivate.R;

public class ItemDataSiswa extends AbstractItem<ItemDataSiswa, ItemDataSiswa.ViewHolder> {
    private String nim;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String tanggal_lahir;
    private String kelas;

    public ItemDataSiswa(String nim, String nama, String alamat, String jenis_kelamin, String tanggal_lahir, String kelas) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.kelas = kelas;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getKelas() {
        return kelas;
    }

    @NonNull
    @Override
    public ItemDataSiswa.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_datasiswa;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_siswa;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<ItemDataSiswa> {
        TextView nim, nama, alamat, jenis_kelamin, tanggal_lahir, kelas;

        public ViewHolder(View itemView) {
            super(itemView);
            nim = itemView.findViewById(R.id.txt_nim_siswa);
            nama = itemView.findViewById(R.id.txt_nama_siswa);
            alamat = itemView.findViewById(R.id.txt_alamat_siswa);
            jenis_kelamin = itemView.findViewById(R.id.txt_jk_siswa);
            tanggal_lahir = itemView.findViewById(R.id.txt_tgllahir_siswa);
            kelas = itemView.findViewById(R.id.txt_kelas_siswa);
        }

        @Override
        public void bindView(final ItemDataSiswa item, List<Object> payloads) {
            nim.setText(item.nim);
            nama.setText(item.nama);
            alamat.setText(item.alamat);
            jenis_kelamin.setText(item.jenis_kelamin);
            tanggal_lahir.setText(item.tanggal_lahir);
            kelas.setText(item.kelas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setCancelable(false);
                    builder.setMessage("Pilih " + item.getNama() + " Sebagai Siswa Private Anda?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(itemView.getContext(), GuruActivity.class);
                            intent.putExtra("nim", item.nim);
                            intent.putExtra("nama", item.nama);
                            intent.putExtra("kelas", item.kelas);
                            itemView.getContext().startActivity(intent);
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
            });
        }

        @Override
        public void unbindView(ItemDataSiswa item) {
            nim.setText(null);
            nama.setText(null);
            alamat.setText(null);
            jenis_kelamin.setText(null);
            tanggal_lahir.setText(null);
            kelas.setText(null);
        }
    }
}
