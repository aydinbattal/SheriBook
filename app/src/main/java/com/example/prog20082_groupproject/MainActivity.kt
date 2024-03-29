package com.example.prog20082_groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.prog20082_groupproject.database.Booking
import com.example.prog20082_groupproject.database.BookingViewModel
import com.example.prog20082_groupproject.database.User

class MainActivity : AppCompatActivity() {
    lateinit var bookingViewModel : BookingViewModel
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo: find a way to prepopulate, this doesnt work
        bookingViewModel = BookingViewModel(this.application)
        prePopulateBooking()

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.

    }

    private fun prePopulateBooking(){
        bookingViewModel.allBooking.observe(this@MainActivity,{
            var num = 0;
            for(booking in it){
                num++
            }
            if(num != 30){
                bookingViewModel.insertAll(Booking("100","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("101","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("102","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("103","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("104","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("105","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("106","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("107","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("108","Trafalgar","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("109","Trafalgar","",0,"","","","",""))

                bookingViewModel.insertAll(Booking("100","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("101","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("102","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("103","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("104","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("105","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("106","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("107","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("108","Davis","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("109","Davis","",0,"","","","",""))

                bookingViewModel.insertAll(Booking("100","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("101","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("102","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("103","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("104","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("105","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("106","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("107","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("108","Hazel McCallion","",0,"","","","",""))
                bookingViewModel.insertAll(Booking("109","Hazel McCallion","",0,"","","","",""))
            }
        })
    }
}