package com.example.jarviis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_name.*
import java.util.*

class Name : AppCompatActivity() {
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)


        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        })

        bt1_LOGIN.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)





            val spch:String=welcome.text.toString()+login_edittext_id.text.toString()

            mTTS.speak(spch, TextToSpeech.QUEUE_FLUSH, null)

        }
    }
}
