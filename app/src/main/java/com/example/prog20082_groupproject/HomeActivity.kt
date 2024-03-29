package com.example.prog20082_groupproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.example.prog20082_groupproject.R
import com.example.prog20082_groupproject.SharedPreferencesManager
import com.example.prog20082_groupproject.database.UserViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var userViewModel: UserViewModel

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userViewModel = UserViewModel(this.application)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_support
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete_account -> {
                val alertBuilder = AlertDialog.Builder(this)

                alertBuilder.setTitle("Sad to see you go")
                alertBuilder.setMessage("Are you sure you want to delete your account?")
                alertBuilder.setPositiveButton(android.R.string.yes){ dialog, which ->

                    this.deleteAccount()
                }

                alertBuilder.setNegativeButton(android.R.string.no){ dialog, which ->
                    Toast.makeText(this, "Thank you for staying with us.", Toast.LENGTH_LONG).show()
                }

                alertBuilder.show()
            }
            R.id.action_sign_out -> {
                this.finishAffinity()
                val signInIntent = Intent(this, LoginActivity::class.java)
                startActivity(signInIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAccount(){
        val email = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, "")
        if (email != null) {
            userViewModel.deleteUserByEmail(email).apply {  }
        }

        this.finishAffinity()
        SharedPreferencesManager.removeAll()
        val createAccountIntent = Intent(this, SignUp::class.java)
        startActivity(createAccountIntent)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}