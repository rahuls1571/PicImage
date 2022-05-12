package com.example.picsumimage

import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumimage.network.InternetReceiver
import com.example.picsumimage.network.NetworkApplication
import com.example.picsumimage.network.NoInternetConnection
import com.example.picsumimage.pagination.ItemLoadingState
import com.example.picsumimage.pagination.ItemPagingAdapter
import com.example.picsumimage.viewModel.PicSumViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() , InternetReceiver.InternetConnectListner{


    // Initialize Variable
    private lateinit var recyclerview      : RecyclerView
    private lateinit var progressBar       : ProgressBar
    private lateinit var itemPagingAdapter : ItemPagingAdapter
    private lateinit var layout            : GridLayoutManager
    private lateinit var viewModel         : PicSumViewModel
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize
        recyclerview = findViewById(R.id.recycle_view)
        progressBar  = findViewById(R.id.progressbar)
        // Check Network Connection!
        CheckInternetConnection()
        // Function call !
        init()

    }


    private fun CheckInternetConnection(){
        // Get boolean Network connectivity Form Broadcast Receiver
        val isConnected : Boolean = InternetReceiver.isConnected()
        // Condition check if network not available then Open No InternetConnection Activity
        if(!isConnected){
            val intent = Intent(this, NoInternetConnection::class.java)
            startActivity(intent)
        }
    }


    fun init(){
        // RecycleView
        // GridLayout  Initialize
        layout = GridLayoutManager(this, 2)
        // GridLayout  Set Orientation
        layout.orientation = GridLayoutManager.VERTICAL
        // RecycleView Set Layout
        recyclerview.layoutManager = layout
        // Adapter Initialize
        itemPagingAdapter = ItemPagingAdapter(this)
        // Set Adapter
        recyclerview.adapter = itemPagingAdapter.withLoadStateHeaderAndFooter(
            // Set Header and Footer in RecycleView
            header = ItemLoadingState { itemPagingAdapter.retry() },
            footer = ItemLoadingState { itemPagingAdapter.retry() }
        )

        // ViewMode  Initialize
        viewModel = ViewModelProvider(this).get(PicSumViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            // Fetch API
            viewModel.getAllPhotos().collectLatest {

                // Set RecycleView Visible true
                recyclerview.visibility = View.VISIBLE
                // Set RecycleView Visible False
                progressBar.visibility = View.GONE
                // Set Data to Adapter
                itemPagingAdapter.submitData(it)
            }
        }
    }

    // Menu Options
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Add  menuInflater / Menu layout
        menuInflater.inflate(R.menu.menu, menu)
        // Menu  Initialize
        val item = menu.findItem(R.id.theme_switch)
        // Set Action view Layout / day and night menu layout
        item.setActionView(R.layout.day_night_menu_switch_layout)
        // Switch Initialize
        val mySwitch: SwitchCompat = item.actionView.findViewById(R.id.switch_button)
        // SharedPreferences  Initialize
        sharedPreferences = getSharedPreferences("night", 0)
        val b: Boolean = sharedPreferences.getBoolean("night_mode", true)
        if (b) {
            // Set Night Mode YES
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            // Set Switch True
            mySwitch.isChecked = true
        }
        mySwitch.setOnCheckedChangeListener { buttonView, isChecked -> // do something based on isChecked
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                mySwitch.isChecked = true
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putBoolean("night_mode", true)
                editor.apply()
                // Toast Show Day/Night Mode
                Toast.makeText(this@MainActivity, "Night Mode", Toast.LENGTH_SHORT).show()
            } else {
                // Set Night Mode OFF
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                // Set Switch True
                mySwitch.isChecked = false
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putBoolean("night_mode", false)
                editor.apply()
                // Toast Show Day/Night Mode
                Toast.makeText(this@MainActivity, "Day Mode", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }



    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.VISIBLE

        // Intent Filter Initialize
        val intentFilter = IntentFilter()
        // Set CONNECTIVITY_ACTION
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        // InternetReceiver Initialize
        val internetReceiver : InternetReceiver = InternetReceiver()
        // RegisterReceiver
        registerReceiver(internetReceiver, intentFilter)
        // Set Connectivity Listner
        NetworkApplication.getInstance().SetConnectivityListner(this)

    }

    override fun OnConnected(isConnected: Boolean) {
        TODO("Not yet implemented")
    }


}