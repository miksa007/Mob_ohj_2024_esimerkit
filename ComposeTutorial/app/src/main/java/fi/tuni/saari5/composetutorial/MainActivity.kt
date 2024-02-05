package fi.tuni.saari5.composetutorial

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fi.tuni.saari5.composetutorial.ui.theme.ComposeTutorialTheme
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Softa", "MainActivity.onCreate")
        context = this

        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

                        Greeting("Android")
                        Button(onClick = { buttonPressed() }) {
                            Text("Click write")
                        }
                        Button(onClick = { otherButtonPressed() }) {
                            Text("Click read")
                        }
                    }
                }
            }
        }
    }

    fun otherButtonPressed() {
        val currentTime=LocalDateTime.now()
        Log.d("Softa", "other pressed to read")
        readStringFromFile(context, "pressed.txt")  // read the file
        Log.d("Softa", "other pressed")
    }

    fun buttonPressed() {
        val currentTime=LocalDateTime.now()
        Log.d("Softa", "pressed to write")
        writeStringToFile(context , "pressed at $currentTime", "pressed.txt")


        //Log.d("Softa", "pressed")
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ComposeTutorialTheme {
            Greeting("Android")
        }
    }

    fun readStringFromFile(context: Context, filePath: String): String {
        try {
            val file = File(context.filesDir, filePath)

            // Check if the file exists, if not, create a new file
            if (!file.exists()) {
                file.createNewFile()
            }
            // Create a FileReader and BufferedReader
            val fileReader = FileReader(file)
            val bufferedReader = BufferedReader(fileReader)

            var line: String?
            val stringBuilder = StringBuilder()

            // Read the content line by line and append to StringBuilder
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }

            // Close the BufferedReader
            bufferedReader.close()

            Log.d("Softa", "String has been read from the file successfully." + stringBuilder.toString())
            return stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun writeStringToFile(context: Context, content: String, filePath: String) {
        try {
            val file = File(context.filesDir, filePath)

            // Create a FileWriter and BufferedWriter
            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)

            // Write the content to the file
            bufferedWriter.write(content)

            // Close the BufferedWriter to flush and close the file
            bufferedWriter.close()

            //println("String has been written to the file successfully.")
            Log.d("Softa", "String has been written to the file successfully." + content)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

