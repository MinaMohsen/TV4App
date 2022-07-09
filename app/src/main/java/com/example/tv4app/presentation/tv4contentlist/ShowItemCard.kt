package com.example.tv4app.presentation.tv4contentlist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tv4app.R
import com.example.tv4app.data.model.TV4Show
import com.example.tv4app.util.Constants.IMAGE_PROXY
import com.example.tv4app.util.Constants.IMAGE_PROXY_RESIZE
import com.example.tv4app.util.toFormatedTime
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@SuppressLint("SimpleDateFormat")
@ExperimentalMaterial3Api
@Composable
fun ShowItemCard(
    modifier: Modifier = Modifier,
    tv4Show: TV4Show
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        tv4Show.image?.let {
            Image(
                painter = rememberAsyncImagePainter(
                    model = IMAGE_PROXY + tv4Show.image + IMAGE_PROXY_RESIZE
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = tv4Show.title ?: "",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tv4Show.description ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.start_time,
                    tv4Show.showBroadCastTime?.toFormatedTime() ?: ""
                ),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = 8.dp,
                mainAxisSize = SizeMode.Wrap
            ) {
                tv4Show.showCategory?.let {
                    it.toMutableList().add(tv4Show.type ?: "")
                    it.forEach { tag -> SpecialTags(tagText = tag) }
                } ?: run {
                    SpecialTags(tagText = tv4Show.type ?: "")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpecialTags(
    tagText: String
) {
    if (tagText.isNotEmpty()) {
        AssistChip(
            onClick = { },
            colors = AssistChipDefaults.assistChipColors(
                leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            label = {
                Text(text = tagText)
            }
        )
    }
}
