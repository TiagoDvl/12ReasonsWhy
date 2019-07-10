package br.com.tiagodavila.twelvereasonswhy


import android.view.View
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    val MONTH_2_NUMBER_OF_VISITS = "MONTH_2_VISTIS"
    private val PREFS_FILENAME = "br.com.tiagodavila.twelvereasonswhy"

    lateinit var listener: IMonthHolder

    interface IMonthHolder {
        fun onMonthChosen(fragment: BaseFragment)
        fun onMonthChosen(fragment: BaseFragment, sharedElement: View)
    }

    fun getSharedPrefByKey(key: String) : Int {
        val sharedPreference = context!!.getSharedPreferences(PREFS_FILENAME, 0)
        return sharedPreference.getInt(key, 0)
    }

    fun setSharedPrefByKey(key: String, value: Int) {
        val sharedPreference = context!!.getSharedPreferences(PREFS_FILENAME, 0)
        sharedPreference.edit().putInt(key, value).apply()
    }
}
