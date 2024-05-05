package com.example.newsfeedapp.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.model.Source
import com.example.newsfeedapp.presentation.Dimens.ArticleImageHeight
import com.example.newsfeedapp.presentation.Dimens.MediumPadding1
import com.example.newsfeedapp.presentation.Dimens.SmallPadding
import com.example.newsfeedapp.presentation.details.components.DetailsTopBar
import com.example.newsfeedapp.ui.theme.NewsFeedAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowseClick = {
                Intent(Intent.ACTION_VIEW).also { intent ->
                    intent.data = Uri.parse(article.url)
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also { intent ->
                    intent.putExtra(Intent.EXTRA_TEXT, article.url)
                    intent.type = "text/plain"
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = SmallPadding,
                end = SmallPadding,
                top = SmallPadding
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    NewsFeedAppTheme {
        Surface {
            DetailsScreen(article = Article(
                author = "",
                content = "If ain't broken, don't fix! But do the work to mitigate the future risk.If ain't broken, don't fix! But do the work to mitigate the future risk.",
                description = "",
                publishedAt = "3 hours",
                source = Source(id = "", name = "BBC News"),
                title = "If ain't broken, don't fix! But do the work to mitigate the future risk.",
                url = "",
                urlToImage = "https://img.freepik.com/free-photo/abstract-autumn-beauty-multi-colored-leaf-vein-pattern-generated-by-ai_188544-9871.jpg"
            ), event = {}, navigateUp = {})
        }
    }
}
