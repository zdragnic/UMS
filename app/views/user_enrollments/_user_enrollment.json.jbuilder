json.extract! user_enrollment, :id, :study_year_id, :user_id, :course_department_id, :lab_group_id, :created_at, :updated_at
json.url user_enrollment_url(user_enrollment, format: :json)
