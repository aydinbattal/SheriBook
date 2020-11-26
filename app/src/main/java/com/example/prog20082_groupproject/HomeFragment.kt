package com.example.prog20082_groupproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
import com.example.prog20082_groupproject.BookingViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class HomeFragment : Fragment(), View.OnClickListener {

    private val TAG = this.toString()

    lateinit var edtName: EditText
    lateinit var edtStudentId: EditText
    lateinit var edtEmail: EditText
    lateinit var btnSave: Button
    lateinit var fabEditProfile: FloatingActionButton

    lateinit var userViewModel: UserViewModel
    lateinit var existingUser: User

    var currentUserEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, "")

    private lateinit var bookingViewModel : BookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val fabBook: FloatingActionButton = root.findViewById(R.id.fabBook)

        root.fabEditProfile.setOnClickListener(this)
        root.btnSave.setOnClickListener(this)

        fabBook.setOnClickListener {
//            findNavController().navigate(R.id.action_nav_home_to_add_parking_fragment)
        }

        edtName = root.edtName
        edtStudentId = root.edtStudentId
        edtEmail = root.edtEmail
        btnSave = root.btnSave
        fabEditProfile = root.fabEditProfile

        this.disableEdit()

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookingViewModel = BookingViewModel()
        userViewModel = UserViewModel(this.requireActivity().application)

        this.populateProfile()
    }

    override fun onResume() {
        super.onResume()

        bookingViewModel.getAllBookings()
    }

    fun disableEdit(){
        edtName.isEnabled = false
        edtStudentId.isEnabled = false
        edtEmail.isEnabled = false
    }

    fun enableEdit(){
        edtName.isEnabled = true
        edtStudentId.isEnabled = true
        edtEmail.isEnabled = true
    }

    fun populateProfile(){
        if (currentUserEmail != null){
            userViewModel.getUserByEmail(currentUserEmail!!)?.observe(this.requireActivity(), {matchedUser ->

                if (matchedUser != null) {

                    this.existingUser = matchedUser

                    Log.d("Home Fragment", "Matched user : " + matchedUser.toString())

                    edtName.setText(matchedUser.name)
                    edtStudentId.setText(matchedUser.studentId)
                    edtEmail.setText(matchedUser.email)
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            fabEditProfile.id -> {
                this.enableEdit()
                fabEditProfile.visibility = View.GONE
                btnSave.visibility = View.VISIBLE
            }
            btnSave.id -> {
                this.disableEdit()
                fabEditProfile.visibility = View.VISIBLE
                btnSave.visibility = View.GONE

                this.saveToDB()
            }
        }
    }


    private fun saveToDB(){
        this.existingUser.name = edtName.text.toString()
        this.existingUser.studentId = edtStudentId.text.toString()
        this.existingUser.email = edtEmail.text.toString()

        try{
            userViewModel.updateUser(existingUser)
        }catch (ex: Exception){
            Log.d("Home Fragment", ex.localizedMessage)
        }
    }
}