package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in.*
import pnu.hakathon.anyone.R
import java.util.concurrent.TimeUnit

class SignInActivity : AppCompatActivity() {
    var mResendToken: PhoneAuthProvider.ForceResendingToken? = null
    var mVerificationId: String? = null
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sign_in)

        mAuth = FirebaseAuth.getInstance()
        signin_request_button.setOnClickListener {
            val number = "+82" + signin_phone.text.toString()
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                50,
                TimeUnit.SECONDS,
                this,
                callbacks
            )
        }

        signin_verify_button.setOnClickListener {
            verify(signin_verification_code.text.toString())
        }
    }

    fun verify(code: String) {
        mVerificationId?.let {
            val credential = PhoneAuthProvider.getCredential(it, code)
            signInWithPhoneAuthCredential(credential)
        }
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
            super.onCodeSent(p0, p1)
            Log.i("AUTH", "THE CODE HAS BEEN SENT")
            Log.i("AUTH", p0)
            mVerificationId = p0
            mResendToken = p1
        }

        override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
            Log.i("AUTH", "VERIFICATION COMPLETED")
            val code: String? = p0?.smsCode
            Log.i("AUTH", code)
            if (code != null) {

            }
        }

        override fun onVerificationFailed(e: FirebaseException?) {
            Log.i("AUTH", "VERIFICATION FAILED")
            Log.i("AUTH", e?.message)
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            val w = Log.w("AUTH", "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
            }

            // Show a message and update the UI
            // ...
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth?.let { auth ->
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("AUTH", "signInWithCredential:success")
                        val user = task.result?.user
//                        user?.uid
//                        user?.photoUrl
//                        user?.phoneNumber
//                        user?.getIdToken(true)?
                        finish()
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("AUTH", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                }
        }
    }
}