package com.example.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.Photos
import android.view.LayoutInflater
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaper.API.ApiClient
import com.example.wallpaper.API.Apiinterface
import com.example.wallpaper.Adapter.WallpaperAdapter
import com.example.wallpaper.Model.PhotoModel
import com.example.wallpaper.Model.PhotosItem
import com.example.wallpaper.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: WallpaperAdapter
    var auth = "yE3MfGPhMucNhYqYSlWrjwalrPvk92c6vlOfXxUJdkLu345OGHPhtFnL"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = WallpaperAdapter()
        binding.btnsearch.setOnClickListener {
            callApi()
        }
    }

    private fun callApi() {

        var txt = binding.edtsearch.text.toString()

        var api: Apiinterface = ApiClient.getApiClient().create(Apiinterface::class.java)
        api.getphotos(auth, txt).enqueue(object : Callback<PhotoModel> {

            override fun onResponse(call: Call<PhotoModel>, response: Response<PhotoModel>) {
                if (response.isSuccessful) {
                    var photos = response.body()?.photos
                    adapter.setPhotos(photos as List<PhotosItem>?)
                    binding.rcvphotos.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.rcvphotos.adapter = adapter

                }


            }

            override fun onFailure(call: Call<PhotoModel>, t: Throwable) {

            }

        })


    }
}
