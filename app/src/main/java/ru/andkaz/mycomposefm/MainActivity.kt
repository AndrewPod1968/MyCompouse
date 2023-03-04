package ru.andkaz.mycomposefm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.andkaz.mycomposefm.ui.theme.MyComposeFMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeFMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
   //Card(modifier = Modifier.fillMaxWidth())
    val message = remember{mutableStateOf("")}
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
   {
  /*     Text(
           text = "Test git lesson1",
//           Modifier
//               .padding(30.dp)
               //.offset(x = 20.dp, y = 30.dp),
           //fontSize = 28.sp
       )*/
       Text(text = "12324")
       Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().padding(10.dp)
       ) {
           Text("Get")
       }
       TextField(value = message.value, onValueChange ={message.value=it} )
       Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().padding(10.dp)
       ) {
           Text("Save")
       }
       //OutlinedTextField(value = "Hello",){


       //Text(text = "12324")

   }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeFMTheme {
        Greeting("Android")
    }
}