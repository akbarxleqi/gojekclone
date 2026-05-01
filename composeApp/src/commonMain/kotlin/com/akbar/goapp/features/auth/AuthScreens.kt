package com.akbar.goapp.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.Blue
import com.akbar.goapp.Primary
import com.akbar.goapp.PrimaryContainer
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.TextMuted
import com.akbar.goapp.PrimaryButton
import com.akbar.goapp.SimpleTopBar

@Composable
fun LoginScreen(open: (Screen) -> Unit) {
    var phone by remember { mutableStateOf("") }
    
    Column(Modifier.fillMaxSize().padding(top = 112.dp, start = 32.dp, end = 32.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Text("GoApp", color = Primary, fontSize = 36.sp, fontWeight = FontWeight.Black)
        Text("Satu aplikasi untuk semua kebutuhan harianmu.", color = Color(0xFF334155), fontSize = 17.sp)
        Spacer(Modifier.height(24.dp))
        Text("Nomor HP", fontWeight = FontWeight.Black)
        PhoneField(phone) { phone = it }
        PrimaryButton("Lanjut", modifier = Modifier.fillMaxWidth().height(58.dp)) { 
            open(Screen.Home) 
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(Modifier.weight(1f), color = Color(0xFFB8C8AE))
            Text("Atau masuk dengan", Modifier.padding(horizontal = 14.dp), color = Color(0xFF243123))
            HorizontalDivider(Modifier.weight(1f), color = Color(0xFFB8C8AE))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SocialButton("Google", Modifier.weight(1f))
            SocialButton("Facebook", Modifier.weight(1f), Blue)
        }
        Spacer(Modifier.weight(1f))
        TextButton(onClick = { open(Screen.Register) }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Belum punya akun? ", color = Color(0xFF334155))
            Text("Daftar", color = Primary, fontWeight = FontWeight.Black)
        }
        Text("Dengan masuk, kamu menyetujui Ketentuan\nLayanan dan Kebijakan Privasi GoApp.", color = Color(0xFFD8D8D8), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(28.dp))
    }
}

@Composable
fun RegisterScreen(open: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    LazyColumn(contentPadding = PaddingValues(top = 54.dp, start = 16.dp, end = 16.dp, bottom = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { SimpleTopBar("Daftar Akun", Icons.Default.ArrowBack, null, topPadding = 0.dp) { open(Screen.Login) } }
        item {
            Box(Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(14.dp)).background(Brush.linearGradient(listOf(Color(0xFF062D27), Color(0xFF75B7A2), Color.White)))) 
        }
        item {
            Text("Mulai Perjalanan Anda", fontSize = 32.sp, fontWeight = FontWeight.Black, lineHeight = 36.sp)
            Text("Lengkapi data diri untuk menikmati layanan super app terbaik.", color = Color(0xFF334155), fontSize = 16.sp, lineHeight = 24.sp)
        }
        item { InputField("Nama Lengkap", "Contoh: Budi Santoso", Icons.Default.Person, name) { name = it } }
        item { InputField("Alamat Email", "nama@email.com", Icons.Default.Email, email) { email = it } }
        item { InputField("Nomor Telepon", "+62     812 3456 7890", Icons.Default.Phone, phone) { phone = it } }
        item {
            Row(verticalAlignment = Alignment.Top) {
                Box(Modifier.size(24.dp).border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(8.dp)))
                Spacer(Modifier.width(14.dp))
                Text("Saya menyetujui Syarat dan Ketentuan serta Kebijakan Privasi yang berlaku.", fontSize = 17.sp, lineHeight = 24.sp)
            }
        }
        item { PrimaryButton("Daftar Sekarang", onClick = { open(Screen.Home) }) }
        item {
            TextButton(onClick = { open(Screen.Login) }, modifier = Modifier.fillMaxWidth()) {
                Text("Sudah punya akun? ", color = Color(0xFF334155))
                Text("Masuk di sini", color = Primary, fontWeight = FontWeight.Black)
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BenefitMini("Data Terenkripsi & Aman", Icons.Default.Check, Color(0xFFE9FFF6), Modifier.weight(1f))
                BenefitMini("Proses Instan 1 Menit", Icons.Default.Check, Color(0xFFF0EEEE), Modifier.weight(1f))
            }
        }
        item { Text("POWERED BY URBANCONCIERGE", color = Color(0xFFD8D8D8), letterSpacing = 3.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) }
    }
}

@Composable
fun InputField(label: String, placeholder: String, icon: ImageVector, value: String, onValueChange: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, color = Color(0xFF294129), fontWeight = FontWeight.Black)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White)
                        .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(14.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.weight(1f)) {
                        if (value.isEmpty()) Text(placeholder, color = TextMuted, fontSize = 16.sp)
                        innerTextField()
                    }
                    Icon(icon, null, tint = Color(0xFF687565))
                }
            }
        )
    }
}

@Composable
fun PhoneField(value: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(14.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("ID  +62", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.width(14.dp))
                HorizontalDivider(Modifier.height(28.dp).width(1.dp), color = SurfaceLine)
                Spacer(Modifier.width(14.dp))
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) Text("812 3456 7890", color = Color(0xFFCBD0D6), fontSize = 16.sp)
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun SocialButton(label: String, modifier: Modifier = Modifier, color: Color = Color(0xFFEA4335)) {
    Row(
        modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(14.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(28.dp).clip(RoundedCornerShape(6.dp)).background(color.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
            Text(label.first().toString(), color = color, fontWeight = FontWeight.Black)
        }
        Spacer(Modifier.width(12.dp))
        Text(label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun BenefitMini(text: String, icon: ImageVector, bg: Color, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(18.dp)).background(bg).padding(18.dp)) {
        Icon(icon, null, tint = Primary)
        Spacer(Modifier.height(16.dp))
        Text(text, fontSize = 16.sp)
    }
}
