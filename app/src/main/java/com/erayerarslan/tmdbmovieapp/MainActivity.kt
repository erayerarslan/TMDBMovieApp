package com.erayerarslan.tmdbmovieapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.erayerarslan.tmdbmovieapp.databinding.ActivityMainBinding
import com.erayerarslan.tmdbmovieapp.ui.home.HomeFragment
import com.erayerarslan.tmdbmovieapp.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        createBottomNavigation()

    }
    private fun createBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation,navHostFragment.navController)
    }

    fun showBottomNavigationView() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    fun hideBottomNavigationView() {
        binding.bottomNavigation.visibility = View.GONE
    }







    override fun onSupportNavigateUp(): Boolean {

        return  navController.navigateUp() ||   super.onSupportNavigateUp()
    }

}