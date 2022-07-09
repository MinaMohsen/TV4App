package com.example.tv4app.presentation.tv4contentlist

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tv4app.R
import com.example.tv4app.presentation.ui.theme.Red40
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun TV4ContentListScreen(
    viewModel: TV4ContentViewModel = hiltViewModel()
) {
    val tv4Content by remember { viewModel.tv4ShowsList }
    val isLoading by remember { viewModel.isLoading }
    val loadError by remember { viewModel.loadError }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    androidx.compose.material3.Text(text = stringResource(id = R.string.title))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        scaffoldState = rememberScaffoldState()
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isLoading),
            onRefresh = { viewModel.loadTV4Content() }
        ) {
            LazyColumn {
                items(tv4Content) { item ->
                    ShowItemCard(
                        tv4Show = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if (loadError.asString().isNotEmpty()) {
                    RetryView(error = loadError.asString(LocalContext.current)) {
                        viewModel.loadTV4Content()
                    }

                }
            }
        }
    }
}

@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(
            text = error, color = Color.Red, fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(30.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onRetry()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 45.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Red40)
        ) {
            Text(
                text = stringResource(id = R.string.retry_label),
                color = Color.White
            )
        }
    }
}
