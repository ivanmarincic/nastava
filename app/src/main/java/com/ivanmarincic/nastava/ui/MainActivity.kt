package com.ivanmarincic.nastava.ui

import android.graphics.Rect
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.ivanmarincic.nastava.R
import com.ivanmarincic.nastava.dataaccess.api.FakultetiService
import com.ivanmarincic.nastava.dataaccess.api.StudijiService
import com.ivanmarincic.nastava.databinding.ActivityMainBinding
import com.ivanmarincic.nastava.util.SharedPreferenceStorage
import com.ivanmarincic.nastava.util.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationHost {

    companion object {

        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_classes,
            R.id.navigation_settings,
            R.id.navigation_details
        )

        private val STATE_FAKULTET_ID = "fakultet_id"
        private val STATE_STUDIJ_ID = "fakultet_id"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fakultetiService: FakultetiService

    @Inject
    lateinit var studijiService: StudijiService

    @Inject
    lateinit var preferenceStorage: SharedPreferenceStorage

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val drawer: DrawerLayout by lazy {
        binding.drawer
    }

    private val navigation: NavigationView by lazy {
        binding.navigation
    }

    private val navController: NavController by lazy {
        findNavController(R.id.navigationHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = viewModelProvider(viewModelFactory)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val lockMode = if (destination.id == R.id.navigation_classes) {
                DrawerLayout.LOCK_MODE_UNLOCKED
            } else {
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            }
            drawer.setDrawerLockMode(lockMode)
        }
        drawer.post {
            ViewCompat.setSystemGestureExclusionRects(
                drawer,
                listOf(Rect(drawer.left, drawer.top, drawer.measuredWidth / 2, drawer.bottom))
            )
        }
        viewModel.studijList.observe(this, Observer {
            val menu = navigation.menu
            menu.clear()
            viewModel.studijList.value!!.forEachIndexed { index, studij ->
                menu.add(1, index, index, studij.naziv)
            }
            menu.setGroupCheckable(1, true, true)
            if (it.isNotEmpty()) {
                val savedStudij = preferenceStorage.selectedStudij
                var index = if (savedStudij != -1L) {
                    it.indexOfFirst { item -> item.id == savedStudij }
                } else {
                    0
                }
                if (index == -1) {
                    index = 0
                }
                viewModel.setStudij(it[index])
                navigation.menu.getItem(index).isChecked = true
            }
        })
        viewModel.selectedFaculty.observe(this, Observer {
            preferenceStorage.selectedFakultet = it.id
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        })
        navigation.setNavigationItemSelectedListener {
            val value = viewModel.studijList.value!![it.itemId]
            viewModel.setStudij(value)
            preferenceStorage.selectedStudij = value.id
            navigation.setCheckedItem(it)
            return@setNavigationItemSelectedListener true
        }
        val savedFakultet = preferenceStorage.selectedFakultet
        if (savedFakultet != -1) {
            viewModel.setFakultetId(savedFakultet)
        }
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar, resId: Int) {
        toolbar.title = applicationContext.getString(resId)
        toolbar.setNavigationOnClickListener {
            if (drawer.getDrawerLockMode(navigation) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
                if (drawer.isDrawerOpen(navigation)) {
                    drawer.openDrawer(navigation)
                } else {
                    drawer.openDrawer(navigation)
                }
            } else {
                Toast.makeText(applicationContext, "Odaberite fakultet", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

interface NavigationHost {
    fun registerToolbarWithNavigation(toolbar: Toolbar, resId: Int)
}
