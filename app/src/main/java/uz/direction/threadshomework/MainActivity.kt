package uz.direction.threadshomework

import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.direction.threadshomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val handler1 = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val progress = bundle.getInt(R.string.progress.toString())
            binding.progress1.progress = progress
        }
    }

    private val handler2 = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val progress = bundle.getInt(R.string.progress.toString())
            binding.progress2.progress = progress
        }
    }

    private val handler3 = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val progress = bundle.getInt(R.string.progress.toString())
            binding.progress3.progress = progress
        }
    }

    private val handler4 = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val progress = bundle.getInt(R.string.progress.toString())
            binding.progress4.progress = progress
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var thread1 : Thread
        lateinit var thread2 : Thread
        lateinit var thread3 : Thread
        lateinit var thread4 : Thread

        var raceStarted = false

        binding.btnShuffle.setOnClickListener{
            if (!raceStarted) {
                thread1 = Thread {
                    val freq = (50..150).random().toLong()
                    val finish = (1..100).random()
                    for (i in 0 until finish) {
                        SystemClock.sleep(freq)
                        val message = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler1.sendMessage(message)
                    }
                }

                thread2 = Thread {
                    val freq = (50..150).random().toLong()
                    for (i in 0 until (1..100).random()) {
                        SystemClock.sleep(freq)
                        val message = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler2.sendMessage(message)
                    }
                }

                thread3 = Thread {
                    val freq = (50..150).random().toLong()
                    for (i in 0 until (1..100).random()) {
                        SystemClock.sleep(freq)
                        val message = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler3.sendMessage(message)
                    }
                }

                thread4 = Thread {
                    val freq = (50..150).random().toLong()
                    for (i in 0 until (1..100).random()) {
                        SystemClock.sleep(freq)
                        val message = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler4.sendMessage(message)
                    }
                }

                thread1.start()
                thread2.start()
                thread3.start()
                thread4.start()
                raceStarted = true
                binding.btnShuffle.text = "Reset"
            } else {
                if (thread1.isAlive || thread2.isAlive || thread3.isAlive || thread4.isAlive)
                {
                    Toast.makeText(applicationContext, "Race in progress", Toast.LENGTH_SHORT).show()
                } else {
                    raceStarted = false
                    binding.progress1.progress = 0
                    binding.progress2.progress = 0
                    binding.progress3.progress = 0
                    binding.progress4.progress = 0
                    binding.btnShuffle.text = "Start the race"
                }
            }
        }
    }
}
