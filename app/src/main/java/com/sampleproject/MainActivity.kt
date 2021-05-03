package com.sampleproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.sampleproject.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(),UserClickListener {
    lateinit var response: UserResponse
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

       val json = loadJSONFromAsset()
        try {
            response = Gson().fromJson(json, UserResponse::class.java)
            val  userList = ArrayList<User>()
            for(i in response.circles.indices){
                userList.addAll(response.circles[i].users)
            }
            binding.userList = userList.distinct()
            binding.circleList = response.circles
            binding.listener = this as UserClickListener



        }catch (ex: Exception){
            ex.printStackTrace()
        }

    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("circles.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json =  String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    override fun onUserClick(user: User) {
        val circleList = ArrayList<Circle>()
        for(i in response.circles.indices){
            for (j in response.circles[i].users.indices){
                if(response.circles[i].users[j].name.equals(user.name)){
                    circleList.add(response.circles[i])
                    break
                }
            }

        }

        BindingAdapters.setEntries(binding.circle,circleList,R.layout.circle_layout)
    }
}