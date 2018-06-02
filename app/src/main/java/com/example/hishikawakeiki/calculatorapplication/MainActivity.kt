package com.example.hishikawakeiki.calculatorapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


/**
 * calculateSign
 *
 * 符号
 */
enum class CalculateSign {
    INIT,
    PLUS,
    MINUS,
    MULTI,
    DIVISION,
}

class MainActivity : AppCompatActivity() {

    /* Parameters */
    private var beforeValue: Int = 0
    private var afterValue: Int = 0
    private var isafterValueStarted: Boolean = false
    private var sign: CalculateSign = CalculateSign.INIT

    /* method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultView: TextView = findViewById(R.id.resultView)

        val clearButton: Button = findViewById(R.id.clear)
        val plusButton: Button = findViewById(R.id.plus)
        val minusButton: Button = findViewById(R.id.minus)
        val multiButton: Button = findViewById(R.id.multi)
        val divisionButton: Button = findViewById(R.id.division)
        val equalButton: Button = findViewById(R.id.equal)

        var viewId: Int
        var resViewName: String
        var buttonView: Button

        resultView.text = 0.toString()

        // 数字ボタンが押された時の処理
        for (i in 0 until 10) {

            // viewIdを動的に取得
            resViewName = "button" + i
            viewId = resources.getIdentifier(resViewName, "id", packageName)
            buttonView = findViewById(viewId)

            buttonView.setOnClickListener {

                Toast.makeText(this, "トーストメッセージ${i}", Toast.LENGTH_LONG).show();

                // 結果テキストに値を表示
                resultView.text = i.toString()

                // グルーバル変数に値を保持
                if(isafterValueStarted) {
                    afterValue = i
                } else {
                    beforeValue = i
                }
            }
        }

        plusButton.setOnClickListener {
            sign = CalculateSign.PLUS
            isafterValueStarted = true
        }

        minusButton.setOnClickListener {
            sign = CalculateSign.MINUS
            isafterValueStarted = true
        }

        multiButton.setOnClickListener {
            sign = CalculateSign.MULTI
            isafterValueStarted = true
        }

        divisionButton.setOnClickListener {
            sign = CalculateSign.DIVISION
            isafterValueStarted = true
        }

        equalButton.setOnClickListener {
            when(sign) {
                CalculateSign.PLUS     -> resultView.text = (beforeValue + afterValue).toString()
                CalculateSign.MINUS    -> resultView.text = (beforeValue - afterValue).toString()
                CalculateSign.MULTI    -> resultView.text = (beforeValue * afterValue).toString()
                CalculateSign.DIVISION -> resultView.text = (beforeValue / afterValue).toString()
            }
            isafterValueStarted = false
            beforeValue = 0
            afterValue = 0
        }

        clearButton.setOnClickListener {
            resultView.text = 0.toString()
            isafterValueStarted = false
            beforeValue = 0
            afterValue = 0
        }







    }
}
