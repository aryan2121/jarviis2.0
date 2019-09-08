package com.example.jarviis

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.repeat

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT=100
    //Text To Speech
    lateinit var mTTS: TextToSpeech

var x=0
    var check=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language
                mTTS.language = Locale.US
            }
        })
        var chngvoicetotext=chngevoice.text.toString()






        //speak button click
        button.setOnClickListener {
            //get text from edit text
            val toSpeak = editText.text.toString()
            if (toSpeak == ""){
                //if there is no text in edit text

                val se:String=textView3.text.toString()

                mTTS.speak(se, TextToSpeech.QUEUE_FLUSH, null)

                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
            }
            else{
                //if there is text in edit text
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
                mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
            }
        }

        //stop speaking button click
        help.setOnClickListener {
            val second:String=textView2.text.toString()

            mTTS.speak(second, TextToSpeech.QUEUE_FLUSH, null)
        }

        mic.setOnClickListener{
            Speakk()
        }

        repeat.setOnClickListener{
            x=1

            Speakk()
            repeattextview.text=blanktextview.text


           }

    }

    private fun Speakk() {
        //intent to show speech to text dialogbox
        val intnt= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intnt.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intnt.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intnt.putExtra(RecognizerIntent.EXTRA_PROMPT,"HI speak something PLEASE!")
        try{
            //if there is no error show speech to text dialog box
            startActivityForResult(intnt,REQUEST_CODE_SPEECH_INPUT)
        }catch(e: Exception){

            //there is any error get error message and show a toast
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            REQUEST_CODE_SPEECH_INPUT->{
                if (resultCode== Activity.RESULT_OK&& null!=data) {
                    //get test from result

                    val result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    repeattextview.text=result[0]
                }

            }
        }

        val final=repeattextview.text
        //whatsapp
        if (whatsappid.text.toString()==final) {
            var a =packageManager.getLaunchIntentForPackage("com.whatsapp")

            startActivity(a)
            check=2
        }

        //instagram
        if (instagramid.text.toString()==repeattextview.text.toString()) {
            var a =packageManager.getLaunchIntentForPackage("com.instagram.android")

            startActivity(a)
            check=2
        }

        //youtube
        if (youtubeid.text.toString()==repeattextview.text.toString()) {
            var a =packageManager.getLaunchIntentForPackage("com.google.android.yputube")

            startActivity(a)
            check=2
        }

        //facebook
        if (facebookid.text.toString()==repeattextview.text.toString()) {
            var a =packageManager.getLaunchIntentForPackage("com.facebooklite")

            startActivity(a)
            check=2
            Toast.makeText(this,"faceebookkkk",Toast.LENGTH_SHORT).show()
        }

         //LINK

        //google.com
        if(googleid.text.toString()==repeattextview.text.toString())
        {
            val intent=Intent(this,webview::class.java)

            val googlevar=googleid.text.toString()
            intent.putExtra("webVariable",googlevar)
            startActivity(intent)
        }
        //flipkart.com
        if(flipkartid.text.toString()==repeattextview.text.toString())
        {
            val intent=Intent(this,webview::class.java)

            val flipvar=flipkartid.text.toString()
            intent.putExtra("webVariable",flipvar)
            startActivity(intent)
        }






        //for repeate
        if(x==1){
            val third:String=blanktextview.text.toString()

            mTTS.speak(third, TextToSpeech.QUEUE_FLUSH, null)
        }

        //voice to text
        if(check==0)
        {
            blanktextview.text=repeattextview.text
        }


    }




}

