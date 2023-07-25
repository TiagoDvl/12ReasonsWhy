package br.com.tiagodavila.twelvereasonswhy


import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.GridLayoutManager
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth2Binding
import br.com.tiagodavila.twelvereasonswhy.databinding.FragmentMonth3Binding


class Month3Fragment : BaseFragment(), SeekBar.OnSeekBarChangeListener {

    private var isVibratorStarted: Boolean = false
    var vibrator: Vibrator? = null
    val PAUSE = 100L
    val PAUSE_STRENGTH = 0
    var TIME_MULTIPLYER = 2L

    private var _binding: FragmentMonth3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vibrator = activity!!.getSystemService(VIBRATOR_SERVICE) as Vibrator?
        _binding = FragmentMonth3Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.month3SeekBar.setOnSeekBarChangeListener(this)
        binding.month3SeekBar.progress = 1
        binding.month3VibratorStart.setOnClickListener { toggleVibrator() }
        val gridLayoutManager = GridLayoutManager(this.context, 2)
        val travelMemories = arrayOf(
            TravelMemories("O que você é?", resources.getDrawable(R.drawable.o_que_voce_e), 40.971294, -6.508380),
            TravelMemories(":(", resources.getDrawable(R.drawable.o_que_voce_e_2), 40.971294, -6.508380),
            TravelMemories("Bb não tira foto", resources.getDrawable(R.drawable.airplane_sleep), 40.763727, -5.083934),
            TravelMemories("Dont touch me!!", resources.getDrawable(R.drawable.retiro_dont_touch_me), 40.417378, -3.683306),
            TravelMemories("Tá com medo que eu caia?", resources.getDrawable(R.drawable.medo_de_cair), 40.413653, -3.681795),
            TravelMemories("Posso te abraçar?", resources.getDrawable(R.drawable.drag_hug), 40.4224084,-3.7027493),
            TravelMemories("Desculpa, bb.", resources.getDrawable(R.drawable.desculpa_bb), 40.4253337,-3.7051338),
            TravelMemories("Não tenho tloco", resources.getDrawable(R.drawable.chines_correndo), 40.423797, -3.718102),
            TravelMemories("Aponta pro gordo!", resources.getDrawable(R.drawable.olha_o_gordo), 40.415462, -3.706425))
        val month3Adapter = Month3Adapter(travelMemories)
        binding.month3Recycler.apply {
            layoutManager = gridLayoutManager
            hasFixedSize()
            adapter = month3Adapter
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        vibrator?.cancel()
        super.onDestroy()
    }

    private fun toggleVibrator() {
        if (isVibratorStarted) {
            vibrator?.cancel()
            isVibratorStarted = false
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.
                    createWaveform(
                        longArrayOf(500 * TIME_MULTIPLYER,
                            PAUSE, 300 * TIME_MULTIPLYER,
                            PAUSE, 200 * TIME_MULTIPLYER,
                            PAUSE, 100 * TIME_MULTIPLYER,
                            PAUSE, 50 * TIME_MULTIPLYER),
                        intArrayOf(255,
                            PAUSE_STRENGTH, 255,
                            PAUSE_STRENGTH, 255,
                            PAUSE_STRENGTH, 255,
                            PAUSE_STRENGTH, 255),
                        0)
                )
                isVibratorStarted = true
            }
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        // Do nothing.
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        // Do nothing.
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.month3VibratorIndicator.text = progress.toString()
        TIME_MULTIPLYER = progress.toLong()
    }

}
