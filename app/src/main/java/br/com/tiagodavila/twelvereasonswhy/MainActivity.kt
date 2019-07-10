package br.com.tiagodavila.twelvereasonswhy

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

class MainActivity : AppCompatActivity(), BaseFragment.IMonthHolder {

    val PREFS_FILENAME = "br.com.tiagodavila.twelvereasonswhy"
    val IS_FIRST_ACCESS = "first_access"
    var sharedPreference: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreference = getSharedPreferences(PREFS_FILENAME, 0)
        var isFirstAccess = sharedPreference!!.getBoolean(IS_FIRST_ACCESS, true)
        if (isFirstAccess) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("É muito bom estar contigo!")
            builder.setMessage("São 12 meses pra fazermos 1 ano. Cada mês esse app terá uma atualização. 12 razões de estarmos juntos :)")
            builder.setPositiveButton("Ok") { dialog, _ ->
                sharedPreference!!.edit().putBoolean(IS_FIRST_ACCESS, false).apply()
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

        launchMonthHolder()
    }

    private fun launchMonthHolder() = launchChosenMonth(MonthHolderFragment())

    private fun launchChosenMonth(fragment: BaseFragment) {
        fragment.listener = this
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.app_main_container, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }

    private fun launchChosenMonth(fragment: BaseFragment, sharedElement: View) {
        fragment.listener = this
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(sharedElement, ViewCompat.getTransitionName(sharedElement)!!)
            .replace(R.id.app_main_container, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }

    override fun onMonthChosen(fragment: BaseFragment) {
        launchChosenMonth(fragment)
    }

    override fun onMonthChosen(fragment: BaseFragment, sharedElement: View) {
        launchChosenMonth(fragment, sharedElement)
    }
}
