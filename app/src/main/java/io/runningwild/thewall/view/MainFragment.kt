package io.runningwild.thewall.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.runningwild.thewall.R
import io.runningwild.thewall.viewmodel.StayViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    companion object {
        val TAG = MainFragment::class.java.simpleName

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StayViewModel

    private val disposable = CompositeDisposable()

    private lateinit var buttonAddStay: FloatingActionButton

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        buttonAddStay = rootView.findViewById(R.id.button_add_stay)
        buttonAddStay.setOnClickListener { addStay() }

        return rootView
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
                            Timber.e(error, "Unable to insert stay.")
                        }
                    })
        )
    }
}