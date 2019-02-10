package io.runningwild.thewall.view

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.runningwild.thewall.Constants.VISA_WAIVER_PROGRAM_MAX_DAYS_IN_ONE_YEAR
import io.runningwild.thewall.R
import io.runningwild.thewall.Utils
import io.runningwild.thewall.persistence.Stay
import io.runningwild.thewall.viewmodel.StayViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import java.util.Calendar.getInstance


class DashboardFragment : DaggerFragment() {

    companion object {
        val TAG = DashboardFragment::class.java.simpleName

        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

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
    ): View? = inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
            viewModel.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { updateDashboard(it) },
                    { error -> Timber.e(error, "Unable to fetch stay.") })
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun updateDashboard(stayList: List<Stay>) {
        var dayCount = 0
        stayList.forEach {
            dayCount += Utils.getDayCount(this.activity!!, it)
        }
        text_day_count.text = "DAY COUNT: $dayCount"
        text_days_left.text = "DAYS LEFT: ${VISA_WAIVER_PROGRAM_MAX_DAYS_IN_ONE_YEAR - dayCount}"
    }
}