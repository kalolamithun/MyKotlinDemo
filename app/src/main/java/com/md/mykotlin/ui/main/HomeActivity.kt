package com.md.mykotlin.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.md.mykotlin.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        nav_view.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.nav_home)
                }
                R.id.nav_gallery -> {
                    Toast.makeText(this, "Gallery clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.nav_gallery)
                }
                R.id.nav_slideshow -> {
                    Toast.makeText(this, "Slide Show clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.nav_slideshow)
                }

            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        bnv_home_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.nav_home)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_gallery -> {
                    Toast.makeText(applicationContext, "Gallery", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.nav_gallery)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_slideshow -> {
                    Toast.makeText(applicationContext, "Slide Show", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.nav_slideshow)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    Toast.makeText(applicationContext, "Profile", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
