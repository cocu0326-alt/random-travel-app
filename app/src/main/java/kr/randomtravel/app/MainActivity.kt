package kr.randomtravel.app

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.os.Build
import android.view.WindowInsets
import android.content.Intent
import android.content.Context
import android.net.Uri
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.ForegroundColorSpan
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.AdapterView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

data class Destination(val province: String, val city: String, val lat: Double, val lon: Double)

class MainActivity : Activity() {
    private val destinations = listOf(
        Destination("서울특별시","종로구",37.59910,126.98613),
        Destination("서울특별시","중구",37.55791,126.99418),
        Destination("서울특별시","용산구",37.53110,126.98106),
        Destination("서울특별시","성동구",37.55067,127.04094),
        Destination("서울특별시","광진구",37.54814,127.08574),
        Destination("서울특별시","동대문구",37.58364,127.05075),
        Destination("서울특별시","중랑구",37.59519,127.09396),
        Destination("서울특별시","성북구",37.60702,127.02306),
        Destination("서울특별시","강북구",37.64703,127.01470),
        Destination("서울특별시","도봉구",37.66593,127.03172),
        Destination("서울특별시","노원구",37.65520,127.07707),
        Destination("서울특별시","은평구",37.61761,126.92268),
        Destination("서울특별시","서대문구",37.58286,126.93565),
        Destination("서울특별시","마포구",37.56229,126.90868),
        Destination("서울특별시","양천구",37.52701,126.85614),
        Destination("서울특별시","강서구",37.56576,126.82264),
        Destination("서울특별시","구로구",37.49548,126.85811),
        Destination("서울특별시","금천구",37.46026,126.90008),
        Destination("서울특별시","영등포구",37.52063,126.91392),
        Destination("서울특별시","동작구",37.49650,126.94429),
        Destination("서울특별시","관악구",37.46537,126.94379),
        Destination("서울특별시","서초구",37.47695,127.03776),
        Destination("서울특별시","강남구",37.49598,127.06639),
        Destination("서울특별시","송파구",37.50484,127.11445),
        Destination("서울특별시","강동구",37.54902,127.14647),
        Destination("부산광역시","중구",35.10605,129.03287),
        Destination("부산광역시","서구",35.09453,129.01357),
        Destination("부산광역시","동구",35.12739,129.04525),
        Destination("부산광역시","영도구",35.07016,129.06811),
        Destination("부산광역시","부산진구",35.16766,129.04670),
        Destination("부산광역시","동래구",35.20480,129.08029),
        Destination("부산광역시","남구",35.12612,129.09586),
        Destination("부산광역시","북구",35.23218,129.02475),
        Destination("부산광역시","해운대구",35.20143,129.15932),
        Destination("부산광역시","사하구",35.01083,128.97071),
        Destination("부산광역시","금정구",35.25806,129.09059),
        Destination("부산광역시","강서구",35.11259,128.87999),
        Destination("부산광역시","연제구",35.17881,129.08125),
        Destination("부산광역시","수영구",35.16110,129.11326),
        Destination("부산광역시","사상구",35.15919,128.98927),
        Destination("부산광역시","기장군",35.28533,129.20708),
        Destination("대구광역시","중구",35.86721,128.59431),
        Destination("대구광역시","동구",35.93373,128.68303),
        Destination("대구광역시","서구",35.87507,128.54913),
        Destination("대구광역시","남구",35.83376,128.58213),
        Destination("대구광역시","북구",35.93094,128.56832),
        Destination("대구광역시","수성구",35.83412,128.65924),
        Destination("대구광역시","달서구",35.82130,128.52814),
        Destination("대구광역시","달성군",35.77484,128.52352),
        Destination("대구광역시","군위군",36.16725,128.65532),
        Destination("인천광역시","중구",37.45029,126.49994),
        Destination("인천광역시","동구",37.48353,126.63031),
        Destination("인천광역시","미추홀구",37.45701,126.66598),
        Destination("인천광역시","연수구",37.39062,126.64485),
        Destination("인천광역시","남동구",37.43122,126.72393),
        Destination("인천광역시","부평구",37.49428,126.72550),
        Destination("인천광역시","계양구",37.55856,126.73738),
        Destination("인천광역시","서구",37.55521,126.62562),
        Destination("인천광역시","강화군",37.70580,126.25117),
        Destination("인천광역시","옹진군",37.45208,125.65233),
        Destination("광주광역시","동구",35.11898,126.95574),
        Destination("광주광역시","서구",35.13458,126.85519),
        Destination("광주광역시","남구",35.10167,126.84140),
        Destination("광주광역시","북구",35.18975,126.92856),
        Destination("광주광역시","광산구",35.16436,126.75256),
        Destination("대전광역시","동구",36.31738,127.48667),
        Destination("대전광역시","중구",36.27838,127.41335),
        Destination("대전광역시","서구",36.27769,127.34188),
        Destination("대전광역시","유성구",36.38220,127.33346),
        Destination("대전광역시","대덕구",36.40902,127.44697),
        Destination("울산광역시","중구",35.57201,129.30316),
        Destination("울산광역시","남구",35.50731,129.31272),
        Destination("울산광역시","동구",35.52163,129.42294),
        Destination("울산광역시","북구",35.59769,129.37664),
        Destination("울산광역시","울주군",35.52712,129.17663),
        Destination("세종특별자치시","세종특별자치시",36.57033,127.26918),
        Destination("경기도","수원시 장안구",37.31812,127.00006),
        Destination("경기도","수원시 권선구",37.26394,126.98462),
        Destination("경기도","수원시 팔달구",37.27506,127.01165),
        Destination("경기도","수원시 영통구",37.27608,127.06101),
        Destination("경기도","성남시 수정구",37.43508,127.10935),
        Destination("경기도","성남시 중원구",37.44000,127.15640),
        Destination("경기도","성남시 분당구",37.37533,127.10258),
        Destination("경기도","의정부시",37.73300,127.07465),
        Destination("경기도","안양시 만안구",37.40455,126.90967),
        Destination("경기도","안양시 동안구",37.40102,126.95511),
        Destination("경기도","부천시",37.50631,126.78754),
        Destination("경기도","광명시",37.44830,126.86301),
        Destination("경기도","평택시",37.02313,126.96677),
        Destination("경기도","동두천시",37.92069,127.08080),
        Destination("경기도","안산시 상록구",37.32288,126.87706),
        Destination("경기도","안산시 단원구",37.22319,126.61166),
        Destination("경기도","고양시 덕양구",37.66014,126.87677),
        Destination("경기도","고양시 일산동구",37.68133,126.79426),
        Destination("경기도","고양시 일산서구",37.67740,126.72612),
        Destination("경기도","과천시",37.43416,127.00415),
        Destination("경기도","구리시",37.60125,127.13577),
        Destination("경기도","남양주시",37.64310,127.23061),
        Destination("경기도","오산시",37.16305,127.04908),
        Destination("경기도","시흥시",37.39021,126.75517),
        Destination("경기도","군포시",37.34202,126.91780),
        Destination("경기도","의왕시",37.35794,126.98812),
        Destination("경기도","하남시",37.53024,127.21282),
        Destination("경기도","용인시 처인구",37.22204,127.26824),
        Destination("경기도","용인시 기흥구",37.27009,127.12058),
        Destination("경기도","용인시 수지구",37.33268,127.07950),
        Destination("경기도","파주시",37.84960,126.83821),
        Destination("경기도","이천시",37.20148,127.48390),
        Destination("경기도","안성시",37.02476,127.30930),
        Destination("경기도","김포시",37.68484,126.65982),
        Destination("경기도","화성시",37.15832,126.84482),
        Destination("경기도","광주시",37.40044,127.28962),
        Destination("경기도","양주시",37.80539,127.01207),
        Destination("경기도","포천시",37.96762,127.26844),
        Destination("경기도","여주시",37.28774,127.58325),
        Destination("경기도","연천군",38.10521,126.98898),
        Destination("경기도","가평군",37.81119,127.44203),
        Destination("경기도","양평군",37.51890,127.57700),
        Destination("충청북도","청주시 상당구",36.56913,127.60752),
        Destination("충청북도","청주시 서원구",36.54791,127.43583),
        Destination("충청북도","청주시 흥덕구",36.64330,127.37873),
        Destination("충청북도","청주시 청원구",36.71255,127.46842),
        Destination("충청북도","충주시",37.00575,127.89249),
        Destination("충청북도","제천시",37.03517,128.12766),
        Destination("충청북도","보은군",36.49146,127.71033),
        Destination("충청북도","옥천군",36.30789,127.68866),
        Destination("충청북도","영동군",36.16440,127.82381),
        Destination("충청북도","증평군",36.78044,127.60090),
        Destination("충청북도","진천군",36.88042,127.43649),
        Destination("충청북도","괴산군",36.77434,127.84677),
        Destination("충청북도","음성군",36.99456,127.62191),
        Destination("충청북도","단양군",36.98014,128.43461),
        Destination("충청남도","천안시 동남구",36.75597,127.21437),
        Destination("충청남도","천안시 서북구",36.87894,127.18233),
        Destination("충청남도","공주시",36.47818,127.08293),
        Destination("충청남도","보령시",36.34996,126.34710),
        Destination("충청남도","아산시",36.79542,126.97442),
        Destination("충청남도","서산시",36.81347,126.48475),
        Destination("충청남도","논산시",36.20166,127.15869),
        Destination("충청남도","계룡시",36.29096,127.24411),
        Destination("충청남도","당진시",36.90701,126.63083),
        Destination("충청남도","금산군",36.12506,127.47888),
        Destination("충청남도","부여군",36.22904,126.87438),
        Destination("충청남도","서천군",36.09159,126.66986),
        Destination("충청남도","청양군",36.44316,126.85009),
        Destination("충청남도","홍성군",36.56638,126.59969),
        Destination("충청남도","예산군",36.66512,126.77887),
        Destination("충청남도","태안군",36.68693,125.98793),
        Destination("전북특별자치도","전주시 완산구",35.78595,127.13380),
        Destination("전북특별자치도","전주시 덕진구",35.85238,127.11607),
        Destination("전북특별자치도","군산시",35.89910,126.44110),
        Destination("전북특별자치도","익산시",36.02007,126.99556),
        Destination("전북특별자치도","정읍시",35.60997,126.91614),
        Destination("전북특별자치도","남원시",35.43137,127.42899),
        Destination("전북특별자치도","김제시",35.79193,126.78322),
        Destination("전북특별자치도","완주군",35.87479,127.18669),
        Destination("전북특별자치도","진안군",35.82410,127.44507),
        Destination("전북특별자치도","무주군",35.92841,127.71785),
        Destination("전북특별자치도","장수군",35.65338,127.53081),
        Destination("전북특별자치도","임실군",35.61869,127.25960),
        Destination("전북특별자치도","순창군",35.42768,127.08704),
        Destination("전북특별자치도","고창군",35.43779,126.60044),
        Destination("전북특별자치도","부안군",35.68562,126.47135),
        Destination("전라남도","목포시",34.79474,126.37460),
        Destination("전라남도","여수시",34.45198,127.56260),
        Destination("전라남도","순천시",35.00760,127.38427),
        Destination("전라남도","나주시",34.99140,126.70617),
        Destination("전라남도","광양시",35.03325,127.65617),
        Destination("전라남도","담양군",35.29126,126.98610),
        Destination("전라남도","곡성군",35.20320,127.24676),
        Destination("전라남도","구례군",35.23001,127.49576),
        Destination("전라남도","고흥군",34.57261,127.31448),
        Destination("전라남도","보성군",34.81124,127.22633),
        Destination("전라남도","화순군",35.01892,127.01276),
        Destination("전라남도","장흥군",34.64991,126.91956),
        Destination("전라남도","강진군",34.61651,126.77116),
        Destination("전라남도","해남군",34.52723,126.48738),
        Destination("전라남도","영암군",34.80572,126.61090),
        Destination("전라남도","무안군",34.96764,126.39953),
        Destination("전라남도","함평군",35.10455,126.52124),
        Destination("전라남도","영광군",35.29691,126.31549),
        Destination("전라남도","장성군",35.33880,126.75492),
        Destination("전라남도","완도군",34.21458,126.79084),
        Destination("전라남도","진도군",34.36680,126.09299),
        Destination("전라남도","신안군",34.63021,125.72837),
        Destination("경상북도","포항시 남구",35.96502,129.43075),
        Destination("경상북도","포항시 북구",36.17376,129.22698),
        Destination("경상북도","경주시",35.85989,129.24547),
        Destination("경상북도","김천시",36.04364,128.08826),
        Destination("경상북도","안동시",36.55944,128.71504),
        Destination("경상북도","구미시",36.21152,128.34839),
        Destination("경상북도","영주시",36.88053,128.58477),
        Destination("경상북도","영천시",36.00734,128.92274),
        Destination("경상북도","상주시",36.44689,128.06759),
        Destination("경상북도","문경시",36.69268,128.12317),
        Destination("경상북도","경산시",35.84719,128.82618),
        Destination("경상북도","의성군",36.36165,128.59215),
        Destination("경상북도","청송군",36.37771,129.05055),
        Destination("경상북도","영양군",36.68519,129.14895),
        Destination("경상북도","영덕군",36.47012,129.30031),
        Destination("경상북도","청도군",35.70650,128.78685),
        Destination("경상북도","고령군",35.73164,128.32125),
        Destination("경상북도","성주군",35.91629,128.22289),
        Destination("경상북도","칠곡군",36.00617,128.46284),
        Destination("경상북도","예천군",36.65150,128.43558),
        Destination("경상북도","봉화군",36.92344,128.91147),
        Destination("경상북도","울진군",36.89518,129.27795),
        Destination("경상북도","울릉군",37.39398,131.33248),
        Destination("경상남도","창원시 의창구",35.30524,128.66170),
        Destination("경상남도","창원시 성산구",35.19840,128.67033),
        Destination("경상남도","창원시 마산합포구",35.13736,128.49396),
        Destination("경상남도","창원시 마산회원구",35.22707,128.55030),
        Destination("경상남도","창원시 진해구",35.11853,128.72494),
        Destination("경상남도","진주시",35.20099,128.12801),
        Destination("경상남도","통영시",34.75479,128.43082),
        Destination("경상남도","사천시",35.02316,128.03166),
        Destination("경상남도","김해시",35.27301,128.85320),
        Destination("경상남도","밀양시",35.49306,128.79793),
        Destination("경상남도","거제시",34.85523,128.61573),
        Destination("경상남도","양산시",35.40301,129.04657),
        Destination("경상남도","의령군",35.38540,128.26001),
        Destination("경상남도","함안군",35.28283,128.43285),
        Destination("경상남도","창녕군",35.52586,128.50104),
        Destination("경상남도","고성군",35.00561,128.30140),
        Destination("경상남도","남해군",34.72450,127.96013),
        Destination("경상남도","하동군",35.12836,127.75767),
        Destination("경상남도","산청군",35.39762,127.90156),
        Destination("경상남도","함양군",35.54356,127.73139),
        Destination("경상남도","거창군",35.71214,127.88731),
        Destination("경상남도","합천군",35.60008,128.16654),
        Destination("제주특별자치도","제주시",33.64412,126.55727),
        Destination("제주특별자치도","서귀포시",33.29841,126.55701),
        Destination("강원특별자치도","춘천시",37.88028,127.76788),
        Destination("강원특별자치도","원주시",37.32516,127.98130),
        Destination("강원특별자치도","강릉시",37.71016,128.82720),
        Destination("강원특별자치도","동해시",37.51567,129.05950),
        Destination("강원특별자치도","태백시",37.20381,128.98141),
        Destination("강원특별자치도","속초시",38.17489,128.51774),
        Destination("강원특별자치도","삼척시",37.25752,129.10581),
        Destination("강원특별자치도","홍천군",37.74891,128.06222),
        Destination("강원특별자치도","횡성군",37.48946,128.03093),
        Destination("강원특별자치도","영월군",37.21830,128.50879),
        Destination("강원특별자치도","평창군",37.54405,128.50432),
        Destination("강원특별자치도","정선군",37.36998,128.74782),
        Destination("강원특별자치도","철원군",38.21878,127.47084),
        Destination("강원특별자치도","화천군",38.14118,127.67209),
        Destination("강원특별자치도","양구군",38.16946,128.01207),
        Destination("강원특별자치도","인제군",38.09566,128.24554),
        Destination("강원특별자치도","고성군",38.40050,128.41390),
        Destination("강원특별자치도","양양군",38.01196,128.60672)
    )
    private val handler = Handler(Looper.getMainLooper())
    private var selected: Destination? = null
    private var rolling = false
    private val visited = mutableSetOf<String>()
    private val history = mutableListOf<String>()
    private val travelCounts = mutableMapOf<String, Int>()
    private fun destinationKey(place: Destination) = place.province + "/" + place.city
    private fun starLabel(place: Destination?): String {
        val count = place?.let { travelCounts[destinationKey(it)] } ?: 0
        return if (count == 0) "아직 방문 기록이 없습니다"
            else "★ " + count + "개 · 다녀온 여행 " + count + "회"
    }
    private fun saveHistory() {
        getSharedPreferences("travel_history", Context.MODE_PRIVATE).edit()
            .putString("visited", visited.joinToString("|"))
            .putString("recent", history.joinToString("|"))
            .putString("selected", selected?.let { it.province + "/" + it.city } ?: "")
            .putString("travel_counts", travelCounts.entries.joinToString("|") { it.key + "=" + it.value })
            .apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = getSharedPreferences("travel_history", Context.MODE_PRIVATE)
        preferences.getString("visited", "")?.split("|")?.filter { it.isNotBlank() }?.let { visited.addAll(it) }
        preferences.getString("recent", "")?.split("|")?.filter { it.isNotBlank() }?.let { history.addAll(it.take(5)) }
        preferences.getString("travel_counts", "")?.split("|")?.forEach { entry ->
            val separator = entry.lastIndexOf('=')
            if (separator > 0) {
                val count = entry.substring(separator + 1).toIntOrNull()
                if (count != null && count > 0) travelCounts[entry.substring(0, separator)] = count
            }
        }
        selected = destinations.firstOrNull { it.province + "/" + it.city == preferences.getString("selected", "") }
        val background = Color.rgb(235, 243, 245)
        window.statusBarColor = Color.rgb(220, 234, 238)
        window.navigationBarColor = background
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(background)
            setPadding(22, 24, 22, 20)
        }
        val heading = TextView(this).apply {
            val title = SpannableString("🎲 랜덤여행지  by서우아빠")
            val byStart = title.indexOf("by서우아빠")
            title.setSpan(RelativeSizeSpan(0.48f), byStart, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            title.setSpan(ForegroundColorSpan(Color.rgb(91, 115, 124)), byStart, title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = title
            textSize = 29f
            setTextColor(Color.rgb(26, 51, 77))
            gravity = Gravity.CENTER
        }
        root.addView(heading)
        val subtitle = TextView(this).apply {
            text = "시·도를 먼저 뽑고, 해당 지역의 시·군·구를 뽑아보세요."
            textSize = 14f
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 20)
        }
        root.addView(subtitle)
        val provinces = destinations.map { it.province }.distinct()
        var chosenProvince: String? = null
        val provinceLabel = TextView(this).apply {
            text = "1단계 · 시·도 추첨"
            textSize = 18f
        }
        root.addView(provinceLabel)
        val provinceResult = TextView(this).apply {
            text = "시·도를 먼저 뽑아주세요"
            textSize = 20f
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 12)
        }
        root.addView(provinceResult)
        selected?.let {
            chosenProvince = it.province
            provinceResult.text = it.province
        }
        val provinceDraw = Button(this).apply { text = "🎲 시·도 룰렛 돌리기" }
        root.addView(provinceDraw)
        val cityLabel = TextView(this).apply {
            text = "2단계 · 시·군·구 추첨"
            textSize = 18f
            setPadding(0, 12, 0, 0)
        }
        root.addView(cityLabel)
        val noRepeat = CheckBox(this).apply {
            text = "중복 없이 뽑기 (모든 후보를 뽑으면 초기화)"
            isChecked = true
        }
        root.addView(noRepeat)
        val result = TextView(this).apply {
            text = selected?.let { it.province + "\n" + it.city } ?: "여행지를 뽑아보세요!"
            textSize = 25f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(13, 97, 150))
            setPadding(0, 24, 0, 24)
        }
        root.addView(result)
        val stars = TextView(this).apply {
            text = starLabel(selected)
            textSize = 20f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(190, 140, 25))
            setPadding(0, 0, 0, 10)
        }
        root.addView(stars)
        val travelSummary = TextView(this).apply {
            textSize = 14f
            gravity = Gravity.CENTER
            setPadding(0, 4, 0, 8)
            setTextColor(Color.rgb(92, 109, 116))
        }
        fun refreshTravelSummary() {
            val recorded = travelCounts.filterValues { it > 0 }
            travelSummary.text = "나의 여행 기록 · 방문 지역 " + recorded.size +
                "곳 / 총 여행 " + recorded.values.sum() + "회"
        }
        refreshTravelSummary()
        root.addView(travelSummary)
        val confirmVisit = Button(this).apply {
            text = "★ 여행 다녀왔어요 (+1)"
            isEnabled = selected != null
            setOnClickListener {
                if (rolling) return@setOnClickListener
                val place = selected ?: return@setOnClickListener
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("여행 완료 기록")
                    .setMessage(place.province + " " + place.city + " 여행을 다녀오셨나요? 금색 별 1개가 추가됩니다.")
                    .setNegativeButton("취소", null)
                    .setPositiveButton("별 추가") { _, _ ->
                        val key = destinationKey(place)
                        travelCounts[key] = (travelCounts[key] ?: 0) + 1
                        saveHistory()
                        stars.text = starLabel(selected)
                        refreshTravelSummary()
                        Toast.makeText(this@MainActivity, "여행 완료! 금색 별이 추가되었습니다", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }
        }
        root.addView(confirmVisit)
        val travelLog = Button(this).apply {
            text = "★ 나의 여행 기록 보기"
            setOnClickListener {
                val records = destinations.mapNotNull { place ->
                    val count = travelCounts[destinationKey(place)] ?: 0
                    if (count > 0) place to count else null
                }.sortedWith(compareByDescending<Pair<Destination, Int>> { it.second }
                    .thenBy { it.first.province }.thenBy { it.first.city })
                val content = if (records.isEmpty()) {
                    "아직 완료한 여행이 없습니다. 여행을 다녀온 뒤 금색 별을 추가해 보세요."
                } else {
                    "방문 지역 " + records.size + "곳 · 총 " +
                        records.sumOf { it.second } + "회 여행\\n\\n" +
                        records.joinToString("\\n\\n") { (place, count) ->
                            "★ " + count + "  " + place.province + " " + place.city
                        }
                }
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("★ 나의 여행 기록")
                    .setMessage(content)
                    .setPositiveButton("닫기", null)
                    .show()
            }
        }
        root.addView(travelLog)
        val travelMapButton = Button(this).apply {
            text = "🗺️ 금색 별 여행 지도 보기"
            setOnClickListener {
                if (travelCounts.values.none { it > 0 }) {
                    Toast.makeText(this@MainActivity, "아직 방문 기록이 없습니다", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val visitMap = WebView(this@MainActivity).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webViewClient = WebViewClient()
                }
                val height = (resources.displayMetrics.heightPixels * 0.62).toInt()
                val wrapper = LinearLayout(this@MainActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(12, 8, 12, 8)
                    addView(visitMap, LinearLayout.LayoutParams(-1, height))
                }
                showTravelStarsMap(visitMap)
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("★ 나의 여행 지도")
                    .setView(wrapper)
                    .setPositiveButton("닫기", null)
                    .show()
            }
        }
        root.addView(travelMapButton)
        val draw = Button(this).apply {
            text = "🎯 시·군·구 룰렛 돌리기"
            isEnabled = chosenProvince != null
        }
        root.addView(draw)
        val historyLabel = TextView(this).apply {
            text = if (history.isEmpty()) "최근 추첨: 없음" else "최근 추첨: " + history.joinToString(" · ")
            textSize = 13f
            setPadding(0, 10, 0, 10)
        }
        root.addView(historyLabel)
        val reset = Button(this).apply {
            text = "추첨 기록 초기화"
            setOnClickListener {
                if (!rolling) {
                    visited.clear()
                    history.clear()
                    saveHistory()
                    historyLabel.text = "최근 추첨: 없음"
                    Toast.makeText(this@MainActivity, "추첨 기록을 초기화했습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
        root.addView(reset)
        val map = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.setSupportZoom(true)
            webViewClient = WebViewClient()
            setBackgroundColor(Color.rgb(230, 237, 243))
        }
        val mapHeight = (resources.displayMetrics.density * 310).toInt()
        root.addView(map, LinearLayout.LayoutParams(-1, mapHeight))
        val external = Button(this).apply {
            text = "네이버 지도에서 자세히 보기"
            isEnabled = selected != null
            setOnClickListener {
                selected?.let {
                    val q = it.province + " " + it.city
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://map.naver.com/p/search/" + Uri.encode(q))))
                }
            }
        }
        root.addView(external)
        val scroll = ScrollView(this).apply {
            setBackgroundColor(background)
            isFillViewport = true
            addView(root)
        }
        // Android 15+ can draw behind the system bars; reserve their insets.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            scroll.setOnApplyWindowInsetsListener { view, insets ->
                val bars = insets.getInsets(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
                insets
            }
        } else {
            @Suppress("DEPRECATION")
            scroll.fitsSystemWindows = true
        }
        setContentView(scroll)
        selected?.let { showMap(map, it.lat, it.lon, 11, it.province + " " + it.city) }
            ?: showProvinceRouletteMap(map, emptyList())
        provinceDraw.setOnClickListener {
            if (rolling) return@setOnClickListener
            rolling = true
            provinceDraw.isEnabled = false
            draw.isEnabled = false
            external.isEnabled = false
            confirmVisit.isEnabled = false
            stars.text = starLabel(null)
            chosenProvince = null
            selected = null
            result.text = "시·도 추첨이 끝나면 2단계를 진행하세요"
            val sequence = List(18) { provinces.random() }
            showProvinceRouletteMap(map, sequence)
            var tick = 0
            val runner = object : Runnable {
                override fun run() {
                    val province = sequence[tick]
                    provinceResult.text = province
                    map.evaluateJavascript("window.highlightProvince && window.highlightProvince('" + province + "'," + (tick == 17) + ");", null)
                    tick++
                    if (tick < 18) {
                        handler.postDelayed(this, (65 + tick * 12).toLong())
                    } else {
                        chosenProvince = province
                        rolling = false
                        provinceDraw.isEnabled = true
                        draw.isEnabled = true
                        result.text = "이제 " + province + "의 시·군·구를 뽑아주세요"
                        // Keep the winning province highlighted on the map.
                    }
                }
            }
            handler.post(runner)
        }
        draw.setOnClickListener {
            if (rolling) return@setOnClickListener
            val province = chosenProvince ?: return@setOnClickListener
            val allCandidates = destinations.filter { it.province == province }
            var candidates = if (noRepeat.isChecked) allCandidates.filter {
                (it.province + "/" + it.city) !in visited
            } else allCandidates
            if (candidates.isEmpty() && noRepeat.isChecked) {
                allCandidates.forEach { visited.remove(it.province + "/" + it.city) }
                candidates = allCandidates
                saveHistory()
                Toast.makeText(this, "해당 시·도의 모든 지역을 뽑아 기록을 초기화했습니다", Toast.LENGTH_SHORT).show()
            }
            if (candidates.isEmpty()) return@setOnClickListener
            rolling = true
            provinceDraw.isEnabled = false
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
                        visited.add(place.province + "/" + place.city)
                        history.add(0, place.province + " " + place.city)
                        while (history.size > 5) history.removeAt(history.lastIndex)
                        saveHistory()
                        historyLabel.text = "최근 추첨: " + history.joinToString(" · ")
                        rolling = false
                        provinceDraw.isEnabled = true
                        draw.isEnabled = true
                        external.isEnabled = true
                        confirmVisit.isEnabled = true
                        stars.text = starLabel(place)
                        showMap(map, place.lat, place.lon, 11, place.province + " " + place.city)
                    }
                }
            }
            handler.post(runner)
        }
    }

    private fun showTravelStarsMap(webView: WebView) {
        val points = destinations.mapNotNull { place ->
            val count = travelCounts[destinationKey(place)] ?: 0
            if (count <= 0) null else {
                val safeName = (place.province + " " + place.city)
                    .replace("\\", "\\\\").replace("'", "\\'").replace("<", "").replace(">", "")
                "[$" + "{place.lat},$" + "{place.lon},'$" + "{safeName}',$" + "{count}]"
            }
        }.joinToString(",")
        val html = """
            <!doctype html><html lang="ko"><head>
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
            <style>html,body,#map{margin:0;width:100%;height:100%;background:#e8eef3}
            .gold-star{color:#d4a017;font-size:29px;line-height:31px;text-shadow:0 1px 3px #654b10}
            </style></head><body><div id="map"></div>
            <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
            <script>
            var map=L.map('map').setView([36.4,127.8],7);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
              {attribution:'© OpenStreetMap contributors',maxZoom:18}).addTo(map);
            var points=[$points],bounds=[];
            points.forEach(function(p){
              var icon=L.divIcon({className:'',html:'<div class="gold-star">★</div>',iconSize:[32,32],iconAnchor:[16,16]});
              L.marker([p[0],p[1]],{icon:icon}).addTo(map)
                .bindPopup('<b>'+p[2]+'</b><div>★ '+p[3]+'회 방문</div>');
              bounds.push([p[0],p[1]]);
            });
            if(bounds.length)map.fitBounds(bounds,{padding:[35,35],maxZoom:10});
            </script></body></html>
        """.trimIndent()
        webView.loadDataWithBaseURL("https://unpkg.com/", html, "text/html", "UTF-8", null)
    }

    private fun showProvinceRouletteMap(webView: WebView, sequence: List<String>) {
        val names = sequence.joinToString(",") { "'" + it.replace("'", "") + "'" }
        val html = """
            <!doctype html><html lang="ko"><head>
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
            <style>html,body,#map{margin:0;width:100%;height:100%;background:#e8eef3}
            #status{position:absolute;z-index:1000;left:8px;bottom:8px;background:white;padding:7px 12px;border-radius:7px;font:14px sans-serif;color:#234;box-shadow:0 1px 5px #999}</style>
            </head><body><div id="map"></div><div id="status">시·도 경계 불러오는 중...</div>
            <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
            <script>
            var map=L.map('map').setView([36.35,127.8],7);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
              {attribution:'© OpenStreetMap contributors',maxZoom:18}).addTo(map);
            var status=document.getElementById('status');
            var sequence=[$names];
            if(!sequence.length)status.textContent='전국 시·도 경계 표시 중...';
            window.highlightProvince=function(name,isFinal){
              window.pendingProvince={name:name,final:isFinal};
              status.textContent=(isFinal?'🎉 당첨: ':'🎲 추첨 중: ')+name;
            };
            var aliases={'전라북도':'전북특별자치도'};
            fetch('https://raw.githubusercontent.com/KnellBalm/kr-admin-geojson/main/sig.geojson')
              .then(function(r){if(!r.ok)throw new Error('GeoJSON');return r.json()})
              .then(function(data){
                var groups={};
                data.features.forEach(function(f){
                  var name=aliases[f.properties.SIDO_NM]||f.properties.SIDO_NM;
                  (groups[name]||(groups[name]=[])).push(f);
                });
                var layers={};
                Object.keys(groups).forEach(function(name){
                  layers[name]=L.geoJSON({type:'FeatureCollection',features:groups[name]},
                    {style:{color:'#667788',weight:1,fillColor:'#dce8f2',fillOpacity:0.25}})
                    .addTo(map);
                });
                var previous=null;
                window.highlightProvince=function(name,isFinal){
                  if(previous&&layers[previous])layers[previous].setStyle(
                    {color:'#667788',weight:1,fillColor:'#dce8f2',fillOpacity:0.25});
                  if(layers[name]){
                    layers[name].setStyle({color:'#e05a22',weight:3,fillColor:'#ffb13b',fillOpacity:0.7});
                    layers[name].bringToFront();
                    if(isFinal)map.fitBounds(layers[name].getBounds(),{padding:[16,16],maxZoom:9});
                  }
                  status.textContent=(isFinal?'🎉 당첨: ':'🎲 추첨 중: ')+name;
                  previous=name;
                };
                if(window.pendingProvince)window.highlightProvince(window.pendingProvince.name,window.pendingProvince.final);
                else status.textContent='시·도 경계 준비 완료 · 룰렛을 돌려보세요';
              }).catch(function(){status.textContent='경계 연결 실패 · 추첨은 계속됩니다';});
            </script></body></html>
        """.trimIndent()
        webView.loadDataWithBaseURL("https://unpkg.com/", html, "text/html", "UTF-8", null)
    }

    private fun showMap(webView: WebView, lat: Double, lon: Double, zoom: Int, title: String) {
        val safeTitle = title.replace("\\", "\\\\").replace("'", "\\'").replace("<", "").replace(">", "")
        val html = """
            <!doctype html><html lang="ko"><head>
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
            <style>html,body,#map{margin:0;width:100%;height:100%;background:#e8eef3}#status{position:absolute;z-index:1000;left:8px;bottom:8px;background:white;padding:5px 9px;border-radius:5px;font:12px sans-serif;color:#345;box-shadow:0 1px 4px #999}</style>
            </head><body><div id="map"></div><div id="status">지도 불러오는 중...</div>
            <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
            <script>
            var map=L.map('map').setView([$lat,$lon],$zoom);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
            {attribution:'© OpenStreetMap contributors',maxZoom:18}).addTo(map);
            var marker=L.marker([$lat,$lon]).addTo(map).bindPopup('$safeTitle').openPopup();
            var status=document.getElementById('status');
            map.whenReady(function(){status.textContent='지도를 이동하거나 확대할 수 있습니다';});
            if ('$safeTitle' === '대한민국') status.textContent='전국 지도';
            if ('$safeTitle' !== '대한민국') {
                fetch('https://raw.githubusercontent.com/KnellBalm/kr-admin-geojson/main/sig.geojson')
                  .then(function(response) { if (!response.ok) throw new Error('boundary unavailable'); return response.json(); })
                  .then(function(data) {
                    var matches=data.features.filter(function(feature) {
                        var p=feature.properties || {};
                        return p.FULL_NM === '$safeTitle' ||
                          (p.FULL_NM && p.FULL_NM.replace('전라북도','전북특별자치도') === '$safeTitle') ||
                          (p.FULL_NM && p.FULL_NM.replace(/특별자치도/g,'도').replace(/특별자치시/g,'시') === '$safeTitle'.replace(/특별자치도/g,'도').replace(/특별자치시/g,'시'));
                    });
                    if(matches.length) {
                        var layer=L.geoJSON({type:'FeatureCollection',features:matches},
                            {style:{color:'#0b79ba',weight:3,fillColor:'#42b8f4',fillOpacity:0.25}}).addTo(map);
                        map.fitBounds(layer.getBounds(),{padding:[18,18]});
                        status.textContent='선택한 행정구역 경계';
                    } else { status.textContent='경계 데이터 없음 · 위치 표시 중'; }
                  }).catch(function(error) { status.textContent='경계 연결 실패 · 위치 표시 중'; console.warn('Boundary unavailable',error); });
            }
            </script></body></html>
        """.trimIndent()
        webView.loadDataWithBaseURL("https://unpkg.com/", html, "text/html", "UTF-8", null)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
