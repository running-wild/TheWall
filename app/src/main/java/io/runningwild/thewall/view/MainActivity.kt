package io.runningwild.thewall.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.runningwild.thewall.R
import io.runningwild.thewall.viewmodel.StayViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StayViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, MainFragment.newInstance(), MainFragment.TAG)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
            viewModel.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { Timber.d("Stay list: $it") },
                    { error -> Timber.e(error, "Unable to fetch stay.") })
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}