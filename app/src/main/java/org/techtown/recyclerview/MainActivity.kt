package org.techtown.recyclerview

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.techtown.recyclerview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { Adapter() } // 늦은 초기화

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.user = User("지성", "킴") // 사용안하니 필요없음 리사이클러뷰에 꽂아주기때문

        binding.rv.adapter = adapter // 어댑터연결, 필수임 안하면 안뜸 연결안됌 그냥 커넥트시켜주는거

        ApiManager.getIntance().getUser("idstudent").enqueue(object : Callback<GetUserResp> {
            override fun onFailure(call: Call<GetUserResp>, t: Throwable) {
                Log.e("tag", "getUserResp 에러")
            }

            override fun onResponse(call: Call<GetUserResp>, response: Response<GetUserResp>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        adapter.submitList(listOf(it)) // 어댑터 메소드에 리스트 넘겨줌
                        adapter.notifyDataSetChanged() // 어댑터 갱신하라고 츠발라마 하는 코드

//                        val adapter = Adapter(listOf(it))
//                        binding.rv.adapter = adapter // 어댑터연결
                    }

//                    body.apply {
//                        Log.e("tag", "login : " + this?.login + ", url : " + this?.url)
//                    }
                }
            }
        })
        ApiManager.getIntance().getRepo("idstudent", "KaKaoMapAPITest").enqueue(object : Callback<GetRepoUrlResp> {
            override fun onFailure(call: Call<GetRepoUrlResp>, t: Throwable) {
                Log.e("tag", "getRepo 에러")
            }

            override fun onResponse(call: Call<GetRepoUrlResp>, response: Response<GetRepoUrlResp>) {
                if(response.isSuccessful){
                    val body = response.body()
                    body.apply {
                        Log.e("tag", "name : " + this?.name + ", fullName : " + this?.full_name + ", owner login : " + this?.owner + ", owner url : " + this?.owner?.url)
                    }
                }
            }
        })
    }
}