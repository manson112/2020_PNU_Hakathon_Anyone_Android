package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in_by_phone.*
import pnu.hakathon.anyone.R
import java.util.concurrent.TimeUnit

class SignInByPhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_by_phone)

        btn.setOnClickListener {
            val number = "+82" + edt.text.toString()
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                10,
                TimeUnit.SECONDS,
                this,
                callbacks
            )
        }

    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
            super.onCodeSent(p0, p1)
            Log.i("AUTH", "THE CODE HAS BEEN SENT")
        }

        override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
            Log.i("AUTH", "VERIFICATION COMPLETED")

        }

        override fun onVerificationFailed(p0: FirebaseException?) {
            Log.i("AUTH", "VERIFICATION FAILED")
            Log.i("AUTH", p0?.message)
        }
    }
}