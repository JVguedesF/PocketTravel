//package br.com.renankoji.pockettravel.components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ChatBubble
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.PedalBike
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import br.com.fiap.pockettravel.R
//
//@Composable
//fun MenuBottom(
//    onIconClicked: (Int) -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    colorResource(id = R.color.background_color),
//                    shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp)
//                )
//                .clip(
//                    RoundedCornerShape(
//                        topStart = 36.dp,
//                        topEnd = 36.dp
//                    )
//                )
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            Icon(
//                imageVector = Icons.Filled.ChatBubble,
//                contentDescription = null,
//                tint = colorResource(id = R.color.white),
//                modifier = Modifier
//                    .clickable { onIconClicked(0) }
//                    .size(30.dp)
//            )
//            Icon(
//                imageVector = Icons.Filled.Search,
//                contentDescription = null,
//                tint = colorResource(id = R.color.white),
//                modifier = Modifier
//                    .clickable { onIconClicked(1) }
//                    .size(30.dp)
//            )
//            Icon(
//                imageVector = Icons.Filled.Home,
//                contentDescription = null,
//                tint = colorResource(id = R.color.white),
//                modifier = Modifier
//                    .clickable { onIconClicked(2) }
//                    .size(30.dp)
//            )
//            Icon(
//                imageVector = Icons.Filled.PedalBike,
//                contentDescription = null,
//                tint = colorResource(id = R.color.white),
//                modifier = Modifier
//                    .clickable { onIconClicked(3) }
//                    .size(30.dp)
//            )
//            Icon(
//                imageVector = Icons.Filled.Person,
//                contentDescription = null,
//                tint = colorResource(id = R.color.white),
//                modifier = Modifier
//                    .clickable { onIconClicked(4) }
//                    .size(30.dp)
//            )
//        }
//    }
//}
