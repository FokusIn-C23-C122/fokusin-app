package com.capstone.fokusin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.fokusin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.setting)) },
                Modifier.background(MaterialTheme.colorScheme.primary)
            )

        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingItem(text = stringResource(id = R.string.profile))
                SettingItem(text = stringResource(id = R.string.privacy_policy))
                SettingItem(text = stringResource(id = R.string.help))
                SettingItem(text = stringResource(id = R.string.logout))
            }
        }
    )
}

@Composable
fun SettingItem(text: String) {
    TextButton(
        onClick = { /* Tindakan yang dijalankan saat item diklik */ },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun PreviewSettingScreen() {
    SettingScreen()
}