package io.runningwild.thewall.view

import android.app.DatePickerDialog
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
import java.util.Calendar.*
import javax.inject.Inject
import android.text.format.DateFormat


class MainFragment : DaggerFragment() {

    companion object {
        val TAG = MainFragment::class.java.simpleName

        const val DATE_FORMAT = "hh:mm:ss:SSS a, EEE dd MMM, yyyy"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StayViewModel

    private val disposable = CompositeDisposable()

    private lateinit var buttonAddStay: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        buttonAddStay = rootView.findViewById(R.id.button_add_stay)
        buttonAddStay.setOnClickListener { showDatePickerDialog() }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]
    }

    private fun addStay(entry: Date, leave: Date) {
        button_add_stay.isEnabled = false

        val dateFormat = DateFormat.getDateFormat(activity)
        disposable.add(
            viewModel.insert(dateFormat.format(entry), dateFormat.format(leave))
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

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(
            activity,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(YEAR, year)
                calendar.set(MONTH, month)
                calendar.set(DAY_OF_MONTH, dayOfMonth)
                val date = Date(calendar.timeInMillis)
                addStay(date, date)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()

    }
}