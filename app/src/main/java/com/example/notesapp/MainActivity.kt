package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notesapp.Presentation.NotesScreen
import com.example.notesapp.Presentation.NotesVM
import com.example.notesapp.db.NoteDataBase
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {

    // All of this implementation because I'm not using dependencies

    // database object
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext, NoteDataBase::class.java,
            "notes.dp"
        ).build()
    }

    // view model instance
    @Suppress("UNCHECKED_CAST")
    private val viewModel by viewModels<NotesVM>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NotesVM(database.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NotesAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "NotesScreen"){
                        composable("NotesScreen"){

                            NotesScreen(
                                state= state,
                                navController = navController,
                                onEvents = viewModel::onEvent
                            )

                        }
                        composable("AddNotesScreen"){

//                            AddNotesScreen(
//                                state= state,
//                                navController = navController,
//                                onEvent = viewModel::onEvent
//                            )
                        }
                    }
                }
            }

        }
    }
}


// latest error
// exception: warning: kapt currently doesn't support language version 2.0+. Falling back to 1.9.
/*
exception: info: [ksp] loaded provider(s): [androidx.room.RoomKspProcessor$Provider]
exception: warning: [ksp] C:/Users/ahads_q4dtp7v/AndroidStudioProjects/NotesApp/app/src/main/java/com/example/notesapp/db/NoteDataBase.kt:10: Schema export directory was not provided to the annotation processor so Room cannot export the schema. You can either provide `room.schemaLocation` annotation processor argument by applying the Room Gradle plugin (id 'androidx.room') OR set exportSchema to false.
*/
