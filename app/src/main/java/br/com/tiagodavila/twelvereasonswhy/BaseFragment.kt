package br.com.tiagodavila.twelvereasonswhy


import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    lateinit var listener: IMonthHolder

    interface IMonthHolder {
        fun onMonthChosen(fragment: BaseFragment)
    }
}
