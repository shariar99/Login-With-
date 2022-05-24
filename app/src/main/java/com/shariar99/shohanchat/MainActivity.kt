package com.shariar99.shohanchat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var emailEditText : EditText?= null
    var passEditText : EditText?= null
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);
        if (mAuth.currentUser != null){
            logIn()
        }

    }

    fun goClicked (view: View){
        //check if we can login the user
        mAuth.signInWithEmailAndPassword(emailEditText?.text.toString(), passEditText?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    logIn()
                } else {
                    // Sign up the user
                    mAuth.createUserWithEmailAndPassword(emailEditText?.text.toString(), passEditText?.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            logIn()
                        } else {
                            Toast.makeText(this,"Login Failed. Try Again.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

    }
    fun logIn(){
        // Move to next activity
        val intent = Intent(this,snapActivity::class.java)
        startActivity(intent)
    }

}