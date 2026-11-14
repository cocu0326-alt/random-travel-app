package kr.randomtravel.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

data class Destination(val province: String, val city: String, val lat: Double, val lon: Double)

class MainActivity : Activity() {
    private val destinations = listOf(
        Destination("서울특별시","종로구",37.573,126.979), Destination("서울특별시","마포구",37.566,126.902), Destination("서울특별시","송파구",37.515,127.106),
        Destination("부산광역시","해운대구",35.163,129.163), Destination("부산광역시","수영구",35.145,129.113), Destination("부산광역시","기장군",35.244,129.222),
        Destination("대구광역시","중구",35.869,128.606), Destination("대구광역시","달성군",35.774,128.431), Destination("대구광역시","수성구",35.858,128.631),
        Destination("인천광역시","강화군",37.747,126.488), Destination("인천광역시","중구",37.473,126.622), Destination("인천광역시","연수구",37.410,126.678),
        Destination("광주광역시","동구",35.146,126.923), Destination("광주광역시","북구",35.174,126.912),
        Destination("대전광역시","유성구",36.362,127.356), Destination("대전광역시","중구",36.325,127.421),
        Destination("울산광역시","울주군",35.522,129.242), Destination("울산광역시","남구",35.544,129.330),
        Destination("세종특별자치시","세종시",36.480,127.289),
        Destination("경기도","수원시",37.263,127.029), Destination("경기도","용인시",37.241,127.178), Destination("경기도","가평군",37.831,127.510), Destination("경기도","양평군",37.491,127.488), Destination("경기도","파주시",37.760,126.780), Destination("경기도","포천시",37.895,127.201),
        Destination("강원특별자치도","춘천시",37.882,127.730), Destination("강원특별자치도","강릉시",37.752,128.876), Destination("강원특별자치도","속초시",38.207,128.592), Destination("강원특별자치도","평창군",37.371,128.390), Destination("강원특별자치도","양양군",38.075,128.619),
        Destination("충청북도","청주시",36.642,127.489), Destination("충청북도","충주시",36.991,127.926), Destination("충청북도","단양군",36.984,128.365), Destination("충청북도","제천시",37.132,128.191),
        Destination("충청남도","천안시",36.815,127.114), Destination("충청남도","공주시",36.446,127.119), Destination("충청남도","보령시",36.333,126.612), Destination("충청남도","태안군",36.745,126.299), Destination("충청남도","서산시",36.784,126.450),
        Destination("전북특별자치도","전주시",35.824,127.148), Destination("전북특별자치도","군산시",35.967,126.736), Destination("전북특별자치도","남원시",35.416,127.390), Destination("전북특별자치도","부안군",35.731,126.733),
        Destination("전라남도","여수시",34.760,127.662), Destination("전라남도","순천시",34.950,127.487), Destination("전라남도","목포시",34.811,126.392), Destination("전라남도","담양군",35.321,126.988), Destination("전라남도","완도군",34.311,126.755),
        Destination("경상북도","경주시",35.856,129.224), Destination("경상북도","포항시",36.019,129.343), Destination("경상북도","안동시",36.568,128.729), Destination("경상북도","영덕군",36.416,129.366), Destination("경상북도","울릉군",37.484,130.905),
        Destination("경상남도","창원시",35.228,128.681), Destination("경상남도","통영시",34.854,128.434), Destination("경상남도","거제시",34.880,128.621), Destination("경상남도","남해군",34.838,127.892), Destination("경상남도","하동군",35.067,127.751),
        Destination("제주특별자치도","제주시",33.500,126.531), Destination("제주특별자치도","서귀포시",33.254,126.560)
    )
    private val handler = Handler(Looper.getMainLooper())
    private var selected: Destination? = null
    private var rolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val background = Color.rgb(245, 249, 253)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(background)
            setPadding(22, 24, 22, 20)
        }
        val heading = TextView(this).apply {
            text = "🎲 랜덤여행지"
            textSize = 29f
            setTextColor(Color.rgb(26, 51, 77))
            gravity = Gravity.CENTER
        }
        root.addView(heading)
        val subtitle = TextView(this).apply {
            text = "오늘은 어디로 떠날까요? 지역을 선택하고 룰렛을 돌려보세요."
            textSize = 14f
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 20)
        }
        root.addView(subtitle)
        val provinces = listOf("전국 전체") + destinations.map { it.province }.distinct()
        val filter = Spinner(this)
        filter.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provinces)
        root.addView(filter)
        val result = TextView(this).apply {
            text = "여행지를 뽑아보세요!"
            textSize = 25f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(13, 97, 150))
            setPadding(0, 24, 0, 24)
        }
        root.addView(result)
        val draw = Button(this).apply { text = "🎯 여행지 룰렛 돌리기" }
        root.addView(draw)
        val map = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            setBackgroundColor(Color.rgb(230, 237, 243))
        }
        root.addView(map, LinearLayout.LayoutParams(-1, 0, 1f))
        val external = Button(this).apply {
            text = "네이버 지도에서 자세히 보기"
            isEnabled = false
            setOnClickListener {
                selected?.let {
                    val q = it.province + " " + it.city
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://map.naver.com/p/search/" + Uri.encode(q))))
                }
            }
        }
        root.addView(external)
        setContentView(root)
        showMap(map, 36.5, 127.8, 7, "대한민국")
        draw.setOnClickListener {
            if (rolling) return@setOnClickListener
            val chosenProvince = provinces[filter.selectedItemPosition]
            val candidates = destinations.filter { chosenProvince == "전국 전체" || it.province == chosenProvince }
            if (candidates.isEmpty()) {
                Toast.makeText(this, "선택 가능한 여행지가 없습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            rolling = true
            draw.isEnabled = false
            external.isEnabled = false
            var tick = 0
            val runner = object : Runnable {
                override fun run() {
                    val place = candidates.random()
                    result.text = place.province + "\n" + place.city
                    tick++
                    if (tick < 18) {
                        handler.postDelayed(this, (65 + tick * 12).toLong())
                    } else {
                        selected = place
                        rolling = false
                        draw.isEnabled = true
                        external.isEnabled = true
                        showMap(map, place.lat, place.lon, 11, place.province + " " + place.city)
                    }
                }
            }
            handler.post(runner)
        }
    }

    private fun showMap(webView: WebView, lat: Double, lon: Double, zoom: Int, title: String) {
        val safeTitle = title.replace("\\", "\\\\").replace("'", "\\'").replace("<", "").replace(">", "")
        val html = """
            <!doctype html><html lang="ko"><head>
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
            <style>html,body,#map{margin:0;width:100%;height:100%;background:#e8eef3}</style>
            </head><body><div id="map"></div>
            <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
            <script>
            var map=L.map('map').setView([$lat,$lon],$zoom);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            {attribution:'© OpenStreetMap contributors',maxZoom:18}).addTo(map);
            L.marker([$lat,$lon]).addTo(map).bindPopup('$safeTitle').openPopup();
            </script></body></html>
        """.trimIndent()
        webView.loadDataWithBaseURL("https://unpkg.com/", html, "text/html", "UTF-8", null)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
