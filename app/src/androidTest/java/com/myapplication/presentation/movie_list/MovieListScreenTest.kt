package com.myapplication.presentation.movie_list

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.myapplication.di.AppModule
import com.myapplication.presentation.MainActivity
import com.myapplication.presentation.movie_list.components.TEST_TAG_LIST_ITEM
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test

@UninstallModules(AppModule::class)
@HiltAndroidTest
class MovieListScreenTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testShowListMovieScreen() {
        composeRule.onNodeWithTag(TAG_LIST_MOVIES_SCREEN).assertIsDisplayed()
    }
    @Test
    fun testShowListMoviesWithDummyData() {
        composeRule.onNodeWithTag(TAG_LIST_MOVIES).assertIsDisplayed()
        composeRule.onAllNodesWithTag(TEST_TAG_LIST_ITEM).assertCountEquals(3)
    }
}

val testResponse =
    "{\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/bf9shWfUKyEB5oB7awJeKIoCehl.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        10751,\n" +
            "        14\n" +
            "      ],\n" +
            "      \"id\": 129,\n" +
            "      \"original_language\": \"ja\",\n" +
            "      \"original_title\": \"千と千尋の神隠し\",\n" +
            "      \"overview\": \"A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.\",\n" +
            "      \"popularity\": 64.487,\n" +
            "      \"poster_path\": \"/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg\",\n" +
            "      \"release_date\": \"2001-07-20\",\n" +
            "      \"title\": \"Spirited Away\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 12047\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/1fOsyhVz5qyX2rl1qqX6KImVhTx.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        35\n" +
            "      ],\n" +
            "      \"id\": 644479,\n" +
            "      \"original_language\": \"es\",\n" +
            "      \"original_title\": \"Dedicada a mi ex\",\n" +
            "      \"overview\": \"The film tells the story of Ariel, a 21-year-old who decides to form a rock band to compete for a prize of ten thousand dollars in a musical band contest, this as a last option when trying to get money to save their relationship and reunite with his ex-girlfriend, which breaks due to the trip she must make to Finland for an internship. Ariel with her friend Ortega, decides to make a casting to find the other members of the band, although they do not know nothing about music, thus forming a band with members that have diverse and opposite personalities.\",\n" +
            "      \"popularity\": 15.811,\n" +
            "      \"poster_path\": \"/xc4bTXVwYNXi10jG9dwcaYt5IpU.jpg\",\n" +
            "      \"release_date\": \"2019-11-01\",\n" +
            "      \"title\": \"Dedicated to my ex\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 434\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        53,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 680,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Pulp Fiction\",\n" +
            "      \"overview\": \"A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.\",\n" +
            "      \"popularity\": 50.067,\n" +
            "      \"poster_path\": \"/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg\",\n" +
            "      \"release_date\": \"1994-09-10\",\n" +
            "      \"title\": \"Pulp Fiction\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 22082\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total_pages\": 467,\n" +
            "  \"total_results\": 9326\n" +
            "}"