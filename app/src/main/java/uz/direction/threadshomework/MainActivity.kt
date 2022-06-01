package uz.direction.threadshomework

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import uz.direction.threadshomework.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var firstProgressBar: Thread? = null
    private var secondProgressBar: Thread? = null
    private var thirdProgressBar: Thread? = null
    private var fourthProgressBar: Thread? = null

    private val handler1 = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val sum = bundle.getInt(R.string.progress.toString())
            binding.progress1.progress = sum
        }

    }
    private val handler2 = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val sum = bundle.getInt(R.string.progress.toString())
            binding.progress2.progress = sum
        }

    }
    private val handler3 = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val sum = bundle.getInt(R.string.progress.toString())
            binding.progress3.progress = sum
        }

    }
    private val handler4 = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val sum = bundle.getInt(R.string.progress.toString())
            binding.progress4.progress = sum
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isRaceGoing = false
        binding.btnShuffle.setOnClickListener {
            if (!isRaceGoing) {
                firstProgressBar = Thread {
                    for (i in 0 until randomNumberGenerator()) {
                        SystemClock.sleep(randomNumberGenerator().toLong())
                        val msg = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler1.sendMessage(msg)
                    }
                }
                secondProgressBar = Thread {
                    for (i in 0 until randomNumberGenerator()) {
                        SystemClock.sleep(randomNumberGenerator().toLong())
                        val msg = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler2.sendMessage(msg)
                    }
                }
                thirdProgressBar = Thread {
                    for (i in 0 until randomNumberGenerator()) {
                        SystemClock.sleep(randomNumberGenerator().toLong())
                        val msg = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler3.sendMessage(msg)
                    }
                }
                fourthProgressBar = Thread {
                    for (i in 0 until randomNumberGenerator()) {
                        SystemClock.sleep(randomNumberGenerator().toLong())
                        val msg = Message().also {
                            val bundle = Bundle()
                            bundle.putInt(R.string.progress.toString(), i)
                            it.data = bundle
                        }
                        handler4.sendMessage(msg)
                    }
                }
                firstProgressBar?.start()
                secondProgressBar?.start()
                thirdProgressBar?.start()
                fourthProgressBar?.start()
                binding.btnShuffle.text = "Look at the results"
                isRaceGoing = true
            } else {
                    if (firstProgressBar!!.isAlive || secondProgressBar!!.isAlive
                        || thirdProgressBar!!.isAlive || fourthProgressBar!!.isAlive
                    ) {
                        firstProgressBar?.interrupt()
                        secondProgressBar?.interrupt()
                        thirdProgressBar?.interrupt()
                        fourthProgressBar?.interrupt()

                        firstProgressBar?.join()
                        secondProgressBar?.join()
                        thirdProgressBar?.join()
                        fourthProgressBar?.join()


                        binding.apply {
                            progress1.progress = 0
                            progress2.progress = 0
                            progress3.progress = 0
                            progress4.progress = 0
                        }
                    }
                binding.btnShuffle.text = "START THE RACE"
                isRaceGoing = false

                }
            }

        }

    }

    private fun randomNumberGenerator(): Int {
        return Random.nextInt(0, 100)
    }
