package fi.tuni.saari5.testingfirebase2024_04

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore
import fi.tuni.saari5.testingfirebase2024_04.ui.theme.TestingFirebase2024_04Theme

class MainActivity : ComponentActivity() {
    val TAG = "MainActivity"
    //val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(this)
        setContent {
            TestingFirebase2024_04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskList()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskList() {
    val TAG = "TaskList"
    val taskInput = remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<String>() }
    val db = Firebase.firestore

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = taskInput.value,
            onValueChange = { taskInput.value = it },
            label = { Text("Task") }
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                val task = hashMapOf(
                    "name" to taskInput.value
                )

                db.collection("tasks")
                    .add(task)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        taskInput.value = ""
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }) {
                Text("Add")
            }
        }

        LazyColumn {
            items(tasks) { task ->
                Text(task)
            }
        }
    }

    db.collection("tasks")
        .addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            tasks.clear()
            for (doc in value!!) {
                tasks.add(doc.getString("name")!!)
            }
        }
}