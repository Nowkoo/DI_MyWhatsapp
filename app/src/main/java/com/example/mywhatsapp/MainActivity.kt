package com.example.mywhatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mywhatsapp.ui.theme.MyWhatsappTheme
import com.example.mywhatsapp.ui.theme.VerdeWhatsApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWhatsappTheme {
                val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehaviour.nestedScrollConnection),
                    topBar = { CreateTopAppBar(scrollBehaviour) }
                ) { innerPadding ->
                    val tabs = listOf("Chats", "Novedades", "Llamadas")
                    var selectedTabIndex by remember { mutableStateOf(0) }
                    val modifier = Modifier.padding(innerPadding)

                    Column (modifier.fillMaxSize()) {
                        TabRow(
                            selectedTabIndex = 0,
                            contentColor = Color.White
                        ) {
                            tabs.forEachIndexed { index, s ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },
                                    text = { Text(s) },
                                    modifier = Modifier.background(VerdeWhatsApp)
                                )
                            }
                        }

                        when (selectedTabIndex) {
                            0 -> Chats()
                            1 -> Novedades()
                            2 -> Llamadas()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTopAppBar(scrollBehaviour: TopAppBarScrollBehavior) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = VerdeWhatsApp,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        title = {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "MyWhatsApp",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        actions = {
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Localized description"
                )
            }

            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehaviour
    )
}