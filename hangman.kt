import java.util.Random

fun main() {
    val words = listOf("apple", "banana", "orange", "computer", "python")
    val chosenWord = words.random()

    var guessedLetters = mutableSetOf<Char>()
    var wrongGuesses = 0

    println("Welcome to Hangman!")
    println("Guess the word letter by letter.")

    while (wrongGuesses < 6 && !isWordGuessed(chosenWord, guessedLetters)) {
        displayHangman(wrongGuesses)
        displayWord(chosenWord, guessedLetters)
        println("Guess a letter:")
        val guess = readLine()?.firstOrNull()?.lowercaseChar() ?: continue

        if (guess in guessedLetters) {
            println("You already guessed that letter.")
        } else if (guess !in chosenWord.toCharArray()) {
            wrongGuesses++
            guessedLetters.add(guess)
            println("Wrong guess.")
        } else {
            guessedLetters.add(guess)
            println("Correct guess!")
        }
    }

    if (isWordGuessed(chosenWord, guessedLetters)) {
        println("Congratulations! You guessed the word: $chosenWord")
    } else {
        displayHangman(wrongGuesses)
        println("You ran out of guesses. The word was: $chosenWord")
    }
}

fun isWordGuessed(word: String, guessedLetters: Set<Char>): Boolean {
    return word.all { it in guessedLetters }
}

fun displayHangman(wrongGuesses: Int) {
    val stages = listOf(
      """
          --------
          |      |
          |      
          |
          |
      """,
      """
          --------
          |      |
          |      0
          |
          |
      """,
      """
          --------
          |      |
          |      O
          |     /
          |     
      """,
     
  
        """
            --------
            |      |
            |      0
            |     /|
            |
        """,
      """
          --------
          |      |
          |      0
          |     /|\
          |
      """,
      """
          --------
          |      |
          |      0
          |     /|\
          |     /
      """,

        """
            --------
            |      |
            |      0
            |     /|\
            |     / \
        """,
    )

    println(stages[wrongGuesses])
}

fun displayWord(word: String, guessedLetters: Set<Char>) {
    val displayedWord = word.map { char ->
        if (char in guessedLetters) char else '-'
    }.joinToString("")
    println(displayedWord)
}
