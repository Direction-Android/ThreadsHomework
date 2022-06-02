package uz.direction.threadshomework

import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import uz.direction.threadshomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShuffle.setOnClickListener {
            Thread {
                runProgressBar(binding.progress1, binding.textView)

            }.start()
            Thread {
                runProgressBar(binding.progress2, binding.textView2)
            }.start()
            Thread {
                runProgressBar(binding.progress3, binding.textView3)
            }.start()
            Thread {
                runProgressBar(binding.progress4, binding.textView4)
            }.start()
        }
    }

    private fun runProgressBar(progressBar: ProgressBar, textView: TextView) {
        var i = 0
        val random = (Math.random()) * 100
        while (i < random) {
            i += 1
            handler.post {
                progressBar.progress = i
                textView.text =
                    i.toString() + "/" + progressBar.max + "    random num - " + (random.toInt() + 1)
            }
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
