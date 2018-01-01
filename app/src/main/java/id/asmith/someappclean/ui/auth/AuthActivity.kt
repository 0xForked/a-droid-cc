package id.asmith.someappclean.ui.auth

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import id.asmith.someappclean.R
import id.asmith.someappclean.ui.auth.fragment.AuthSigninFragment

/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Check that the activity is using the layout
        if (savedInstanceState == null) {

            replaceFragment(AuthSigninFragment())

        }

    }


    //Function fragment transition
    private fun replaceFragment (fragment: Fragment, cleanStack: Boolean = false) {

        val ft = supportFragmentManager.beginTransaction()

        if (cleanStack) {
            clearBackStack()
        }

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.replace(R.id.fragment_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    // ini for mo clear dpe balik kanan

    private fun clearBackStack() {

        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /*
    * Ini fungsi supaya pass tekan balik,
    * langsung dismiss dari kalau nda pake ini,
    * dia pe fragment mo ta ilang mar dpe activity
    * masih on jadi tetap aktif dia mar nda ada tampilan
    * apa2 atau white screen dari di activity_auth dia cuma ada
    * frame layout yang siap mo replace dengan fragment2 pe layout
    */
    override fun onBackPressed() {

        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }

    }

}