package com.example.sergiobelda.iot_cloud_weather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sergiobelda.iot_cloud_weather.model.WeatherState
import com.example.sergiobelda.iot_cloud_weather.viewmodel.WeatherStateViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(WeatherStateViewModel::class.java)
        val liveData = viewModel.getWeatherStateLiveData("esp8266_4CB5CD")
        liveData.observe(this, Observer<WeatherState> { weatherState ->
            if (weatherState != null) {
                val temperature = weatherState.temperature
                temp.text = (Math.round(temperature * 100.0) / 100.0).toString() + '°'
            }
        })
    }
}