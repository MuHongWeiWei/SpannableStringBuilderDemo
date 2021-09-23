package com.example.spannablestringbuilderdemo

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //段落文字
        leadingMargin()

        //底線放大
        underLineSize()

        //背景文字顏色
        backgroundForeground()

        //點擊圖標
        clickImageText()

        //段落圖刪除線
        drawableStrikeThrough()

        //段落圓點修飾
        bulletMaskFilter()

        //粗體斜體
        boldItalic()

        //超連結行數高度
        urlLineHeight()
    }

    @SuppressLint("NewApi")
    private fun urlLineHeight() {
        val urlLineHeightText = findViewById<TextView>(R.id.urlLineHeightText)
        val spannableStringBuilder = SpannableStringBuilder("文字的Google超連結和行數高度行數高度行數高度範例。")
        //超連結
        spannableStringBuilder.setSpan(URLSpan("https://www.google.com.tw/"), 3,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //文字顏色(改藍色比較像可點擊的樣子)
        spannableStringBuilder.setSpan(ForegroundColorSpan(Color.BLUE), 3,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //行數高度
        spannableStringBuilder.setSpan(LineHeightSpan.Standard(90), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        //設定才能點
        urlLineHeightText.movementMethod = LinkMovementMethod.getInstance()
        urlLineHeightText.text = spannableStringBuilder
    }

    private fun boldItalic() {
        val boldItalicText = findViewById<TextView>(R.id.boldItalicText)
        val spannableStringBuilder = SpannableStringBuilder("文字的粗體和斜體範例")
        //粗體
        spannableStringBuilder.setSpan(StyleSpan(Typeface.BOLD), 3,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //斜體
        spannableStringBuilder.setSpan(StyleSpan(Typeface.ITALIC), 6,8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        boldItalicText.text = spannableStringBuilder
    }

    private fun bulletMaskFilter() {
        val bulletMaskFilterText = findViewById<TextView>(R.id.bulletMaskFilterText)
        val spannableStringBuilder = SpannableStringBuilder("文字的段落圓點和修飾模糊範例")
        //段落圓點
        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()
        val bulletSpan = BulletSpan(width, Color.RED)
        spannableStringBuilder.setSpan(bulletSpan, 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //修飾模糊
        val blurMaskFilterSpan = MaskFilterSpan(BlurMaskFilter(resources.displayMetrics.density * 2, BlurMaskFilter.Blur.NORMAL))
        spannableStringBuilder.setSpan(blurMaskFilterSpan, 8,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        bulletMaskFilterText.text = spannableStringBuilder
    }

    private fun drawableStrikeThrough() {
        val drawableStrikeThroughText = findViewById<TextView>(R.id.drawableStrikeThroughText)
        val spannableStringBuilder = SpannableStringBuilder("文字的首圖和刪除線範例")
        //首圖
        val apple = ContextCompat.getDrawable(this, R.drawable.apple)!!
        spannableStringBuilder.setSpan(DrawableMarginSpan(apple), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //刪除線
        spannableStringBuilder.setSpan(StrikethroughSpan(), 6,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        drawableStrikeThroughText.text = spannableStringBuilder
    }

    private fun clickImageText() {
        val clickImageText = findViewById<TextView>(R.id.clickImageText)
        val spannableStringBuilder = SpannableStringBuilder("文字的部分點擊和圖標範例")
        //背景顏色
        spannableStringBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                Toast.makeText(this@MainActivity, "點擊了", Toast.LENGTH_SHORT).show()
            }
        }, 3,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //文字顏色(改藍色比較像可點擊的樣子)
        spannableStringBuilder.setSpan(ForegroundColorSpan(Color.BLUE), 3,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //圖標
        val imageSpan = ImageSpan(this, R.drawable.apple)
        spannableStringBuilder.setSpan(imageSpan, 8,10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        //設定才能點
        clickImageText.movementMethod = LinkMovementMethod.getInstance()
        clickImageText.text = spannableStringBuilder
    }

    private fun backgroundForeground() {
        val backgroundForegroundText = findViewById<TextView>(R.id.backgroundForegroundText)
        val spannableStringBuilder = SpannableStringBuilder("文字的背景顏色和文字顏色範例")
        //背景顏色
        spannableStringBuilder.setSpan(BackgroundColorSpan(Color.RED), 3,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //文字顏色
        spannableStringBuilder.setSpan(ForegroundColorSpan(Color.BLUE), 8,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        backgroundForegroundText.text = spannableStringBuilder
    }

    private fun underLineSize() {
        val underlineSizeText = findViewById<TextView>(R.id.underlineSizeText)
        val spannableStringBuilder = SpannableStringBuilder("文字的下底線和放大和縮小範例")
        //下底線
        spannableStringBuilder.setSpan(UnderlineSpan(), 4,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //放大
        spannableStringBuilder.setSpan(RelativeSizeSpan(1.5f), 7,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //縮小
        spannableStringBuilder.setSpan(RelativeSizeSpan(0.5f), 10,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        underlineSizeText.text = spannableStringBuilder
    }

    private fun leadingMargin() {
        val leadingMarginText = findViewById<TextView>(R.id.leadingMarginText)

        val one = "1.此APP所發送之驗證碼,將在您索取3分鐘後失效。\n2.無法收到驗證碼嗎？\n"
        val spannableStringBuilder = SpannableStringBuilder(one)
        //計算要空多少格
        val oneMeasure = leadingMarginText.paint.measureText("1.").toInt()
        //設定段落的文字長度
        spannableStringBuilder.setSpan(
                LeadingMarginSpan.Standard(0, oneMeasure),
                0,
                spannableStringBuilder.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val two = "a.手機設定是否有阻攔廣告訊息\nb.訊息容量已滿、訊號是否正常\nc.若以上皆正常仍無法收到簡訊請先與您的電信公司確認，以免重複發送多次簡訊驗 證碼導致帳號無法使用。"
        spannableStringBuilder.append(two)
        //計算要空多少格
        val twoMeasure = leadingMarginText.paint.measureText("1.a.").toInt()
        //設定段落的文字長度
        spannableStringBuilder.setSpan(
                LeadingMarginSpan.Standard(
                        oneMeasure,
                        twoMeasure
                ), one.length, spannableStringBuilder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        leadingMarginText.text = spannableStringBuilder
    }
}