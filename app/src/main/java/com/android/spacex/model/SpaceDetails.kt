package com.android.spacex.model

data class SpaceDetails(
    val flight_number: String,
    val mission_name: String,
    val rocket: Rocket,
    val launch_site: LaunchSite,
    val launch_date_utc: String,
    val launch_year: String,
    val details: String,
    val links: Links,
    val launch_failure_details: LaunchFailure
)

data class LaunchFailure(
    val reason: String,
    val time: String
)

data class Rocket(
    val rocket_name: String,
    val rocket_type: String
)

data class LaunchSite(
    val site_name: String
)

data class Links(
    val article_link: String,
    val wikipedia: String,
    val video_link: String,
    val youtube_id: String,
    val reddit_launch: String,
    val mission_patch: String,
    val mission_patch_small: String
)