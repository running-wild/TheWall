package io.runningwild.thewall.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.runningwild.thewall.R
import io.runningwild.thewall.viewmodel.StayViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StayViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]

        button_add_stay.setOnClickListener { addStay() }
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
            viewModel.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { Log.d(TAG, "Stay list: $it") },
                    { error -> Log.e(TAG, "Unable to fetch stay.", error)})
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun addStay() {
        button_add_stay.isEnabled = false

        val entryDate = Date().toString()
        val leaveDate = Date().toString()

        disposable.add(
            viewModel.insert(entryDate, leaveDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { button_add_stay.isEnabled = true },
                    { error ->
                        run {
                            button_add_stay.isEnabled = true
                            Log.e(TAG, "Unable to insert stay.", error)
                        }
                    })
        )
    }
}