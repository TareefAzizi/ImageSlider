package com.tareef.imageslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import com.tareef.imageslider.data.Image
import com.tareef.imageslider.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
    }

    private fun setupAdapter(){
        adapter = ImageAdapter(listOf(
                    Image(R.drawable.cat),
                    Image(R.drawable.cat2),
                    Image(R.drawable.cat),
                    Image(R.drawable.cat2),
                    Image(R.drawable.cat)
        ))

        binding.vpImages.let{ viewPager ->
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer{ page,position ->
                Log.d("debugging", position.toString())
                val r = 1 - abs(position)
                page.scaleY = 0.70f + 0.30f*r
            }

            viewPager.setPageTransformer(compositePageTransformer)
            lifecycleScope.launch(){
                repeatOnLifecycle(Lifecycle.State.RESUMED){
                   var position = 0
                    while (true){
                        delay(2000)
                        position = position +1
                        if(position == adapter.itemCount){
                            position = 0
                        }
                        viewPager.setCurrentItem(position, true)
                    }
                }
            }

        }
    }


}