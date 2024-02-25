import java.util.Random

// Immutable list of words 
val words = listOf("germany", "icecream", "food", "steak", "pizza")
// Choose a random word 
val chosenWord = words.random()

// Mutable to store guessed letters
var guessedLetters = mutableSetOf<Char>()
// count wrong guesses
var wrongGuesses = 0

// start point
fun main() {
    println("Welcome to Hangman!")
    println("Guess the word letter by letter.")

    // Game loop
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

    //print result
    if (isWordGuessed(chosenWord, guessedLetters)) {
        println("Congratulations! You guessed the word: $chosenWord")
    } else {
        displayHangman(wrongGuesses)
        println("You ran out of guesses. The word was: $chosenWord")
    }
}

//  check if the word is guessed
fun isWordGuessed(word: String, guessedLetters: Set<Char>): Boolean {
    return word.all { it in guessedLetters }
}

//  display the hangman based on missed guesses
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
           |      O
           |     /|
           |
       """,
      """
          --------
          |      |
          |      O
          |     /|\
          |
      """,
      """
          --------
          |      |
          |      O
          |     /|\
          |     /
      """,
      """
          --------
          |      |
          |      O
          |     /|\
          |     / \
      """
    )

    println(stages[wrongGuesses])
}

//  display the word with guessed letters
fun displayWord(word: String, guessedLetters: Set<Char>) {
    val displayedWord = word.map { char ->
        if (char in guessedLetters) char else '-'
    }.joinToString("")
    println(displayedWord)
}
