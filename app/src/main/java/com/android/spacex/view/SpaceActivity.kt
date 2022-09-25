package com.android.spacex.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.spacex.R
import com.android.spacex.api.RetroManager
import com.android.spacex.api.source.remote.SpaceRepository
import com.android.spacex.databinding.ActivitySpaceBinding
import com.android.spacex.viewmodel.SpaceViewModel
import com.android.spacex.viewmodel.ViewModelFactory

class SpaceActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val viewModel by viewModels<SpaceViewModel> {
        ViewModelFactory(application, SpaceRepository(RetroManager.getRetrofitService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySpaceBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //Just to instantiate the view model as it is defined as lazy
        print(viewModel)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_item_detail) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_item_detail)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}