package com.hackyeon.realm_observe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.hackyeon.realm_observe.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        regObserve()
        binding.insertButton.setOnClickListener(this)
        binding.deleteButton.setOnClickListener(this)
    }


    private fun regObserve(){
        viewModel.getMyDataLiveData()?.observe(this) {
            it.forEach { data ->
                Log.d("hack", "name: ${data.name} - age: ${data.age}")
            }
        }
    }

    override fun onClick(view: View?) {
        val name = binding.nameEditText.text.toString()
        val age = binding.ageEditText.text.toString().toInt()
        when(view?.id) {
            R.id.deleteButton -> viewModel.deleteMyData(name, age)
            R.id.insertButton -> viewModel.insertMyData(name, age)
        }
    }
}