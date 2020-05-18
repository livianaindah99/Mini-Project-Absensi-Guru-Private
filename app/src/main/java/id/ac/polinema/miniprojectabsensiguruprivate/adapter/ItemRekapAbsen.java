package id.ac.polinema.miniprojectabsensiguruprivate.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.miniprojectabsensiguruprivate.R;

public class ItemRekapAbsen extends AbstractItem<ItemRekapAbsen, ItemRekapAbsen.ViewHolder> {
    private String username;
    private String password;
    private String rekap;

    public ItemRekapAbsen(String username, String password, String rekap) {
        this.username = username;
        this.password = password;
        this.rekap = rekap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRekap() {
        return rekap;
    }

    public void setRekap(String rekap) {
        this.rekap = rekap;
    }

    @NonNull
    @Override
    public ItemRekapAbsen.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_rekapabsen;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_rekap_absen;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<ItemRekapAbsen> {
        TextView username, password, rekap;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txt_rek_username);
            password = itemView.findViewById(R.id.txt_rek_pass);
            rekap = itemView.findViewById(R.id.txt_rekap);
        }

        @Override
        public void bindView(ItemRekapAbsen item, List<Object> payloads) {
            username.setText(item.username);
            password.setText(item.password);
            rekap.setText(item.rekap);
        }

        @Override
        public void unbindView(ItemRekapAbsen item) {
            username.setText(null);
            password.setText(null);
            rekap.setText(null);
        }
    }
}
