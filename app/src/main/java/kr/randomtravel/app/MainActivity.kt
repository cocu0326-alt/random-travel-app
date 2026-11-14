package kr.randomtravel.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private val regions = listOf(
        "서울특별시", "부산광역시", "대구광역시", "인천광역시",
        "광주광역시", "대전광역시", "울산광역시", "세종특별자치시",
        "경기도", "강원특별자치도", "충청북도", "충청남도",
        "전북특별자치도", "전라남도", "경상북도", "경상남도", "제주특별자치도"
    )
    private var selected = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(36, 36, 36, 36)
            setBackgroundColor(Color.rgb(245, 249, 252))
        }
        val heading = TextView(this).apply {
            text = "랜덤여행지 룰렛"
            textSize = 28f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(25, 48, 68))
        }
        val result = TextView(this).apply {
            text = "어디로 떠나볼까요?"
            textSize = 23f
            gravity = Gravity.CENTER
            setPadding(0, 55, 0, 55)
            setTextColor(Color.rgb(19, 99, 145))
        }
        val draw = Button(this).apply {
            text = "랜덤 여행지 뽑기"
            setOnClickListener {
                selected = regions.random()
                result.text = selected
            }
        }
        val map = Button(this).apply {
            text = "네이버 지도에서 보기"
            setOnClickListener {
                if (selected.isNotEmpty()) {
                    val uri = Uri.parse("https://map.naver.com/p/search/" + Uri.encode(selected))
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            }
        }
        layout.addView(heading)
        layout.addView(result)
        layout.addView(draw)
        layout.addView(map)
        setContentView(layout)
    }
}
