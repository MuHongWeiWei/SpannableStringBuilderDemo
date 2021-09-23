# Android SpannableStringBuilder 用法

##### SpannableStringBuilder，和String一樣都是一種字串型別。不同的是SpannableString可以通過使用其方法setSpan方法實現字串各種文字的樣式。例如:段落文字、底線、文字放大、背景顏色、文字顏色、圖片塞入文字、刪除線、毛玻璃、段落圓點、粗體斜體、超連結、行數高度

---

#### 文章目錄
<ol>
    <li><a href="#a">段落文字</a></li>
    <li><a href="#b">底線放大</a></li>
    <li><a href="#c">背景顏色文字顏色</a></li>
    <li><a href="#d">點擊圖標</a></li>
    <li><a href="#e">段落圖刪除線</a></li>
	<li><a href="#f">段落圓點修飾</a></li>
	<li><a href="#g">粗體斜體</a></li>
	<li><a href="#h">超連結行數高度</a></li>
	<li><a href="#i">畫面布局</a></li>
	<li><a href="#j">程式範例</a></li>
	<li><a href="#k">效果展示</a></li>
	<li><a href="#l">Github</a></li>
</ol>

---

<a id="a"></a>
#### 1.段落文字
```Kotlin
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
```

<a id="b"></a>
#### 2.底線放大
```Kotlin
val underlineSizeText = findViewById<TextView>(R.id.underlineSizeText)
val spannableStringBuilder = SpannableStringBuilder("文字的下底線和放大和縮小範例")
//下底線
spannableStringBuilder.setSpan(UnderlineSpan(), 4,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//放大
spannableStringBuilder.setSpan(RelativeSizeSpan(1.5f), 7,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//縮小
spannableStringBuilder.setSpan(RelativeSizeSpan(0.5f), 10,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

underlineSizeText.text = spannableStringBuilder
```

<a id="c"></a>
#### 3.背景顏色文字顏色
```Kotlin
val backgroundForegroundText = findViewById<TextView>(R.id.backgroundForegroundText)
val spannableStringBuilder = SpannableStringBuilder("文字的背景顏色和文字顏色範例")
//背景顏色
spannableStringBuilder.setSpan(BackgroundColorSpan(Color.RED), 3,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//文字顏色
spannableStringBuilder.setSpan(ForegroundColorSpan(Color.BLUE), 8,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

backgroundForegroundText.text = spannableStringBuilder
```

<a id="d"></a>
#### 4.點擊圖標
```Kotlin
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
```

<a id="e"></a>
#### 5.段落圖刪除線
```Kotlin
val drawableStrikeThroughText = findViewById<TextView>(R.id.drawableStrikeThroughText)
val spannableStringBuilder = SpannableStringBuilder("文字的首圖和刪除線範例")
//首圖
val apple = ContextCompat.getDrawable(this, R.drawable.apple)!!
spannableStringBuilder.setSpan(DrawableMarginSpan(apple), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//刪除線
spannableStringBuilder.setSpan(StrikethroughSpan(), 6,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

drawableStrikeThroughText.text = spannableStringBuilder
```


<a id="f"></a>
#### 6.段落圓點修飾
```Kotlin
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
```



<a id="g"></a>
#### 7.粗體斜體
```Kotlin
val boldItalicText = findViewById<TextView>(R.id.boldItalicText)
val spannableStringBuilder = SpannableStringBuilder("文字的粗體和斜體範例")
//粗體
spannableStringBuilder.setSpan(StyleSpan(Typeface.BOLD), 3,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//斜體
spannableStringBuilder.setSpan(StyleSpan(Typeface.ITALIC), 6,8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
boldItalicText.text = spannableStringBuilder
```

<a id="h"></a>
#### 8.超連結行數高度
```Kotlin
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
```



<a id="i"></a>
#### 9.畫面布局
```XML
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/leadingMarginText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <View
        android:id="@+id/view"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/leadingMarginText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/underlineSizeText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view" />

    <View
        android:id="@+id/view2"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/underlineSizeText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/backgroundForegroundText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view2" />

    <View
        android:id="@+id/view3"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/backgroundForegroundText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/clickImageText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view3" />

    <View
        android:id="@+id/view4"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/clickImageText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/drawableStrikeThroughText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view4" />

    <View
        android:id="@+id/view5"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/drawableStrikeThroughText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/bulletMaskFilterText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view5" />

    <View
        android:id="@+id/view6"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/bulletMaskFilterText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/boldItalicText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view6" />

    <View
        android:id="@+id/view7"
        android:layout_width="wrap_content"
        android:layout_height="1dp"
        android:layout_marginTop="8dp"
        android:background="@color/black"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/boldItalicText" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/urlLineHeightText"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="32dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view7" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

<a id="j"></a>
#### 10.程式範例
```Kotlin
package com.example.test

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
```

<a id="k"></a>
#### 11.效果展示
<a href="https://badgameshow.com/fly/wp-content/uploads/2021/09/242552281_608315493518720_6274551621216007257_n.jpg"><img src="https://badgameshow.com/fly/wp-content/uploads/2021/09/242552281_608315493518720_6274551621216007257_n.jpg" width="50%"/></a>

<a id="l"></a>
#### 12.Github
[Android SpannableStringBuilder 用法 Github](https://github.com/MuHongWeiWei/SpannableStringBuilderDemo)
