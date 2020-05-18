package id.ac.polinema.miniprojectabsensiguruprivate.restapi;

import java.util.List;
import java.util.Map;

import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemAbsen;
import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemDataSiswa;
import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemGuru;
import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemRekapAbsen;
import id.ac.polinema.miniprojectabsensiguruprivate.adapter.ItemSiswa;
import id.ac.polinema.miniprojectabsensiguruprivate.model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("loginAdmin")
    Call<ResponseBody> loginAdmin(@Body User user);

    @POST("loginGuru")
    Call<ResponseBody> loginGuru(@Body User user);

    @GET("guru")
    Call<List<ItemGuru>> getGuru();

    @GET("guru")
    Call<List<ItemGuru>> getGuruByUsername(
            @Query("username") String username
    );

    @GET("absenGuru")
    Call<List<ItemAbsen>> getAbsenByUsername(
            @Query("username") String username
    );

    @POST("absenGuru")
    Call<ResponseBody> absenGuru(@Body ItemAbsen absen);

    @Multipart
    @POST("guru")
    Call<ResponseBody> tambahGuru(
            @Part MultipartBody.Part photo,
            @PartMap Map<String, RequestBody> text);

    @Multipart
    @POST("siswa")
    Call<ResponseBody> tambahSiswa(
            @PartMap Map<String, RequestBody> text);

    @GET("siswa")
    Call<List<ItemSiswa>> getSiswa();

    @GET("siswa")
    Call<List<ItemDataSiswa>> getDataSiswa();

    @GET("siswa")
    Call<List<ItemSiswa>> getSiswaByNama(
            @Query("nama") String nama
    );

    @GET("rekapAbsen")
    Call<List<ItemGuru>> getRekapByUsername(
            @Query("username") String username
    );

    @GET("rekapAbsen")
    Call<List<ItemRekapAbsen>>listRekap();

}
