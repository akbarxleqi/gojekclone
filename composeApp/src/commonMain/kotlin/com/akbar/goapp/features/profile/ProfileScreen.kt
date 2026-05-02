package com.akbar.goapp.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.PrimaryContainer
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLow
import com.akbar.goapp.TextDark
import com.akbar.goapp.TextMuted
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.Avatar
import com.akbar.goapp.CircleIcon
import com.akbar.goapp.IconBubble
import com.akbar.goapp.SimpleTopBar

@Composable
fun ProfileScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(top = 56.dp, bottom = 108.dp), verticalArrangement = Arrangement.spacedBy(22.dp)) {
            item { SimpleTopBar("Profile", Icons.Default.ArrowBack, Icons.Default.Settings, topPadding = 0.dp) { open(Screen.Home) } }
            item { ProfileHero() }
            item { ProfileStats() }
            item { ProfileMenu() }
            item { Text("GoApp v4.52.1", color = Color(0xFFA0A7B3), fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }
        }
        AppBottomBar(
        selected = "Account",
        onHome = { open(Screen.Home) },
        onPromos = { open(Screen.Promos) },
        onOrders = { open(Screen.Orders) },
        onChat = { open(Screen.Chat) }
    )
    }
}

@Composable
fun ProfileHero() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Avatar(Modifier.size(104.dp), large = true)
            CircleIcon(Icons.Default.Edit, PrimaryContainer, Color.White, Modifier.align(Alignment.BottomEnd).size(38.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text("Akbar Pratama", fontSize = 25.sp, fontWeight = FontWeight.Black)
        Text("+62 812 3456 7890", fontSize = 16.sp)
        Spacer(Modifier.height(12.dp))
        Text("GOLD MEMBER", color = Color(0xFF92400E), fontWeight = FontWeight.Bold, letterSpacing = 1.sp, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(Color(0xFFFFF8C5)).border(1.dp, Color(0xFFFDE047), RoundedCornerShape(999.dp)).padding(horizontal = 18.dp, vertical = 8.dp))
    }
}

@Composable
fun ProfileStats() {
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        StatCard("Saldo GoPay", "Rp125.000", Icons.Default.AccountBalanceWallet, Blue, Modifier.weight(1f))
        StatCard("GoPay Points", "2.450 Points", Icons.Default.Star, Color(0xFF00A680), Modifier.weight(1f))
    }
}

@Composable
fun StatCard(label: String, value: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(14.dp)).background(Color.White).padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBubble(icon, tint.copy(alpha = 0.1f), tint, Modifier.size(40.dp), 22)
            Spacer(Modifier.width(10.dp))
            Text(label, color = TextMuted, fontSize = 14.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
fun ProfileMenu() {
    val rows = listOf(
        "Pesanan saya" to Icons.Default.Receipt,
        "Voucher saya" to Icons.Default.Star,
        "Metode pembayaran" to Icons.Default.CreditCard,
        "Alamat tersimpan" to Icons.Default.Place,
        "Pusat Bantuan" to Icons.Default.Help,
        "Keamanan Akun" to Icons.Default.Check
    )
    Column(Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(18.dp)).background(Color.White)) {
        rows.forEach { (text, icon) -> MenuRow(text, icon, TextDark) }
        MenuRow("Keluar", Icons.Default.ExitToApp, FoodRed)
    }
}

@Composable
fun MenuRow(text: String, icon: ImageVector, tint: Color) {
    Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
        IconBubble(icon, if (tint == FoodRed) Color(0xFFFFDADA) else SurfaceLow, tint, Modifier.size(44.dp), 24)
        Spacer(Modifier.width(18.dp))
        Text(text, color = tint, fontSize = 17.sp, fontWeight = if (tint == FoodRed) FontWeight.Black else FontWeight.Normal, modifier = Modifier.weight(1f))
        Icon(Icons.Default.KeyboardArrowRight, null, tint = Color(0xFFA0A7B3))
    }
    HorizontalDivider(color = Color(0xFFF1F1F1))
}
