package com.example.imagesapp.views.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager
import com.appsologix.verepass.viewmodel.DashboardVM
import com.example.imagesapp.R
import com.example.imagesapp.adapters.CategoriesAdapter
import com.example.imagesapp.databinding.ActivityMainBinding
import com.example.imagesapp.models.ImagesResponse
import com.example.imagesapp.models.Categories
import com.example.imagesapp.views.custom.DialogUtils
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardVM: DashboardVM

    private var columns: Int = 3

    private lateinit var imagesData: ImagesResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dashboardVM = ViewModelProvider(this).get(DashboardVM::class.java)
        val horizontalLayoutManagaer =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.layoutManager = horizontalLayoutManagaer

        fetchData()

        binding.edtColumns?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.edtColumns?.getText().toString().length === 1) {
                    val value: Int = binding.edtColumns?.getText().toString().toInt()

                    if (value in 1..5) //size as per your requirement
                    {
                        initData(imagesData, value)

                    } else {
                        binding.edtColumns?.setError("No of columns must be between 1 to 5")
                    }
                }

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
    }

    private fun initData(imagesData: ImagesResponse, columns: Int) {
        if (imagesData != null) {
            //var array:JsonArray  = JsonArray(json)
            try {
                // val data = Gson().fromJson(json, MoviesResponse::class.java)
                Log.d("", "")

                val dataList: ArrayList<Categories> = ArrayList()

                var layoutId = R.layout.item_a
                var category = Categories(1, "Trending", imagesData.hits, layoutId)
                dataList.add(category)

                /*layoutId = R.layout.item_b
                category = MovieCategory(1, "Previews", imagesData.hits, layoutId)
                dataList.add(category)

                layoutId = R.layout.item_c
                category = MovieCategory(1, "Today Trending", imagesData.hits, layoutId)
                dataList.add(category)

                layoutId = R.layout.item_d
                category = MovieCategory(1, "Podcast", imagesData.hits, layoutId)
                dataList.add(category)

                layoutId = R.layout.item_e
                category = MovieCategory(1, "For You", imagesData.hits, layoutId)
                dataList.add(category)

                layoutId = R.layout.item_f
                category = MovieCategory(1, "Listen Again", imagesData.hits, layoutId)
                dataList.add(category)

                layoutId = R.layout.item_c
                category = MovieCategory(1, "Action Movies", imagesData.hits, layoutId)
                dataList.add(category)*/

                binding.rvCategories.layoutManager = LinearLayoutManager(this)
                binding.rvCategories.adapter = CategoriesAdapter(this, dataList, columns)
                binding.rvCategories.setNestedScrollingEnabled(false);
                Log.d("", "")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }//end of method initData....

    private fun fetchData() {

        val observer: Observer<ImagesResponse> = Observer { response ->
            response?.let { data ->
                initData(data, columns)
                imagesData = data
                binding.edtColumns.visibility = View.VISIBLE
            }
            DialogUtils.HideDialog()
        }

        DialogUtils.ShowProgress(this, "Loading Images....")
        dashboardVM.imagesResponse.observe(this@MainActivity, observer)
        dashboardVM.getImages("online")

    }//end of method fetchData....


}