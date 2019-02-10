package io.runningwild.thewall.view

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.runningwild.thewall.R
import io.runningwild.thewall.viewmodel.StayViewModel
import javax.inject.Inject

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
}