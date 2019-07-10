package br.com.tiagodavila.twelvereasonswhy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.*


class Month1Fragment : BaseFragment() {
    private val mNotificationTime = Calendar.getInstance().timeInMillis + 15000 //Set after 5 seconds from the current time.
    private var mNotified = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_month1, container, false)

        Toast.makeText(context, "SAPATILHA! YAAAAAAAY", Toast.LENGTH_LONG).show()
        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, activity!!)
        }

        return view
    }


}
