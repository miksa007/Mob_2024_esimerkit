package fi.tuni.saari5.mybutton2024_05_14

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.tuni.saari5.mybutton2024_05_14.ui.theme.MyButton2024_05_14Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyButton2024_05_14Theme {
                // A surface container using the 'background' color from the theme
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyCustomUI()
                }
            }
        }
    }
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
    MyButton2024_05_14Theme {
        Greeting("Android")
    }
}
@Composable
fun MyCustomUI() {
    var button1PressCount = remember { mutableStateOf(0) }
    var button2PressCount = remember { mutableStateOf(0) }

    var button1Pressed = remember { mutableStateOf(false) }
    var button2Pressed = remember { mutableStateOf(false) }

    // Create a text field to display the button state
    var textFieldValue = remember { mutableStateOf("") }

    // Observe changes in the button states
    LaunchedEffect(button1Pressed.value, button2Pressed.value) {
        if (button1Pressed.value) {
            textFieldValue.value = "Button 1 pressed"

        } else if (button2Pressed.value) {
            textFieldValue.value = "Button 2 pressed"
            //button2Pressed.value = false
        } else {
            textFieldValue.value = ""
        }
    }
    // Create a column layout to hold the UI elements
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Add a text field for user input
        TextField(
            value = textFieldValue.value,
            onValueChange = { textFieldValue.value = it },
            label = { Text("Button State") },
            modifier = Modifier.padding(16.dp)
        )

        // Add two buttons
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                Log.d("MyCustomUI", "Button 1 pressed")
                button1PressCount.value++
                button1Pressed.value = true
                button2Pressed.value = false
            }) {
                Text("Button ${button1PressCount.value + 1}. push")
                //Text("Button 1")
            }

            Button(onClick = {
                Log.d("MyCustomUI", "Button 2 pressed")
                button2Pressed.value = true
                button1Pressed.value = false
                button1PressCount.value =0
            }) {
                Text("Reset")
            }
        }
    }
}