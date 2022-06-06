package hh.game.customaiwuclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aiwu.core.AIWUJNIUtils
import hh.game.customaiwuclient.Utils.AiwuGameUtils
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       Log.d("thelog::",AiwuGameUtils.getGameDetail("00040000000E9C00"))


    }
}