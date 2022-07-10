package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.model.LectureInfoEntity

data class CoachLectureResponse(
    @SerializedName("lecture")
    val lecture: LectureResponse,
    @SerializedName("lectureInformation")
    val lectureInfo: LectureInfoResponse,
    @SerializedName("forms")
    val forms: List<FormResponse>,
    @SerializedName("matechedUser")
    val matchedUser: UserResponse
)

internal fun CoachLectureResponse.toLectureInfoEntity() = LectureInfoEntity(
    id = lectureInfo.id,
    title = lectureInfo.title,
    imageUrls = lectureInfo.images.map { it.imageUrl },
    startAt = lecture.startAt,
    endAt = lecture.endAt
)

internal fun CoachLectureResponse.toData(): LectureEntity =
    when (lecture.state) {
        LectureResponse.State.Onboard -> {
            LectureEntity.Onboard(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                applicants = forms.map { it.toData() }
            )
        }
        LectureResponse.State.Ongoing -> {
            LectureEntity.Ongoing(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                player = matchedUser.toData()
            )
        }
        LectureResponse.State.Done -> {
            LectureEntity.Done(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                player = matchedUser.toData(),
                isReviewed = lecture.isReviewed
            )
        }
    }