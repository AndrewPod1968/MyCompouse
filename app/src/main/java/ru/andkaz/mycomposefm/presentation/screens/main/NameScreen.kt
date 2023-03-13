package ru.andkaz.mycomposefm.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.usecase.GetUserNameUseCase
import ru.andkaz.mycomposefm.damain.usecase.SaveUserNameUseCase

@Composable
fun NameScreen(
    getUserNameUseCase: GetUserNameUseCase,
    saveUserNameUseCase: SaveUserNameUseCase,
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
