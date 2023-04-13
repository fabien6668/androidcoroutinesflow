package biz.ei6.alphormcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import biz.ei6.alphormcoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val vm = ViewModelProvider(this).get(CoViewModel::class.java)

        vm.data.observe(this) {
            binding.textMe.text = it
        }
            binding.clicMe.setOnClickListener {
            //vm.traitementLong()

                    vm.traitementLongFlowAsLiveData().observe(this){
                        Log.d("COR", "${Thread.currentThread().id}et observe !")
                        binding.textMe.text = it

                    }
                }


/*
        binding.clicMe.setOnClickListener {
            //vm.traitementLong()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.traitementLongFlow().collect {
                        binding.textMe.text = it
                    }
                }
            }

        }*/
    }


}