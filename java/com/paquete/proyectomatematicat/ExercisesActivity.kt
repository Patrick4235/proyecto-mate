package com.paquete.proyectomatematicat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.system.exitProcess


class ExercisesActivity : AppCompatActivity() {

    private val numbers: MutableList<String> = mutableListOf()
    private var result: Int = 0
    private var correctAnswers: Int = 0
    private var incorrectAnswers: Int = 0

    private lateinit var txtExercise: TextView
    private lateinit var txtCorrectAnswersCounter: TextView
    private lateinit var txtIncorrectAnswersCounter: TextView
    private lateinit var txtCorrectAnswer: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnNextExercise: Button
    private lateinit var btnCheckAnswer: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercises)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtExercise = findViewById(R.id.txtExercise)
        txtCorrectAnswersCounter = findViewById(R.id.txtCorrectAnswersCounter)
        txtIncorrectAnswersCounter = findViewById(R.id.txtIncorrectAnswersCounter)
        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer)
        etAnswer = findViewById(R.id.etAnswer)

        btnNextExercise = findViewById(R.id.btnNextExercise)
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer)

        createSimpleExercise()
    }

    fun createRandomExercise(){
        var expression = "3+5*(3+4)"
        var result = ExpressionBuilder(expression).build().evaluate().toInt();
        txtExercise.text = result.toString()

    }

    fun createSimpleExercise(){

        numbers.add(Random.nextInt(100).toString())
        numbers.add(Random.nextInt(100).toString())

        val randomSign = createRandomSign()
        val exercise = "${numbers[0]} $randomSign ${numbers[1]}"

        result = ExpressionBuilder(exercise).build().evaluate().toInt();

        txtExercise.text = exercise
    }

    fun createRandomSign(): String{
        val number = Random.nextInt(101)

        return when{
            number <= 50 -> "+"
            else -> "-"
        }
    }

    fun checkAnswer(view: View){

        btnCheckAnswer.isEnabled = false

        if(etAnswer.text.toString() == result.toString()){
            correctAnswers++
            txtCorrectAnswersCounter.text = correctAnswers.toString()
        }else{
            incorrectAnswers++
            txtIncorrectAnswersCounter.text = incorrectAnswers.toString()
        }

        checkTotalAnswer()

        txtCorrectAnswer.text = "Respuesta correcta: $result"
        btnNextExercise.visibility = View.VISIBLE
    }

    fun nextExercise(view: View){

        btnCheckAnswer.isEnabled = true

        etAnswer.setText("")
        txtCorrectAnswer.setText("")
        btnNextExercise.visibility = View.INVISIBLE
        numbers.clear()
        createSimpleExercise()
    }

    fun checkTotalAnswer(){
        if(correctAnswers >= 5){
            this.finish()
            exitProcess(0)
        }
    }

}