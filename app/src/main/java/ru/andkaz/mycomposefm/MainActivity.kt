package ru.andkaz.mycomposefm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.usecase.GetUserNameUseCase
import ru.andkaz.mycomposefm.damain.usecase.SaveUserNameUseCase
import ru.andkaz.mycomposefm.data.repository.UserRepositoryImpl
//import ru.andkaz.mycomposefm.data.repository.UserRepsitoryImpl
import ru.andkaz.mycomposefm.data.storage.SharedPrefUserStorage
import ru.andkaz.mycomposefm.presentation.MainViewModel
import ru.andkaz.mycomposefm.presentation.MyViewModel
import ru.andkaz.mycomposefm.presentation.screens.main.NameScreen
import ru.andkaz.mycomposefm.ui.theme.MyComposeFMTheme

class MainActivity : ComponentActivity() {
    private val userStorage by lazy(LazyThreadSafetyMode.NONE){ SharedPrefUserStorage(contex=applicationContext)

    }

    private val userRepsitory  by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(SharedPrefUserStorage(contex=applicationContext))
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepsitory=userRepsitory)
    }
    private val  saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepsitory=userRepsitory)
    }
    // private lateinit var  vm: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AAA","Activity created")
        // vm= ViewModelProvider(this,MainVieModelFactory(this)).get(MainViewModel::class.java)
       //vm= ViewModelProvider(this).get(MainViewModel::class.java)
        setContent {
            MyComposeFMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // Greeting(getUserNameUseCase = getUserNameUseCase,saveUserNameUseCase=saveUserNameUseCase,vm,this)
//                    Screen()
                    NameScreen(getUserNameUseCase = getUserNameUseCase,saveUserNameUseCase=saveUserNameUseCase)
                }
            }
        }
    }
}

@Composable
fun Greeting(getUserNameUseCase:GetUserNameUseCase,saveUserNameUseCase:SaveUserNameUseCase,
             vm:MainViewModel,owner:ViewModelStoreOwner) {

   //Card(modifier = Modifier.fillMaxWidth())
    var saveName:String=""
    val message = remember{mutableStateOf("")}
    val textH  = remember {
        mutableStateOf("")
    }
    //vm=
    //val mViewModel:MainViewModel=    ViewModelProvider(owner).get(MainViewModel::class.java)
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
           Text("GET USER DATA")
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
           Text("SAVE USER DATA")
       }
       //OutlinedTextField(value = "Hello",){


       //Text(text = "12324")

   }
}
/*
@Composable
fun NameScreen(
    getUserNameUseCase:GetUserNameUseCase,
    saveUserNameUseCase:SaveUserNameUseCase,
    viewModel: NameFiewModel = viewModel()
){
//   val state =  viewModel.state.value

    val title by viewModel.titleInputFlow.collectAsState()

//    val message = remember{mutableStateOf("")}
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

//        Text(text =  state.name)
        Text(text =  title.name)
        Button(onClick = {
            val userName=getUserNameUseCase.execute()
            viewModel.onEvent(NameFiewModel.NameEvent.GetName("${userName.firstName}  ${userName.lastName}"))


        }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ) {
            Text("GET USER DATA")
        }
//        TextField(value = state.edit_name, onValueChange ={
        TextField(value = title.edit_name, onValueChange ={
            viewModel.onEvent(NameFiewModel.NameEvent.EnteredName(it))
        } )
        Button(onClick = {

//            val text_save =state.edit_name
            val text_save =title.edit_name
            val params = SaveUserNameParam(name = text_save)
            val result =saveUserNameUseCase.execute(param=params)

            viewModel.onEvent(NameFiewModel.NameEvent.SaveName(result.toString()))

        }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ) {
            Text("SAVE USER DATA")
        }
    }

}

*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeFMTheme {
        //Greeting("Android")
    }
}

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = viewModel()
) {
    //val viewModel: MyViewModel = viewModel()
    val state = viewModel.state.value

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
        //verticalArrangment = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangment
            verticalArrangement= Arrangement.spacedBy(16.dp)
            //verticalA = Arrangment.spacedBy(16.dp)
        ) {
            Text(text = "Counter: ${state.counter}")
            Text(text = "Button number: ${state.buttonNumber}")
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                viewModel.onEvent(MyViewModel.UIEvent.IncrementCounter)
            }) {
                Text(text = "Increment counter")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {viewModel.onEvent(MyViewModel.UIEvent.ChooseButton(1))}) {
                    Text(text = "Button 1")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {viewModel.onEvent(MyViewModel.UIEvent.ChooseButton(2))}) {
                    Text(text = "Button 2")
                }
            }
        }
    }
}