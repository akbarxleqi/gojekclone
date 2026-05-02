package com.akbar.goapp.features.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.Primary
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.TextMuted

data class ChatItem(
    val name: String,
    val message: String,
    val time: String,
    val unread: Int = 0,
    val icon: ImageVector = Icons.Default.Chat
)

val mockChats = listOf(
    ChatItem("Budi (GoRide)", "Saya sudah di depan pagar ya pak.", "12:45"),
    ChatItem("Siti (GoFood)", "Pesanan sedang disiapkan resto.", "11:20"),
    ChatItem("Customer Service", "Ada promo menarik buat kamu nih!", "Yesterday", 1),
    ChatItem("Gojek", "Keamanan akun kamu terjaga.", "2 May"),
    ChatItem("Agus (GoRide)", "Oke pak, saya meluncur.", "2 May")
)

@Composable
fun ChatScreen(open: (Screen) -> Unit) {
    var selectedTab by remember { mutableStateOf("Chat") }

    Column(Modifier.fillMaxSize()) {
        ChatHeader()
        ChatTabs(selectedTab) { selectedTab = it }
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            if (selectedTab == "Chat") {
                items(mockChats) { chat ->
                    ChatRow(chat)
                }
            } else {
                item {
                    EmptyNotifications()
                }
            }
        }
    }

    AppBottomBar(
        selected = "Chat",
        onHome = { open(Screen.Home) },
        onPromos = { open(Screen.Promos) },
        onOrders = { open(Screen.Orders) },
        onChat = { open(Screen.Chat) }
    )
}

@Composable
fun ChatHeader() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Chat", fontSize = 24.sp, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
            Icon(Icons.Default.Search, null, tint = Color(0xFF687565), modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun ChatTabs(selected: String, onSelect: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        listOf("Chat", "Notifikasi").forEach { tab ->
            val isSelected = selected == tab
            Column(
                Modifier
                    .weight(1f)
                    .clickable { onSelect(tab) }
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    tab,
                    color = if (isSelected) Primary else TextMuted,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    Modifier
                        .height(3.dp)
                        .fillMaxWidth(0.6f)
                        .background(if (isSelected) Primary else Color.Transparent, RoundedCornerShape(99.dp))
                )
            }
        }
    }
}

@Composable
fun ChatRow(chat: ChatItem) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF1F5F9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(chat.icon, null, tint = Primary, modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(chat.name, fontWeight = FontWeight.Black, fontSize = 17.sp, modifier = Modifier.weight(1f))
                Text(chat.time, color = TextMuted, fontSize = 12.sp)
            }
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(chat.message, color = TextMuted, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                if (chat.unread > 0) {
                    Box(Modifier.size(20.dp).clip(CircleShape).background(Primary), contentAlignment = Alignment.Center) {
                        Text(chat.unread.toString(), color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
    HorizontalDivider(color = SurfaceLine, modifier = Modifier.padding(horizontal = 16.dp))
}

@Composable
fun EmptyNotifications() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Notifications, null, tint = TextMuted.copy(alpha = 0.2f), modifier = Modifier.size(80.dp))
        Spacer(Modifier.height(24.dp))
        Text("Belum ada notifikasi", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("Semua pemberitahuan sistem akan muncul di sini.", color = TextMuted, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.padding(horizontal = 40.dp))
    }
}
