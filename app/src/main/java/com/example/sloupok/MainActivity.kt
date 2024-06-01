package com.example.sloupok

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerChoice: ImageView
    private lateinit var computerChoice: ImageView
    private lateinit var buttonRock: Button
    private lateinit var buttonPaper: Button
    private lateinit var buttonScissors: Button
    private lateinit var buttonLizard: Button
    private lateinit var buttonSpock: Button
    private lateinit var buttonPlay: Button

    private var playerSelection = -1
    private val choices = intArrayOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors, R.drawable.leather, R.drawable.spock)
    private val choicesNames = arrayOf("КАМЕНЬ", "БУМАГА", "НОЖНИЦЫ", "ЯЩЕРИЦА", "СПОК")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoice = findViewById(R.id.player_choice)
        computerChoice = findViewById(R.id.computer_choice)
        buttonRock = findViewById(R.id.button_rock)
        buttonPaper = findViewById(R.id.button_paper)
        buttonScissors = findViewById(R.id.button_scissors)
        buttonLizard = findViewById(R.id.button_lizard)
        buttonSpock = findViewById(R.id.button_spock)
        buttonPlay = findViewById(R.id.button_play)

        buttonRock.setOnClickListener { setPlayerChoice(0) }
        buttonPaper.setOnClickListener { setPlayerChoice(1) }
        buttonScissors.setOnClickListener { setPlayerChoice(2) }
        buttonLizard.setOnClickListener { setPlayerChoice(3) }
        buttonSpock.setOnClickListener { setPlayerChoice(4) }

        buttonPlay.setOnClickListener {
            if (playerSelection == -1) {
                Toast.makeText(this, "Необходимо сделать выбор", Toast.LENGTH_SHORT).show()
            } else {
                playGame()
            }
        }
    }

    private fun setPlayerChoice(choice: Int) {
        playerSelection = choice
        playerChoice.setImageResource(choices[choice])
    }

    private fun playGame() {
        val computerSelection = Random.nextInt(5)
        computerChoice.setImageResource(choices[computerSelection])

        if (playerSelection == computerSelection) {
            Toast.makeText(this, "Ничья! Переиграйте!", Toast.LENGTH_SHORT).show()
        } else {
            val result = determineWinner(playerSelection, computerSelection)
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
    }

    private fun determineWinner(player: Int, computer: Int): String {
        return if ((player == 0 && (computer == 2 || computer == 3)) ||
            (player == 1 && (computer == 0 || computer == 4)) ||
            (player == 2 && (computer == 1 || computer == 3)) ||
            (player == 3 && (computer == 1 || computer == 4)) ||
            (player == 4 && (computer == 0 || computer == 2))) {
            "Вы победили!"
        } else {
            "Вы проиграли!"
        }
    }
}
