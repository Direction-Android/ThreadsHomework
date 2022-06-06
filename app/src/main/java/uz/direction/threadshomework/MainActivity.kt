package uz.direction.threadshomework

import android.os.*
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import uz.direction.threadshomework.databinding.ActivityMainBinding
import kotlin.math.log
import kotlin.math.max
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val compositeDisposable :CompositeDisposable = CompositeDisposable()
    private var finishLine = 0
    private var longestProgressIndicator = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShuffle.setOnClickListener{
            val progressBars = arrayListOf<LinearProgressIndicator>(
                binding.progress1,
                binding.progress2,
                binding.progress3,
                binding.progress4)

            var disposable : Disposable? = null

            if (longestProgressIndicator >= finishLine) {
                longestProgressIndicator = 0
                finishLine = 0
                binding.btnShuffle.text = "Reset and Start"
                for (i in 0 until progressBars.size) {
                    disposable = Service().getObserver().subscribe { progress ->
                        progressBars[i].progress = progress.progress
                        finishLine = max(finishLine, progress.finishLine - 1)
                        longestProgressIndicator = max(longestProgressIndicator, progress.progress)
                        Log.d("TAG", "longest indicator: $longestProgressIndicator, finishline: $finishLine")
                    }
                    if (disposable != null) {
                        compositeDisposable.add(disposable)
                    }
                }
            } else {
                Toast.makeText(this, "Race is currently running!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
