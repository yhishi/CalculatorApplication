package com.example.hishikawakeiki.calculatorapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


/* class */

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
    private var beforeValues: MutableList<Int> = mutableListOf()
    private var afterValues: MutableList<Int> = mutableListOf()
    private var resultValues: MutableList<Int> = mutableListOf()
    private var isafterValueStarted: Boolean = false
    private var sign: CalculateSign = CalculateSign.INIT

    /* method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 入力値や結果テキストビュー
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

        // 数字ボタンが押された時の処理
        for (i in 0 until 10) {

            // viewIdを動的に取得
            resViewName = "button" + i
            viewId = resources.getIdentifier(resViewName, "id", packageName)
            buttonView = findViewById(viewId)

            buttonView.setOnClickListener {

                // 結果テキストに値を表示
                resultValues.add(i)
                resultView.text = resultValues.joinToString(separator = "")

                // グルーバル変数に値を保持（符号入力前後で保持する変数を変更）
                if(isafterValueStarted) {
                    afterValues.add(i)
                } else {
                    beforeValues.add(i)
                }

            }

        }

        plusButton.setOnClickListener {
            sign = CalculateSign.PLUS
            resultValues = mutableListOf()
            isafterValueStarted = true
        }

        minusButton.setOnClickListener {
            sign = CalculateSign.MINUS
            resultValues = mutableListOf()
            isafterValueStarted = true
        }

        multiButton.setOnClickListener {
            sign = CalculateSign.MULTI
            resultValues = mutableListOf()
            isafterValueStarted = true
        }

        divisionButton.setOnClickListener {
            sign = CalculateSign.DIVISION
            resultValues = mutableListOf()
            isafterValueStarted = true
        }

        equalButton.setOnClickListener {

            // 入力値リストを数値化
            val beforeValue = beforeValues.joinToString(separator = "").toInt()
            val afterValue = afterValues.joinToString(separator = "").toInt()

            // 入力した符号で計算
            when(sign) {
                CalculateSign.PLUS     -> resultView.text = (beforeValue + afterValue).toString()
                CalculateSign.MINUS    -> resultView.text = (beforeValue - afterValue).toString()
                CalculateSign.MULTI    -> resultView.text = (beforeValue * afterValue).toString()
                CalculateSign.DIVISION -> resultView.text = (beforeValue.toDouble() / afterValue.toDouble()).toString()
            }
            init()
        }

        clearButton.setOnClickListener {
            resultView.text = 0.toString()
            init()
        }
    }

    /**
     * init
     *
     * 初期状態に戻す
     */
    private fun init() {
        isafterValueStarted = false
        beforeValues = mutableListOf()
        afterValues = mutableListOf()
        resultValues = mutableListOf()
    }
}
