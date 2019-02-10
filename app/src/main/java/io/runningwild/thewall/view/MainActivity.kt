package io.runningwild.thewall.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.runningwild.thewall.R
import io.runningwild.thewall.viewmodel.StayViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StayViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[StayViewModel::class.java]

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_dashboard)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, DashboardFragment.newInstance(), DashboardFragment.TAG)
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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, DashboardFragment.newInstance(), DashboardFragment.TAG)
                    .commit()
            }
            R.id.nav_calendar -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, InputFragment.newInstance(), InputFragment.TAG)
                    .commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
