package com.example.mywhatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
                    topBar = { CreateTopAppBar(scrollBehaviour) },
                    floatingActionButton = { CreateFAB() }
                ) { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)
                    val tabs = listOf("Chats", "Novedades", "Llamadas")
                    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })
                    val scope = rememberCoroutineScope()

                    Column (modifier.fillMaxSize()) {
                        TabRow(
                            selectedTabIndex = pagerState.currentPage,
                            contentColor = Color.White,
                            indicator = { tabPositions ->
                                SecondaryIndicator(
                                    Modifier
                                        .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                    color = Color.White
                                )
                            }
                        ) {
                            tabs.forEachIndexed { index, s ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(page = index)
                                        }},
                                    text = { Text(s) },
                                    modifier = Modifier.background(VerdeWhatsApp),
                                )
                            }
                        }

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            when (page) {
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

@Composable
fun CreateFAB() {
    FloatingActionButton(
        onClick = {},
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        containerColor = VerdeWhatsApp,
        contentColor = Color.White
    ) {
        Icon(Icons.Filled.Add, "Nuevo chat")
    }
}