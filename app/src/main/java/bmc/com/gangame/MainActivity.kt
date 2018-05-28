package bmc.com.gangame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import bmc.com.gangame.fragments.deals.DealsFragment
import bmc.com.gangame.fragments.owned.OwnedFragment
import bmc.com.gangame.fragments.rated.TopRatedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val DEFAULT_OPTION: Int = R.id.action_deals
    }

    val fragments: HashMap<Int, Fragment> = hashMapOf(
            Pair(R.id.action_deals, DealsFragment()),
            Pair(R.id.action_top_rated, TopRatedFragment()),
            Pair(R.id.action_most_owned, OwnedFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        navigationView.selectedItemId = DEFAULT_OPTION
        navigationView.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment? = fragments[item.itemId]

            if (fragment != null) {
                replaceFragment(fragment)
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragmentContainer, fragment)
                .commit()
    }

    fun initView() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.flFragmentContainer)
        if (currentFragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.flFragmentContainer, defaultFragment())
                    .commit()
        }
    }

    private fun defaultFragment() = fragments[DEFAULT_OPTION]
}
