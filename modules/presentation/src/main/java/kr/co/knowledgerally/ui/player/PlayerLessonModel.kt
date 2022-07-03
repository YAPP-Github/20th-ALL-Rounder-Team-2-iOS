package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lesson
import java.time.temporal.ChronoUnit

sealed interface PlayerLessonModel {

    data class Matching(
        val lessonId: String,
        val lessonTitle: String,
        val startTime: String,
        val runningTime: String
    ) : PlayerLessonModel

    data class Scheduled(
        val lessonId: String,
        val lessonTitle: String,
        val coachName: String,
        val coachKakaoId: String,
        val startTime: String,
        val runningTime: String
    ) : PlayerLessonModel

    data class Completed(
        val lessonId: String,
        val lessonTitle: String,
        val coachName: String,
        val startTime: String,
        val runningTime: String,
        val isReviewed: Boolean
    ) : PlayerLessonModel
}

fun Lesson.toPlayerPresentation() =
    when (type) {
        Lesson.Type.Matching -> {
            PlayerLessonModel.Matching(
                lessonId = id.toString(),
                lessonTitle = title,
                startTime = startAt.toString(),
                runningTime = ChronoUnit.HOURS.between(endAt, startAt).toString()
            )
        }
        Lesson.Type.Scheduled -> {
            PlayerLessonModel.Scheduled(
                lessonId = id.toString(),
                lessonTitle = title,
                coachName = coachName,
                coachKakaoId = coachKakaoId,
                startTime = startAt.toString(),
                runningTime = ChronoUnit.HOURS.between(endAt, startAt).toString()
            )
        }
        Lesson.Type.Completed -> {
            PlayerLessonModel.Completed(
                lessonId = id.toString(),
                lessonTitle = title,
                coachName = coachName,
                startTime = startAt.toString(),
                runningTime = ChronoUnit.HOURS.between(endAt, startAt).toString(),
                isReviewed = isReviewed
            )
        }
    }
