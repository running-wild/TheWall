package io.runningwild.thewall.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.runningwild.thewall.R
import io.runningwild.thewall.viewmodel.StayViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import javax.inject.Inject

class InputFragment : DaggerFragment() {

    companion object {
        val TAG = InputFragment::class.java.simpleName

        private const val DATE_FORMAT = "EEE dd MMM, yyyy"

        fun newInstance(): InputFragment {
            return InputFragment()
        }
    }

    private val entryDate: Calendar = getInstance()
    private val leaveDate: Calendar = getInstance()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StayViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_text_input_entry.inputType = EditorInfo.TYPE_NULL
        edit_text_input_entry.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                showDatePickerDialog(edit_text_input_entry, entryDate)
            }
        }
        edit_text_input_leave.inputType = EditorInfo.TYPE_NULL
        edit_text_input_leave.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                showDatePickerDialog(edit_text_input_leave, leaveDate)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.action_save -> {
                addStay(entryDate, leaveDate)
                true
            }
            else -> super.onOptionsItemSelected(item)
        })
    }

    private fun addStay(entry: Calendar, leave: Calendar) {
        val dateFormat = DateFormat.getDateFormat(activity)
        disposable.add(
            viewModel.insert(dateFormat.format(entry.time), dateFormat.format(leave.time))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { },
                    { error ->
                        run {
                            Timber.e(error, "Unable to insert stay.")
                        }
                    })
        )
    }

    private fun showDatePickerDialog(view: EditText, date: Calendar,
                                     minDate: Calendar? = null, maxDate: Calendar? = null) {
        val dialog = DatePickerDialog(
            activity as Context,
            DatePickerDialog.OnDateSetListener { _, entryYear, entryMonth, entryDayOfMonth ->
                date.set(YEAR, entryYear)
                date.set(MONTH, entryMonth)
                date.set(DAY_OF_MONTH, entryDayOfMonth)

                view.setText(SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date.time))
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)
        )
        if (minDate != null) {
            dialog.datePicker.minDate = minDate.timeInMillis
        }
        if (maxDate != null) {
            dialog.datePicker.maxDate = maxDate.timeInMillis
        }
        dialog.show()
    }
}