package com.wajahatkarim3.zocdoc.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding
    lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    fun setupViews() {
        // ViewPager
        viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        bi.viewPager.adapter = viewPagerAdapter

        // Tabs
        bi.tabLayout.setupWithViewPager(bi.viewPager)
        var icons = arrayOf(R.drawable.ic_baseline_search_24, R.drawable.ic_baseline_access_time_24, R.drawable.ic_baseline_groups_24, R.drawable.ic_baseline_medical_services_24, R.drawable.ic_baseline_person_24)
        for (i in 0 until bi.tabLayout.tabCount) {
            bi.tabLayout.getTabAt(i)?.setIcon(icons[i])
        }
    }

    fun selectTab(tabIndex: Int) {
        if (tabIndex >= 0 && tabIndex < viewPagerAdapter.count)
        {
            bi.viewPager.setCurrentItem(tabIndex, true)
        }
    }

    inner class MainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount() = 5

        override fun getItem(position: Int): Fragment {
            var fragment = SearchFragment()
            if (position == 0) return SearchFragment()
            if (position == 1) return AppointmentsFragment()
            if (position == 2) return MedicalTeamFragment()
            if (position == 3) return RecommendationsFragment()
            if (position == 4) return ProfileFragment()
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return ""
        }
    }
}
