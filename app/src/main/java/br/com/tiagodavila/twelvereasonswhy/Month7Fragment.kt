package br.com.tiagodavila.twelvereasonswhy


import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_month7.*


class Month7Fragment : BaseFragment() {

    var mediaPlayer: MediaPlayer? = null
    val glauciaToken = "glaucia"
    val irisToken = "iris"
    val susyToken = "susy"
    val nosToken = "nos"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month7, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        month_7_glaucia.setOnClickListener {
            playRelativeMessage(glauciaToken)
        }

        month_7_iris.setOnClickListener {
            playRelativeMessage(irisToken)
        }

        month_7_susy.setOnClickListener {
            playRelativeMessage(nosToken)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun playRelativeMessage(relativeName: String) {
        val relativeRawSource = when (relativeName) {
            "glaucia" -> -1
            "iris" -> R.raw.iris
            "nos" -> R.raw.jose_love
            else -> -1
        }

        if (relativeRawSource == -1) {
            Toast.makeText(context, "Não consegui :(", Toast.LENGTH_SHORT).show()
        } else {
            mediaPlayer = MediaPlayer.create(context, relativeRawSource)
            mediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        mediaPlayer?.isPlaying ?: mediaPlayer?.stop()
        super.onDestroy()
    }


}
