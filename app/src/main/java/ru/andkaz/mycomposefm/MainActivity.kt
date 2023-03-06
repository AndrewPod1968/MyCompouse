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
import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.usecase.GetUserNameUseCase
import ru.andkaz.mycomposefm.damain.usecase.SaveUserNameUseCase
import ru.andkaz.mycomposefm.ui.theme.MyComposeFMTheme

class MainActivity : ComponentActivity() {
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {   GetUserNameUseCase(/*userRepsitory=userRepsitory*/) }
    private val  saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) { SaveUserNameUseCase(/*userRepsitory=userRepsitory*/) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeFMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(getUserNameUseCase = getUserNameUseCase,saveUserNameUseCase=saveUserNameUseCase,)
                }
            }
        }
    }
}

@Composable
fun Greeting(getUserNameUseCase:GetUserNameUseCase,saveUserNameUseCase:SaveUserNameUseCase) {
   // private val  saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) { SaveUserNameUseCase(/*userRepsitory=userRepsitory*/) }


   //Card(modifier = Modifier.fillMaxWidth())
    var saveName:String=""
    val message = remember{mutableStateOf("")}
    val textH  = remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
   {

       Text(text = textH.value)
       Button(onClick = {
           textH.value=saveName
           val userName=getUserNameUseCase.execute()
           textH.value="${userName.firstName}  ${userName.lastName}"


                        }, modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp)
       ) {
           Text("GET DATA")
       }
       TextField(value = message.value, onValueChange ={message.value=it} )
       Button(onClick = {

           val text=message.value
           val params = SaveUserNameParam(name = text)
           val result =saveUserNameUseCase.execute(param=params)
           textH.value="Save result = ${result.toString()}"

                        }, modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp)
       ) {
           Text("SAVE DATA")
       }
       //OutlinedTextField(value = "Hello",){


       //Text(text = "12324")

   }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeFMTheme {
        //Greeting("Android")
    }
}