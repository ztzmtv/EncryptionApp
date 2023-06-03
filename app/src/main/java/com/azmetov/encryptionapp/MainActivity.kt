package com.azmetov.encryptionapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.azmetov.encryptionapp.ui.theme.EncryptionAppTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testMessage = "testMessage"
        val cryptoManager = CryptoManager()

        val bytes = testMessage.encodeToByteArray()
        val file = File(filesDir, "secret.txt")
        if (!file.exists())
            file.createNewFile()
        val fos = FileOutputStream(file)
        val fis = FileInputStream(file)

        val encoded = cryptoManager.encrypt(bytes, fos).decodeToString()
        Log.d("TAG encoded", encoded)
        val decoded = cryptoManager.decrypt(fis).decodeToString()
        Log.d("TAG decoded", decoded)


        setContent {
            EncryptionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
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
    EncryptionAppTheme {
        Greeting("Android")
    }
}