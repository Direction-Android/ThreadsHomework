package uz.direction.threadshomework

import android.os.*
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import uz.direction.threadshomework.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var stateChecker = false
        val progressBar = arrayListOf(
            binding.progress1,
            binding.progress2,
            binding.progress3,
            binding.progress4
        )

        binding.btnShuffle.setOnClickListener {
            if(!stateChecker) {
                for (i in 0 until progressBar.size) {
                    changeProgressBarProcess(progressBar[i])
                }
                stateChecker = true
                changeTextViewText(binding.btnShuffle, "STOP THE RACE")
            }
            else{
                changeTextViewText(binding.btnShuffle, "START THE RACE")

                compositeDisposable.clear()
                stateChecker = false
            }
        }

    }
    private fun changeTextViewText(textView: TextView, text: String){
            textView.text = text
    }
    private fun changeProgressBarProcess(progressBar: ProgressBar): Int {
        val disposable = ObservableService().getObservable().subscribe(){
            num-> progressBar.progress = num
        }
        compositeDisposable.add(disposable)
        return progressBar.progress
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
