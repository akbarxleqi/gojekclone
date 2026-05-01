package com.akbar.goapp.features.send

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingBasket
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.PrimaryContainer
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.TextMuted
import com.akbar.goapp.GreenBanner
import com.akbar.goapp.IconBubble
import com.akbar.goapp.MapPreview
import com.akbar.goapp.PaymentFooter
import com.akbar.goapp.SectionTitle
import com.akbar.goapp.SimpleTopBar

@Composable
fun SendScreen(open: (Screen) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(bottom = 124.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { SimpleTopBar("GoSend", Icons.Default.ArrowBack, Icons.Default.Help, topPadding = 0.dp) { open(Screen.Home) } }
        item { SendAddressCard() }
        item { FormSection() }
        item { SendServiceSection() }
        item { GreenBanner("Hemat Rp7.000 pakai GoPay!", "Promo otomatis terpasang untukmu.") }
        item { MapPreview() }
    }
    PaymentFooter("Total Pembayaran", "Rp15.000", "Pesan GoSend")
}

@Composable
fun SendAddressCard() {
    Column(
        Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(14.dp)).background(Color.White).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AddressLine("Titik Jemput", "Apartemen Green Pramuka City, Tower F", "Jakarta Pusat, DKI Jakarta", Primary)
        HorizontalDivider(color = SurfaceLine, modifier = Modifier.padding(start = 30.dp))
        AddressLine("Titik Antar", "Sudirman Central Business District (SCBD)", "Kebayoran Baru, Jakarta Selatan", FoodRed)
    }
}

@Composable
fun AddressLine(label: String, title: String, subtitle: String, color: Color) {
    Row {
        Icon(Icons.Default.LocationOn, null, tint = color)
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color(0xFF334155))
            Text(title, fontSize = 17.sp, fontWeight = FontWeight.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(subtitle, fontSize = 14.sp, color = Color(0xFF334155))
        }
    }
}

@Composable
fun FormSection() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Detail Paket", fontWeight = FontWeight.Bold)
        FormCard("Detail barang", "Contoh: Dokumen, Makanan", Icons.Default.ShoppingBasket)
        FormCard("Berat barang", "< 20kg", Icons.Default.ShoppingBasket, trailing = Icons.Default.KeyboardArrowDown)
        FormCard("Instruksi pengiriman", "Titip di resepsionis / telpon saat", Icons.Default.Edit)
    }
}

@Composable
fun FormCard(label: String, value: String, icon: ImageVector, trailing: ImageVector? = null) {
    Column(Modifier.clip(RoundedCornerShape(14.dp)).background(Color.White).padding(16.dp)) {
        Text(label, fontSize = 12.sp, color = Color(0xFF334155))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Color(0xFF687565))
            Spacer(Modifier.width(10.dp))
            Text(value, color = TextMuted, fontSize = 17.sp, modifier = Modifier.weight(1f))
            trailing?.let { Icon(it, null, tint = TextMuted) }
        }
        HorizontalDivider(color = SurfaceLine, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun SendServiceSection() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionTitle("Pilih Layanan", "Lihat Detail")
        ServiceChoice("Instant", "TERCEPAT", "Tiba dalam 1-2 jam", "Rp15.000", "Rp22.000", true)
        ServiceChoice("Same Day", null, "Tiba dalam 6-8 jam", "Rp10.000", null, false)
    }
}

@Composable
fun ServiceChoice(title: String, badge: String?, desc: String, price: String, oldPrice: String?, selected: Boolean) {
    Row(
        Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(if (selected) 2.dp else 0.dp, if (selected) PrimaryContainer else Color.Transparent, RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBubble(if (selected) Icons.Default.Check else Icons.Default.Star, if (selected) Color(0xFFE9FFF6) else Color(0xFFEFF6FF), if (selected) Primary else Color(0xFF007A98))
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, fontWeight = FontWeight.Black, fontSize = 17.sp)
                if (badge != null) {
                    Spacer(Modifier.width(8.dp))
                    Text(badge, color = Primary, fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(Color(0xFF78F26D)).padding(horizontal = 8.dp, vertical = 2.dp))
                }
            }
            Text(desc, color = Color(0xFF334155), fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(price, fontWeight = FontWeight.Black, fontSize = 17.sp)
            if (oldPrice != null) Text(oldPrice, color = TextMuted, fontSize = 12.sp, textDecoration = TextDecoration.LineThrough)
        }
    }
}
