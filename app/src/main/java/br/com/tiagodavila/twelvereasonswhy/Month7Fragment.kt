package br.com.tiagodavila.twelvereasonswhy


import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth7Binding


class Month7Fragment : BaseFragment() {

    var mediaPlayer: MediaPlayer? = null
    val glauciaToken = "glaucia"
    val irisToken = "iris"
    val susyToken = "susy"
    val nosToken = "nos"

    private var _binding: FragmentMonth7Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonth7Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.month7Glaucia.setOnClickListener {
            playRelativeMessage(glauciaToken)
        }

        binding.month7Iris.setOnClickListener {
            playRelativeMessage(irisToken)
        }

        binding.month7Susy.setOnClickListener {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
