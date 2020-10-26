package com.example.composeclock.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import coil.transform.CircleCropTransformation
import com.example.composeclock.data.model.User
import com.example.composeclock.ui.theme.ComposeClockTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun UserProfileCard(user: User, modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .clip(RoundedCornerShape(4.dp))
      .background(color = MaterialTheme.colors.surface)
      .clickable(onClick = {})
      .padding(16.dp)
  ) {
    CoilImage(
      data = user.profileImage,
      modifier = Modifier.preferredSize(50.dp),
      requestBuilder = {
        transformations(CircleCropTransformation())
      },

      loading = {
        Box(Modifier.fillMaxSize()) {
          CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
      },
      error = {
        Surface(
          modifier = Modifier.fillMaxSize(),
          shape = CircleShape,
          color = Color.Gray
        ) {}
      }
    )
    Column(
      modifier = Modifier.padding(start = 8.dp)
        .align(Alignment.CenterVertically)
    ) {
      Text(text = user.name, fontWeight = FontWeight.Bold)
      ProvideEmphasis(AmbientEmphasisLevels.current.medium) {
        Text("3 minutes ago", style = MaterialTheme.typography.body2)
      }
    }
  }

}

@Preview(showBackground = true, name = "UserProfileCard Preview")
@Composable
fun PreviewUserProfile() {
  val user = User(
    "1",
    "Ashish Suman",
    "https://randomuser.me/api/portraits/women/40.jpg"
  )
  ComposeClockTheme {
    UserProfileCard(user = user)
  }
}
