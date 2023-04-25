package com.learn.learnroomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.learnroomdatabase.di.DaggerMainActivityComponent
import com.learn.learnroomdatabase.ui.theme.LearnRoomDatabaseTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var db: ContactDatabase


    private val viewModel by viewModels<ContactViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewModel(db.contactDao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainActivityComponent
            .factory()
            .create((application as LearnDatabaseApplication).appComponent)
            .inject(this)


        super.onCreate(savedInstanceState)
        setContent {
            LearnRoomDatabaseTheme {
                val state by viewModel.state.collectAsState()
                ContactScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}
